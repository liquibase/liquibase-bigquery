package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.statement.core.AddColumnStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryAddColumnGeneratorTest {

    private BigQueryAddColumnGenerator generator;
    private BigQueryDatabase database;
    private AddColumnStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryAddColumnGenerator();
        statement = new AddColumnStatement();
    }

    @Test
    public void generateSingleColumnSQL() {
        String sql = generator.generateSingleColumnSQL(statement, database);
        assertEquals(" ADD COLUMN null", sql);
    }
}