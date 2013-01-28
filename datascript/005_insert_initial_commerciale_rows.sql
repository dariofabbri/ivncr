INSERT INTO com_gruppo_cliente (id, descrizione) VALUES (1, 'Grande utenza');
INSERT INTO com_gruppo_cliente (id, descrizione) VALUES (2, 'Piccola utenza');
SELECT setval('com_gruppo_cliente_id_seq', (SELECT MAX(id) FROM com_gruppo_cliente));

INSERT INTO com_tipo_business_partner (id, descrizione) VALUES (1, 'Società');
INSERT INTO com_tipo_business_partner (id, descrizione) VALUES (2, 'Privato');
INSERT INTO com_tipo_business_partner (id, descrizione) VALUES (3, 'Ente');
SELECT setval('com_tipo_business_partner_id_seq', (SELECT MAX(id) FROM com_tipo_business_partner));

INSERT INTO com_divisa (id, simbolo, iso_alpha, iso_num, descrizione) VALUES (1, '€', 'EUR', 978, 'Euro');
INSERT INTO com_divisa (id, simbolo, iso_alpha, iso_num, descrizione) VALUES (2, '$', 'USD', 840, 'US Dollar');
SELECT setval('com_divisa_id_seq', (SELECT MAX(id) FROM com_divisa));
