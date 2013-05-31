package it.ivncr.erp.jsf.managedbean.test;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;

public class LargeGridRow {

	private OrdineServizio ods;
	private Orario orario;
	private AddettoCell addetto1;
	private AddettoCell addetto2;

	public OrdineServizio getOds() {
		return ods;
	}

	public void setOds(OrdineServizio ods) {
		this.ods = ods;
	}

	public Orario getOrario() {
		return orario;
	}

	public void setOrario(Orario orario) {
		this.orario = orario;
	}

	public AddettoCell getAddetto1() {
		return addetto1;
	}

	public void setAddetto1(AddettoCell addetto1) {
		this.addetto1 = addetto1;
	}

	public AddettoCell getAddetto2() {
		return addetto2;
	}

	public void setAddetto2(AddettoCell addetto2) {
		this.addetto2 = addetto2;
	}
}
