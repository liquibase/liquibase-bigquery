package liquibase.ext.bigquery.change;

import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.AddColumnStatement;
import liquibase.statement.core.DropColumnStatement;
import liquibase.statement.core.RawSqlStatement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BigQueryMergeColumnChangeTest {

    private BigQueryDatabase database;

    @Before
    public void setUp() {
        database = new BigQueryDatabase();
    }

    @Test
    public void generateStatements() {
        BigQueryMergeColumnChange change = new BigQueryMergeColumnChange();
        change.setTableName("tableName");
        change.setColumn1Name("column1Name");
        change.setColumn2Name("column2Name");
        change.setSchemaName("schemaName");
        change.setCatalogName("catalogName");
        change.setFinalColumnName("finalColumnName");
        change.setFinalColumnType("finalColumnName");
        change.setJoinString("joinString");

        SqlStatement[] sqlStatements = change.generateStatements(database);
        assertEquals(4, sqlStatements.length);
        assertTrue(sqlStatements[0] instanceof AddColumnStatement);
        assertTrue(sqlStatements[1] instanceof RawSqlStatement);
        assertTrue(sqlStatements[2] instanceof DropColumnStatement);
        assertTrue(sqlStatements[3] instanceof DropColumnStatement);

        AddColumnStatement addColumnStatement = (AddColumnStatement) sqlStatements[0];
        assertEquals("finalColumnName", addColumnStatement.getColumnName());
        assertEquals("finalColumnName", addColumnStatement.getColumnType());

        RawSqlStatement rawSqlStatement = (RawSqlStatement) sqlStatements[1];
        assertEquals("UPDATE schemaName.tableName SET finalColumnName = column1Name || 'joinString' || column2Name WHERE 1 = 1 ", rawSqlStatement.getSql());

        DropColumnStatement drop1ColumnStatement = (DropColumnStatement) sqlStatements[2];
        assertEquals("column1Name", drop1ColumnStatement.getColumnName());

        DropColumnStatement drop2ColumnStatement = (DropColumnStatement) sqlStatements[3];
        assertEquals("column2Name", drop2ColumnStatement.getColumnName());


    }
}