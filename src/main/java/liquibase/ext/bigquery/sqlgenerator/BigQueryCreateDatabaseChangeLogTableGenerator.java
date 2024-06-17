package liquibase.ext.bigquery.sqlgenerator;

import liquibase.database.Database;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sqlgenerator.core.CreateDatabaseChangeLogTableGenerator;
import liquibase.statement.core.CreateDatabaseChangeLogTableStatement;

import static liquibase.ext.bigquery.database.BigQueryDatabase.BIGQUERY_PRIORITY_DATABASE;

public class BigQueryCreateDatabaseChangeLogTableGenerator extends CreateDatabaseChangeLogTableGenerator {

    @Override
    public int getPriority() {
        return BIGQUERY_PRIORITY_DATABASE;
    }

    @Override
    public boolean supports(CreateDatabaseChangeLogTableStatement statement, Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    protected String getCharTypeName(Database database) {
        return "string";
    }

}
