package it.ivncr.erp.jsf.managedbean.accesso.ruolo;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.ruolo.RuoloService;

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
public class GestioneRuoli implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneRuoli.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Ruolo> model;
	private Ruolo selected;
	private Ruolo edited;
	
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
				
				RuoloService rs = ServiceFactory.createService("Ruolo");
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
				
				RuoloService rs = ServiceFactory.createService("Ruolo");
				Ruolo ruolo = rs.retrieve(Integer.decode(rowKey));
				return ruolo;
			}
		};
	}
	
	public String startCreate() {
		
		edited = new Ruolo();
		
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
			
			RuoloService rs = ServiceFactory.createService("Ruolo");
			rs.delete(selected.getId());
			logger.debug("Entity successfully deleted.");
			
			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO, 
					"Record cancellato", 
					"La cancellazione del ruolo selezionato si è conclusa con successo.");
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
}
