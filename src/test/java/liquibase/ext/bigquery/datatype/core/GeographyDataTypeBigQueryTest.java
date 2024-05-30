package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeographyDataTypeBigQueryTest {

    @Test
    void toDatabaseDataType() {
        GeographyDataTypeBigQuery datatype = new GeographyDataTypeBigQuery();
        DatabaseDataType databaseDataType = datatype.toDatabaseDataType(new BigqueryDatabase());
        assertEquals("GEOGRAPHY", databaseDataType.getType());
    }
}