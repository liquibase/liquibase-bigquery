package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.DropPrimaryKeyStatement;
import liquibase.statement.core.RenameTableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigQueryDropPrimaryKeyConstraintGeneratorTest {

    private BigQueryDropPrimaryKeyConstraintGenerator generator;
    private BigqueryDatabase database;
    private DropPrimaryKeyStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigQueryDropPrimaryKeyConstraintGenerator();
        statement = new DropPrimaryKeyStatement("catalogName", "schemaName", "tableName", "columnName");
    }

    @Test
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("SELECT 1", sql[0].toSql());
    }
}