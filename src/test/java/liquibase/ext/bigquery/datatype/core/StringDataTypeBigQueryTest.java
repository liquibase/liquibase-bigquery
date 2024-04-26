package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        DatabaseDataType databaseDataType = stringDataTypeBigQuery.toDatabaseDataType(new BigqueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("STRING", databaseDataType.getType());
    }

    @Test
    void objectToSql() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        String sql = stringDataTypeBigQuery.objectToSql("TEST", new BigqueryDatabase());
        assertEquals("'TEST'", sql);
    }

    @Test
    void objectToSqlNewLineCharacter() {
        StringDataTypeBigQuery stringDataTypeBigQuery = new StringDataTypeBigQuery();
        String sql = stringDataTypeBigQuery.objectToSql("TEST\n NEW LINE", new BigqueryDatabase());
        assertEquals("'''TEST\n NEW LINE'''", sql);
    }
}