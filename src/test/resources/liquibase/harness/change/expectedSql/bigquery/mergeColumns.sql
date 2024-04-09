CREATE TABLE oleh.full_name_table (first_name STRING(50), last_name STRING(50))
INSERT INTO oleh.full_name_table (first_name) VALUES ('John')
UPDATE oleh.full_name_table SET last_name = 'Doe' WHERE first_name='John'
INSERT INTO oleh.full_name_table (first_name) VALUES ('Jane')
UPDATE oleh.full_name_table SET last_name = 'Doe' WHERE first_name='Jane'
ALTER TABLE oleh.full_name_table ADD COLUMN full_name STRING(255)
UPDATE oleh.full_name_table SET full_name = first_name || ' ' || last_name WHERE 1 = 1
ALTER TABLE oleh.full_name_table DROP COLUMN first_name
ALTER TABLE oleh.full_name_table DROP COLUMN last_name