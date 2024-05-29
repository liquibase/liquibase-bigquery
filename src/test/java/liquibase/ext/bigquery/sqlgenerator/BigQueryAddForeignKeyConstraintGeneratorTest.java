package liquibase.ext.bigquery.sqlgenerator;

import liquibase.change.ColumnConfig;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.AddForeignKeyConstraintStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigQueryAddForeignKeyConstraintGeneratorTest {

    private BigQueryAddForeignKeyConstraintGenerator generator;
    private BigqueryDatabase database;
    private AddForeignKeyConstraintStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
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
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER TABLE baseTableSchemaName.baseTableName ADD CONSTRAINT constraintName FOREIGN KEY (`null`) REFERENCES referencedTableSchemaName.referencedTableName (`null`) NOT ENFORCED", sql[0].toSql());
    }
}