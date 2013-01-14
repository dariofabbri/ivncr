package it.ivncr.erp.jsf.managedbean.utente;

import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.utente.UtenteService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class GestioneUtenti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneUtenti.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Utente> model;
	private Utente selected;
	private Utente edited;
	
	public GestioneUtenti() {
		
		model = new LazyDataModel<Utente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Utente> load(
					int first, 
					int pageSize, 
					String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				logger.debug("Fetching data model.");
				
				UtenteService us = ServiceFactory.createUtenteService();
				QueryResult<Utente> result = us.list(
						first, 
						pageSize, 
						sortField, 
						SortDirection.fromSortOrder(sortOrder), 
						filters);
				
				this.setRowCount(result.getRecords());
				
				return result.getResults();
			}
			
			@Override
			public Object getRowKey(Utente utente) {
				
				return utente == null ? null : utente.getId();
			}

			@Override
			public Utente getRowData(String rowKey) {
				
				UtenteService us = ServiceFactory.createUtenteService();
				Utente utente = us.retrieve(Integer.decode(rowKey));
				return utente;
			}
		};
	}
	
	public String startCreate() {
		
		edited = new Utente();
		
		logger.debug("Moving to detail page for new record creation.");
		return "detail?faces-redirect=true";
	}
	
	public String startUpdate() {
		
		if(selected == null) {
			logger.error("Invalid status. No row selected on start update request.");
			throw new RuntimeException("Invalid status. No row selected on start update request.");
		}
		
		edited = selected;
		
		logger.debug("Moving to detail page for record update.");
		return "detail?faces-redirect=true";
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
			
			UtenteService us = ServiceFactory.createUtenteService();
			us.delete(selected.getId());
			logger.debug("Entity successfully deleted.");
			
			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO, 
					"Record cancellato", 
					"La cancellazione dell'utente selezionato si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch(Exception e) {
			
			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Errore di sistema", 
					"Si è verificato un errore in fase di cancellazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		} finally {

			// Clean up selection.
			//
			selected = null;
		}
	}

	public LazyDataModel<Utente> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Utente> model) {
		this.model = model;
	}

	public Utente getSelected() {
		return selected;
	}

	public void setSelected(Utente selected) {
		this.selected = selected;
	}

	public Utente getEdited() {
		return edited;
	}

	public void setEdited(Utente edited) {
		this.edited = edited;
	}
}
