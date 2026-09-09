package liquibase.ext.bigquery.sqlgenerator;

import liquibase.datatype.DataTypeFactory;
import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.serializer.LiquibaseSerializable;
import liquibase.sql.Sql;
import liquibase.statement.core.CreateTableStatement;
import liquibase.structure.core.DataType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryCreateTableGeneratorTest {

    private BigQueryCreateTableGenerator generator;
    private BigQueryDatabase database;
    private CreateTableStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryCreateTableGenerator();
        statement = new CreateTableStatement("catalog", "schema", "table");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("CREATE TABLE schema.table ()", sql[0].toSql());
    }

    @Test
    public void generateSqlWithDefaultValue() {
        statement.addColumn("name", DataTypeFactory.getInstance().fromDescription("string", database), "default_name");
        Sql[] sql = generator.generateSql(statement, database, null);
        String generatedCreateTableStatement = sql[0].toSql();

        assertEquals("CREATE TABLE schema.table (name STRING DEFAULT 'default_name')", generatedCreateTableStatement);
    }
}