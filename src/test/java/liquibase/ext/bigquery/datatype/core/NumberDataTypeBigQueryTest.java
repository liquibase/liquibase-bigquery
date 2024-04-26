package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        NumberDataTypeBigQuery numberDataTypeBigQuery = new NumberDataTypeBigQuery();
        DatabaseDataType databaseDataType = numberDataTypeBigQuery.toDatabaseDataType(new BigqueryDatabase());
        assertNotNull(databaseDataType);
        assertEquals("NUMERIC", databaseDataType.getType());
    }
}