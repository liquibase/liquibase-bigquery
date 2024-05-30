package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.DeleteStatement;
import liquibase.statement.core.RenameTableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigqueryDeleteGeneratorTest {

    private BigqueryDeleteGenerator generator;
    private BigqueryDatabase database;
    private DeleteStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigqueryDeleteGenerator();
        statement = new DeleteStatement("catalog", "schema", "table");
    }

    @Test
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("DELETE FROM schema.table WHERE 1 = 1", sql[0].toSql());
    }
}