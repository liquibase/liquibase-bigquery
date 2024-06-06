package liquibase.ext.bigquery.sqlgenerator;

import liquibase.change.ColumnConfig;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.AddForeignKeyConstraintStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryAddForeignKeyConstraintGeneratorTest {

    private BigQueryAddForeignKeyConstraintGenerator generator;
    private BigQueryDatabase database;
    private AddForeignKeyConstraintStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryAddForeignKeyConstraintGenerator();
        statement = new AddForeignKeyConstraintStatement(
                "constraintName",
                "baseTableCatalogName",
                "baseTableSchemaName",
                "baseTableName",
                new ColumnConfig[]{new ColumnConfig()},
                "referencedTableCatalogName",
                "referencedTableSchemaName",
                "referencedTableName",
                new ColumnConfig[]{new ColumnConfig()});
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER TABLE baseTableSchemaName.baseTableName ADD CONSTRAINT constraintName FOREIGN KEY (`null`) REFERENCES referencedTableSchemaName.referencedTableName (`null`) NOT ENFORCED", sql[0].toSql());
    }
}