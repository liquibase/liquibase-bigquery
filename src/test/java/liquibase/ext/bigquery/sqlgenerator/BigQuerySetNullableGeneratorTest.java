package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.RenameTableStatement;
import liquibase.statement.core.SetNullableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigQuerySetNullableGeneratorTest {

    private BigQuerySetNullableGenerator generator;
    private BigqueryDatabase database;
    private SetNullableStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigQuerySetNullableGenerator();
        statement = new SetNullableStatement("catalogName", "schemaName", "tableName", "columnName", "columnDataType", false);
    }

    @Test
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(0, sql.length);
    }
}