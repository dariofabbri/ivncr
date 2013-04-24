package it.ivncr.erp.jsf.managedbean.commerciale.ods;


import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@ViewScoped
public class DettaglioOdsModalitaOperative implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsModalitaOperative.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioOdsGenerale}")
	private DettaglioOdsGenerale dettaglioOdsGenerale;

	private String modalitaOperative;

	@PostConstruct
	public void init() {

		OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
		OrdineServizio ordineServizio = oss.retrieve(dettaglioOdsGenerale.getId());
		modalitaOperative = ordineServizio.getModalitaOperative();

		logger.debug("Initialization performed.");
	}


	public void doSave() {

		// Save the entity.
		//
		try {
			OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");

			oss.setModalitaOperative(dettaglioOdsGenerale.getId(), modalitaOperative);
			logger.debug("Entity successfully updated.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record aggiornato",
					"La modifica delle modalità operative si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch(Exception e) {

			logger.warn("Exception caught while saving entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di salvataggio del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public DettaglioOdsGenerale getDettaglioOdsGenerale() {
		return dettaglioOdsGenerale;
	}

	public void setDettaglioOdsGenerale(
			DettaglioOdsGenerale dettaglioOdsGenerale) {
		this.dettaglioOdsGenerale = dettaglioOdsGenerale;
	}

	public String getModalitaOperative() {
		return modalitaOperative;
	}

	public void setModalitaOperative(String modalitaOperative) {
		this.modalitaOperative = modalitaOperative;
	}
}
