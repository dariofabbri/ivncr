package it.ivncr.erp.jsf.managedbean.accesso.utente;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
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

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private String password;
	private String confirmPassword;


	private void clean() {

		password = null;
		confirmPassword = null;
	}

	public void doChangePassword() {

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
			us.changePassword(loginInfo.getUtente().getId(), password);
			logger.debug("Password successfully changed.");

			// Clean up form state.
			//
			clean();

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

			logger.warn("Exception caught while creating user.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di creazione dell'utente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
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
