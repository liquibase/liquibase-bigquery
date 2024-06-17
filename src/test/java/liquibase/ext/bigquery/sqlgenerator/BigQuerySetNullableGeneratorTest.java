package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.SetNullableStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQuerySetNullableGeneratorTest {

    private BigQuerySetNullableGenerator generator;
    private BigQueryDatabase database;
    private SetNullableStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQuerySetNullableGenerator();
        statement = new SetNullableStatement("catalogName", "schemaName", "tableName", "columnName", "columnDataType", false);
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(0, sql.length);
    }
}