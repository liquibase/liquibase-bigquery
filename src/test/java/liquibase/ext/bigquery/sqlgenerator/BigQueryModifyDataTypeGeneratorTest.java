package liquibase.ext.bigquery.sqlgenerator;

import liquibase.ext.bigquery.database.BigqueryDatabase;
import liquibase.sql.Sql;
import liquibase.statement.core.ModifyDataTypeStatement;
import liquibase.statement.core.RenameTableStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigQueryModifyDataTypeGeneratorTest {

    private BigQueryModifyDataTypeGenerator generator;
    private BigqueryDatabase database;
    private ModifyDataTypeStatement statement;

    @BeforeEach
    void setUp() {
        database = new BigqueryDatabase();
        generator = new BigQueryModifyDataTypeGenerator();
        statement = new ModifyDataTypeStatement("catalogName", "schemaName", "tableName", "columnName", "newDataType");
    }

    @Test
    void generateSql() {
        Sql[] sql = generator.generateSql(statement, database, null);
        assertEquals(1, sql.length);
        assertEquals(";", sql[0].getEndDelimiter());
        assertEquals("ALTER TABLE schemaName.tableName ALTER COLUMN columnName SET DATA TYPE NEWDATATYPE", sql[0].toSql());
    }
}