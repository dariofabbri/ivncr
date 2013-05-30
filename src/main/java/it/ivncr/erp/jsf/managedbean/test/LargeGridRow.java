package it.ivncr.erp.jsf.managedbean.test;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.personale.Addetto;

import java.util.ArrayList;
import java.util.List;

public class LargeGridRow {

	private OrdineServizio ods;
	private List<Orario> orari;
	private Addetto addetto1;
	private Addetto addetto2;

	public LargeGridRow() {
		orari = new ArrayList<Orario>();
	}

	public OrdineServizio getOds() {
		return ods;
	}

	public void setOds(OrdineServizio ods) {
		this.ods = ods;
	}

	public List<Orario> getOrari() {
		return orari;
	}

	public void setOrari(List<Orario> orari) {
		this.orari = orari;
	}

	public Addetto getAddetto1() {
		return addetto1;
	}

	public void setAddetto1(Addetto addetto1) {
		this.addetto1 = addetto1;
	}

	public Addetto getAddetto2() {
		return addetto2;
	}

	public void setAddetto2(Addetto addetto2) {
		this.addetto2 = addetto2;
	}
}
