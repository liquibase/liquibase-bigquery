ALTER TABLE oleh.authors ADD COLUMN varcharColumn STRING
ALTER TABLE oleh.authors ADD COLUMN intColumn INT64
ALTER TABLE oleh.authors ADD COLUMN dateColumn date
UPDATE oleh.authors SET varcharColumn = 'INITIAL_VALUE' WHERE 1 = 1
UPDATE oleh.authors SET intColumn = 5 WHERE 1 = 1
UPDATE oleh.authors SET dateColumn = '2020-09-21' WHERE 1 = 1