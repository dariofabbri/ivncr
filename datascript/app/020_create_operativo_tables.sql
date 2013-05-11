\encoding UTF8;
SET client_min_messages TO WARNING;


CREATE TABLE app.ope_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
	ods_nuova_attivazione_id INTEGER NOT NULL REFERENCES app.con_ordine_servizio(id),
	sovrafatturazione BOOLEAN,
	data_mattinale DATE NOT NULL,
	data_contabile DATE NOT NULL,
	orario_da TIME WITH TIME ZONE,
	orario_a TIME WITH TIME ZONE,
	retribuito BOOLEAN,
	...

	note VARCHAR(4000),
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);