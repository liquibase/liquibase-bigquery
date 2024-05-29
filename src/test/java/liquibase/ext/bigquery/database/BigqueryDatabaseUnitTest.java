package liquibase.ext.bigquery.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigqueryDatabaseUnitTest {

    private BigqueryDatabase database;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
    }

    @Test
    void getShortName() {
        assertEquals("bigquery", database.getShortName());
    }

    @Test
    void getDefaultDatabaseProductName() {
        assertEquals("Google BigQuery", database.getDefaultDatabaseProductName());
    }

    @Test
    void supportsDatabaseChangeLogHistory() {
        assertTrue(database.supportsDatabaseChangeLogHistory());
    }

    @Test
    void getCurrentDateTimeFunction() {
        assertEquals("CURRENT_DATETIME()", database.getCurrentDateTimeFunction());
    }

    @Test
    void getQuotingStartCharacter() {
        assertEquals("`", database.getQuotingStartCharacter());
    }

    @Test
    void getQuotingEndCharacter() {
        assertEquals("`", database.getQuotingEndCharacter());
    }

}