package liquibase.ext.bigquery.database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BigqueryDatabaseUnitTest {

    private BigQueryDatabase database;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
    }

    @Test
    public void getShortName() {
        assertEquals("bigquery", database.getShortName());
    }

    @Test
    public void getDefaultDatabaseProductName() {
        assertEquals("Google BigQuery", database.getDefaultDatabaseProductName());
    }

    @Test
    public void supportsDatabaseChangeLogHistory() {
        assertTrue(database.supportsDatabaseChangeLogHistory());
    }

    @Test
    public void getCurrentDateTimeFunction() {
        assertEquals("CURRENT_DATETIME()", database.getCurrentDateTimeFunction());
    }

    @Test
    public void getQuotingStartCharacter() {
        assertEquals("`", database.getQuotingStartCharacter());
    }

    @Test
    public void getQuotingEndCharacter() {
        assertEquals("`", database.getQuotingEndCharacter());
    }

}