CREATE TABLE com_tipo_contatto
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO com_tipo_contatto (id, descrizione) VALUES (1, 'Referente commerciale');
INSERT INTO com_tipo_contatto (id, descrizione) VALUES (2, 'Referente amministrativo');
INSERT INTO com_tipo_contatto (id, descrizione) VALUES (3, 'Referente operativo');


CREATE TABLE com_contatto
(
	id serial NOT NULL PRIMARY KEY,
  tipo_contatto_id INTEGER NOT NULL REFERENCES com_tipo_contatto(id),
  cliente_id INTEGER NOT NULL REFERENCES com_cliente(id),
	titolo VARCHAR(50),
  nome VARCHAR(255) NOT NULL,
  telefono1 VARCHAR(255),
  telefono2 VARCHAR(255),
  cellulare VARCHAR(255),
  fax VARCHAR(255),
  email VARCHAR(255)
);
