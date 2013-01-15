package it.ivncr.erp.jsf.managedbean.test;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class ValidationFormRestoreTest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ValidationFormRestoreTest.class);

	private String username;
	private String nome;
	private String cognome;
	private String note;

	public String getUsername() {
		
		logger.debug("getUsername!");
		return username;
	}

	public void setUsername(String username) {
		
		logger.debug("setUsername!");
		this.username = username;
	}

	public String getNome() {
		
		logger.debug("getNome!");
		return nome;
	}

	public void setNome(String nome) {
		
		logger.debug("setNome!");
		this.nome = nome;
	}

	public String getCognome() {
		
		logger.debug("getCognome!");
		return cognome;
	}

	public void setCognome(String cognome) {
		
		logger.debug("setCognome!");
		this.cognome = cognome;
	}

	public String getNote() {
		
		logger.debug("getNote!");
		return note;
	}

	public void setNote(String note) {
		
		logger.debug("setNote!");
		this.note = note;
	}
	
	public void doSave(ActionEvent event) {
		
		logger.debug("doSave!");
		
		FacesMessage message = new FacesMessage(
				FacesMessage.SEVERITY_ERROR, 
				"Errore di sistema", 
				"Si è verificato un errore in fase di salvataggio del record.");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
}
