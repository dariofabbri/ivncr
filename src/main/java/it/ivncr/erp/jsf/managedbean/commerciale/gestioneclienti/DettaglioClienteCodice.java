package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.cliente.ClienteService;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioClienteCodice implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteCodice.class);

	private static final long serialVersionUID = 1L;

	private String firstCodice;
	private String lastCodice;

	
	public void generateCodice() {

		// Create cliente service that will be used to query for codice.
		//
		ClienteService cs = ServiceFactory.createService("Cliente");
		String[] codici = cs.retrieveNextCodice();
		logger.debug(String.format("Found a pair of codes: [%s, %s]", codici[0], codici[1]));

		lastCodice = codici[0];
		firstCodice = codici[1];
	}


	public String getFirstCodice() {
		return firstCodice;
	}

	public void setFirstCodice(String firstCodice) {
		this.firstCodice = firstCodice;
	}

	public String getLastCodice() {
		return lastCodice;
	}

	public void setLastCodice(String lastCodice) {
		this.lastCodice = lastCodice;
	}
	
}