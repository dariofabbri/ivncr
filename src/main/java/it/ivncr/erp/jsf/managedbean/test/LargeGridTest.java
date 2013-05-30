package it.ivncr.erp.jsf.managedbean.test;


import it.ivncr.erp.model.commerciale.ods.OrdineServizio;

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

			int noOfOrari = rnd.nextInt(3) + 1;
			for(int j = 0; j < noOfOrari; ++j) {
				Orario orario = buildRandomOrario();
				row.getOrari().add(orario);
			}

			// addetto1 & addetto2
		}
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
}
