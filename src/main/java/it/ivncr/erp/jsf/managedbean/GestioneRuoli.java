package it.ivncr.erp.jsf.managedbean;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.ruolo.RuoloService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class GestioneRuoli implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneRuoli.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Ruolo> model;
	private Ruolo selected;
	private Ruolo edited;
	private List<Permesso> permessi;
	private List<Permesso> filteredPermessi;
	private Permesso[] selectedPermessi;
	
	public GestioneRuoli() {
		
		model = new LazyDataModel<Ruolo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Ruolo> load(
					int first, 
					int pageSize, 
					String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				logger.debug("Fetching data model.");
				
				RuoloService rs = ServiceFactory.createRuoloService();
				QueryResult<Ruolo> result = rs.list(
						first, 
						pageSize, 
						sortField, 
						SortDirection.fromSortOrder(sortOrder), 
						filters);
				
				this.setRowCount(result.getRecords());
				
				return result.getResults();
			}
			
			@Override
			public Object getRowKey(Ruolo ruolo) {
				
				return ruolo == null ? null : ruolo.getId();
			}

			@Override
			public Ruolo getRowData(String rowKey) {
				
				RuoloService rs = ServiceFactory.createRuoloService();
				Ruolo ruolo = rs.retrieve(Integer.decode(rowKey));
				return ruolo;
			}
		};
	}
	
	public String startCreate() {
		
		edited = new Ruolo();
		permessi = new ArrayList<Permesso>();
		
		logger.debug("Moving to detail page for new record creation.");
		return "detail?faces-redirect=true";
	}
	
	public String startUpdate() {
		
		if(selected == null) {
			logger.error("Invalid status. No row selected on start update request.");
			throw new RuntimeException("Invalid status. No row selected on start update request.");
		}
		
		edited = selected;
		permessi = new ArrayList<Permesso>();
		
		logger.debug("Moving to detail page for record update.");
		return "detail?faces-redirect=true";
	}
	
	public void onTabChange(TabChangeEvent event) {

		logger.debug("Selected tab changed.");
		
		// If permessi tab has been selected it is necessary to initialize
		// the permessi list in the bean.
		//
		if(event.getTab().getId().equals("permessiTab")) {
		
			logger.debug("Loading permessi list.");
			RuoloService rs = ServiceFactory.createRuoloService();
			permessi = rs.retrievePermessi(edited.getId());
		}
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
	
	public void doDelete() {

		// Check if a row has been selected.
		//
		if(selected == null) {
			
			logger.warn("No entity selected for deletion.");

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Errore di sistema", 
					"Nessun record selezionato.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		
		// Delete the selected entity.
		//
		try {
			
			RuoloService rs = ServiceFactory.createRuoloService();
			rs.delete(selected.getId());
			logger.debug("Entity successfully deleted.");

			// Clean up selection.
			//
			selected = null;
			
			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {
			
			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Errore di sistema", 
					"Si è verificato un errore in fase di cancellazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public LazyDataModel<Ruolo> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Ruolo> model) {
		this.model = model;
	}

	public Ruolo getSelected() {
		return selected;
	}

	public void setSelected(Ruolo selected) {
		this.selected = selected;
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

	public List<Permesso> getFilteredPermessi() {
		return filteredPermessi;
	}

	public void setFilteredPermessi(List<Permesso> filteredPermessi) {
		this.filteredPermessi = filteredPermessi;
	}

	public Permesso[] getSelectedPermessi() {
		return selectedPermessi;
	}

	public void setSelectedPermessi(Permesso[] selectedPermessi) {
		this.selectedPermessi = selectedPermessi;
	}
}
