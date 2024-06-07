package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NumberDataTypeBigQueryTest {

    @Test
    public void toDatabaseDataType() {
        NumericDataTypeBigQuery numberDataTypeBigQuery = new NumericDataTypeBigQuery();
        DatabaseDataType databaseDataType = numberDataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("NUMERIC", databaseDataType.getType());
    }
}