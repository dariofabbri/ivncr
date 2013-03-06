\encoding UTF8;

CREATE TABLE app.gen_paese
(
	id SERIAL NOT NULL PRIMARY KEY,
  iso_alpha2 VARCHAR(2) NOT NULL,
  iso_alpha3 VARCHAR(3) NOT NULL,
  iso_num VARCHAR(3) NOT NULL,
	descrizione VARCHAR(255) NOT NULL
);


INSERT INTO app.gen_paese (iso_alpha2, iso_alpha3, iso_num, descrizione) VALUES ('IT', 'ITA', '380', 'Italia');
INSERT INTO app.gen_paese (iso_alpha2, iso_alpha3, iso_num, descrizione) VALUES ('US', 'USA', '840', 'United States');

