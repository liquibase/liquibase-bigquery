package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographyDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        GeographyDataTypeBigQuery datatype = new GeographyDataTypeBigQuery();
        DatabaseDataType databaseDataType = datatype.toDatabaseDataType(new BigQueryDatabase());
        assertEquals("GEOGRAPHY", databaseDataType.getType());
    }
}