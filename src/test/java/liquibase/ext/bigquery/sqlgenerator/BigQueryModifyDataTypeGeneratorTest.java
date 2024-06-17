package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.ModifyDataTypeStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigQueryModifyDataTypeGeneratorTest {

    private BigQueryModifyDataTypeGenerator generator;
    private BigQueryDatabase database;
    private ModifyDataTypeStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryModifyDataTypeGenerator();
        statement = new ModifyDataTypeStatement("catalogName", "schemaName", "tableName", "columnName", "newDataType");
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER TABLE schemaName.tableName ALTER COLUMN columnName SET DATA TYPE NEWDATATYPE", sql[0].toSql());
    }
}