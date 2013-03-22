package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;


import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.addetto.AddettoService;

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
public class DettaglioAddettoNote implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoNote.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private String note;

	@PostConstruct
	public void init() {

		AddettoService as = ServiceFactory.createService("Addetto");
		Addetto addetto = as.retrieve(addettoId);
		note = addetto.getNote();

		logger.debug("Initialization performed.");
	}


	public void doSave() {

		// Save the entity.
		//
		try {
			AddettoService as = ServiceFactory.createService("Addetto");

			as.setNote(addettoId, note);
			logger.debug("Entity successfully updated.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record aggiornato",
					"La modifica delle note si è conclusa con successo.");
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


	public Integer getAddettoId() {
		return addettoId;
	}

	public void setAddettoId(Integer addettoId) {
		this.addettoId = addettoId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
