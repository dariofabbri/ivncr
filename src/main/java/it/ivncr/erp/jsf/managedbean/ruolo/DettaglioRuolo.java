package it.ivncr.erp.jsf.managedbean.ruolo;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.ruolo.RuoloService;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioRuolo implements Serializable {

	private static final Logger logger = LoggerFactory
			.getLogger(DettaglioRuolo.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneRuoli.edited}")
	private Ruolo edited;

	private List<Permesso> permessi;
	private List<Permesso> filtered;
	private Permesso[] selected;
	
	public void onTabChange(TabChangeEvent event) {

		logger.debug("Selected tab changed.");
		
		// If permessi tab has been selected it is necessary to initialize
		// the permessi list in the bean.
		//
		if(event.getTab().getId().equals("permessiTab")) {

			loadList();
		}
	}
	
	private void loadList() {
		
		logger.debug("Loading permessi list.");
		RuoloService rs = ServiceFactory.createRuoloService();
		permessi = rs.retrievePermessi(edited.getId());		
	}

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
		
		// Save the entity.
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

	public Ruolo getEdited() {
		return edited;
	}

	public void setEdited(Ruolo edited) {
		this.edited = edited;
	}

	public List<Permesso> getPermessi() {
		return permessi;
	}

	public void setPermessi(List<Permesso> permessi) {
		this.permessi = permessi;
	}

	public List<Permesso> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Permesso> filtered) {
		this.filtered = filtered;
	}

	public Permesso[] getSelected() {
		return selected;
	}

	public void setSelected(Permesso[] selected) {
		this.selected = selected;
	}
}
