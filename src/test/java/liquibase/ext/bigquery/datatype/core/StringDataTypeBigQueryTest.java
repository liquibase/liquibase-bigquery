package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringDataTypeBigQueryTest {

    @Test
    public void toDatabaseDataType() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        DatabaseDataType databaseDataType = stringDataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("STRING", databaseDataType.getType());
    }

    @Test
    public void objectToSql() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        String sql = stringDataTypeBigQuery.objectToSql("TEST", new BigQueryDatabase());
        assertEquals("'TEST'", sql);
    }

    @Test
    public void objectToSqlNewLineCharacter() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        String sql = stringDataTypeBigQuery.objectToSql("TEST\n NEW LINE", new BigQueryDatabase());
        assertEquals("'''TEST\n NEW LINE'''", sql);
    }
}