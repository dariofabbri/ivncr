\encoding UTF8;

CREATE TABLE app.gen_contatore
(
	id serial NOT NULL PRIMARY KEY,
  codice VARCHAR(50) NOT NULL,
  descrizione VARCHAR(255),
  contatore INTEGER NOT NULL DEFAULT 1
);
