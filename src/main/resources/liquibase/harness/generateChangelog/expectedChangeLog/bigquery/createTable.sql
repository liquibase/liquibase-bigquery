--liquibase formatted sql

--changeset as:1
CREATE TABLE test_table_xml (test_column INT64);

--rollback DROP TABLE test_table_xml;