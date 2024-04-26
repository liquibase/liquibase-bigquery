package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoolDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        BoolDataTypeBigQuery boolDataTypeBigQuery = new BoolDataTypeBigQuery();
        DatabaseDataType databaseDataType = boolDataTypeBigQuery.toDatabaseDataType(new BigqueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("BOOL", databaseDataType.getType());
    }
}