package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.CreateDatabaseChangeLogLockTableStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BigqueryCreateDatabaseChangeLogLockTableGeneratorTest {

    private BigQueryCreateDatabaseChangeLogLockTableGenerator generator;
    private BigQueryDatabase database;
    private CreateDatabaseChangeLogLockTableStatement statement;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
        generator = new BigQueryCreateDatabaseChangeLogLockTableGenerator();
        statement = new CreateDatabaseChangeLogLockTableStatement();
    }

    @Test
    public void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("CREATE TABLE DATABASECHANGELOGLOCK (ID INT, LOCKED BOOLEAN, LOCKGRANTED datetime, LOCKEDBY STRING(255))", sql[0].toSql());
    }
}