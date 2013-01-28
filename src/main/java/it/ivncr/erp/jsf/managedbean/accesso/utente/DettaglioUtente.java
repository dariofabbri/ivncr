package it.ivncr.erp.jsf.managedbean.accesso.utente;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.ruolo.RuoloService;
import it.ivncr.erp.service.utente.UtenteService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioUtente implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioUtente.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneUtenti.edited.id}")
	private Integer id;

	private String username;
	private String nome;
	private String cognome;
	private String note;

	private String password;
	private String confirmPassword;

	private Date ultimoLogin;
	private Date creazione;
	private Date ultimaAttivazione;
	private Date ultimaDisattivazione;
	private Boolean attivo;

	private List<Ruolo> ruoli;
	private List<Ruolo> filtered;
	private Ruolo[] selected;


	@PostConstruct
	public void init() {

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			UtenteService us = ServiceFactory.createService("Utente");
			Utente utente = us.retrieve(id);

			username = utente.getUsername();
			nome = utente.getNome();
			cognome = utente.getCognome();
			note = utente.getNote();

			ultimoLogin = utente.getUltimoLogin();
			creazione = utente.getCreazione();
			ultimaAttivazione = utente.getUltimaAttivazione();
			ultimaDisattivazione = utente.getUltimaDisattivazione();
			attivo = utente.getAttivo();
		}
	}

	public void onTabChange(TabChangeEvent event) {

		logger.debug("Selected tab changed.");

		// If ruoli tab has been selected it is necessary to initialize
		// the ruoli list in the bean.
		//
		if(event.getTab().getId().equals("ruoliTab")) {

			loadRuoliList();
		}
	}

	public void doSave() {

		logger.debug("Entering doSave() method.");

		// Save the entity.
		//
		try {
			UtenteService us = ServiceFactory.createService("Utente");

			if(id == null) {

				// Check if password matches confirmation field.
				//
				if(!password.equals(confirmPassword)) {

					logger.debug("Password and confirmation do not match.");

					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Le password differiscono",
							"La password e la relativa conferma differiscono.");
					FacesContext.getCurrentInstance().addMessage(null, message);
					return;
				}

				Utente utente = us.create(
						username,
						password,
						nome,
						cognome,
						note);
				id =  utente.getId();

				logger.debug("Entity successfully created.");

			} else {

				us.update(
						id,
						username,
						nome,
						cognome,
						note);

				logger.debug("Entity successfully updated.");
			}

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

	public void doUpdateRuoli() {

		logger.debug("Entering doUpdateRuoli() method.");

		// Get selected ids.
		//
		Integer[] ids = new Integer[selected.length];
		for(int i = 0; i < selected.length; ++i) {
			ids[i] = selected[i].getId();
		}

		// Set the roles.
		//
		try {
			UtenteService us = ServiceFactory.createService("Utente");
			us.setRuoli(
				id,
				ids);

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch(Exception e) {

			logger.warn("Exception caught while updating entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di aggiornamento dei record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	private void loadRuoliList() {

		logger.debug("Loading ruoli list.");
		RuoloService rs = ServiceFactory.createService("Ruolo");
		QueryResult<Ruolo> result = rs.list(null, null, null, null);
		ruoli = result.getResults();

		UtenteService us = ServiceFactory.createService("Utente");
		List<Ruolo> list = us.listRuoli(id);
		selected = list.toArray(new Ruolo[0]);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public Date getCreazione() {
		return creazione;
	}

	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}

	public Date getUltimaAttivazione() {
		return ultimaAttivazione;
	}

	public void setUltimaAttivazione(Date ultimaAttivazione) {
		this.ultimaAttivazione = ultimaAttivazione;
	}

	public Date getUltimaDisattivazione() {
		return ultimaDisattivazione;
	}

	public void setUltimaDisattivazione(Date ultimaDisattivazione) {
		this.ultimaDisattivazione = ultimaDisattivazione;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public List<Ruolo> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Ruolo> filtered) {
		this.filtered = filtered;
	}

	public Ruolo[] getSelected() {
		return selected;
	}

	public void setSelected(Ruolo[] selected) {
		this.selected = selected;
	}
}
