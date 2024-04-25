package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.AddColumnStatement;
import liquibase.statement.core.RenameTableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigQueryAddColumnGeneratorTest {

    private BigQueryAddColumnGenerator generator;
    private BigqueryDatabase database;
    private AddColumnStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigQueryAddColumnGenerator();
        statement = new AddColumnStatement();
    }

    @Test
    void generateSingleColumnSQL() {
        String sql = generator.generateSingleColumnSQL(statement, database);
        assertEquals(" ADD COLUMN null", sql);
    }
}