package it.ivncr.erp.jsf.managedbean.test;

import it.ivncr.erp.model.operativo.Servizio;
import it.ivncr.erp.model.personale.Addetto;

import java.util.List;

public class AddettoCell {

	private Addetto addetto;
	private List<Servizio> servizi;

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public List<Servizio> getServizi() {
		return servizi;
	}

	public void setServizi(List<Servizio> servizi) {
		this.servizi = servizi;
	}
}
