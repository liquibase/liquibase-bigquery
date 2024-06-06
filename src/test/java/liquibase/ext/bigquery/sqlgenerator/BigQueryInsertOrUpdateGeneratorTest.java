package liquibase.ext.bigquery.sqlgenerator;

import liquibase.change.ColumnConfig;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.statement.core.InsertOrUpdateStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BigQueryInsertOrUpdateGeneratorTest {

    private BigQueryInsertOrUpdateGenerator generator;
    private BigQueryDatabase database;
    private InsertOrUpdateStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryInsertOrUpdateGenerator();
        statement = new InsertOrUpdateStatement("catalog", "schema", "table", "column", false);
        ColumnConfig columnConfig = new ColumnConfig();
        columnConfig.setName("columnName");
        columnConfig.setType("columnType");
        columnConfig.setValueBoolean("valueBoolean");
        statement.addColumn(columnConfig);
    }

    @Test
    public void getInsertStatement() {
        String insertStatement = generator.getInsertStatement(statement, database, null);
        assertNotNull(insertStatement);
        assertEquals("INSERT (columnName) VALUES (valueBoolean)", insertStatement);
    }

    @Test
    public void getUpdateStatement() {
        String updateStatement = generator.getUpdateStatement(statement, database, "", null);
        assertNotNull(updateStatement);
        assertEquals("UPDATE SET columnName = valueBoolean", updateStatement);
    }

    @Test
    public void getRecordEmptyCheck() {
        String recordCheck = generator.getRecordCheck(statement, database, null);
        assertNotNull(recordCheck);
        assertEquals("MERGE INTO table USING (SELECT 1) ON WHERE 1 = 1 WHEN NOT MATCHED THEN ", recordCheck);
    }

    @Test
    public void getRecordWhereCheck() {
        String recordCheck = generator.getRecordCheck(statement, database, "WHERE ID = 1");
        assertNotNull(recordCheck);
        assertEquals("MERGE INTO table USING (SELECT 1) ON WHERE ID = 1 WHEN NOT MATCHED THEN ", recordCheck);
    }
}