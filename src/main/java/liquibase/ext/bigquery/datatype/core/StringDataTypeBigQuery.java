package liquibase.ext.bigquery.datatype.core;

import liquibase.change.core.LoadDataChange;
import liquibase.database.Database;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.core.VarcharType;
import liquibase.ext.bigquery.database.BigQueryDatabase;
import liquibase.servicelocator.PrioritizedService;


@DataTypeInfo(
        name = "string",
        minParameters = 0,
        maxParameters = 0,
        priority = PrioritizedService.PRIORITY_DATABASE,
        aliases = { "varchar", "clob", "java.lang.String" }
)
public class StringDataTypeBigQuery extends VarcharType {

    @Override
    public boolean supports(Database database) {
        return database instanceof BigQueryDatabase;
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        if (database instanceof BigQueryDatabase) {
            String dataTypeString = "STRING";
            DatabaseDataType type = new DatabaseDataType(dataTypeString, this.getParameters());
            if (this.getParameters().length == 0) {
                type.setType(dataTypeString);
            } else {
                String firstParameter = String.valueOf(this.getParameters()[0]);
                try {
                    int stringSize = Integer.parseInt(firstParameter);
                    if (stringSize == 65535) {
                     type.setType(dataTypeString);
                    }
                } catch (NumberFormatException e) {
                    type.setType(dataTypeString);
                }
            }
            return type;
        } else {
            return super.toDatabaseDataType(database);
        }

    }

    @Override
    public String objectToSql(Object value, Database database) {
        String ret =  super.objectToSql(value, database);
        if (ret.contains("\n")) {
            return "''" + ret + "''";
        } else {
            return ret;
        }
    }

    @Override
    public LoadDataChange.LOAD_DATA_TYPE getLoadTypeName() {
        return LoadDataChange.LOAD_DATA_TYPE.STRING;
    }
}
