package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.RecapitoTelefonico;
import it.ivncr.erp.model.personale.TipoRecapitoTelefonico;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.recapitotelefonico.RecapitoTelefonicoService;

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
public class DettaglioAddettoTelefoni implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoTelefoni.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneAddetti.edited.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoRecapitoTelefonico;
	private String recapito;

	private List<TipoRecapitoTelefonico> listTipoRecapitoTelefonico;

	private List<RecapitoTelefonico> listRecapitiTelefonici;

	private RecapitoTelefonico selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo recapito telefonico LUT.
		//
		listTipoRecapitoTelefonico = lutService.listItems("TipoRecapitoTelefonico");

		// Load list for data table.
		//
		loadRecapitiTelefonici();

		logger.debug("Initialization performed.");
	}


	public void loadRecapitiTelefonici() {

		if(addettoId == null)
			return;

		RecapitoTelefonicoService rts = ServiceFactory.createService("RecapitoTelefonico");
		listRecapitiTelefonici = rts.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		RecapitoTelefonicoService rts = ServiceFactory.createService("RecapitoTelefonico");
		selected = rts.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoRecapitoTelefonico = selected.getTipoRecapitoTelefonico().getId();
		recapito = selected.getRecapito();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoRecapitoTelefonico = null;
		recapito = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		RecapitoTelefonicoService rts = ServiceFactory.createService("RecapitoTelefonico");

		try {
			RecapitoTelefonico entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = rts.update(
						id,
						codiceTipoRecapitoTelefonico,
						recapito);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = rts.create(
						addettoId,
						codiceTipoRecapitoTelefonico,
						recapito);
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
			loadRecapitiTelefonici();

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

		// Create service to persist data.
		//
		RecapitoTelefonicoService rts = ServiceFactory.createService("RecapitoTelefonico");

		try {
			rts.delete(selected.getId());

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
			loadRecapitiTelefonici();

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

	public Integer getCodiceTipoRecapitoTelefonico() {
		return codiceTipoRecapitoTelefonico;
	}

	public void setCodiceTipoRecapitoTelefonico(
			Integer codiceTipoRecapitoTelefonico) {
		this.codiceTipoRecapitoTelefonico = codiceTipoRecapitoTelefonico;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public List<TipoRecapitoTelefonico> getListTipoRecapitoTelefonico() {
		return listTipoRecapitoTelefonico;
	}

	public void setListTipoRecapitoTelefonico(
			List<TipoRecapitoTelefonico> listTipoRecapitoTelefonico) {
		this.listTipoRecapitoTelefonico = listTipoRecapitoTelefonico;
	}

	public List<RecapitoTelefonico> getListRecapitiTelefonici() {
		return listRecapitiTelefonici;
	}

	public void setListRecapitiTelefonici(
			List<RecapitoTelefonico> listRecapitiTelefonici) {
		this.listRecapitiTelefonici = listRecapitiTelefonici;
	}

	public RecapitoTelefonico getSelected() {
		return selected;
	}

	public void setSelected(RecapitoTelefonico selected) {
		this.selected = selected;
	}
}