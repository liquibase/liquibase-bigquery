package liquibase.ext.bigquery.snapshot.jvm;

import liquibase.CatalogAndSchema;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.executor.Executor;
import liquibase.executor.ExecutorService;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.SnapshotGenerator;
import liquibase.snapshot.jvm.PrimaryKeySnapshotGenerator;
import liquibase.statement.core.RawParameterizedSqlStatement;
import liquibase.structure.DatabaseObject;
import liquibase.structure.core.Column;
import liquibase.structure.core.Index;
import liquibase.structure.core.PrimaryKey;
import liquibase.structure.core.Schema;
import liquibase.structure.core.Table;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BigQueryPrimaryKeySnapshotGenerator extends PrimaryKeySnapshotGenerator {
    private static final String CONSTRAINT_NAME = "CONSTRAINT_NAME";
    @Override
    public int getPriority(Class<? extends DatabaseObject> objectType, Database database) {
        if (database instanceof BigQueryDatabase) {
            return super.getPriority(objectType, database) + PRIORITY_DATABASE;
        } else {
            return PRIORITY_NONE;
        }
    }

    @Override
    public Class<? extends SnapshotGenerator>[] replaces() {
        return new Class[]{PrimaryKeySnapshotGenerator.class};
    }

    @Override
    protected DatabaseObject snapshotObject(DatabaseObject example, DatabaseSnapshot snapshot) throws DatabaseException {
        Database database = snapshot.getDatabase();
        PrimaryKey returnKey = null;

        String keyColumnUsageStatement = String.format("SELECT * FROM %s.INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE CONSTRAINT_NAME = ?",
                example.getSchema().getName());
        Executor executor = Scope.getCurrentScope().getSingleton(ExecutorService.class).getExecutor("jdbc", database);
        List<Map<String, ?>> maps = executor.queryForList(new RawParameterizedSqlStatement(keyColumnUsageStatement, example.getName()));
        String columnName;
        for (Map<String, ?> map : maps) {
            columnName = Objects.toString(map.get("COLUMN_NAME"), null);
            int position = ((Long) map.get("ORDINAL_POSITION")).intValue();

            if (returnKey == null) {
                returnKey = new PrimaryKey();
                String catalogName = (String) map.get("TABLE_CATALOG");
                String schemaName = (String) map.get("TABLE_SCHEMA");
                CatalogAndSchema tableSchema = new CatalogAndSchema(catalogName, schemaName);
                returnKey.setTable((Table) new Table().setName(Objects.toString(map.get("TABLE_NAME"), null)).setSchema(new Schema(tableSchema.getCatalogName(), tableSchema.getSchemaName())));
                returnKey.setName(Objects.toString(map.get(CONSTRAINT_NAME), null));
            }

            returnKey.addColumn(position - 1, new Column(columnName)
                    .setRelation(((PrimaryKey) example).getTable()));
        }
        if (returnKey != null) {
            Index exampleIndex = new Index().setRelation(returnKey.getTable());
            exampleIndex.setColumns(returnKey.getColumns());
            returnKey.setBackingIndex(exampleIndex);
        }
        return returnKey;
    }

    @Override
    protected void addTo(DatabaseObject foundObject, DatabaseSnapshot snapshot) throws DatabaseException {
        if (!snapshot.getSnapshotControl().shouldInclude(PrimaryKey.class)) {
            return;
        }

        if (foundObject instanceof Table) {
            Table table = (Table) foundObject;
            Database database = snapshot.getDatabase();

            Executor executor = Scope.getCurrentScope().getSingleton(ExecutorService.class).getExecutor("jdbc", database);
            String tableConstraintsStatement = String.format("SELECT * FROM %s.INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE " +
                    "CONSTRAINT_TYPE = 'PRIMARY KEY' AND table_name = ?", database.escapeObjectName(table.getSchema().getName(), Schema.class));
            List<Map<String, ?>> maps = executor.queryForList(new RawParameterizedSqlStatement(tableConstraintsStatement, table.getName()));

            for (Map<String, ?> map : maps) {
                if (map.containsKey(CONSTRAINT_NAME)) {
                    String constraintName = Objects.toString(map.get(CONSTRAINT_NAME), null);
                    PrimaryKey primaryKey = new PrimaryKey().setName(constraintName);
                    primaryKey.setTable((Table) foundObject);
                    if (!database.isSystemObject(primaryKey)) {
                        table.setPrimaryKey(primaryKey.setTable(table));
                    }
                }
            }
        }
    }
}
