\encoding UTF8;
SET client_min_messages TO WARNING;

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


CREATE TABLE app.con_tipo_fatturazione
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_fatturazione (id, descrizione) VALUES (1, 'Anticipata');
INSERT INTO app.con_tipo_fatturazione (id, descrizione) VALUES (2, 'Posticipata');
SELECT setval('app.con_tipo_fatturazione_id_seq', (SELECT MAX(id) FROM app.con_tipo_fatturazione));


CREATE TABLE app.con_tipo_frazionamento_fatturazione
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_frazionamento_fatturazione (id, descrizione) VALUES (1, 'Mensile');
INSERT INTO app.con_tipo_frazionamento_fatturazione (id, descrizione) VALUES (2, 'Bimestrale');
INSERT INTO app.con_tipo_frazionamento_fatturazione (id, descrizione) VALUES (3, 'Trimestrale');
SELECT setval('app.con_tipo_frazionamento_fatturazione_id_seq', (SELECT MAX(id) FROM app.con_tipo_frazionamento_fatturazione));


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


CREATE TABLE app.con_tipo_apparecchiatura_tecnologica
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, descrizione) VALUES (1, 'Allarme');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, descrizione) VALUES (2, 'TVCC');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, descrizione) VALUES (3, 'Periferica');
INSERT INTO app.con_tipo_apparecchiatura_tecnologica (id, descrizione) VALUES (4, 'Apparati di controllo');
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
	gestore_contratto_id INTEGER NOT NULL REFERENCES app.con_gestore_contratto(id),
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	attivo BOOLEAN
);


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
	esattore_id INTEGER NOT NULL REFERENCES app.con_esattore(id),
	azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
	attivo BOOLEAN
);


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
	tacito_rinnovo BOOLEAN,
	giorni_periodo_rinnovo INTEGER,
	mesi_periodo_rinnovo INTEGER,
	anni_periodo_rinnovo INTEGER,
	giorni_preavviso_scadenza INTEGER,
	note VARCHAR(4000),
	creazione_ts TIMESTAMP WITH TIME ZONE,
	ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_contratto_contatto
(
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	contatto_id INTEGER NOT NULL REFERENCES app.com_contatto(id),
	valido_da TIMESTAMP WITH TIME ZONE,
	valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_contratto_gestore
(
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	gestore_contratto_id INTEGER NOT NULL REFERENCES app.con_gestore_contratto(id),
	valido_da TIMESTAMP WITH TIME ZONE,
	valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_contratto_esattore
(
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	esattore_id INTEGER NOT NULL REFERENCES app.con_esattore(id),
	valido_da TIMESTAMP WITH TIME ZONE,
	valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_dettaglio_fatturazione
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	tipo_fatturazione_id INTEGER NOT NULL REFERENCES app.con_tipo_fatturazione(id),
	tipo_frazionamento_fatturazione_id INTEGER NOT NULL REFERENCES app.con_tipo_frazionamento_fatturazione(id),
	condizioni_pagamento_id INTEGER NOT NULL REFERENCES app.con_condizioni_pagamento(id),
	metodo_pagamento_id INTEGER NOT NULL REFERENCES app.con_metodo_pagamento(id),
    indirizzo_id INTEGER NOT NULL REFERENCES app.com_indirizzo(id),
	layout_stampa_id INTEGER NOT NULL REFERENCES app.con_layout_stampa(id),
	valido_da TIMESTAMP WITH TIME ZONE,
	valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.con_canone
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	descrizione VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	obiettivo_servizio_id INTEGER NOT NULL REFERENCES app.com_obiettivo_servizio(id),
	data_inizio_validita DATE NOT NULL,
	raggruppamento_fatturazione_id INTEGER REFERENCES app.con_raggruppamento_fatturazione(id),
	importo_mensile NUMERIC(18, 4) NOT NULL,
	tipo_fatturazione_id INTEGER NOT NULL REFERENCES app.con_tipo_fatturazione(id),
	data_cessazione DATE,
	note VARCHAR(4000)
);


CREATE TABLE app.con_tariffa
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	descrizione VARCHAR(255) NOT NULL,
	tipo_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_servizio(id),
	specifica_servizio_id INTEGER NOT NULL REFERENCES app.con_specifica_servizio(id),
	obiettivo_servizio_id INTEGER NOT NULL REFERENCES app.com_obiettivo_servizio(id),
	costo_orario NUMERIC(18, 4),
	numero_franchigie INTEGER,
	costo_fisso_una_tantum NUMERIC(18, 4),
	data_inizio_validita DATE NOT NULL,
	tipo_frazionamento_fatturazione_id INTEGER NOT NULL REFERENCES app.con_tipo_frazionamento_fatturazione(id),
	raggruppamento_fatturazione_id INTEGER REFERENCES app.con_raggruppamento_fatturazione(id),
	tipo_fatturazione_id INTEGER NOT NULL REFERENCES app.con_tipo_fatturazione(id),
	data_cessazione DATE,
	note VARCHAR(4000)
);


CREATE TABLE app.con_ordine_servizio
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	data_decorrenza DATE NOT NULL,
	data_termine DATE NOT NULL,
	obiettivo_servizio_id INTEGER NOT NULL REFERENCES app.com_obiettivo_servizio(id),
	tipo_ordine_servizio_id INTEGER NOT NULL REFERENCES app.con_tipo_ordine_servizio(id),
	numero INTEGER NOT NULL,
	orario_inizio TIME WITH TIME ZONE NOT NULL,
	orario_fine TIME WITH TIME ZONE NOT NULL,
	lunedi BOOLEAN,
	martedi BOOLEAN,
	mercoledi BOOLEAN,
	giovedi BOOLEAN,
	venerdi BOOLEAN,
	sabato BOOLEAN,
	domenica BOOLEAN,
	festivi BOOLEAN,
	note VARCHAR(4000)
);


CREATE TABLE app.con_apparecchiature_tecnologiche
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	tipo_apparecchiatura_id INTEGER NOT NULL REFERENCES app.con_tipo_apparecchiatura_tecnologica(id),
	comodato_uso BOOLEAN NOT NULL,
	data_installazione DATE,
	data_ritiro DATE,
	numero_telecamere INTEGER,
	numero_periferiche INTEGER,
	numero_sensori INTEGER,
	numero_centrali INTEGER,
	numero_telecomandi INTEGER,
	numero_tastiere INTEGER,
	numero_videoregistratori INTEGER,
	numero_datix INTEGER,
	numero_metal_detector INTEGER,
	note VARCHAR(4000)
);


CREATE TABLE app.con_documenti_contratti
(
	id SERIAL NOT NULL PRIMARY KEY,
	contratto_id INTEGER NOT NULL REFERENCES app.con_contratto(id),
	descrizione VARCHAR(255),
	filename VARCHAR(255),
	mime_type VARCHAR(255),
	data_caricamento TIMESTAMP WITH TIME ZONE,
	documento OID,
	note VARCHAR(4000)
);
