package liquibase.ext.bigquery.datatype.core;

import liquibase.change.core.LoadDataChange;
import liquibase.database.Database;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.ext.bigquery.database.BigqueryDatabase;


@DataTypeInfo(
        name = "numeric",
        minParameters = 0,
        maxParameters = 0,
        priority = LiquibaseDataType.PRIORITY_DATABASE
)
public class NumericDataTypeBigQuery extends LiquibaseDataType {
    public NumericDataTypeBigQuery() {
    }

    @Override
    public boolean supports(Database database) {
        return database instanceof BigqueryDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof BigqueryDatabase) {

            return new DatabaseDataType("NUMERIC", this.getParameters());
        } else {
            return super.toDatabaseDataType(database);
        }

    }

    public LoadDataChange.LOAD_DATA_TYPE getLoadTypeName() {
        return LoadDataChange.LOAD_DATA_TYPE.NUMERIC;
    }
}
