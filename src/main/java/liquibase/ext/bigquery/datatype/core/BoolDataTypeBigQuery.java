package liquibase.ext.bigquery.datatype.core;

import liquibase.change.core.LoadDataChange;
import liquibase.database.Database;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;

import static liquibase.ext.bigquery.database.BigQueryDatabase.BIGQUERY_PRIORITY_DATABASE;


@DataTypeInfo(
        name = "boolean",
        minParameters = 0,
        maxParameters = 0,
        priority = LiquibaseDataType.PRIORITY_DATABASE
)
public class BoolDataTypeBigQuery extends LiquibaseDataType {
    public BoolDataTypeBigQuery() {
    }

    @Override
    public boolean supports(Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof BigQueryDatabase) {

            DatabaseDataType type = new DatabaseDataType("BOOL", this.getParameters());
            type.setType("BOOL");
            return type;
        } else {
            return super.toDatabaseDataType(database);
        }

    }

    public LoadDataChange.LOAD_DATA_TYPE getLoadTypeName() {
        return LoadDataChange.LOAD_DATA_TYPE.BOOLEAN;
    }
}
