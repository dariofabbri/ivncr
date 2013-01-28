package it.ivncr.erp.jsf.managedbean.accesso.utente;

import it.ivncr.erp.model.accesso.AccountEmail;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.utente.UtenteService;

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
public class DettaglioUtenteAccountEmail implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioUtenteAccountEmail.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneUtenti.edited.id}")
	private Integer id;

	private String account;
	private String password;


	@PostConstruct
	public void init() {

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			UtenteService us = ServiceFactory.createService("Utente");
			Utente utente = us.retrieveWithAccountEmail(id);
			AccountEmail accountEmail = utente.getAccountEmail();

			if(accountEmail != null) {
				account = utente.getAccountEmail().getAccount();
				password = utente.getAccountEmail().getPassword();
			}
		}
	}

	public void doSave() {

		logger.debug("Entering doSave() method.");

		// Save the entity.
		//
		try {
			UtenteService us = ServiceFactory.createService("Utente");
			us.updateAccountEmail(
				id,
				account,
				password);

			logger.debug("Entity successfully updated.");

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
