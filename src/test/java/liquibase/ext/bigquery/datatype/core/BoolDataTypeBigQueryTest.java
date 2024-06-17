package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BoolDataTypeBigQueryTest {

    @Test
    public void toDatabaseDataType() {
        BoolDataTypeBigQuery boolDataTypeBigQuery = new BoolDataTypeBigQuery();
        DatabaseDataType databaseDataType = boolDataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("BOOL", databaseDataType.getType());
    }
}