package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Int64DataTypeBigQueryTest {

    @Test
    public void toDatabaseDataType() {
        Int64DataTypeBigQuery int64DataTypeBigQuery = new Int64DataTypeBigQuery();
        DatabaseDataType databaseDataType = int64DataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("INT64", databaseDataType.getType());
    }
}