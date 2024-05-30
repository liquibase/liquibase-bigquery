package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        DatabaseDataType databaseDataType = stringDataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("STRING", databaseDataType.getType());
    }

    @Test
    void objectToSql() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        String sql = stringDataTypeBigQuery.objectToSql("TEST", new BigQueryDatabase());
        assertEquals("'TEST'", sql);
    }

    @Test
    void objectToSqlNewLineCharacter() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        String sql = stringDataTypeBigQuery.objectToSql("TEST\n NEW LINE", new BigQueryDatabase());
        assertEquals("'''TEST\n NEW LINE'''", sql);
    }
}