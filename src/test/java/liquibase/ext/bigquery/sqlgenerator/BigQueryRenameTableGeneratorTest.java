package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.RenameTableStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryRenameTableGeneratorTest {

    private BigQueryRenameTableGenerator generator;
    private BigQueryDatabase database;
    private RenameTableStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryRenameTableGenerator();
        statement = new RenameTableStatement("catalogName", "schemaName", "oldTableName", "newTableName");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER TABLE schemaName.oldTableName RENAME TO newTableName", sql[0].toSql());
    }
}