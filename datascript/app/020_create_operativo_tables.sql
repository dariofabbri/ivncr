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


CREATE TABLE app.ope_gruppo_causale_ods
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (1, 'A DISPOSIZIONE');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (2, 'Aspettative');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (3, 'Assenze varie');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (4, 'Congedi');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (5, 'Ferie');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (6, 'Festivita');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (7, 'Infortuni');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (8, 'Malattie');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (9, 'Maternita');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (10, 'Non in forza');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (11, 'Non in Forza');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (12, 'Permessi');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (13, 'Permessi a Conguaglio');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (14, 'Permessi annuali');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (15, 'Permessi sindacali');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (16, 'Ricoveri');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (17, 'Riposi');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (18, 'Sospensioni');
INSERT INTO app.ope_gruppo_causale_ods (id, descrizione) VALUES (19, 'Varie');
SELECT setval('app.ope_gruppo_causale_ods_id_seq', (SELECT MAX(id) FROM app.ope_gruppo_causale_ods));


CREATE TABLE app.ope_causale_ods
(
	id SERIAL NOT NULL PRIMARY KEY,
	codice VARCHAR(255) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	retribuito BOOLEAN NOT NULL,
	orario BOOLEAN NOT NULL,
	gruppo_causale_ods_id INTEGER NULL REFERENCES app.ope_gruppo_causale_ods(id),
	visibile BOOLEAN NOT NULL
);


CREATE TABLE app.ope_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
	ods_nuova_attivazione_id INTEGER NULL REFERENCES app.con_ordine_servizio(id),
	ods_id INTEGER NULL REFERENCES app.con_ordine_servizio(id),
	data_mattinale DATE NOT NULL,
	data_retribuzione DATE NOT NULL,
	giorno_successivo BOOLEAN NOT NULL,
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
	stato BOOLEAN, -- ?
	inibisci_trigger_paghe BOOLEAN, -- ?
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.ope_tipo_indennita
(
	id SERIAL NOT NULL PRIMARY KEY,
	codice VARCHAR(255) NOT NULL,
	descrizione VARCHAR(255) NOT NULL,
	notturno NUMERIC(10,2) NOT NULL,
	diurno NUMERIC(10,2) NOT NULL,
	oraria BOOLEAN NOT NULL,
	visibile BOOLEAN NOT NULL,
	riferimento_id INTEGER REFERENCES app.ope_tipo_indennita(id),
	aggiuntivo_id INTEGER REFERENCES app.ope_tipo_indennita(id)
);


CREATE TABLE app.ope_indennita
(
	id SERIAL NOT NULL PRIMARY KEY,
	ods_id INTEGER NOT NULL REFERENCES app.con_ordine_servizio(id),
	tipo_indennita_id INTEGER NOT NULL REFERENCES app.ope_tipo_indennita(id),
	livello_id INTEGER NULL REFERENCES app.per_livello_ccnl(id),
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.ope_recupero_riposo
(
	id SERIAL NOT NULL PRIMARY KEY,
	addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
	data_riposo_spostato DATE NOT NULL,
	data_recupero_riposo DATE NOT NULL,
	tipo_riposo_id INTEGER NULL REFERENCES app.ope_tipo_riposo(id),
	attuale BOOLEAN NOT NULL,
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);
