package liquibase.ext.bigquery.change;

import liquibase.change.AddColumnConfig;
import liquibase.change.DatabaseChange;
import liquibase.change.core.AddColumnChange;
import liquibase.change.core.DropColumnChange;
import liquibase.change.core.MergeColumnChange;
import liquibase.database.Database;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.servicelocator.PrioritizedService;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.RawParameterizedSqlStatement;
import liquibase.structure.core.Column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DatabaseChange(name="mergeColumns",
        description = "Concatenates the values in two columns, joins them by with string, and stores the resulting value in a new column.",
        priority = PrioritizedService.PRIORITY_DATABASE)
public class BigQueryMergeColumnChange extends MergeColumnChange {

    @Override
    public boolean supports(Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public SqlStatement[] generateStatements(final Database database) {

        AddColumnChange addNewColumnChange = new AddColumnChange();
        addNewColumnChange.setSchemaName(getSchemaName());
        addNewColumnChange.setTableName(getTableName());
        final AddColumnConfig columnConfig = new AddColumnConfig();
        columnConfig.setName(getFinalColumnName());
        columnConfig.setType(getFinalColumnType());
        addNewColumnChange.addColumn(columnConfig);
        List<SqlStatement> statements = new ArrayList<>(Arrays.asList(addNewColumnChange.generateStatements(database)));

        String updateStatement;

        updateStatement = "UPDATE " + database.escapeTableName(getCatalogName(), getSchemaName(), getTableName()) +
                " SET " + database.escapeObjectName(getFinalColumnName(), Column.class)
                + " = " + database.getConcatSql(database.escapeObjectName(getColumn1Name(), Column.class)
                , "'" + getJoinString() + "'", database.escapeObjectName(getColumn2Name(), Column.class))
                + " WHERE 1 = 1 ";

        statements.add(new RawParameterizedSqlStatement(updateStatement));

        DropColumnChange dropColumn1Change = new DropColumnChange();
        dropColumn1Change.setSchemaName(getSchemaName());
        dropColumn1Change.setTableName(getTableName());
        dropColumn1Change.setColumnName(getColumn1Name());
        statements.addAll(Arrays.asList(dropColumn1Change.generateStatements(database)));

        DropColumnChange dropColumn2Change = new DropColumnChange();
        dropColumn2Change.setSchemaName(getSchemaName());
        dropColumn2Change.setTableName(getTableName());
        dropColumn2Change.setColumnName(getColumn2Name());
        statements.addAll(Arrays.asList(dropColumn2Change.generateStatements(database)));

        return statements.toArray(new SqlStatement[0]);
    }
}
