package it.ivncr.erp.jsf.managedbean.utente;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.ruolo.RuoloService;
import it.ivncr.erp.service.utente.UtenteService;

import java.io.Serializable;
import java.util.List;

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

	private static final Logger logger = LoggerFactory
			.getLogger(DettaglioUtente.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneUtenti.edited}")
	private Utente edited;

	private String password;
	private String confirmPassword;

	private List<Ruolo> ruoli;
	private List<Ruolo> filtered;
	private Ruolo[] selected;

	public void onTabChange(TabChangeEvent event) {

		logger.debug("Selected tab changed.");

		// If ruoli tab has been selected it is necessary to initialize
		// the ruoli list in the bean.
		//
		if(event.getTab().getId().equals("ruoliTab")) {

			loadList();
		}
	}

	public void doSave() {

		logger.debug("Entering doSave() method.");

		// Save the entity.
		//
		try {
			UtenteService us = ServiceFactory.createUtenteService();

			if(edited.getId() == null) {

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

				edited = us.create(
						edited.getUsername(),
						password,
						edited.getNome(),
						edited.getCognome(),
						edited.getNote());

				logger.debug("Entity successfully created.");

			} else {

				edited = us.update(
						edited.getId(),
						edited.getUsername(),
						edited.getNome(),
						edited.getCognome(),
						edited.getNote());

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

			logger.warn("Exception caught while creating entity.", e);

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
			UtenteService us = ServiceFactory.createUtenteService();
			us.setRuoli(
				edited.getId(),
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

	private void loadList() {

		logger.debug("Loading ruoli list.");
		RuoloService rs = ServiceFactory.createRuoloService();
		QueryResult<Ruolo> result = rs.list(null, null, null, null);
		ruoli = result.getResults();

		UtenteService us = ServiceFactory.createUtenteService();
		List<Ruolo> list = us.listRuoli(edited.getId());
		selected = list.toArray(new Ruolo[0]);
	}

	public Utente getEdited() {
		return edited;
	}

	public void setEdited(Utente edited) {
		this.edited = edited;
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
