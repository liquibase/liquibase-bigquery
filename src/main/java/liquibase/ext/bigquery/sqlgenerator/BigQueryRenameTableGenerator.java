package liquibase.ext.bigquery.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.RenameTableGenerator;
import liquibase.statement.core.RenameTableStatement;
import liquibase.structure.DatabaseObject;
import liquibase.structure.core.Table;

import static liquibase.ext.bigquery.database.BigQueryDatabase.BIGQUERY_PRIORITY_DATABASE;

public class BigQueryRenameTableGenerator extends RenameTableGenerator {
    @Override
    public int getPriority() {
        return BIGQUERY_PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(RenameTableStatement statement, Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public Sql[] generateSql(RenameTableStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        String sql;
        sql = "ALTER TABLE " + database.escapeTableName(statement.getCatalogName(), statement.getSchemaName(), statement.getOldTableName()) + " RENAME TO "
                + database.escapeObjectName(statement.getNewTableName(), Table.class);

        return new Sql[]{new UnparsedSql(sql, this.getAffectedOldTable(statement), this.getAffectedNewTable(statement))};
    }

}
