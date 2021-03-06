\encoding UTF8;
SET client_min_messages TO WARNING;


CREATE TABLE app.gen_giorno_settimana
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (1, 'Lunedì');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (2, 'Martedì');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (3, 'Mercoledì');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (4, 'Giovedì');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (5, 'Venerdì');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (6, 'Sabato');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (7, 'Domenica');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (8, 'Festivi');
INSERT INTO app.gen_giorno_settimana (id, descrizione) VALUES (9, 'Prefestivi');
SELECT setval('app.gen_giorno_settimana_id_seq', (SELECT MAX(id) FROM app.gen_giorno_settimana));


CREATE TABLE app.con_tipo_provenienza
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_provenienza (id, descrizione) VALUES (1, 'Fax del cliente');
INSERT INTO app.con_tipo_provenienza (id, descrizione) VALUES (2, 'Email del cliente');
INSERT INTO app.con_tipo_provenienza (id, descrizione) VALUES (3, 'Commerciale');
INSERT INTO app.con_tipo_provenienza (id, descrizione) VALUES (4, 'Produttore');
SELECT setval('app.con_tipo_provenienza_id_seq', (SELECT MAX(id) FROM app.con_tipo_provenienza));


CREATE TABLE app.con_condizioni_pagamento
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (1, 'DESCRIZIONE');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (2, '30 GG D.F. F.M.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (3, '60 GG D.F.F.M.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (4, '90 GG D.F. F.M.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (5, 'RIMESSA DIRETTA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (6, '30 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (7, '60 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (8, '90 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (9, '120 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (10, '120 GG D.F. F.M.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (11, '60-90-120 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (12, 'Presentazione Fattura FM');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (13, '150 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (14, '180 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (15, '180 GG D.F. F.M.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (16, '15 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (17, '45 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (18, 'conto corrente postale');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (19, '365 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (20, '20 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (21, '25 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (22, '15 GG Ric. Fatt. (Max 30 GG D.F.)');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (23, '150 GG D.F. F.M.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (24, '75 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (25, '40 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (26, '70 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (27, '60-90 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (28, '90-120 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (29, '8 Rate 30 GG D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (30, 'RIM.DIR D.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (31, 'RIMESSA DIRETTA 60 GG R.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (32, 'RIMESSA DIRETTA 45 GG P.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (33, 'RIMESSA DIRETTA 30 GG DATA P.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (34, '30 GG RIC. FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (35, '62 G. DATA FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (36, 'RIMESSA DIRETTA RICEVIMENTO FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (37, '192 GG R.F.');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (38, 'RID BANCARIO');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (39, '20 GG DATA RICEVIMENTO FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (40, '210 GG F.M. DATA RICEVIMENTO FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (41, '90 GG RICEVIMENTO FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (42, '180 GG DALLA DATA DI INSERIMENTO DELLE FATTURE NEL SISTEMA TELEMATICO REGIONALE');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (43, 'ASSEGNO BANCARIO');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (44, '150 GG F.M. DATA PRESENTAZIONE FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (45, 'BONIFICO BANCARIO');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (46, 'anticipato');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (47, '120 GG DATA RICEVIMENTO FATTURA');
INSERT INTO app.con_condizioni_pagamento (id, descrizione) VALUES (48, '45 GG D.F. F.M.');
SELECT setval('app.con_condizioni_pagamento_id_seq', (SELECT MAX(id) FROM app.con_condizioni_pagamento));


CREATE TABLE app.con_metodo_pagamento
(
	id SERIAL NOT NULL PRIMARY KEY,
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (1, 1, 'Bonifico Bancario');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (2, 1, 'Inc. Bonifico Banca Nazionale del Lavoro - BNP Paribas c/000000000017');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (3, 1, 'Inc. Bonifico CARIFAC  spa  -  Ag. Roma 1 c/038570020242');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (4, 1, 'Inc. Bonifico Banco Desio Lazio spa  -  Ag. Roma 88 c/000000238600');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (5, 1, 'Inc. Bonifico Banca Intesa s.p.a. c/100000066387');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (6, 1, 'Inc. Bonifico Monte Paschi Siena s.p.a. c/000000126478');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (7, 1, 'Inc. Bonifico Unicredit Banca di Roma spa - Ag. 7693 c/000500046630');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (8, 1, 'Inc. Bonifico Banca Nazionale del Lavoro c/00061');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (9, 1, 'Inc. Bonifico Banca Nazionale del Lavoro c/00071');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (10, 1, 'Inc. tramite c/c postale');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (11, 1, 'Inc. Bonifico Banca Cred.Coop. RM c/39648');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (12, 1, 'Inc. Bonifico Banca Pop dell''Emilia Romagna c/76928');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (13, 1, 'Inc. Bonifico Banco Posta c/86540');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (14, 1, 'Inc. Bonifico Banca Toscana c/02349');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (15, 1, 'Inc. Bonifico Cassa di Risparmio di Firenze c/36C00');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (16, 1, 'Inc. Bonifico Cassa di Risparmio di Firenze c/99C00');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (17, 1, 'Inc. Bonifico Banca Intesa San Paolo RM c/02067');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (18, 1, 'Inc. Bonifico Monte dei Paschi di Siena c/78277');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (19, 1, 'Inc. Bonifico Unicredit Corporate Banking c/27954');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (21, 1, 'Inc. Bonifico Unicredit Corporate Banking c/30068');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (22, 1, 'Inc. Bonifico Unicredit Corporate Banking c/99061');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (23, 1, 'Inc. Bonifico Unipol Banca c/00756');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (24, 1, 'RID bancario BCC');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (25, 1, 'Inc. Bonifico Banca di Credito Cooperativo di Roma c/000000040751');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (26, 1, 'Inc. Bonifico Banca Intesa s.p.a. c/100000066512');
INSERT INTO app.con_metodo_pagamento (id, azienda_id, descrizione) VALUES (27, 1, 'IFITALIA: IBAN IT 22 I 01005 01612 000000002000');
SELECT setval('app.con_metodo_pagamento_id_seq', (SELECT MAX(id) FROM app.con_metodo_pagamento));


CREATE TABLE app.con_layout_stampa
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_layout_stampa (id, descrizione) VALUES (1, 'Totalizzato');
INSERT INTO app.con_layout_stampa (id, descrizione) VALUES (2, 'Totale dettaglio');
INSERT INTO app.con_layout_stampa (id, descrizione) VALUES (3, 'Bollettino');
SELECT setval('app.con_layout_stampa_id_seq', (SELECT MAX(id) FROM app.con_layout_stampa));


CREATE TABLE app.con_tipo_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_servizio (id, descrizione) VALUES (1, 'Piantonamento');
INSERT INTO app.con_tipo_servizio (id, descrizione) VALUES (2, 'Ispezione');
SELECT setval('app.con_tipo_servizio_id_seq', (SELECT MAX(id) FROM app.con_tipo_servizio));


CREATE TABLE app.con_specifica_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_specifica_servizio (id, tipo_servizio_id, descrizione) VALUES (1, 1, 'Vigilanza');
INSERT INTO app.con_specifica_servizio (id, tipo_servizio_id, descrizione) VALUES (2, 1, 'Portierato');
INSERT INTO app.con_specifica_servizio (id, tipo_servizio_id, descrizione) VALUES (3, 2, 'Interna');
INSERT INTO app.con_specifica_servizio (id, tipo_servizio_id, descrizione) VALUES (4, 2, 'Esterna');
SELECT setval('app.con_specifica_servizio_id_seq', (SELECT MAX(id) FROM app.con_specifica_servizio));


CREATE TABLE app.con_raggruppamento_fatturazione
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_raggruppamento_fatturazione (id, descrizione) VALUES (1, 'Gruppo A');
INSERT INTO app.con_raggruppamento_fatturazione (id, descrizione) VALUES (2, 'Gruppo B');
INSERT INTO app.con_raggruppamento_fatturazione (id, descrizione) VALUES (3, 'Gruppo C');
INSERT INTO app.con_raggruppamento_fatturazione (id, descrizione) VALUES (4, 'Gruppo D');
SELECT setval('app.con_raggruppamento_fatturazione_id_seq', (SELECT MAX(id) FROM app.con_raggruppamento_fatturazione));


CREATE TABLE app.con_tipo_ordine_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_ordine_servizio (id, descrizione) VALUES (1, 'Nuova attivazione');
INSERT INTO app.con_tipo_ordine_servizio (id, descrizione) VALUES (2, 'Var. extra');
INSERT INTO app.con_tipo_ordine_servizio (id, descrizione) VALUES (3, 'Var. occasionale');
INSERT INTO app.con_tipo_ordine_servizio (id, descrizione) VALUES (4, 'Var. contrattuale');
SELECT setval('app.con_tipo_ordine_servizio_id_seq', (SELECT MAX(id) FROM app.con_tipo_ordine_servizio));


CREATE TABLE app.con_gruppo_apparecchiatura_tecnologica
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_gruppo_apparecchiatura_tecnologica (id, descrizione) VALUES (1, 'Allarme');
INSERT INTO app.con_gruppo_apparecchiatura_tecnologica (id, descrizione) VALUES (2, 'TVCC');
INSERT INTO app.con_gruppo_apparecchiatura_tecnologica (id, descrizione) VALUES (3, 'Periferica');
INSERT INTO app.con_gruppo_apparecchiatura_tecnologica (id, descrizione) VALUES (4, 'Apparati di controllo');
SELECT setval('app.con_gruppo_apparecchiatura_tecnologica_id_seq', (SELECT MAX(id) FROM app.con_gruppo_apparecchiatura_tecnologica));


CREATE TABLE app.con_tipo_apparecchiatura_tecnologica
(
	id SERIAL NOT NULL PRIMARY KEY,
	gruppo_apparecchiatura_id INTEGER NOT NULL REFERENCES app.con_gruppo_apparecchiatura_tecnologica(id),
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (1, 1, 'Sensore');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (2, 1, 'Contatto');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (3, 1, 'Telecomando');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (4, 1, 'Centralina');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (5, 1, 'Sirena');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (6, 1, 'Tastiera');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (7, 1, 'Colonnina perimetrale');

INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (8, 2, 'Telecamera fissa');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (9, 2, 'Dome');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (10, 2, 'Videoregistratore');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (11, 2, 'Monitor');

INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (12, 3, 'Mono');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (13, 3, 'Bidi');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (14, 3, 'Telefonico');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (15, 3, 'GSM');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (16, 3, 'GPRS');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (17, 3, 'Arcvision');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (18, 3, 'Periferica');

INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (19, 4, 'Orologio');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (20, 4, 'Datix');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, gruppo_apparecchiatura_id, descrizione) VALUES (21, 4, 'Metal detector');
SELECT setval('app.con_tipo_apparecchiatura_tecnologica_id_seq', (SELECT MAX(id) FROM app.con_tipo_apparecchiatura_tecnologica));


CREATE TABLE app.con_gestore_contratto
(
	id SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(255),
	cognome VARCHAR(255),
	data_nascita DATE,
	luogo_nascita VARCHAR(255),
	codice_fiscale VARCHAR(255),
	sesso CHAR,
	foto OID,
	note VARCHAR(4000)
);


CREATE TABLE app.con_gestore_contratto_azienda
(
	id SERIAL NOT NULL PRIMARY KEY,
	gestore_contratto_id INTEGER NOT NULL REFERENCES app.con_gestore_contratto(id),
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	attivo BOOLEAN
);
ALTER TABLE app.con_gestore_contratto_azienda ADD CONSTRAINT con_gestore_contratto_azienda_unique_gestore_contratto_id_azienda_id UNIQUE(gestore_contratto_id, azienda_id);


CREATE TABLE app.con_esattore
(
	id SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(255),
	cognome VARCHAR(255),
	data_nascita DATE,
	luogo_nascita VARCHAR(255),
	codice_fiscale VARCHAR(255),
	sesso CHAR,
	foto OID,
	note VARCHAR(4000)
);


CREATE TABLE app.con_esattore_azienda
(
	id SERIAL NOT NULL PRIMARY KEY,
	esattore_id INTEGER NOT NULL REFERENCES app.con_esattore(id),
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	attivo BOOLEAN
);
ALTER TABLE app.con_esattore_azienda ADD CONSTRAINT con_esattore_azienda_unique_esattore_id_azienda_id UNIQUE(esattore_id, azienda_id);


CREATE TABLE app.con_contratto
(
	id SERIAL NOT NULL PRIMARY KEY,
	cliente_id INTEGER NOT NULL REFERENCES app.com_cliente(id),
	ragione_sociale VARCHAR(255) NOT NULL,
	codice VARCHAR(255) NOT NULL,
	alias VARCHAR(255),
	data_contratto DATE NOT NULL,
	data_decorrenza DATE NOT NULL,
	data_termine DATE,
	data_cessazione DATE,
	tacito_rinnovo BOOLEAN,
	giorni_periodo_rinnovo INTEGER,
	mesi_periodo_rinnovo INTEGER,
	anni_periodo_rinnovo INTEGER,
	giorni_preavviso_scadenza INTEGER,
	note VARCHAR(4000),
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_rinnovo_contrattuale
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	data_decorrenza_pre DATE,
	data_termine_pre DATE,
	data_decorrenza_post DATE,
	data_termine_post DATE,
	note VARCHAR(4000)
);


CREATE TABLE app.con_contratto_contatto
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	contatto_id INTEGER NOT NULL REFERENCES app.com_contatto(id),
	valido_da DATE,
	valido_a DATE
);
ALTER TABLE app.con_contratto_contatto ADD CONSTRAINT con_contratto_contatto_unique_contratto_id_contatto_id UNIQUE(contratto_id, contatto_id);


CREATE TABLE app.con_contratto_gestore
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	gestore_contratto_id INTEGER NOT NULL REFERENCES app.con_gestore_contratto(id),
	valido_da DATE,
	valido_a DATE
);
ALTER TABLE app.con_contratto_gestore ADD CONSTRAINT con_contratto_gestore_unique_contratto_id_gestore_contratto_id UNIQUE(contratto_id, gestore_contratto_id);


CREATE TABLE app.con_contratto_esattore
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	esattore_id INTEGER NOT NULL REFERENCES app.con_esattore(id),
	valido_da DATE,
	valido_a DATE
);
ALTER TABLE app.con_contratto_esattore ADD CONSTRAINT con_contratto_esattore_unique_contratto_id_esattore_id UNIQUE(contratto_id, esattore_id);


CREATE TABLE app.con_dettaglio_fatturazione
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	condizioni_pagamento_id INTEGER NOT NULL REFERENCES app.con_condizioni_pagamento(id),
	metodo_pagamento_id INTEGER NOT NULL REFERENCES app.con_metodo_pagamento(id),
	indirizzo_id INTEGER NOT NULL REFERENCES app.com_indirizzo(id),
	layout_stampa_id INTEGER NOT NULL REFERENCES app.con_layout_stampa(id),
	valido_da DATE,
	valido_a DATE
);


CREATE TABLE app.con_canone
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	alias VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	data_inizio_validita DATE NOT NULL,
	data_cessazione DATE,
	fattura_minimo_un_mese BOOLEAN,
	fatturazione_anticipata BOOLEAN,
	fattura_ogni_mesi INTEGER,	
	canone_mensile NUMERIC(18, 4) NOT NULL,
	note VARCHAR(4000)
);


CREATE TABLE app.con_canone_storico
(
	id SERIAL NOT NULL PRIMARY KEY,
	canone_id INTEGER NOT NULL REFERENCES app.con_canone(id),
  modifica_ts TIMESTAMP WITH TIME ZONE NOT NULL,
	alias VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	data_inizio_validita DATE NOT NULL,
	data_cessazione DATE,
	fattura_minimo_un_mese BOOLEAN,
	fatturazione_anticipata BOOLEAN,
	fattura_ogni_mesi INTEGER,	
	canone_mensile NUMERIC(18, 4) NOT NULL,
	note VARCHAR(4000)
);


CREATE TABLE app.con_tariffa
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	alias VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	costo_orario NUMERIC(18, 4),
	costo_operazione NUMERIC(18, 4),
	costo_fisso_una_tantum NUMERIC(18, 4),
	costo_fisso_a_tempo NUMERIC(18, 4),
	costo_fisso_mesi INTEGER,
	franchigie_totali INTEGER,
	franchigie_a_tempo INTEGER,
	franchigie_mesi INTEGER,
	ritenuta_garanzia NUMERIC(5, 2),
	ritenuta_garanzia_giorni INTEGER,	
	data_inizio_validita DATE NOT NULL,	
	data_cessazione DATE,
	fatturazione_anticipata BOOLEAN,
	extra_fatturato_a_parte BOOLEAN,
	fattura_spezzata BOOLEAN,
	fattura_ogni_mesi INTEGER,
	fattura_minimo_un_mese BOOLEAN,
	note VARCHAR(4000)
);


CREATE TABLE app.con_tariffa_storico
(
	id SERIAL NOT NULL PRIMARY KEY,
	tariffa_id INTEGER NOT NULL REFERENCES app.con_tariffa(id),
  modifica_ts TIMESTAMP WITH TIME ZONE NOT NULL,
	alias VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	costo_orario NUMERIC(18, 4),
	costo_operazione NUMERIC(18, 4),
	costo_fisso_una_tantum NUMERIC(18, 4),
	costo_fisso_a_tempo NUMERIC(18, 4),
	costo_fisso_mesi INTEGER,
	franchigie_totali INTEGER,
	franchigie_a_tempo INTEGER,
	franchigie_mesi INTEGER,
	ritenuta_garanzia NUMERIC(5, 2),
	ritenuta_garanzia_giorni INTEGER,	
	data_inizio_validita DATE NOT NULL,	
	data_cessazione DATE,
	fatturazione_anticipata BOOLEAN,
	extra_fatturato_a_parte BOOLEAN,
	fattura_spezzata BOOLEAN,
	fattura_ogni_mesi INTEGER,
	fattura_minimo_un_mese BOOLEAN,
	note VARCHAR(4000)
);


CREATE TABLE app.con_ricavo_extra_vigilanza
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	alias VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	raggruppamento_fatturazione_id INTEGER REFERENCES app.con_raggruppamento_fatturazione(id),
	data_inizio_validita DATE NOT NULL,
	data_cessazione DATE,
	fattura_minimo_un_mese BOOLEAN,
	fatturazione_anticipata BOOLEAN,
	fattura_ogni_mesi INTEGER,	
	canone_mensile NUMERIC(18, 4) NOT NULL,
	note VARCHAR(4000)
);


CREATE TABLE app.con_apparecchiatura_tecnologica
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	tipo_apparecchiatura_id INTEGER NOT NULL REFERENCES app.con_tipo_apparecchiatura_tecnologica(id),
	descrizione VARCHAR(255),
	matricola VARCHAR(255),
	comodato_uso BOOLEAN,
	data_installazione DATE,
	data_fatturazione DATE,
	data_ritiro DATE,
	costo_una_tantum NUMERIC(18, 4),
	note VARCHAR(4000)
);


CREATE TABLE app.con_documento_contratto
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	descrizione VARCHAR(255),
	filename VARCHAR(255) NOT NULL,
	mime_type VARCHAR(255),
	caricamento_ts TIMESTAMP WITH TIME ZONE NOT NULL,
	documento OID NOT NULL,
	note VARCHAR(4000)
);


CREATE TABLE app.con_ordine_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	tipo_ordine_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_ordine_servizio(id),
	padre_id INTEGER REFERENCES app.con_ordine_servizio(id),
	nuova_attivazione_id INTEGER REFERENCES app.con_ordine_servizio(id),
	codice VARCHAR(255) NOT NULL,
	alias VARCHAR(255),
	data_decorrenza DATE NOT NULL,
	data_termine DATE,
	data_fine_validita DATE,
	orario_fine_validita TIME WITH TIME ZONE,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	obiettivo_servizio_id INTEGER NOT NULL REFERENCES app.com_obiettivo_servizio(id),
	tariffa_id INTEGER REFERENCES app.con_tariffa(id),
	canone_id INTEGER REFERENCES app.con_canone(id),
	oneroso BOOLEAN NOT NULL,
	raggruppamento_fatturazione_id INTEGER REFERENCES app.con_raggruppamento_fatturazione(id),
	cessato BOOLEAN,
	note VARCHAR(4000),
	modalita_operative VARCHAR(4000),
	osservazioni_fattura VARCHAR(4000),
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_ods_frazionamento
(
	id SERIAL NOT NULL PRIMARY KEY,
	ordine_servizio_id INTEGER NOT NULL REFERENCES app.con_ordine_servizio(id),
	cliente_id INTEGER NOT NULL REFERENCES app.com_cliente(id),
	quota NUMERIC(5, 2) NOT NULL,
	esclusione_ritenuta_garanzia BOOLEAN
);


CREATE TABLE app.con_ods_orari_ricorrenti
(
	id SERIAL NOT NULL PRIMARY KEY,
	ordine_servizio_id INTEGER NOT NULL REFERENCES app.con_ordine_servizio(id),
	giorno_settimana_id INTEGER NOT NULL REFERENCES app.gen_giorno_settimana(id),
	escluso_festivo BOOLEAN,
	quantita_1 INTEGER,
	orario_inizio_1 TIME WITH TIME ZONE,
	orario_fine_1 TIME WITH TIME ZONE,
	quantita_2 INTEGER,
	orario_inizio_2 TIME WITH TIME ZONE,
	orario_fine_2 TIME WITH TIME ZONE,
	quantita_3 INTEGER,
	orario_inizio_3 TIME WITH TIME ZONE,
	orario_fine_3 TIME WITH TIME ZONE
);

ALTER TABLE app.con_ods_orari_ricorrenti 
  ADD CONSTRAINT con_ods_orari_ricorrenti_unique_ordine_servizio_id_giorno_settimana_id
  UNIQUE(ordine_servizio_id, giorno_settimana_id);


CREATE TABLE app.con_ods_orari_calendario
(
	id SERIAL NOT NULL PRIMARY KEY,
	ordine_servizio_id INTEGER NOT NULL REFERENCES app.con_ordine_servizio(id),
	data_servizio DATE NOT NULL,
	quantita_1 INTEGER,
	orario_inizio_1 TIME WITH TIME ZONE,
	orario_fine_1 TIME WITH TIME ZONE,
	quantita_2 INTEGER,
	orario_inizio_2 TIME WITH TIME ZONE,
	orario_fine_2 TIME WITH TIME ZONE,
	quantita_3 INTEGER,
	orario_inizio_3 TIME WITH TIME ZONE,
	orario_fine_3 TIME WITH TIME ZONE
);

ALTER TABLE app.con_ods_orari_calendario 
  ADD CONSTRAINT con_ods_orari_calendario_unique_ordine_servizio_id_data_servizio 
  UNIQUE(ordine_servizio_id, data_servizio);


CREATE TABLE app.con_ods_apparecchiatura
(
	id SERIAL NOT NULL PRIMARY KEY,
	ordine_servizio_id INTEGER NOT NULL REFERENCES app.con_ordine_servizio(id),
	apparecchiatura_tecnologica_id INTEGER NOT NULL REFERENCES app.con_apparecchiatura_tecnologica(id)
);

ALTER TABLE app.con_ods_apparecchiatura 
  ADD CONSTRAINT con_ods_apparecchiatura_unique_ordine_servizio_id_apparecchiatura_tecnologica_id 
  UNIQUE(ordine_servizio_id, apparecchiatura_tecnologica_id);
