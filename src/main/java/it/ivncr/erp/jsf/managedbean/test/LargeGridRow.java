package it.ivncr.erp.jsf.managedbean.test;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;

import java.util.ArrayList;
import java.util.List;

public class LargeGridRow {

	private OrdineServizio ods;
	private Orario orario;
	private List<AddettoCell> addetti;

	public LargeGridRow() {
		addetti = new ArrayList<AddettoCell>();
	}

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

	public List<AddettoCell> getAddetti() {
		return addetti;
	}

	public void setAddetti(List<AddettoCell> addetti) {
		this.addetti = addetti;
	}
}
