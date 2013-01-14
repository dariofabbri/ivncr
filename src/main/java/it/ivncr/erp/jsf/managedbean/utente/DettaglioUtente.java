package it.ivncr.erp.jsf.managedbean.utente;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
	
	private void loadList() {
		
		/*
		logger.debug("Loading ruoli list.");
		PermessoService ps = ServiceFactory.createPermessoService();
		QueryResult<Permesso> result = ps.list(null, null, null, null);
		permessi = result.getResults();
		
		RuoloService rs = ServiceFactory.createRuoloService();
		List<Permesso> list = rs.retrievePermessi(edited.getId());
		selected = list.toArray(new Permesso[0]);
		*/
	}

	public Utente getEdited() {
		return edited;
	}

	public void setEdited(Utente edited) {
		this.edited = edited;
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

	/*
	public void doSave(ActionEvent event) {
		
		logger.debug("Entering doSave() method.");
		
		// Save the entity.
		//
		try {
			RuoloService rs = ServiceFactory.createRuoloService();
			
			// If no id is present, creation is required.
			//
			if(edited.getId() == null) {
				edited = rs.create(
						edited.getNome(), 
						edited.getDescrizione());
				logger.debug("Entity successfully created.");
			} else {
				edited = rs.update(
						edited.getId(),
						edited.getNome(), 
						edited.getDescrizione());
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
	
	public void doUpdatePermessi() {
		
		logger.debug("Entering doSave() method.");

		// Get selected ids.
		//
		Integer[] ids = new Integer[selected.length];
		for(int i = 0; i < selected.length; ++i) {
			ids[i] = selected[i].getId();
		}
		
		// Set the permissions.
		//
		try {
			RuoloService rs = ServiceFactory.createRuoloService();
			rs.setPermessi(
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
					"Si è verificato un errore in fase di rimozione dei record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	
	public void doRemovePermessi() {
		
		logger.debug("Entering doSave() method.");
		
		if(selected == null || selected.length == 0) {
			logger.error("Selection should not be empty at this point.");
			throw new RuntimeException("Selection should not be empty at this point.");
		}
		
		// Get selected ids.
		//
		Integer[] ids = new Integer[selected.length];
		for(int i = 0; i < selected.length; ++i) {
			ids[i] = selected[i].getId();
		}
		
		// Remove the permissions.
		//
		try {
			RuoloService rs = ServiceFactory.createRuoloService();
			rs.deletePermessi(
				edited.getId(),
				ids);
			
			// Reload updated list.
			//
			loadList();
			
			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {
			
			logger.warn("Exception caught while deleting entity.", e);
			
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Errore di sistema", 
					"Si è verificato un errore in fase di rimozione dei record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	*/

	
}
