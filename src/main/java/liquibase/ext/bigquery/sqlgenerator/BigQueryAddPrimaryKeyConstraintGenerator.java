package liquibase.ext.bigquery.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.sql.UnparsedSql;
import liquibase.sqlgenerator.SqlGenerator;
import liquibase.sqlgenerator.SqlGeneratorChain;
import liquibase.sqlgenerator.core.AddPrimaryKeyGenerator;
import liquibase.statement.core.AddPrimaryKeyStatement;

public class BigQueryAddPrimaryKeyConstraintGenerator extends AddPrimaryKeyGenerator {

    @Override
    public int getPriority() {
        return SqlGenerator.PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(AddPrimaryKeyStatement statement, Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public Sql[] generateSql(AddPrimaryKeyStatement statement, Database database, SqlGeneratorChain sqlGeneratorChain) {
        String sql = String.format("ALTER TABLE %s ADD PRIMARY KEY (%s) NOT ENFORCED",
                database.escapeTableName(statement.getCatalogName(), statement.getSchemaName(), statement.getTableName()),
                database.escapeColumnNameList(statement.getColumnNames()));
        return new Sql[]{
                new UnparsedSql(sql, getAffectedPrimaryKey(statement))
        };
    }
}
