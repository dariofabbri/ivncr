CREATE TABLE acc_utente
(
	id serial NOT NULL PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	nome VARCHAR(255),
	cognome VARCHAR(255),
	note VARCHAR(4000),
  digest VARCHAR(255) NOT NULL,
  salt VARCHAR(255) NOT NULL,
  iterazioni INTEGER NOT NULL,
  ultimo_login_ts TIMESTAMP WITH TIME ZONE,
  creazione_ts TIMESTAMP WITH TIME ZONE,
  ultima_attivazione_ts TIMESTAMP WITH TIME ZONE,
  ultima_disattivazione_ts TIMESTAMP WITH TIME ZONE,
  attivo BOOLEAN NOT NULL
);

CREATE TABLE acc_ruolo
(
	id serial NOT NULL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL UNIQUE,
	descrizione VARCHAR(4000)
);

CREATE TABLE acc_permesso
(
	id serial NOT NULL PRIMARY KEY,
	permesso VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE acc_utente_ruolo
(
	utente_id INTEGER NOT NULL REFERENCES acc_utente(id),
	ruolo_id INTEGER NOT NULL REFERENCES acc_ruolo(id),
	PRIMARY KEY(utente_id, ruolo_id)
);

CREATE TABLE acc_ruolo_permesso
(
	ruolo_id INTEGER NOT NULL REFERENCES acc_ruolo(id),
	permesso_id INTEGER NOT NULL REFERENCES acc_permesso(id),
	PRIMARY KEY(ruolo_id, permesso_id)
);
