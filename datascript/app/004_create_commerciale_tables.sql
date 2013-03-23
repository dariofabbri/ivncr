\encoding UTF8;

SET client_min_messages TO WARNING;

CREATE TABLE app.com_gruppo_cliente
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

CREATE TABLE app.com_divisa
(
	id SERIAL NOT NULL PRIMARY KEY,
  simbolo VARCHAR(255),
  iso_alpha VARCHAR(3),
  iso_num NUMERIC(3),
	descrizione VARCHAR(255) NOT NULL
);

CREATE TABLE app.com_tipo_business_partner
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

CREATE TABLE app.com_cliente
(
	id SERIAL NOT NULL PRIMARY KEY,
	codice VARCHAR(255) NOT NULL,
	ragione_sociale VARCHAR(1024) NOT NULL,
	partita_iva VARCHAR(50),
	codice_fiscale VARCHAR(50),
  gruppo_cliente_id INTEGER REFERENCES app.com_gruppo_cliente(id),
  divisa_id INTEGER REFERENCES app.com_divisa(id),
  telefono_1 VARCHAR(255),
  telefono_2 VARCHAR(255),
  cellulare VARCHAR(255),
  fax VARCHAR(255),
  email VARCHAR(255),
  attivo BOOLEAN NOT NULL,
  attivo_dal_ts TIMESTAMP WITH TIME ZONE,
  attivo_al_ts TIMESTAMP WITH TIME ZONE,
  attivo_note VARCHAR(1024),
  bloccato BOOLEAN NOT NULL,
  bloccato_dal_ts TIMESTAMP WITH TIME ZONE,
  bloccato_al_ts TIMESTAMP WITH TIME ZONE,
  bloccato_note VARCHAR(1024),
  tipo_business_partner_id INTEGER REFERENCES app.com_tipo_business_partner(id),
  saldo_contabile NUMERIC(18, 4),
  creazione_ts TIMESTAMP WITH TIME ZONE,
  ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);
