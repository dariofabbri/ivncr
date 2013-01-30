CREATE TABLE gen_azienda
(
	id serial NOT NULL PRIMARY KEY,
	codice VARCHAR(255) NOT NULL UNIQUE,
	descrizione VARCHAR(4000)
);

INSERT INTO gen_azienda(codice, descrizione) VALUES ('CRM', 'Città di Roma Metronotte');
INSERT INTO gen_azienda(codice, descrizione) VALUES ('IVNCR', 'Nuova Città di Roma');
INSERT INTO gen_azienda(codice, descrizione) VALUES ('MSC', 'Metroservice');


CREATE TABLE acc_utente_azienda
(
	utente_id INTEGER NOT NULL REFERENCES acc_utente(id),
	azienda_id INTEGER NOT NULL REFERENCES gen_azienda(id),
  preferita BOOLEAN,
	PRIMARY KEY(utente_id, azienda_id)
);


ALTER TABLE com_cliente ADD azienda_id INTEGER REFERENCES gen_azienda(id);
UPDATE com_cliente SET azienda_id = (SELECT id FROM gen_azienda WHERE codice = 'IVNCR');
ALTER TABLE com_cliente ALTER COLUMN azienda_id SET NOT NULL;
ALTER TABLE com_cliente ADD CONSTRAINT com_cliente_unique_azienda_id_codice UNIQUE(azienda_id, codice);


ALTER TABLE gen_contatore ADD azienda_id INTEGER REFERENCES gen_azienda(id);
UPDATE gen_contatore SET azienda_id = (SELECT id FROM gen_azienda WHERE codice = 'IVNCR');
ALTER TABLE gen_contatore ALTER COLUMN azienda_id SET NOT NULL;
ALTER TABLE gen_contatore ADD CONSTRAINT gen_contatore_unique_azienda_id_codice UNIQUE(azienda_id, codice);

INSERT INTO acc_utente_azienda (utente_id, azienda_id, preferita)
  SELECT ute.id, azi.id, null 
    FROM acc_utente ute, gen_azienda azi;
