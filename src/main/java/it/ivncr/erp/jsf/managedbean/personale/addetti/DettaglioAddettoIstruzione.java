package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.Istruzione;
import it.ivncr.erp.model.personale.TitoloStudio;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.istruzione.IstruzioneService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.util.Date;
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
public class DettaglioAddettoIstruzione implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoIstruzione.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTitoloStudio;
	private Date dataConseguimento;
	private String presso;

	private List<TitoloStudio> listTitoloStudio;

	private List<Istruzione> listIstruzione;

	private Istruzione selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load titolo studio LUT.
		//
		listTitoloStudio = lutService.listItems("TitoloStudio");

		// Load list for data table.
		//
		loadIstruzione();

		logger.debug("Initialization performed.");
	}


	public void loadIstruzione() {

		if(addettoId == null)
			return;

		IstruzioneService is = ServiceFactory.createService("Istruzione");
		listIstruzione = is.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		IstruzioneService is = ServiceFactory.createService("Istruzione");
		selected = is.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTitoloStudio = selected.getTitoloStudio() != null ? selected.getTitoloStudio().getId() : null;
		dataConseguimento = selected.getDataConseguimento();
		presso = selected.getPresso();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTitoloStudio = null;
		dataConseguimento = null;
		presso = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		IstruzioneService is = ServiceFactory.createService("Istruzione");

		try {
			Istruzione entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = is.update(
						id,
						codiceTitoloStudio,
						dataConseguimento,
						presso);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = is.create(
						addettoId,
						codiceTitoloStudio,
						dataConseguimento,
						presso);
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
			loadIstruzione();

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
		IstruzioneService is = ServiceFactory.createService("Istruzione");

		try {
			is.delete(selected.getId());

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
			loadIstruzione();

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

	public Integer getCodiceTitoloStudio() {
		return codiceTitoloStudio;
	}

	public void setCodiceTitoloStudio(Integer codiceTitoloStudio) {
		this.codiceTitoloStudio = codiceTitoloStudio;
	}

	public Date getDataConseguimento() {
		return dataConseguimento;
	}

	public void setDataConseguimento(Date dataConseguimento) {
		this.dataConseguimento = dataConseguimento;
	}

	public String getPresso() {
		return presso;
	}

	public void setPresso(String presso) {
		this.presso = presso;
	}

	public List<TitoloStudio> getListTitoloStudio() {
		return listTitoloStudio;
	}

	public void setListTitoloStudio(List<TitoloStudio> listTitoloStudio) {
		this.listTitoloStudio = listTitoloStudio;
	}

	public List<Istruzione> getListIstruzione() {
		return listIstruzione;
	}

	public void setListIstruzione(List<Istruzione> listIstruzione) {
		this.listIstruzione = listIstruzione;
	}

	public Istruzione getSelected() {
		return selected;
	}

	public void setSelected(Istruzione selected) {
		this.selected = selected;
	}
}