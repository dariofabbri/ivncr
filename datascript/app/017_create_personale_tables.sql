CREATE TABLE per_tipo_indirizzo
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (1, 'Residenza');
INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (2, 'Domicilio');
INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (3, 'Residenza (cessata)');
INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (4, 'Domicilio (cessata)');



CREATE TABLE per_tipo_recapito_telefonico
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_recapito_telefonico (id, descrizione) VALUES (1, 'Cellulare personale');
INSERT INTO per_tipo_recapito_telefonico (id, descrizione) VALUES (2, 'Cellulare aziendale');
INSERT INTO per_tipo_recapito_telefonico (id, descrizione) VALUES (3, 'Telefono abitazione');



CREATE TABLE per_ruolo
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_ruolo (id, descrizione) VALUES (1, 'Dirigente');
INSERT INTO per_ruolo (id, descrizione) VALUES (2, 'Portiere');
INSERT INTO per_ruolo (id, descrizione) VALUES (3, 'Operativo');
INSERT INTO per_ruolo (id, descrizione) VALUES (4, 'Amministrativo');



CREATE TABLE per_sistema_lavoro
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (1, 'DESCRIZIONE');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (2, 'RB');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (3, 'Part Time 5+2 4ore Amm.');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (4, 'PT6+1_4h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (5, 'PT5+2_3h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (6, 'PT5+2_350h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (7, 'PT5+1_5h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (8, 'PT5+1_4h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (9, 'PT3+4_7h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (10, 'OPT5+2_4h');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (11, 'Multiservizi 5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (12, 'Commercio 6+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (13, 'Commercio 5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (14, 'CFL6+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (15, 'CFL5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (16, 'CFL5+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (17, 'CFL RB');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (18, 'APP_RB');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (19, 'APP_6+1+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (20, 'APP_6+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (21, 'APP_5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (22, 'APP_5+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (23, '6+1+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (24, '6+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (25, '5+2 Portierato');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (26, '5+2 Dirigenza');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (27, '5+2 Amministrativo');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (28, '5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (29, '5+1');


CREATE TABLE per_tipo_contratto
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);



CREATE TABLE per_ccnl
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);



CREATE TABLE per_grado
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);



CREATE TABLE per_sigla_sindacale
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_carica_sindacale
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);



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


CREATE TABLE per_titolo_studio
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_grado_parentela
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_tipo_documento
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_tipo_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_modello_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_calibro_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_stato_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_tipo_certificato
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_lingua
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_livello_lingua
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);



CREATE TABLE per_addetto
(
  id SERIAL NOT NULL PRIMARY KEY,
  azienda_id INTEGER NOT NULL REFERENCES gen_azienda(id),
  matricola VARCHAR(255) NOT NULL,
  nome VARCHAR(255),
  cognome VARCHAR(255),
  data_nascita DATE,
  luogo_nascita VARCHAR(255),
  codice_fiscale VARCHAR(255),
  sesso CHAR,
  foto OID,
  note VARCHAR(4000),
  fittizio BOOLEAN,
  attivo BOOLEAN,
  creazione_ts TIMESTAMP WITH TIME ZONE,
  ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_reparto
(
  id SERIAL NOT NULL PRIMARY KEY,
  azienda_id INTEGER NOT NULL REFERENCES gen_azienda(id),
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE per_addetto_sistema_lavoro
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  sistema_lavoro_id INTEGER NOT NULL REFERENCES per_sistema_lavoro(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_addetto_reparto
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  reparto_id INTEGER NOT NULL REFERENCES per_reparto(id),
  ruolo_id INTEGER NOT NULL REFERENCES per_ruolo(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_posizione_lavorativa
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_contratto_id INTEGER NOT NULL REFERENCES per_tipo_contratto(id),
  ccnl_id INTEGER NOT NULL REFERENCES per_ccnl(id),
  azienda_id INTEGER NOT NULL REFERENCES gen_azienda(id),
  durata_contratto INTEGER,
  data_assunzione DATE,
  durata_prova INTEGER,
  data_primo_giorno DATE,
  data_fine_prova DATE,
  data_cessazione DATE,
  data_fine_contratto DATE,
  motivo_dimissioni VARCHAR(4000)
);


CREATE TABLE per_info_sindacali
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  sigla_sindacale_id INTEGER NOT NULL REFERENCES per_sigla_sindacale(id),
  carica_sindacale_id INTEGER NOT NULL REFERENCES per_carica_sindacale(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_carriera
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  qualifica_id INTEGER NOT NULL REFERENCES per_qualifica(id),
  livello_ccnl_id INTEGER NOT NULL REFERENCES per_livello_ccnl(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);



CREATE TABLE per_indirizzo
(
  id serial NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_indirizzo_id INTEGER NOT NULL REFERENCES per_tipo_indirizzo(id),
  destinatario1 VARCHAR(255) NOT NULL,
  destinatario2 VARCHAR(255),
  toponimo VARCHAR(50),
  indirizzo VARCHAR(255),
  civico VARCHAR(50),
  localita VARCHAR(255),
  cap VARCHAR(50),
  provincia VARCHAR(50),
  paese VARCHAR(255),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_recapito_telefonico
(
  id serial NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_recapito_telefonico_id INTEGER NOT NULL REFERENCES per_tipo_recapito_telefonico(id),
  recapito VARCHAR(255) NOT NULL
);


CREATE TABLE per_recapito_email
(
  id serial NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  email VARCHAR(255) NOT NULL
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


CREATE TABLE per_istruzione
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  titolo_studio_id INTEGER REFERENCE per_titolo_studio(id),
  data_conseguimento DATE,
  presso VARCHAR(255)
);


CREATE TABLE per_stato_famiglia
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  nome VARCHAR(255) NOT NULL,
  grado_parentela_id INTEGER REFERENCE per_grado_parentela(id),
  data_nascita DATE,
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_dati_sanitari
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  medico_base VARCHAR(255),
  gruppo_sanguigno_id INTEGER REFERENCE per_gruppo_sanguigno(id),
  asl VARCHAR(255),
  indirizzo_asl VARCHAR(255),
  comune VARCHAR(255),
  provincia VARCHAR(255),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE per_corso
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  ente VARCHAR(255),
  abilitazione VARCHAR(255),
  ore_corso INTEGER,
  valutazione VARCHAR(255),
  data_conseguimento DATE,
  data_inizio DATE,
  data_fine DATE,
  note VARCHAR(4000)
);


CREATE TABLE per_documento
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_documento_id INTEGER REFERENCE per_tipo_documento(id),
  numero VARCHAR(255),
  rilasciato_da VARCHAR(255),
  data_rilascio DATE,
  data_scadenza DATE
);


CREATE TABLE per_armamento
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_arma_id INTEGER REFERENCE per_tipo_arma(id),
  modello_arma_id INTEGER REFERENCE per_modello_arma(id),
  calibro_arma_id INTEGER REFERENCE per_calibro_arma(id),
  matricola VARCHAR(255),
  stato_arma_id INTEGER REFERENCE per_stato_arma(id),
  data_denuncia DATE,
  data_inizio DATE,
  data_fine DATE,
  note VARCHAR(4000)
);


CREATE TABLE per_libretto_porto_armi
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  numero VARCHAR(255) NOT NULL,
  fucile BOOLEAN,
  data_rilascio DATE,
  data_scadenza DATE,
  note VARCHAR(4000)
);


CREATE TABLE per_licenza_porto_armi
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  libretto_id INTEGER NOT NULL REFERENCES per_libretto_porto_armi(id),
  numero VARCHAR(255) NOT NULL,
  data_rilascio DATE,
  data_scadenza DATE,
  note VARCHAR(4000)
);


CREATE TABLE per_decreto_gpg
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  numero VARCHAR(255) NOT NULL,
  data_rilascio DATE,
  data_scadenza DATE,
  note VARCHAR(4000)
);


CREATE TABLE per_malattia
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  tipo_certificato_id INTEGER NOT NULL REFERENCES per_tipo_certificato(id),
  data_certificato DATE,
  data_ricezione DATE,
  data_inizio_validita DATE,
  data_fine_validita DATE,  
  note VARCHAR(4000)
);


CREATE TABLE per_estensione_decreto
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  provincia VARCHAR(255),
  data_inizio_validita DATE,
  data_fine_validita DATE,  
  note VARCHAR(4000)
);


CREATE TABLE per_lingua_conosciuta
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES per_addetto(id),
  lingua_id INTEGER NOT NULL REFERENCES per_lingua(id),
  livello_lingua_id INTEGER NOT NULL REFERENCES per_livello_lingua(id),
  note VARCHAR(4000)
);
