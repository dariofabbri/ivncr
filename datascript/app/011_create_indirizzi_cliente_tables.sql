\encoding UTF8;

SET client_min_messages TO WARNING;

CREATE TABLE app.com_tipo_indirizzo
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.com_tipo_indirizzo (id, descrizione) VALUES (1, 'Sede legale');
INSERT INTO app.com_tipo_indirizzo (id, descrizione) VALUES (2, 'Sede operativa');
INSERT INTO app.com_tipo_indirizzo (id, descrizione) VALUES (3, 'Spedizione fatture');
SELECT setval('app.com_tipo_indirizzo_id_seq', (SELECT MAX(id) FROM app.com_tipo_indirizzo));


CREATE TABLE app.com_indirizzo
(
	id serial NOT NULL PRIMARY KEY,
  cliente_id INTEGER NOT NULL REFERENCES app.com_cliente(id),
  tipo_indirizzo_id INTEGER NOT NULL REFERENCES app.com_tipo_indirizzo(id),
  destinatario1 VARCHAR(255) NOT NULL,
  destinatario2 VARCHAR(255),
	toponimo VARCHAR(50),
  indirizzo VARCHAR(255),
  civico VARCHAR(50),
  localita VARCHAR(255),
  cap VARCHAR(50),
  provincia VARCHAR(50),
  paese VARCHAR(255)
);

