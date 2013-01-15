package it.ivncr.erp.jsf.managedbean.utente;

import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.utente.UtenteService;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class CambioPassword implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(CambioPassword.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneUtenti.selected}")
	private Utente selected;

	private String password;
	private String confirmPassword;

	public void doChangePassword() {

		// Check if a row has been selected.
		//
		if(selected == null) {

			logger.warn("No user selected for password change.");

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Nessun record selezionato.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

		// Check if password matches confirmation field.
		//
		if(!password.equals(confirmPassword)) {

			logger.debug("Password and confirmation do not match.");

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Le password differiscono",
					"La password e la relativa conferma differiscono");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

		// Change the password
		//
		try {
			UtenteService us = ServiceFactory.createUtenteService();
			us.changePassword(selected.getId(), password);
			logger.debug("Password successfully changed.");

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"La modifica della password si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {

			logger.warn("Exception caught while changing password.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di modifica della password.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public Utente getSelected() {
		return selected;
	}

	public void setSelected(Utente selected) {
		this.selected = selected;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
