CREATE TABLE per_tipo_indirizzo
(
	id INTEGER NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (1, 'Residenza');
INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (2, 'Domicilio');
INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (3, 'Residenza (cessata)');
INSERT INTO per_tipo_indirizzo (id, descrizione) VALUES (4, 'Domicilio (cessata)');



CREATE TABLE per_tipo_recapito_telefonico
(
	id INTEGER NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_recapito_telefonico (id, descrizione) VALUES (1, 'Cellulare personale');
INSERT INTO per_tipo_recapito_telefonico (id, descrizione) VALUES (2, 'Cellulare aziendale');
INSERT INTO per_tipo_recapito_telefonico (id, descrizione) VALUES (3, 'Telefono abitazione');



CREATE TABLE per_ruolo
(
	id INTEGER NOT NULL PRIMARY KEY,
	descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_ruolo (id, descrizione) VALUES (1, 'Dirigente');
INSERT INTO per_ruolo (id, descrizione) VALUES (2, 'Portiere');
INSERT INTO per_ruolo (id, descrizione) VALUES (3, 'Operativo');
INSERT INTO per_ruolo (id, descrizione) VALUES (4, 'Amministrativo');



CREATE TABLE per_sistema_lavoro
(
  id INTEGER NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (1, '5+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (2, '5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (3, '5+2 Amministrativo');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (4, '5+2 Dirigenza');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (5, '6+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (6, '6+1+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (7, 'Commercio 5+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (8, 'Commercio 6+1');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (9, 'Commercio 6+2');
INSERT INTO per_sistema_lavoro (id, descrizione) VALUES (10, 'Part-time 5+2 4 ore amm.');


CREATE TABLE per_tipo_contratto
(
  id INTEGER NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_contratto (id, descrizione) VALUES (1, 'Tempo determinato');
INSERT INTO per_tipo_contratto (id, descrizione) VALUES (2, 'Tempo indeterminato');
INSERT INTO per_tipo_contratto (id, descrizione) VALUES (3, 'Tempo indeterminato part-time');
INSERT INTO per_tipo_contratto (id, descrizione) VALUES (4, 'Apprendistato');



CREATE TABLE per_ccnl
(
  id INTEGER NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_ccnl (id, descrizione) VALUES (1, 'Commercio');
INSERT INTO per_ccnl (id, descrizione) VALUES (2, 'Multiservizi');
INSERT INTO per_ccnl (id, descrizione) VALUES (3, 'Portierato');
INSERT INTO per_ccnl (id, descrizione) VALUES (4, 'Vigilanza');



CREATE TABLE per_grado
(
  id INTEGER NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_grado (id, descrizione) VALUES (1, '1° Aviere');
INSERT INTO per_grado (id, descrizione) VALUES (2, 'Alpino');
INSERT INTO per_grado (id, descrizione) VALUES (3, 'Autiere');
INSERT INTO per_grado (id, descrizione) VALUES (4, 'Aviere');
INSERT INTO per_grado (id, descrizione) VALUES (5, 'Aviere scelto');
INSERT INTO per_grado (id, descrizione) VALUES (6, 'Caporal maggiore');
INSERT INTO per_grado (id, descrizione) VALUES (7, 'Caporale');
INSERT INTO per_grado (id, descrizione) VALUES (8, 'Carabiniere');
INSERT INTO per_grado (id, descrizione) VALUES (9, 'Carabiniere ausiliario');
INSERT INTO per_grado (id, descrizione) VALUES (10, 'Carrista');
INSERT INTO per_grado (id, descrizione) VALUES (11, 'Com. 1° classe');
INSERT INTO per_grado (id, descrizione) VALUES (12, 'Fante');
INSERT INTO per_grado (id, descrizione) VALUES (13, 'Lanciere');
INSERT INTO per_grado (id, descrizione) VALUES (14, 'Sergente');
INSERT INTO per_grado (id, descrizione) VALUES (15, 'Sergente maggiore');
INSERT INTO per_grado (id, descrizione) VALUES (16, 'Soldato semplice');
INSERT INTO per_grado (id, descrizione) VALUES (17, 'Sottocapo');
INSERT INTO per_grado (id, descrizione) VALUES (18, 'Tenente');
INSERT INTO per_grado (id, descrizione) VALUES (19, 'Vice sovrintendente');



CREATE TABLE per_sigla_sindacale
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (1, 'AEPSGAC');
INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (2, 'FILCAMS CGIL');
INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (3, 'FISASCAT CISL');
INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (4, 'SAVIP');
INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (5, 'SINALV CISAL');
INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (6, 'UGL');
INSERT INTO per_sigla_sindacale (id, descrizione) VALUES (7, 'UIL TuCS UIL');


CREATE TABLE per_carica_sindacale
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_carica_sindacale (id, descrizione) VALUES (1, 'Dirigente sindacale');
INSERT INTO per_carica_sindacale (id, descrizione) VALUES (2, 'Rappresentante sindacale');
INSERT INTO per_carica_sindacale (id, descrizione) VALUES (3, 'RSA');
INSERT INTO per_carica_sindacale (id, descrizione) VALUES (4, 'RSA e dirigente sindacale');


CREATE TABLE per_gruppo_sanguigno
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (1, '0-');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (2, '0+');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (3, 'A-');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (4, 'A+');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (5, 'AB-');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (6, 'AB+');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (7, 'B-');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (8, 'B+');
INSERT INTO per_gruppo_sanguigno (id, descrizione) VALUES (9, 'Non riconosciuto');


CREATE TABLE per_tipo_posizione_militare
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_posizione_militare (id, descrizione) VALUES (1, 'Assolto');
INSERT INTO per_tipo_posizione_militare (id, descrizione) VALUES (2, 'Dispensato');
INSERT INTO per_tipo_posizione_militare (id, descrizione) VALUES (3, 'Esonerato');
INSERT INTO per_tipo_posizione_militare (id, descrizione) VALUES (4, 'Non assolto');
INSERT INTO per_tipo_posizione_militare (id, descrizione) VALUES (5, 'Riformato');


CREATE TABLE per_titolo_studio
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_titolo_studio (id, descrizione) VALUES (1, 'Attestato qualifica professionale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (2, 'Attestato operatore macchine');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (3, 'Computista commerciale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (4, 'Diploma analista contabile');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (5, 'Diploma contabile aziendale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (6, 'Diploma di conservatorio');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (7, 'Diploma di elettrotecnico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (8, 'Diploma di qualifica addetto contabilità');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (9, 'Diploma di tecnico grafico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (10, 'Diploma meccanico macchine agricole');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (11, 'Diploma operatore turistico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (12, 'Diploma qualifica professionale alberghiera');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (13, 'Diploma qualifica fotolitografo');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (14, 'Diploma segretario amministrativo');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (15, 'Diploma tecnico gestione aziendale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (16, 'Diploma');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (17, 'Diploma alberghiero');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (18, 'Diploma assistente all''infanzia');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (19, 'Diploma di chimica');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (20, 'Diploma di geometra');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (21, 'Diploma di informatica');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (22, 'Diploma di perito per turismo');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (23, 'Diploma di radiologia medica');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (24, 'Diploma di ragioneria');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (25, 'Diploma esperto forest/alpicol');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (26, 'Diploma grafico pubblicitario');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (27, 'Diploma istituto tecnico professionale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (28, 'Diploma odontotecnico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (29, 'Diploma ottico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (30, 'Diploma perito aeronautico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (31, 'Diploma perito agrario');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (32, 'Diploma qualifica professionale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (33, 'Diploma tecnico laboratorio biologico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (34, 'Laurea in economia e commercio');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (35, 'Laurea in giurisprudenza');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (36, 'Laurea in architettura');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (37, 'Laurea in pedagogia');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (38, 'Laurea in ingegneria');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (39, 'Laurea in scienze musicali');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (40, 'Licenza elementare');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (41, 'Licenza media');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (42, 'Liceo linguistico ');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (43, 'Maturità alberghiera');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (44, 'Maturità artistica');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (45, 'Maturità classica');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (46, 'Maturità d''arte');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (47, 'Maturità industriale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (48, 'Maturità magistrale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (49, 'Maturità professionale tecnico della cinematografia e TV');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (50, 'Maturità professionale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (51, 'Maturità scientifica');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (52, 'Maturità tecnica edilizia');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (53, 'Nessuno');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (54, 'Perito aeronautico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (55, 'Perito agrario');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (56, 'Perito chimico clinico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (57, 'Perito elettronico e delle telecomunicazioni');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (58, 'Perito industriale capotecnico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (59, 'Perito industrale elettrotecnico e dell''automazione');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (60, 'Perito informatico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (61, 'Perito meccanico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (62, 'Perito nautico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (63, 'Perito tecnico commerciale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (64, 'Perito tecnico industriale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (65, 'Perito telecomunicazioni');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (66, 'Perito trasporto aereo');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (67, 'Qualifica professionale alberghiera');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (68, 'Qualifica professionale elettromeccanico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (69, 'Qualifica professionale elettrotecnico');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (70, 'Qualifica professionale sarta');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (71, 'Qualifica grafico pubblicitario');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (72, 'Ragioniere e perito giuridico economico aziendale');
INSERT INTO per_titolo_studio (id, descrizione) VALUES (73, 'Tecnico delle industrie elettroniche');


CREATE TABLE per_grado_parentela
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_grado_parentela (id, descrizione) VALUES (1, 'Coniuge');
INSERT INTO per_grado_parentela (id, descrizione) VALUES (2, 'Convivente');
INSERT INTO per_grado_parentela (id, descrizione) VALUES (3, 'Ex-coniuge');
INSERT INTO per_grado_parentela (id, descrizione) VALUES (4, 'Familiare');
INSERT INTO per_grado_parentela (id, descrizione) VALUES (5, 'Figlio/a');
INSERT INTO per_grado_parentela (id, descrizione) VALUES (6, 'Figlio/a convivente');


CREATE TABLE per_tipo_documento
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_documento (id, descrizione) VALUES (1, 'Carta di identità');
INSERT INTO per_tipo_documento (id, descrizione) VALUES (2, 'Libretto di lavoro');
INSERT INTO per_tipo_documento (id, descrizione) VALUES (3, 'Non riconosciuto');
INSERT INTO per_tipo_documento (id, descrizione) VALUES (4, 'Nulla osta');
INSERT INTO per_tipo_documento (id, descrizione) VALUES (5, 'Passaporto');
INSERT INTO per_tipo_documento (id, descrizione) VALUES (6, 'Patente');


CREATE TABLE per_tipo_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_arma (id, descrizione) VALUES (1, 'Carabina automatica');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (2, 'Carabina semiautomatica');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (3, 'Fucile');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (4, 'Fucile a pompa');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (5, 'Pistola');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (6, 'Pistola automatica');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (7, 'Pistola revolver');
INSERT INTO per_tipo_arma (id, descrizione) VALUES (8, 'Pistola semiautomatica');


CREATE TABLE per_modello_arma
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_modello_arma (id, descrizione) VALUES (1, 'Arminius');
INSERT INTO per_modello_arma (id, descrizione) VALUES (2, 'Astra');
INSERT INTO per_modello_arma (id, descrizione) VALUES (3, 'Benelli');
INSERT INTO per_modello_arma (id, descrizione) VALUES (4, 'Benelli F');
INSERT INTO per_modello_arma (id, descrizione) VALUES (5, 'Benelli Ultra');
INSERT INTO per_modello_arma (id, descrizione) VALUES (6, 'Benelli Para');
INSERT INTO per_modello_arma (id, descrizione) VALUES (7, 'Benelli Ultra F');
INSERT INTO per_modello_arma (id, descrizione) VALUES (8, 'Beretta');
INSERT INTO per_modello_arma (id, descrizione) VALUES (9, 'Beretta Winchester');
INSERT INTO per_modello_arma (id, descrizione) VALUES (10, 'Beretta P One');
INSERT INTO per_modello_arma (id, descrizione) VALUES (11, 'Beretta 70');
INSERT INTO per_modello_arma (id, descrizione) VALUES (12, 'Beretta 98 FS');
INSERT INTO per_modello_arma (id, descrizione) VALUES (13, 'Beretta 8000');
INSERT INTO per_modello_arma (id, descrizione) VALUES (14, 'Beretta 81');
INSERT INTO per_modello_arma (id, descrizione) VALUES (15, 'Beretta 82');
INSERT INTO per_modello_arma (id, descrizione) VALUES (16, 'Beretta 81 BB');
INSERT INTO per_modello_arma (id, descrizione) VALUES (17, 'Beretta 84 F');
INSERT INTO per_modello_arma (id, descrizione) VALUES (18, 'Beretta 84 FS');
INSERT INTO per_modello_arma (id, descrizione) VALUES (19, 'Beretta 9 SHORT');
INSERT INTO per_modello_arma (id, descrizione) VALUES (20, 'Beretta 952');
INSERT INTO per_modello_arma (id, descrizione) VALUES (21, 'Beretta 96 Centurion');
INSERT INTO per_modello_arma (id, descrizione) VALUES (22, 'Beretta 96 Brigadier');
INSERT INTO per_modello_arma (id, descrizione) VALUES (23, 'Beretta 96 FS');
INSERT INTO per_modello_arma (id, descrizione) VALUES (24, 'Beretta 98 FS');
INSERT INTO per_modello_arma (id, descrizione) VALUES (25, 'Beretta 98 FS Brigadier');
INSERT INTO per_modello_arma (id, descrizione) VALUES (26, 'Beretta PX4');
INSERT INTO per_modello_arma (id, descrizione) VALUES (27, 'Beretta PX4 Storm');
INSERT INTO per_modello_arma (id, descrizione) VALUES (28, 'Beretta 90 Two');
INSERT INTO per_modello_arma (id, descrizione) VALUES (29, 'Beretta 9000 S');
INSERT INTO per_modello_arma (id, descrizione) VALUES (30, 'Beretta 9000 SF');
INSERT INTO per_modello_arma (id, descrizione) VALUES (31, 'Beretta Cougar F');
INSERT INTO per_modello_arma (id, descrizione) VALUES (32, 'Bernardelli');
INSERT INTO per_modello_arma (id, descrizione) VALUES (33, 'Bernardelli P. One');
INSERT INTO per_modello_arma (id, descrizione) VALUES (34, 'Bernardelli P018');
INSERT INTO per_modello_arma (id, descrizione) VALUES (35, 'Bernardelli 4537');
INSERT INTO per_modello_arma (id, descrizione) VALUES (36, 'Bernardelli 4637');
INSERT INTO per_modello_arma (id, descrizione) VALUES (37, 'Bersa');
INSERT INTO per_modello_arma (id, descrizione) VALUES (38, 'Bessa');
INSERT INTO per_modello_arma (id, descrizione) VALUES (39, 'Bodeo');
INSERT INTO per_modello_arma (id, descrizione) VALUES (40, 'Browning');
INSERT INTO per_modello_arma (id, descrizione) VALUES (41, 'Browning High Power');
INSERT INTO per_modello_arma (id, descrizione) VALUES (42, 'Ceska Zibrogovka');
INSERT INTO per_modello_arma (id, descrizione) VALUES (43, 'Colt');
INSERT INTO per_modello_arma (id, descrizione) VALUES (44, 'Colt 45');
INSERT INTO per_modello_arma (id, descrizione) VALUES (45, 'Colt Mk IV');
INSERT INTO per_modello_arma (id, descrizione) VALUES (46, 'Colt Combat');
INSERT INTO per_modello_arma (id, descrizione) VALUES (47, 'Colt Cobra');
INSERT INTO per_modello_arma (id, descrizione) VALUES (48, 'Colt Detective');
INSERT INTO per_modello_arma (id, descrizione) VALUES (49, 'Colt Smith');
INSERT INTO per_modello_arma (id, descrizione) VALUES (50, 'Cz');
INSERT INTO per_modello_arma (id, descrizione) VALUES (51, 'Delven');
INSERT INTO per_modello_arma (id, descrizione) VALUES (52, 'Diamond');
INSERT INTO per_modello_arma (id, descrizione) VALUES (53, 'Diamond Back');
INSERT INTO per_modello_arma (id, descrizione) VALUES (54, 'EM-GE');
INSERT INTO per_modello_arma (id, descrizione) VALUES (55, 'Erma Lager');
INSERT INTO per_modello_arma (id, descrizione) VALUES (56, 'FABARM');
INSERT INTO per_modello_arma (id, descrizione) VALUES (57, 'Franchi');
INSERT INTO per_modello_arma (id, descrizione) VALUES (58, 'Franchi Colt');
INSERT INTO per_modello_arma (id, descrizione) VALUES (59, 'Franchi Llama');
INSERT INTO per_modello_arma (id, descrizione) VALUES (60, 'Franchi Pompa');
INSERT INTO per_modello_arma (id, descrizione) VALUES (61, 'Gamba');
INSERT INTO per_modello_arma (id, descrizione) VALUES (62, 'GGDSG');
INSERT INTO per_modello_arma (id, descrizione) VALUES (63, 'Glock');
INSERT INTO per_modello_arma (id, descrizione) VALUES (64, 'Glock 17');
INSERT INTO per_modello_arma (id, descrizione) VALUES (65, 'Glock 19');
INSERT INTO per_modello_arma (id, descrizione) VALUES (66, 'Glock 22');
INSERT INTO per_modello_arma (id, descrizione) VALUES (67, 'Glock 23');
INSERT INTO per_modello_arma (id, descrizione) VALUES (68, 'Glock 26');
INSERT INTO per_modello_arma (id, descrizione) VALUES (69, 'Glock 30');
INSERT INTO per_modello_arma (id, descrizione) VALUES (70, 'Heckler & Koch');
INSERT INTO per_modello_arma (id, descrizione) VALUES (71, 'Heckler & Koch USP');
INSERT INTO per_modello_arma (id, descrizione) VALUES (72, 'High Standard');
INSERT INTO per_modello_arma (id, descrizione) VALUES (73, 'Jager');
INSERT INTO per_modello_arma (id, descrizione) VALUES (74, 'Luger Franchi');
INSERT INTO per_modello_arma (id, descrizione) VALUES (75, 'MAB');
INSERT INTO per_modello_arma (id, descrizione) VALUES (76, 'Mauser');
INSERT INTO per_modello_arma (id, descrizione) VALUES (77, 'Mauser HSC');
INSERT INTO per_modello_arma (id, descrizione) VALUES (78, 'Moss Bergh');
INSERT INTO per_modello_arma (id, descrizione) VALUES (79, 'Walther');
INSERT INTO per_modello_arma (id, descrizione) VALUES (80, 'Walther PP9');
INSERT INTO per_modello_arma (id, descrizione) VALUES (81, 'Walther P99');
INSERT INTO per_modello_arma (id, descrizione) VALUES (82, 'Walther PP Super');
INSERT INTO per_modello_arma (id, descrizione) VALUES (83, 'Walther Waffenfabuk PP');
INSERT INTO per_modello_arma (id, descrizione) VALUES (84, 'Kassnar London');
INSERT INTO per_modello_arma (id, descrizione) VALUES (85, 'Luger');
INSERT INTO per_modello_arma (id, descrizione) VALUES (86, 'Paraordinance P14');
INSERT INTO per_modello_arma (id, descrizione) VALUES (87, 'Smith & Wesson');
INSERT INTO per_modello_arma (id, descrizione) VALUES (88, 'Smith & Wesson Revolver');
INSERT INTO per_modello_arma (id, descrizione) VALUES (89, 'Smith & Wesson S45');
INSERT INTO per_modello_arma (id, descrizione) VALUES (90, 'Smith & Wesson CS45');
INSERT INTO per_modello_arma (id, descrizione) VALUES (91, 'Tanfoglio');
INSERT INTO per_modello_arma (id, descrizione) VALUES (92, 'Tanfoglio Force');
INSERT INTO per_modello_arma (id, descrizione) VALUES (93, 'Tanfoglio Witness');
INSERT INTO per_modello_arma (id, descrizione) VALUES (94, 'Tanfoglio GT21');
INSERT INTO per_modello_arma (id, descrizione) VALUES (95, 'Tanfoglio T95');
INSERT INTO per_modello_arma (id, descrizione) VALUES (96, 'Tanfoglio T95 F Combat');
INSERT INTO per_modello_arma (id, descrizione) VALUES (97, 'Tanfoglio The Ultra');
INSERT INTO per_modello_arma (id, descrizione) VALUES (98, 'Tanfoglio GT');
INSERT INTO per_modello_arma (id, descrizione) VALUES (99, 'Taurus');
INSERT INTO per_modello_arma (id, descrizione) VALUES (100, 'Python');
INSERT INTO per_modello_arma (id, descrizione) VALUES (101, 'Reck');
INSERT INTO per_modello_arma (id, descrizione) VALUES (102, 'Renato Gamba');
INSERT INTO per_modello_arma (id, descrizione) VALUES (103, 'Ruger');
INSERT INTO per_modello_arma (id, descrizione) VALUES (104, 'Rossi');
INSERT INTO per_modello_arma (id, descrizione) VALUES (105, 'Ruger Magnum');
INSERT INTO per_modello_arma (id, descrizione) VALUES (106, 'RWS Dynamite');
INSERT INTO per_modello_arma (id, descrizione) VALUES (107, 'Saver');
INSERT INTO per_modello_arma (id, descrizione) VALUES (108, 'Sharp');
INSERT INTO per_modello_arma (id, descrizione) VALUES (109, 'Sig Sauer');
INSERT INTO per_modello_arma (id, descrizione) VALUES (110, 'Sig Sauer P230SL');
INSERT INTO per_modello_arma (id, descrizione) VALUES (111, 'Sig Sauer P228');
INSERT INTO per_modello_arma (id, descrizione) VALUES (112, 'Sig Sauer SP2022');
INSERT INTO per_modello_arma (id, descrizione) VALUES (113, 'Star');
INSERT INTO per_modello_arma (id, descrizione) VALUES (114, 'Steyr');
INSERT INTO per_modello_arma (id, descrizione) VALUES (115, 'Targa');
INSERT INTO per_modello_arma (id, descrizione) VALUES (116, 'Tiger');
INSERT INTO per_modello_arma (id, descrizione) VALUES (117, 'Webley & Scott');
INSERT INTO per_modello_arma (id, descrizione) VALUES (118, 'Weihrauch');


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

INSERT INTO per_stato_arma (id, descrizione) VALUES (1, 'Ceduta');
INSERT INTO per_stato_arma (id, descrizione) VALUES (2, 'Da verificare');
INSERT INTO per_stato_arma (id, descrizione) VALUES (3, 'In possesso');
INSERT INTO per_stato_arma (id, descrizione) VALUES (4, 'Rapinata');
INSERT INTO per_stato_arma (id, descrizione) VALUES (5, 'Rubata');
INSERT INTO per_stato_arma (id, descrizione) VALUES (6, 'Sequestrata');


CREATE TABLE per_tipo_certificato
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_tipo_certificato (id, descrizione) VALUES (1, 'Donazione sangue');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (2, 'Infortunio');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (3, 'Malattia');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (4, 'Maternità inizio');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (5, 'Maternità fine');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (6, 'Non riconosciuto');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (7, 'Ricovero ospedaliero inizio');
INSERT INTO per_tipo_certificato (id, descrizione) VALUES (8, 'Ricovero ospedaliero fine');


CREATE TABLE per_lingua
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_lingua (id, descrizione) VALUES (1, 'Francese');
INSERT INTO per_lingua (id, descrizione) VALUES (2, 'Inglese');
INSERT INTO per_lingua (id, descrizione) VALUES (3, 'Spagnolo');
INSERT INTO per_lingua (id, descrizione) VALUES (4, 'Tedesco');
INSERT INTO per_lingua (id, descrizione) VALUES (5, 'Altro');


CREATE TABLE per_livello_lingua
(
  id SERIAL NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_livello_lingua (id, descrizione) VALUES (1, 'Sufficiente');
INSERT INTO per_livello_lingua (id, descrizione) VALUES (2, 'Buono');
INSERT INTO per_livello_lingua (id, descrizione) VALUES (3, 'Ottimo');


CREATE TABLE per_stato_civile
(
  id INTEGER NOT NULL PRIMARY KEY,
  descrizione VARCHAR(255) NOT NULL
);

INSERT INTO per_stato_civile (id, descrizione) VALUES (1, 'Celibe');
INSERT INTO per_stato_civile (id, descrizione) VALUES (2, 'Coniugato/a');
INSERT INTO per_stato_civile (id, descrizione) VALUES (3, 'Convivente');
INSERT INTO per_stato_civile (id, descrizione) VALUES (4, 'Divorziato/a');
INSERT INTO per_stato_civile (id, descrizione) VALUES (5, 'Nessuno');
INSERT INTO per_stato_civile (id, descrizione) VALUES (6, 'Nubile');
INSERT INTO per_stato_civile (id, descrizione) VALUES (7, 'Risposato/a');
INSERT INTO per_stato_civile (id, descrizione) VALUES (8, 'Separato/a');
INSERT INTO per_stato_civile (id, descrizione) VALUES (9, 'Vedovo/a');



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
  stato_civile_id INTEGER NOT NULL REFERENCES per_stato_civile(id),
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
