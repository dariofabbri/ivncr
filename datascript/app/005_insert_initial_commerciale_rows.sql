\encoding UTF8;

SET client_min_messages TO WARNING;

INSERT INTO app.com_gruppo_cliente (id, descrizione) VALUES (1, 'Grande utenza');
INSERT INTO app.com_gruppo_cliente (id, descrizione) VALUES (2, 'Piccola utenza');
INSERT INTO app.com_gruppo_cliente (id, descrizione) VALUES (3, 'Media utenza');
SELECT setval('app.com_gruppo_cliente_id_seq', (SELECT MAX(id) FROM app.com_gruppo_cliente));

INSERT INTO app.com_tipo_business_partner (id, descrizione) VALUES (1, 'Società');
INSERT INTO app.com_tipo_business_partner (id, descrizione) VALUES (2, 'Privato');
INSERT INTO app.com_tipo_business_partner (id, descrizione) VALUES (3, 'Ente');
SELECT setval('app.com_tipo_business_partner_id_seq', (SELECT MAX(id) FROM app.com_tipo_business_partner));

INSERT INTO app.com_divisa (id, simbolo, iso_alpha, iso_num, descrizione) VALUES (1, '€', 'EUR', 978, 'Euro');
INSERT INTO app.com_divisa (id, simbolo, iso_alpha, iso_num, descrizione) VALUES (2, '$', 'USD', 840, 'US Dollar');
SELECT setval('app.com_divisa_id_seq', (SELECT MAX(id) FROM app.com_divisa));
