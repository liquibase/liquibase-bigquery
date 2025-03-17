package liquibase.ext.bigquery.datatype.core;

import liquibase.change.core.LoadDataChange;
import liquibase.database.Database;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.servicelocator.PrioritizedService;


@DataTypeInfo(
        name = "geography",
        minParameters = 0,
        maxParameters = 0,
        priority = PrioritizedService.PRIORITY_DATABASE
)
public class GeographyDataTypeBigQuery extends LiquibaseDataType {

    @Override
    public boolean supports(Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof BigQueryDatabase) {

            DatabaseDataType type = new DatabaseDataType("GEOGRAPHY", this.getParameters());
            type.setType("GEOGRAPHY");
            return type;
        } else {
            return super.toDatabaseDataType(database);
        }

    }

    public LoadDataChange.LOAD_DATA_TYPE getLoadTypeName() {
        return LoadDataChange.LOAD_DATA_TYPE.NUMERIC;
    }
}
