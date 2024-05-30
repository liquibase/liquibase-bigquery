package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.RenameTableStatement;
import liquibase.statement.core.RenameViewStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigQueryRenameViewGeneratorTest {

    private BigQueryRenameViewGenerator generator;
    private BigqueryDatabase database;
    private RenameViewStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigQueryRenameViewGenerator();
        statement = new RenameViewStatement("catalogName", "schemaName", "oldTableName", "newTableName");
    }

    @Test
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER VIEW schemaName.oldTableName RENAME TO schemaName.newTableName", sql[0].toSql());
    }
}