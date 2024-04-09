package liquibase.ext.bigquery.database

import spock.lang.Specification

class BigqueryDatabaseSpec extends Specification {

    def checkSetup() {
        when:
        def db = new BigqueryDatabase()

        then:
        db.getShortName() == "bigquery"
    }
}
