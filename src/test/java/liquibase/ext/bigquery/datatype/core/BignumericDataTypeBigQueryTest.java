package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BignumericDataTypeBigQueryTest {

    @Test
    public void toDatabaseDataType() {
        BignumericDataTypeBigQuery bignumericDataTypeBigQuery = new BignumericDataTypeBigQuery();
        DatabaseDataType databaseDataType = bignumericDataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("BIGNUMERIC", databaseDataType.getType());
    }
}