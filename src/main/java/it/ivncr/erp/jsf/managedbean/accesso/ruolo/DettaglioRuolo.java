package it.ivncr.erp.jsf.managedbean.accesso.ruolo;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.permesso.PermessoService;
import it.ivncr.erp.service.ruolo.RuoloService;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
		PermessoService ps = ServiceFactory.createPermessoService();
		QueryResult<Permesso> result = ps.list(null, null, null, null);
		permessi = result.getResults();
		
		RuoloService rs = ServiceFactory.createRuoloService();
		List<Permesso> list = rs.retrievePermessi(edited.getId());
		selected = list.toArray(new Permesso[0]);
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
	
	public void doUpdatePermessi() {
		
		logger.debug("Entering doUpdatePermessi() method.");

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
					"Si è verificato un errore in fase di aggiornamento dei record.");
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
