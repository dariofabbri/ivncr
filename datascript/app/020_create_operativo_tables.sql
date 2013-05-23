\encoding UTF8;
SET client_min_messages TO WARNING;



CREATE TABLE app.ope_tipo_riposo
(
	id SERIAL NOT NULL PRIMARY KEY,
	codice VARCHAR(255) NOT NULL,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.ope_tipo_riposo (id, codice, descrizione) VALUES (1, 'RP', 'Riposo posticipato');
INSERT INTO app.ope_tipo_riposo (id, codice, descrizione) VALUES (2, 'RS', 'Riposo spostato');
INSERT INTO app.ope_tipo_riposo (id, codice, descrizione) VALUES (3, 'RL', 'Riposo lavorato');
INSERT INTO app.ope_tipo_riposo (id, codice, descrizione) VALUES (4, 'RR', 'Riposo recuperato');
SELECT setval('app.ope_tipo_riposo_id_seq', (SELECT MAX(id) FROM app.ope_tipo_riposo));


CREATE TABLE app.ope_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
	ods_nuova_attivazione_id INTEGER NULL REFERENCES app.con_ordine_servizio(id),
	ods_id INTEGER NULL REFERENCES app.con_ordine_servizio(id),
	data_mattinale DATE NOT NULL,
	data_contabile DATE NOT NULL,
	orario_da TIME WITH TIME ZONE,
	orario_a TIME WITH TIME ZONE,
	retribuito BOOLEAN NOT NULL,
	causale_ods_id INTEGER NULL REFERENCES app.ope_causale_ods(id),
	altro_reparto_id INTEGER NULL REFERENCES app.per_reparto(id),
	tipo_riposo_id INTEGER NULL REFERENCES app.ope_tipo_riposo(id),
	indennita_trasferta BOOLEAN NOT NULL,
	indennita_gra BOOLEAN NOT NULL,
	indennita_poligono BOOLEAN NOT NULL,
	affiancato BOOLEAN NOT NULL,
	note VARCHAR(4000),
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);

--STATO
--INIBISCI_TRIGGER_PAGHE
