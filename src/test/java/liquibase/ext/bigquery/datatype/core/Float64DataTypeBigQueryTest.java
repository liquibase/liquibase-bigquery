package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Float64DataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        Float64DataTypeBigQuery float64DataTypeBigQuery = new Float64DataTypeBigQuery();
        DatabaseDataType databaseDataType = float64DataTypeBigQuery.toDatabaseDataType(new BigQueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("FLOAT64", databaseDataType.getType());
    }
}