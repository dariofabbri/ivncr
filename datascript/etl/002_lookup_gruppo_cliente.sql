CREATE TABLE etl.lut_gruppo_cliente
(
	id SERIAL NOT NULL PRIMARY KEY,
	azienda_id INTEGER NOT NULL,
	codice_sap INTEGER NOT NULL,
	codice_gestionale INTEGER NOT NULL
);

INSERT INTO etl.lut_gruppo_cliente (azienda_id, codice_sap, codice_gestionale) VALUES (1, 100, 1);
INSERT INTO etl.lut_gruppo_cliente (azienda_id, codice_sap, codice_gestionale) VALUES (1, 102, 2);
INSERT INTO etl.lut_gruppo_cliente (azienda_id, codice_sap, codice_gestionale) VALUES (1, 104, 3);