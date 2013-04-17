package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.contratto.ContrattoService;

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
public class DettaglioContrattoNote implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoNote.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private String note;

	@PostConstruct
	public void init() {

		ContrattoService cs = ServiceFactory.createService("Contratto");
		Contratto contratto = cs.retrieve(dettaglioContrattoGenerale.getId());
		note = contratto.getNote();

		logger.debug("Initialization performed.");
	}


	public void doSave() {

		// Save the entity.
		//
		try {
			ContrattoService cs = ServiceFactory.createService("Contratto");

			cs.setNote(dettaglioContrattoGenerale.getId(), note);
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


	public DettaglioContrattoGenerale getDettaglioContrattoGenerale() {
		return dettaglioContrattoGenerale;
	}


	public void setDettaglioContrattoGenerale(
			DettaglioContrattoGenerale dettaglioContrattoGenerale) {
		this.dettaglioContrattoGenerale = dettaglioContrattoGenerale;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
}
