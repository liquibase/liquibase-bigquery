package liquibase.ext.bigquery.datatype.core;

import liquibase.datatype.DatabaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeographyDataTypeBigQueryTest {

    @Test
    public void toDatabaseDataType() {
        GeographyDataTypeBigQuery datatype = new GeographyDataTypeBigQuery();
        DatabaseDataType databaseDataType = datatype.toDatabaseDataType(new BigQueryDatabase());
        assertEquals("GEOGRAPHY", databaseDataType.getType());
    }
}