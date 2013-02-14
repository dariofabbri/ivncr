CREATE TABLE com_obiettivo_servizio
(
	id serial NOT NULL PRIMARY KEY,
  cliente_id INTEGER NOT NULL REFERENCES com_cliente(id),
  alias VARCHAR(255) NOT NULL,
	toponimo VARCHAR(50),
  indirizzo VARCHAR(255),
  civico VARCHAR(50),
  edificio VARCHAR(255),
  scala VARCHAR(255),
  piano VARCHAR(255),
  interno VARCHAR(255),
  localita VARCHAR(255),
  cap VARCHAR(50),
  provincia VARCHAR(50),
  paese VARCHAR(255),
  note VARCHAR(4000)
);

