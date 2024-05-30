package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BignumericDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        BignumericDataTypeBigQuery bignumericDataTypeBigQuery = new BignumericDataTypeBigQuery();
        DatabaseDataType databaseDataType = bignumericDataTypeBigQuery.toDatabaseDataType(new BigqueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("BIGNUMERIC", databaseDataType.getType());
    }
}