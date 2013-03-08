\encoding UTF8;

ALTER TABLE app.gen_azienda ADD COLUMN stile VARCHAR(255);

UPDATE app.gen_azienda SET stile = 'font-weight: bold; font-size: 1.3em; color: rgb(32, 180, 79)' WHERE codice = 'CRM';
UPDATE app.gen_azienda SET stile = 'font-weight: bold; font-size: 1.3em; color: rgb(51, 72, 223)' WHERE codice = 'IVNCR';
UPDATE app.gen_azienda SET stile = 'font-weight: bold; font-size: 1.3em; color: rgb(223, 51, 51);' WHERE codice = 'MSC';
