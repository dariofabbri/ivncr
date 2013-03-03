CREATE TABLE app.per_tipo_indirizzo
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_indirizzo (id, descrizione) VALUES (1, 'Residenza');
INSERT INTO app.per_tipo_indirizzo (id, descrizione) VALUES (2, 'Domicilio');
INSERT INTO app.per_tipo_indirizzo (id, descrizione) VALUES (3, 'Residenza (cessata)');
INSERT INTO app.per_tipo_indirizzo (id, descrizione) VALUES (4, 'Domicilio (cessata)');
SELECT setval('app.per_tipo_indirizzo_id_seq', (SELECT MAX(id) FROM app.per_tipo_indirizzo));



CREATE TABLE app.per_tipo_recapito_telefonico
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_recapito_telefonico (id, descrizione) VALUES (1, 'Cellulare personale');
INSERT INTO app.per_tipo_recapito_telefonico (id, descrizione) VALUES (2, 'Cellulare aziendale');
INSERT INTO app.per_tipo_recapito_telefonico (id, descrizione) VALUES (3, 'Telefono abitazione');
SELECT setval('app.per_tipo_recapito_telefonico_id_seq', (SELECT MAX(id) FROM app.per_tipo_recapito_telefonico));



CREATE TABLE app.per_ruolo
(
	id SERIAL NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_ruolo (id, descrizione) VALUES (1, 'Dirigente');
INSERT INTO app.per_ruolo (id, descrizione) VALUES (2, 'Portiere');
INSERT INTO app.per_ruolo (id, descrizione) VALUES (3, 'Operativo');
INSERT INTO app.per_ruolo (id, descrizione) VALUES (4, 'Amministrativo');
SELECT setval('app.per_ruolo_id_seq', (SELECT MAX(id) FROM app.per_ruolo));



CREATE TABLE app.per_sistema_lavoro
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (1, '5+1');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (2, '5+2');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (3, '5+2 Amministrativo');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (4, '5+2 Dirigenza');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (5, '6+1');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (6, '6+1+1');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (7, 'Commercio 5+2');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (8, 'Commercio 6+1');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (9, 'Commercio 6+2');
INSERT INTO app.per_sistema_lavoro (id, descrizione) VALUES (10, 'Part-time 5+2 4 ore amm.');
SELECT setval('app.per_sistema_lavoro_id_seq', (SELECT MAX(id) FROM app.per_sistema_lavoro));


CREATE TABLE app.per_tipo_contratto
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_contratto (id, descrizione) VALUES (1, 'Tempo determinato');
INSERT INTO app.per_tipo_contratto (id, descrizione) VALUES (2, 'Tempo indeterminato');
INSERT INTO app.per_tipo_contratto (id, descrizione) VALUES (3, 'Tempo indeterminato part-time');
INSERT INTO app.per_tipo_contratto (id, descrizione) VALUES (4, 'Apprendistato');
SELECT setval('app.per_tipo_contratto_id_seq', (SELECT MAX(id) FROM app.per_tipo_contratto));



CREATE TABLE app.per_ccnl
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_ccnl (id, descrizione) VALUES (1, 'Commercio');
INSERT INTO app.per_ccnl (id, descrizione) VALUES (2, 'Multiservizi');
INSERT INTO app.per_ccnl (id, descrizione) VALUES (3, 'Portierato');
INSERT INTO app.per_ccnl (id, descrizione) VALUES (4, 'Vigilanza');
SELECT setval('app.per_ccnl_id_seq', (SELECT MAX(id) FROM app.per_ccnl));



CREATE TABLE app.per_grado
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_grado (id, descrizione) VALUES (1, '1° Aviere');
INSERT INTO app.per_grado (id, descrizione) VALUES (2, 'Alpino');
INSERT INTO app.per_grado (id, descrizione) VALUES (3, 'Autiere');
INSERT INTO app.per_grado (id, descrizione) VALUES (4, 'Aviere');
INSERT INTO app.per_grado (id, descrizione) VALUES (5, 'Aviere scelto');
INSERT INTO app.per_grado (id, descrizione) VALUES (6, 'Caporal maggiore');
INSERT INTO app.per_grado (id, descrizione) VALUES (7, 'Caporale');
INSERT INTO app.per_grado (id, descrizione) VALUES (8, 'Carabiniere');
INSERT INTO app.per_grado (id, descrizione) VALUES (9, 'Carabiniere ausiliario');
INSERT INTO app.per_grado (id, descrizione) VALUES (10, 'Carrista');
INSERT INTO app.per_grado (id, descrizione) VALUES (11, 'Com. 1° classe');
INSERT INTO app.per_grado (id, descrizione) VALUES (12, 'Fante');
INSERT INTO app.per_grado (id, descrizione) VALUES (13, 'Lanciere');
INSERT INTO app.per_grado (id, descrizione) VALUES (14, 'Sergente');
INSERT INTO app.per_grado (id, descrizione) VALUES (15, 'Sergente maggiore');
INSERT INTO app.per_grado (id, descrizione) VALUES (16, 'Soldato semplice');
INSERT INTO app.per_grado (id, descrizione) VALUES (17, 'Sottocapo');
INSERT INTO app.per_grado (id, descrizione) VALUES (18, 'Tenente');
INSERT INTO app.per_grado (id, descrizione) VALUES (19, 'Vice sovrintendente');
SELECT setval('app.per_grado_id_seq', (SELECT MAX(id) FROM app.per_grado));



CREATE TABLE app.per_sigla_sindacale
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (1, 'AEPSGAC');
INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (2, 'FILCAMS CGIL');
INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (3, 'FISASCAT CISL');
INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (4, 'SAVIP');
INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (5, 'SINALV CISAL');
INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (6, 'UGL');
INSERT INTO app.per_sigla_sindacale (id, descrizione) VALUES (7, 'UIL TuCS UIL');
SELECT setval('app.per_sigla_sindacale_id_seq', (SELECT MAX(id) FROM app.per_sigla_sindacale));


CREATE TABLE app.per_carica_sindacale
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_carica_sindacale (id, descrizione) VALUES (1, 'Dirigente sindacale');
INSERT INTO app.per_carica_sindacale (id, descrizione) VALUES (2, 'Rappresentante sindacale');
INSERT INTO app.per_carica_sindacale (id, descrizione) VALUES (3, 'RSA');
INSERT INTO app.per_carica_sindacale (id, descrizione) VALUES (4, 'RSA e dirigente sindacale');
SELECT setval('app.per_carica_sindacale_id_seq', (SELECT MAX(id) FROM app.per_carica_sindacale));


CREATE TABLE app.per_gruppo_sanguigno
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (1, '0-');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (2, '0+');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (3, 'A-');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (4, 'A+');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (5, 'AB-');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (6, 'AB+');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (7, 'B-');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (8, 'B+');
INSERT INTO app.per_gruppo_sanguigno (id, descrizione) VALUES (9, 'Non riconosciuto');
SELECT setval('app.per_gruppo_sanguigno_id_seq', (SELECT MAX(id) FROM app.per_gruppo_sanguigno));


CREATE TABLE app.per_tipo_posizione_militare
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_posizione_militare (id, descrizione) VALUES (1, 'Assolto');
INSERT INTO app.per_tipo_posizione_militare (id, descrizione) VALUES (2, 'Dispensato');
INSERT INTO app.per_tipo_posizione_militare (id, descrizione) VALUES (3, 'Esonerato');
INSERT INTO app.per_tipo_posizione_militare (id, descrizione) VALUES (4, 'Non assolto');
INSERT INTO app.per_tipo_posizione_militare (id, descrizione) VALUES (5, 'Riformato');
SELECT setval('app.per_tipo_posizione_militare_id_seq', (SELECT MAX(id) FROM app.per_tipo_posizione_militare));


CREATE TABLE app.per_titolo_studio
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (1, 'Attestato qualifica professionale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (2, 'Attestato operatore macchine');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (3, 'Computista commerciale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (4, 'Diploma analista contabile');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (5, 'Diploma contabile aziendale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (6, 'Diploma di conservatorio');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (7, 'Diploma di elettrotecnico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (8, 'Diploma di qualifica addetto contabilità');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (9, 'Diploma di tecnico grafico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (10, 'Diploma meccanico macchine agricole');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (11, 'Diploma operatore turistico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (12, 'Diploma qualifica professionale alberghiera');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (13, 'Diploma qualifica fotolitografo');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (14, 'Diploma segretario amministrativo');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (15, 'Diploma tecnico gestione aziendale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (16, 'Diploma');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (17, 'Diploma alberghiero');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (18, 'Diploma assistente all''infanzia');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (19, 'Diploma di chimica');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (20, 'Diploma di geometra');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (21, 'Diploma di informatica');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (22, 'Diploma di perito per turismo');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (23, 'Diploma di radiologia medica');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (24, 'Diploma di ragioneria');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (25, 'Diploma esperto forest/alpicol');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (26, 'Diploma grafico pubblicitario');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (27, 'Diploma istituto tecnico professionale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (28, 'Diploma odontotecnico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (29, 'Diploma ottico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (30, 'Diploma perito aeronautico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (31, 'Diploma perito agrario');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (32, 'Diploma qualifica professionale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (33, 'Diploma tecnico laboratorio biologico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (34, 'Laurea in economia e commercio');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (35, 'Laurea in giurisprudenza');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (36, 'Laurea in architettura');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (37, 'Laurea in pedagogia');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (38, 'Laurea in ingegneria');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (39, 'Laurea in scienze musicali');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (40, 'Licenza elementare');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (41, 'Licenza media');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (42, 'Liceo linguistico ');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (43, 'Maturità alberghiera');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (44, 'Maturità artistica');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (45, 'Maturità classica');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (46, 'Maturità d''arte');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (47, 'Maturità industriale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (48, 'Maturità magistrale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (49, 'Maturità professionale tecnico della cinematografia e TV');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (50, 'Maturità professionale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (51, 'Maturità scientifica');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (52, 'Maturità tecnica edilizia');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (53, 'Nessuno');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (54, 'Perito aeronautico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (55, 'Perito agrario');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (56, 'Perito chimico clinico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (57, 'Perito elettronico e delle telecomunicazioni');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (58, 'Perito industriale capotecnico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (59, 'Perito industrale elettrotecnico e dell''automazione');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (60, 'Perito informatico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (61, 'Perito meccanico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (62, 'Perito nautico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (63, 'Perito tecnico commerciale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (64, 'Perito tecnico industriale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (65, 'Perito telecomunicazioni');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (66, 'Perito trasporto aereo');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (67, 'Qualifica professionale alberghiera');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (68, 'Qualifica professionale elettromeccanico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (69, 'Qualifica professionale elettrotecnico');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (70, 'Qualifica professionale sarta');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (71, 'Qualifica grafico pubblicitario');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (72, 'Ragioniere e perito giuridico economico aziendale');
INSERT INTO app.per_titolo_studio (id, descrizione) VALUES (73, 'Tecnico delle industrie elettroniche');
SELECT setval('app.per_titolo_studio_id_seq', (SELECT MAX(id) FROM app.per_titolo_studio));


CREATE TABLE app.per_grado_parentela
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_grado_parentela (id, descrizione) VALUES (1, 'Coniuge');
INSERT INTO app.per_grado_parentela (id, descrizione) VALUES (2, 'Convivente');
INSERT INTO app.per_grado_parentela (id, descrizione) VALUES (3, 'Ex-coniuge');
INSERT INTO app.per_grado_parentela (id, descrizione) VALUES (4, 'Familiare');
INSERT INTO app.per_grado_parentela (id, descrizione) VALUES (5, 'Figlio/a');
INSERT INTO app.per_grado_parentela (id, descrizione) VALUES (6, 'Figlio/a convivente');
SELECT setval('app.per_grado_parentela_id_seq', (SELECT MAX(id) FROM app.per_grado_parentela));


CREATE TABLE app.per_tipo_documento
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_documento (id, descrizione) VALUES (1, 'Carta di identità');
INSERT INTO app.per_tipo_documento (id, descrizione) VALUES (2, 'Libretto di lavoro');
INSERT INTO app.per_tipo_documento (id, descrizione) VALUES (3, 'Non riconosciuto');
INSERT INTO app.per_tipo_documento (id, descrizione) VALUES (4, 'Nulla osta');
INSERT INTO app.per_tipo_documento (id, descrizione) VALUES (5, 'Passaporto');
INSERT INTO app.per_tipo_documento (id, descrizione) VALUES (6, 'Patente');
SELECT setval('app.per_tipo_documento_id_seq', (SELECT MAX(id) FROM app.per_tipo_documento));


CREATE TABLE app.per_tipo_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (1, 'Carabina automatica');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (2, 'Carabina semiautomatica');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (3, 'Fucile');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (4, 'Fucile a pompa');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (5, 'Pistola');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (6, 'Pistola automatica');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (7, 'Pistola revolver');
INSERT INTO app.per_tipo_arma (id, descrizione) VALUES (8, 'Pistola semiautomatica');
SELECT setval('app.per_tipo_arma_id_seq', (SELECT MAX(id) FROM app.per_tipo_arma));


CREATE TABLE app.per_modello_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_modello_arma (id, descrizione) VALUES (1, 'Arminius');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (2, 'Astra');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (3, 'Benelli');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (4, 'Benelli F');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (5, 'Benelli Ultra');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (6, 'Benelli Para');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (7, 'Benelli Ultra F');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (8, 'Beretta');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (9, 'Beretta Winchester');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (10, 'Beretta P One');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (11, 'Beretta 70');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (12, 'Beretta 98 FS');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (13, 'Beretta 8000');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (14, 'Beretta 81');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (15, 'Beretta 82');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (16, 'Beretta 81 BB');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (17, 'Beretta 84 F');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (18, 'Beretta 84 FS');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (19, 'Beretta 9 SHORT');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (20, 'Beretta 952');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (21, 'Beretta 96 Centurion');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (22, 'Beretta 96 Brigadier');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (23, 'Beretta 96 FS');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (24, 'Beretta 98 FS');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (25, 'Beretta 98 FS Brigadier');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (26, 'Beretta PX4');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (27, 'Beretta PX4 Storm');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (28, 'Beretta 90 Two');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (29, 'Beretta 9000 S');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (30, 'Beretta 9000 SF');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (31, 'Beretta Cougar F');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (32, 'Bernardelli');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (33, 'Bernardelli P. One');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (34, 'Bernardelli P018');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (35, 'Bernardelli 4537');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (36, 'Bernardelli 4637');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (37, 'Bersa');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (38, 'Bessa');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (39, 'Bodeo');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (40, 'Browning');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (41, 'Browning High Power');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (42, 'Ceska Zibrogovka');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (43, 'Colt');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (44, 'Colt 45');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (45, 'Colt Mk IV');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (46, 'Colt Combat');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (47, 'Colt Cobra');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (48, 'Colt Detective');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (49, 'Colt Smith');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (50, 'Cz');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (51, 'Delven');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (52, 'Diamond');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (53, 'Diamond Back');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (54, 'EM-GE');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (55, 'Erma Lager');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (56, 'FABARM');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (57, 'Franchi');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (58, 'Franchi Colt');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (59, 'Franchi Llama');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (60, 'Franchi Pompa');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (61, 'Gamba');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (62, 'GGDSG');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (63, 'Glock');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (64, 'Glock 17');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (65, 'Glock 19');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (66, 'Glock 22');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (67, 'Glock 23');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (68, 'Glock 26');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (69, 'Glock 30');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (70, 'Heckler & Koch');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (71, 'Heckler & Koch USP');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (72, 'High Standard');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (73, 'Jager');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (74, 'Luger Franchi');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (75, 'MAB');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (76, 'Mauser');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (77, 'Mauser HSC');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (78, 'Moss Bergh');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (79, 'Walther');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (80, 'Walther PP9');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (81, 'Walther P99');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (82, 'Walther PP Super');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (83, 'Walther Waffenfabuk PP');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (84, 'Kassnar London');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (85, 'Luger');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (86, 'Paraordinance P14');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (87, 'Smith & Wesson');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (88, 'Smith & Wesson Revolver');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (89, 'Smith & Wesson S45');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (90, 'Smith & Wesson CS45');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (91, 'Tanfoglio');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (92, 'Tanfoglio Force');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (93, 'Tanfoglio Witness');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (94, 'Tanfoglio GT21');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (95, 'Tanfoglio T95');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (96, 'Tanfoglio T95 F Combat');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (97, 'Tanfoglio The Ultra');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (98, 'Tanfoglio GT');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (99, 'Taurus');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (100, 'Python');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (101, 'Reck');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (102, 'Renato Gamba');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (103, 'Ruger');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (104, 'Rossi');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (105, 'Ruger Magnum');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (106, 'RWS Dynamite');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (107, 'Saver');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (108, 'Sharp');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (109, 'Sig Sauer');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (110, 'Sig Sauer P230SL');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (111, 'Sig Sauer P228');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (112, 'Sig Sauer SP2022');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (113, 'Star');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (114, 'Steyr');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (115, 'Targa');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (116, 'Tiger');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (117, 'Webley & Scott');
INSERT INTO app.per_modello_arma (id, descrizione) VALUES (118, 'Weihrauch');
SELECT setval('app.per_modello_arma_id_seq', (SELECT MAX(id) FROM app.per_modello_arma));


CREATE TABLE app.per_calibro_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

--INSERT INTO app.per_calibro_arma (id, descrizione) VALUES (, '');
--SELECT setval('app.per_calibro_arma_id_seq', (SELECT MAX(id) FROM app.per_calibro_arma));


CREATE TABLE app.per_stato_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_stato_arma (id, descrizione) VALUES (1, 'Ceduta');
INSERT INTO app.per_stato_arma (id, descrizione) VALUES (2, 'Da verificare');
INSERT INTO app.per_stato_arma (id, descrizione) VALUES (3, 'In possesso');
INSERT INTO app.per_stato_arma (id, descrizione) VALUES (4, 'Rapinata');
INSERT INTO app.per_stato_arma (id, descrizione) VALUES (5, 'Rubata');
INSERT INTO app.per_stato_arma (id, descrizione) VALUES (6, 'Sequestrata');
SELECT setval('app.per_stato_arma_id_seq', (SELECT MAX(id) FROM app.per_stato_arma));


CREATE TABLE app.per_tipo_certificato
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (1, 'Donazione sangue');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (2, 'Infortunio');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (3, 'Malattia');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (4, 'Maternità inizio');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (5, 'Maternità fine');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (6, 'Non riconosciuto');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (7, 'Ricovero ospedaliero inizio');
INSERT INTO app.per_tipo_certificato (id, descrizione) VALUES (8, 'Ricovero ospedaliero fine');
SELECT setval('app.per_tipo_certificato_id_seq', (SELECT MAX(id) FROM app.per_tipo_certificato));


CREATE TABLE app.per_lingua
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_lingua (id, descrizione) VALUES (1, 'Francese');
INSERT INTO app.per_lingua (id, descrizione) VALUES (2, 'Inglese');
INSERT INTO app.per_lingua (id, descrizione) VALUES (3, 'Spagnolo');
INSERT INTO app.per_lingua (id, descrizione) VALUES (4, 'Tedesco');
INSERT INTO app.per_lingua (id, descrizione) VALUES (5, 'Altro');
SELECT setval('app.per_lingua_id_seq', (SELECT MAX(id) FROM app.per_lingua));


CREATE TABLE app.per_livello_lingua
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_livello_lingua (id, descrizione) VALUES (1, 'Sufficiente');
INSERT INTO app.per_livello_lingua (id, descrizione) VALUES (2, 'Buono');
INSERT INTO app.per_livello_lingua (id, descrizione) VALUES (3, 'Ottimo');
SELECT setval('app.per_livello_lingua_id_seq', (SELECT MAX(id) FROM app.per_livello_lingua));


CREATE TABLE app.per_stato_civile
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO app.per_stato_civile (id, descrizione) VALUES (1, 'Celibe');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (2, 'Coniugato/a');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (3, 'Convivente');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (4, 'Divorziato/a');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (5, 'Nessuno');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (6, 'Nubile');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (7, 'Risposato/a');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (8, 'Separato/a');
INSERT INTO app.per_stato_civile (id, descrizione) VALUES (9, 'Vedovo/a');
SELECT setval('app.per_stato_civile_id_seq', (SELECT MAX(id) FROM app.per_stato_civile));



CREATE TABLE app.per_addetto
(
  id SERIAL NOT NULL PRIMARY KEY,
  azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
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
  stato_civile_id INTEGER NOT NULL REFERENCES app.per_stato_civile(id),
  creazione_ts TIMESTAMP WITH TIME ZONE,
  ultima_modifica_ts TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.per_reparto
(
  id SERIAL NOT NULL PRIMARY KEY,
  azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
  descrizione VARCHAR(255) NOT NULL
);


CREATE TABLE app.per_addetto_sistema_lavoro
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  sistema_lavoro_id INTEGER NOT NULL REFERENCES app.per_sistema_lavoro(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.per_addetto_reparto
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  reparto_id INTEGER NOT NULL REFERENCES app.per_reparto(id),
  ruolo_id INTEGER NOT NULL REFERENCES app.per_ruolo(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.per_posizione_lavorativa
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_contratto_id INTEGER NOT NULL REFERENCES app.per_tipo_contratto(id),
  ccnl_id INTEGER NOT NULL REFERENCES app.per_ccnl(id),
  azienda_id INTEGER NOT NULL REFERENCES app.gen_azienda(id),
  durata_contratto INTEGER,
  data_assunzione DATE,
  durata_prova INTEGER,
  data_primo_giorno DATE,
  data_fine_prova DATE,
  data_cessazione DATE,
  data_fine_contratto DATE,
  motivo_dimissioni VARCHAR(4000)
);


CREATE TABLE app.per_info_sindacali
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  sigla_sindacale_id INTEGER NOT NULL REFERENCES app.per_sigla_sindacale(id),
  carica_sindacale_id INTEGER NOT NULL REFERENCES app.per_carica_sindacale(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.per_carriera
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  qualifica_id INTEGER NOT NULL REFERENCES app.per_qualifica(id),
  livello_ccnl_id INTEGER NOT NULL REFERENCES app.per_livello_ccnl(id),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);



CREATE TABLE app.per_indirizzo
(
  id serial NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_indirizzo_id INTEGER NOT NULL REFERENCES app.per_tipo_indirizzo(id),
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


CREATE TABLE app.per_recapito_telefonico
(
  id serial NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_recapito_telefonico_id INTEGER NOT NULL REFERENCES app.per_tipo_recapito_telefonico(id),
  recapito VARCHAR(255) NOT NULL
);


CREATE TABLE app.per_recapito_email
(
  id serial NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  email VARCHAR(255) NOT NULL
);


CREATE TABLE app.per_posizione_militare
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_posizione_militare_id INTEGER REFERENCES app.per_tipo_posizione_militare(id),
  presso VARCHAR(255),
  grado_id INTEGER REFERENCES app.per_grado(id),
  data_congedo DATE,
  note VARCHAR(4000)
);


CREATE TABLE app.per_istruzione
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  titolo_studio_id INTEGER REFERENCES app.per_titolo_studio(id),
  data_conseguimento DATE,
  presso VARCHAR(255)
);


CREATE TABLE app.per_stato_famiglia
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  nome VARCHAR(255) NOT NULL,
  grado_parentela_id INTEGER REFERENCES app.per_grado_parentela(id),
  data_nascita DATE,
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.per_dati_sanitari
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  medico_base VARCHAR(255),
  gruppo_sanguigno_id INTEGER REFERENCES app.per_gruppo_sanguigno(id),
  asl VARCHAR(255),
  indirizzo_asl VARCHAR(255),
  comune VARCHAR(255),
  provincia VARCHAR(255),
  valido_da TIMESTAMP WITH TIME ZONE,
  valido_a TIMESTAMP WITH TIME ZONE
);


CREATE TABLE app.per_corso
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  ente VARCHAR(255),
  abilitazione VARCHAR(255),
  ore_corso INTEGER,
  valutazione VARCHAR(255),
  data_conseguimento DATE,
  data_inizio DATE,
  data_fine DATE,
  note VARCHAR(4000)
);


CREATE TABLE app.per_documento
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_documento_id INTEGER REFERENCES app.per_tipo_documento(id),
  numero VARCHAR(255),
  rilasciato_da VARCHAR(255),
  data_rilascio DATE,
  data_scadenza DATE
);


CREATE TABLE app.per_armamento
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_arma_id INTEGER REFERENCES app.per_tipo_arma(id),
  modello_arma VARCHAR(255),
  calibro_arma VARCHAR(255),
  matricola VARCHAR(255),
  stato_arma_id INTEGER REFERENCES app.per_stato_arma(id),
  data_denuncia DATE,
  data_inizio DATE,
  data_fine DATE,
  note VARCHAR(4000)
);


CREATE TABLE app.per_libretto_porto_armi
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  numero VARCHAR(255) NOT NULL,
  fucile BOOLEAN,
  data_rilascio DATE,
  data_scadenza DATE,
  note VARCHAR(4000)
);


CREATE TABLE app.per_licenza_porto_armi
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  libretto_id INTEGER NOT NULL REFERENCES app.per_libretto_porto_armi(id),
  numero VARCHAR(255) NOT NULL,
  data_rilascio DATE,
  data_scadenza DATE,
  note VARCHAR(4000)
);


CREATE TABLE app.per_decreto_gpg
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  numero VARCHAR(255) NOT NULL,
  data_rilascio DATE,
  data_scadenza DATE,
  note VARCHAR(4000)
);


CREATE TABLE app.per_malattia
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  tipo_certificato_id INTEGER NOT NULL REFERENCES app.per_tipo_certificato(id),
  data_certificato DATE,
  data_ricezione DATE,
  data_inizio_validita DATE,
  data_fine_validita DATE,  
  note VARCHAR(4000)
);


CREATE TABLE app.per_estensione_decreto
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  provincia VARCHAR(255),
  data_inizio_validita DATE,
  data_fine_validita DATE,  
  note VARCHAR(4000)
);


CREATE TABLE app.per_lingua_conosciuta
(
  id SERIAL NOT NULL PRIMARY KEY,
  addetto_id INTEGER NOT NULL REFERENCES app.per_addetto(id),
  lingua_id INTEGER NOT NULL REFERENCES app.per_lingua(id),
  livello_lingua_id INTEGER NOT NULL REFERENCES app.per_livello_lingua(id),
  note VARCHAR(4000)
);
