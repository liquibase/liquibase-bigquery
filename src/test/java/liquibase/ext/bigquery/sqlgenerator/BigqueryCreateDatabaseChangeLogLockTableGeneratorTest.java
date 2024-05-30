package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.CreateDatabaseChangeLogLockTableStatement;
import liquibase.statement.core.RenameTableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigqueryCreateDatabaseChangeLogLockTableGeneratorTest {

    private BigqueryCreateDatabaseChangeLogLockTableGenerator generator;
    private BigqueryDatabase database;
    private CreateDatabaseChangeLogLockTableStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigqueryCreateDatabaseChangeLogLockTableGenerator();
        statement = new CreateDatabaseChangeLogLockTableStatement();
    }

    @Test
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("CREATE TABLE DATABASECHANGELOGLOCK (ID INT, LOCKED BOOLEAN, LOCKGRANTED datetime, LOCKEDBY STRING(255))", sql[0].toSql());
    }
}