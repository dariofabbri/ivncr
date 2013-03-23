\encoding UTF8;

SET client_min_messages TO WARNING;

CREATE TABLE app.gen_azienda
(
	id serial NOT NULL PRIMARY KEY,
	codice VARCHAR(255) NOT NULL UNIQUE,
	descrizione VARCHAR(4000)
);

INSERT INTO app.gen_azienda(codice, descrizione) VALUES ('CRM', 'Città di Roma Metronotte');
INSERT INTO app.gen_azienda(codice, descrizione) VALUES ('IVNCR', 'Nuova Città di Roma');
INSERT INTO app.gen_azienda(codice, descrizione) VALUES ('MSC', 'Metroservice');


CREATE TABLE app.acc_utente_azienda
(
	utente_id INTEGER NOT NULL REFERENCES app.acc_utente(id),
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
  preferita BOOLEAN,
	PRIMARY KEY(utente_id, azienda_id)
);


ALTER TABLE app.com_cliente ADD azienda_id INTEGER REFERENCES app.gen_azienda(id);
UPDATE app.com_cliente SET azienda_id = (SELECT id FROM app.gen_azienda WHERE codice = 'IVNCR');
ALTER TABLE app.com_cliente ALTER COLUMN azienda_id SET NOT NULL;
ALTER TABLE app.com_cliente ADD CONSTRAINT com_cliente_unique_azienda_id_codice UNIQUE(azienda_id, codice);


ALTER TABLE app.gen_contatore ADD azienda_id INTEGER REFERENCES app.gen_azienda(id);
UPDATE app.gen_contatore SET azienda_id = (SELECT id FROM app.gen_azienda WHERE codice = 'IVNCR');
ALTER TABLE app.gen_contatore ALTER COLUMN azienda_id SET NOT NULL;
ALTER TABLE app.gen_contatore ADD CONSTRAINT gen_contatore_unique_azienda_id_codice UNIQUE(azienda_id, codice);


INSERT INTO app.acc_utente_azienda (utente_id, azienda_id, preferita)
  SELECT ute.id, azi.id, null 
    FROM app.acc_utente ute, app.gen_azienda azi;
