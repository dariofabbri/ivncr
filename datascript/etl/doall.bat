psql -U ivncr -f create_schema.sql ivncr
psql -U ivncr -f 001_lookup_divisa.sql ivncr

psql -U ivncr -f 002_lookup_gruppo_cliente.sql ivncr
