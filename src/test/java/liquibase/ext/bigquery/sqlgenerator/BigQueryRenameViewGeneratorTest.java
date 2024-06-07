package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.RenameViewStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryRenameViewGeneratorTest {

    private BigQueryRenameViewGenerator generator;
    private BigQueryDatabase database;
    private RenameViewStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryRenameViewGenerator();
        statement = new RenameViewStatement("catalogName", "schemaName", "oldTableName", "newTableName");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER VIEW schemaName.oldTableName RENAME TO schemaName.newTableName", sql[0].toSql());
    }
}