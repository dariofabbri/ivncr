package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.RecapitoEmail;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.recapitoemail.RecapitoEmailService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioAddettoEmail implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoEmail.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private String email;

	private List<RecapitoEmail> listRecapitiEmail;

	private RecapitoEmail selected;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadRecapitiEmail();

		logger.debug("Initialization performed.");
	}


	public void loadRecapitiEmail() {

		if(addettoId == null)
			return;

		RecapitoEmailService res = ServiceFactory.createService("RecapitoEmail");
		listRecapitiEmail = res.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		RecapitoEmailService res = ServiceFactory.createService("RecapitoEmail");
		selected = res.retrieve(selected.getId());

		id = selected.getId();
		email = selected.getEmail();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		email = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		RecapitoEmailService res = ServiceFactory.createService("RecapitoEmail");

		try {
			RecapitoEmail entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = res.update(
						id,
						email);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = res.create(
						addettoId,
						email);
				id = entity.getId();

				logger.debug("Entity successfully created.");

			}

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Refresh list.
			//
			loadRecapitiEmail();

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {

			logger.warn("Exception caught while saving entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di salvataggio del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public void doDelete() {

		// Create service to delete record.
		//
		RecapitoEmailService res = ServiceFactory.createService("RecapitoEmail");

		try {
			res.delete(selected.getId());

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"L'eliminazione del record selezionato si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Reset selection.
			//
			selected = null;

			// Refresh list.
			//
			loadRecapitiEmail();

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	private boolean formValidations() {

		// Normalize fields.
		//
		//codiceFiscale = codiceFiscale != null ? codiceFiscale.toUpperCase() : null;

		return true;
	}


	public Integer getAddettoId() {
		return addettoId;
	}

	public void setAddettoId(Integer addettoId) {
		this.addettoId = addettoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RecapitoEmail> getListRecapitiEmail() {
		return listRecapitiEmail;
	}

	public void setListRecapitiEmail(List<RecapitoEmail> listRecapitiEmail) {
		this.listRecapitiEmail = listRecapitiEmail;
	}

	public RecapitoEmail getSelected() {
		return selected;
	}

	public void setSelected(RecapitoEmail selected) {
		this.selected = selected;
	}
}