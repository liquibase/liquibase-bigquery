package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.DeleteStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigqueryDeleteGeneratorTest {

    private BigQueryDeleteGenerator generator;
    private BigQueryDatabase database;
    private DeleteStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryDeleteGenerator();
        statement = new DeleteStatement("catalog", "schema", "table");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("DELETE FROM schema.table WHERE 1 = 1", sql[0].toSql());
    }
}