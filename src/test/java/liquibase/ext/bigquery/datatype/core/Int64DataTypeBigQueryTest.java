package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Int64DataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        Int64DataTypeBigQuery int64DataTypeBigQuery = new Int64DataTypeBigQuery();
        DatabaseDataType databaseDataType = int64DataTypeBigQuery.toDatabaseDataType(new BigqueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("INT64", databaseDataType.getType());
    }
}