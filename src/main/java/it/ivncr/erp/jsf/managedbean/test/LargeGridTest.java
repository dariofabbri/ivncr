package it.ivncr.erp.jsf.managedbean.test;


import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.operativo.Servizio;
import it.ivncr.erp.model.personale.Addetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.RandomStringUtils;


@ManagedBean
@ViewScoped
public class LargeGridTest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LargeGridRow> rows;

	private final Random rnd = new Random();

	@PostConstruct
	public void init() {

		rows = new ArrayList<LargeGridRow>();

		for(int i = 0; i < 20; ++i) {

			LargeGridRow row = new LargeGridRow();

			OrdineServizio ods = new OrdineServizio();
			ods.setAlias("Servizio #" + (i + 1));
			row.setOds(ods);

			row.setOrario(buildRandomOrario());

			row.setAddetto1(buildRandomAddettoCell());
			row.setAddetto2(buildRandomAddettoCell());

			rows.add(row);
		}
	}

	private AddettoCell buildRandomAddettoCell() {

		AddettoCell addettoCell = new AddettoCell();
		Addetto addetto = new Addetto();
		addetto.setMatricola(RandomStringUtils.random(5, false, true));
		addetto.setCognome(RandomStringUtils.random(30));
		addetto.setNome(RandomStringUtils.random(20));
		addettoCell.setAddetto(addetto);

		int noOfServizi = rnd.nextInt(3) + 1;
		for(int j = 0; j < noOfServizi; ++j) {
			addettoCell.getServizi().add(buildRandomServizio());
		}

		return addettoCell;
	}

	private Servizio buildRandomServizio() {

		Servizio servizio = new Servizio();

		servizio.setOrarioDa(buildRandomTime());
		servizio.setOrarioA(buildRandomTime());

		return servizio;
	}

	private Orario buildRandomOrario() {

		Orario orario = new Orario();

		orario.setQuantita1(rnd.nextInt(3) + 1);
		orario.setOrarioInizio1(buildRandomTime());
		orario.setOrarioFine1(buildRandomTime());

		if(rnd.nextDouble() < .4) {
			return orario;
		}
		orario.setQuantita2(rnd.nextInt(3) + 1);
		orario.setOrarioInizio2(buildRandomTime());
		orario.setOrarioFine2(buildRandomTime());

		if(rnd.nextDouble() < .8) {
			return orario;
		}
		orario.setQuantita3(rnd.nextInt(3) + 1);
		orario.setOrarioInizio3(buildRandomTime());
		orario.setOrarioFine3(buildRandomTime());

		return orario;
	}

	private Date buildRandomTime() {

		GregorianCalendar gc = new GregorianCalendar();
		gc.clear();
		gc.set(Calendar.HOUR_OF_DAY, rnd.nextInt(24));
		gc.set(Calendar.MINUTE, rnd.nextInt(60));

		return gc.getTime();
	}

	public List<LargeGridRow> getRows() {
		return rows;
	}

	public void setRows(List<LargeGridRow> rows) {
		this.rows = rows;
	}
}
