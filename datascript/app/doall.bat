psql -U postgres -f create_database.sql postgres

psql -U ivncr -f 001_create_security_tables.sql ivncr
psql -U ivncr -f 002_insert_initial_security_rows.sql ivncr
psql -U ivncr -f 003_insert_initial_permissions.sql ivncr
psql -U ivncr -f 004_create_commerciale_tables.sql ivncr
psql -U ivncr -f 005_insert_initial_commerciale_rows.sql ivncr
psql -U ivncr -f 006_add_user_email_columns.sql ivncr
psql -U ivncr -f 007_add_counters_table.sql ivncr
psql -U ivncr -f 008_create_multi_azienda_tables.sql ivncr
psql -U ivncr -f 009_add_user_picture_column.sql ivncr
psql -U ivncr -f 010_create_contatto_cliente_tables.sql ivncr