\encoding UTF8;

CREATE TABLE app.acc_account_email
(
	id serial NOT NULL PRIMARY KEY,
  utente_id INTEGER NOT NULL REFERENCES app.acc_utente(id),
  account VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);
