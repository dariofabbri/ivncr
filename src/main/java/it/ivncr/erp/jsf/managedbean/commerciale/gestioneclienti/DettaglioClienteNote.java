package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;


import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.cliente.ClienteService;

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
public class DettaglioClienteNote implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteNote.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioClienteGenerale}")
	private DettaglioClienteGenerale dettaglioClienteGenerale;

	private String note;

	@PostConstruct
	public void init() {

		ClienteService cs = ServiceFactory.createService("Cliente");
		Cliente cliente = cs.retrieve(dettaglioClienteGenerale.getId());
		note = cliente.getNote();
		
		logger.debug("Initialization performed.");
	}


	public void doSave() {

		// Save the entity.
		//
		try {
			ClienteService cs = ServiceFactory.createService("Cliente");

			cs.setNote(dettaglioClienteGenerale.getId(), note);
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

	
	public DettaglioClienteGenerale getDettaglioClienteGenerale() {
		return dettaglioClienteGenerale;
	}

	public void setDettaglioClienteGenerale(
			DettaglioClienteGenerale dettaglioClienteGenerale) {
		this.dettaglioClienteGenerale = dettaglioClienteGenerale;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
