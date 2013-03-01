CREATE TABLE per_gruppo_sanguigno
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_tipo_posizione_militare
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_grado
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_addetto
(
	id SERIAL NOT NULL PRIMARY KEY,
	matricola VARCHAR(255) NOT NULL,
  nome VARCHAR(255),
  cognome VARCHAR(255),
  data_nascita DATE,
  luogo_nascita VARCHAR(255),
  codice_fiscale VARCHAR(255),
  sesso CHAR,
  foto OID,
  note VARCHAR(4000),
  creazione_ts TIMESTAMP WITH TIME ZONE,
  ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_dati_sanitari
(
	id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  codice_sanitario VARCHAR(255),
  medico_base VARCHAR(255),
  gruppo_sanguigno_id INTEGER REFERENCE per_gruppo_sanguigno(id),
  asl VARCHAR(255),
  indirizzo_asl VARCHAR(255),
  comune VARCHAR(255)
);


CREATE TABLE per_posizione_militare
(
	id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_posizione_militare_id INTEGER REFERENCE per_tipo_posizione_militare(id),
  presso VARCHAR(255),
  grado_id INTEGER REFERENCE per_grado(id),
  data_congedo DATE,
  note VARCHAR(4000)
);
