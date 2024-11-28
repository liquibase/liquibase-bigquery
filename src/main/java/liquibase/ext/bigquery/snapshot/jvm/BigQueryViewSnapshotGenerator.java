package liquibase.ext.bigquery.snapshot.jvm;

import liquibase.CatalogAndSchema;
import liquibase.Scope;
import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.executor.ExecutorService;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.jvm.ViewSnapshotGenerator;
import liquibase.statement.core.RawParameterizedSqlStatement;
import liquibase.structure.DatabaseObject;
import liquibase.structure.core.Schema;
import liquibase.structure.core.View;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class BigQueryViewSnapshotGenerator extends ViewSnapshotGenerator {

    @Override
    public int getPriority(Class<? extends DatabaseObject> objectType, Database database) {
        if (database instanceof BigQueryDatabase) {
            return super.getPriority(objectType, database) + PRIORITY_DATABASE;
        } else {
            return PRIORITY_NONE;
        }
    }



    @Override
    protected DatabaseObject snapshotObject(DatabaseObject example, DatabaseSnapshot snapshot) throws DatabaseException {
        if (((View) example).getDefinition() != null) {
            return example;
        } else {
            Database database = snapshot.getDatabase();
            Schema schema = example.getSchema();

            CatalogAndSchema catalogAndSchema = (new CatalogAndSchema(schema.getCatalogName(), schema.getName())).customize(database);
            String jdbcSchemaName = database.escapeObjectName(((AbstractJdbcDatabase) database).getJdbcSchemaName(catalogAndSchema), Schema.class);
            String query = String.format("SELECT view_definition FROM %s.INFORMATION_SCHEMA.VIEWS WHERE table_name=? AND table_schema=? AND " +
                            "table_catalog=?", jdbcSchemaName);

            List<Map<String, ?>> viewsMetadataRs = Scope.getCurrentScope().getSingleton(ExecutorService.class)
                    .getExecutor("jdbc", database).queryForList(new RawParameterizedSqlStatement(query, example.getName(), schema.getName(), schema.getCatalogName()));

            if (viewsMetadataRs.isEmpty()) {
                return null;
            } else {
                Map<String, ?> row = viewsMetadataRs.get(0);
                String rawViewName = example.getName();
                String rawSchemaName = schema.getName();
                String rawCatalogName = schema.getCatalogName();
                String remarks = null;

                View view = (new View()).setName(this.cleanNameFromDatabase(rawViewName, database));
                view.setRemarks(remarks);
                CatalogAndSchema schemaFromJdbcInfo = ((AbstractJdbcDatabase) database).getSchemaFromJdbcInfo(rawCatalogName, rawSchemaName);
                view.setSchema(new Schema(schemaFromJdbcInfo.getCatalogName(), schemaFromJdbcInfo.getSchemaName()));

                String definition = (String) row.get("VIEW_DEFINITION");
                if (definition.startsWith("FULL_DEFINITION: ")) {
                    definition = definition.replaceFirst("^FULL_DEFINITION: ", "");
                    view.setContainsFullDefinition(true);
                }

                int length = definition.length();
                if (length > 0 && definition.charAt(length - 1) == 0) {
                    definition = definition.substring(0, length - 1);
                }

                definition = StringUtils.trimToNull(definition);
                if (definition == null) {
                    definition = "[CANNOT READ VIEW DEFINITION]";
                }

                view.setDefinition(definition);

                return view;
            }

        }
    }

}
