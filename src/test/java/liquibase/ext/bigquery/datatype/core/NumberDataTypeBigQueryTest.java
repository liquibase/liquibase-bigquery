package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        NumericDataTypeBigQuery numberDataTypeBigQuery = new NumericDataTypeBigQuery();
        DatabaseDataType databaseDataType = numberDataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("NUMERIC", databaseDataType.getType());
    }
}