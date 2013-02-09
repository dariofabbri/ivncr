CREATE TABLE etl.lut_divisa
(
	id SERIAL NOT NULL PRIMARY KEY,
	azienda_id INTEGER NOT NULL,
	codice_sap VARCHAR(50) NOT NULL,
	codice_gestionale INTEGER NOT NULL
);

INSERT INTO etl.lut_divisa (azienda_id, codice_sap, codice_gestionale) VALUES (1, 'EUR', 1);
INSERT INTO etl.lut_divisa (azienda_id, codice_sap, codice_gestionale) VALUES (1, 'USD', 2);