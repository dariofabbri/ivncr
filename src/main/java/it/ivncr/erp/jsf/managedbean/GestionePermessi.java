package it.ivncr.erp.jsf.managedbean;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.permesso.PermessoService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class GestionePermessi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestionePermessi.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Permesso> model;
	private Permesso selected;
	
	private String permesso;
	private String descrizione;
	
	public GestionePermessi() {
		
		model = new LazyDataModel<Permesso>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Permesso> load(
					int first, 
					int pageSize, 
					String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				logger.debug("Fetching data model.");
				
				PermessoService ps = ServiceFactory.createPermessoService();
				QueryResult<Permesso> result = ps.list(
						first, 
						pageSize, 
						sortField, 
						SortDirection.fromSortOrder(sortOrder), 
						filters);
				
				this.setRowCount(result.getRecords());
				
				return result.getResults();
			}
			
			@Override
			public Object getRowKey(Permesso permesso) {
				
				return permesso == null ? null : permesso.getId();
			}

			@Override
			public Permesso getRowData(String rowKey) {
				
				PermessoService ps = ServiceFactory.createPermessoService();
				Permesso permesso = ps.retrieve(Integer.decode(rowKey));
				return permesso;
			}
		};
	}
	
	private void clean() {
		
		permesso = null;
		descrizione = null;
	}
	
	public void doCreate() {
		
		// Create the new entity.
		//
		try {
			PermessoService ps = ServiceFactory.createPermessoService();
			ps.create(
					permesso, 
					descrizione);
			logger.debug("Entity successfully created.");
			
			// Clean up form state.
			//
			clean();
			
			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);
			
		} catch(Exception e) {
			
			logger.warn("Exception caught while creating entity.", e);
			
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Errore di sistema", 
					"Si è verificato un errore in fase di creazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void doUpdate() {

		// Update the entity.
		//
		try {
			PermessoService ps = ServiceFactory.createPermessoService();
			ps.update(
					selected.getId(),
					selected.getPermesso(),
					selected.getDescrizione());
			logger.debug("Entity successfully updated.");

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);
			
		} catch(Exception e) {
			
			logger.warn("Exception caught while updating entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Errore di sistema", 
					"Si è verificato un errore in fase di aggiornamento del record.");
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
			
			PermessoService ps = ServiceFactory.createPermessoService();
			ps.delete(selected.getId());
			logger.debug("Entity successfully deleted.");

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

	public LazyDataModel<Permesso> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Permesso> model) {
		this.model = model;
	}

	public Permesso getSelected() {
		return selected;
	}

	public void setSelected(Permesso selected) {
		this.selected = selected;
	}

	public String getPermesso() {
		return permesso;
	}

	public void setPermesso(String permesso) {
		this.permesso = permesso;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
