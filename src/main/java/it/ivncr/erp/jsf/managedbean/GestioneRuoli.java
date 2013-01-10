package it.ivncr.erp.jsf.managedbean;

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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class GestioneRuoli implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneRuoli.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Ruolo> model;
	private Ruolo selected;
	
	private String nome;
	private String descrizione;
	
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
	
	private void clean() {
		
		nome = null;
		descrizione = null;
	}
	
	public String startCreate() {
		
		return "create?faces-redirect=true";
	}
	
	public void doCreate() {
		
		// Create the new entity.
		//
		try {
			RuoloService rs = ServiceFactory.createRuoloService();
			rs.create(
					nome, 
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
			RuoloService rs = ServiceFactory.createRuoloService();
			rs.update(
					selected.getId(),
					selected.getNome(),
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
			
			RuoloService rs = ServiceFactory.createRuoloService();
			rs.delete(selected.getId());
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
