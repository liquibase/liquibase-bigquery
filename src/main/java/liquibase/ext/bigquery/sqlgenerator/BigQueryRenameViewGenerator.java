package liquibase.ext.bigquery.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.RenameViewGenerator;
import liquibase.statement.core.RenameViewStatement;
import liquibase.structure.DatabaseObject;

import static liquibase.ext.bigquery.database.BigQueryDatabase.BIGQUERY_PRIORITY_DATABASE;

public class BigQueryRenameViewGenerator extends RenameViewGenerator {

    @Override
    public int getPriority() {
        return BIGQUERY_PRIORITY_DATABASE;
    }


    @Override
    public boolean supports(RenameViewStatement statement, Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public Sql[] generateSql(RenameViewStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        return new Sql[]{
                new UnparsedSql("ALTER VIEW " + database.escapeTableName(statement.getCatalogName(), statement.getSchemaName(), statement.getOldViewName())
                        + " RENAME TO " + database.escapeTableName(statement.getCatalogName(), statement.getSchemaName(), statement.getNewViewName()),
                this.getAffectedOldView(statement), this.getAffectedNewView(statement))};
    }
}
