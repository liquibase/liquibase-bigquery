package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.UpdateStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryUpdateGeneratorTest {

    private BigQueryUpdateGenerator generator;
    private BigQueryDatabase database;
    private UpdateStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryUpdateGenerator();
        statement = new UpdateStatement("catalogName", "schemaName", "tableName");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("UPDATE schemaName.tableName SET WHERE 1 = 1", sql[0].toSql());
    }
}