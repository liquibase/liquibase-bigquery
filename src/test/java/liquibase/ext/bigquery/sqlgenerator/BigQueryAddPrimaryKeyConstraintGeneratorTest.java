package liquibase.ext.bigquery.sqlgenerator;

import liquibase.change.ColumnConfig;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.AddPrimaryKeyStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryAddPrimaryKeyConstraintGeneratorTest {

    private BigQueryAddPrimaryKeyConstraintGenerator generator;
    private BigQueryDatabase database;
    private AddPrimaryKeyStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryAddPrimaryKeyConstraintGenerator();
        statement = new AddPrimaryKeyStatement(
                "catalogName",
                "schemaName",
                "tableName",
                new ColumnConfig[]{new ColumnConfig()},
                "constraintName");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER TABLE schemaName.tableName ADD PRIMARY KEY (`null`) NOT ENFORCED", sql[0].toSql());
    }
}