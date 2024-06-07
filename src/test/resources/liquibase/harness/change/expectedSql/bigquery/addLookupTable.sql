CREATE TABLE harness_test_ds.authors_data AS SELECT DISTINCT email AS authors_email FROM harness_test_ds.authors WHERE email IS NOT NULL
ALTER TABLE harness_test_ds.authors_data ADD PRIMARY KEY (authors_email) NOT ENFORCED
ALTER TABLE harness_test_ds.authors ADD CONSTRAINT FK_AUTHORS_AUTHORS_DATA FOREIGN KEY (email) REFERENCES harness_test_ds.authors_data (authors_email) NOT ENFORCED