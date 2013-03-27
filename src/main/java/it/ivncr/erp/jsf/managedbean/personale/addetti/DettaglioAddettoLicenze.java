package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.LibrettoPortoArmi;
import it.ivncr.erp.model.personale.LicenzaPortoArmi;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.librettoportoarmi.LibrettoPortoArmiService;
import it.ivncr.erp.service.licenzaportoarmi.LicenzaPortoArmiService;

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
public class DettaglioAddettoLicenze implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoLicenze.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceLibretto;
	private String numero;
	private Date dataRilascio;
	private Date dataScadenza;
	private String note;

	private List<LibrettoPortoArmi> listLibretti;

	private List<LicenzaPortoArmi> listLicenze;

	private LicenzaPortoArmi selected;


	@PostConstruct
	public void init() {

		LibrettoPortoArmiService lpaService = ServiceFactory.createService("LibrettoPortoArmi");

		// Load libretti.
		//
		listLibretti = lpaService.listByAddetto(addettoId);

		// Load list for data table.
		//
		loadLicenze();

		logger.debug("Initialization performed.");
	}


	public void loadLicenze() {

		if(addettoId == null)
			return;

		LicenzaPortoArmiService ls = ServiceFactory.createService("LicenzaPortoArmi");
		listLicenze = ls.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		LicenzaPortoArmiService ls = ServiceFactory.createService("LicenzaPortoArmi");
		selected = ls.retrieve(selected.getId());

		id = selected.getId();
		codiceLibretto = selected.getLibrettoPortoArmi() != null ? selected.getLibrettoPortoArmi().getId() : null;
		numero = selected.getNumero();
		dataRilascio = selected.getDataRilascio();
		dataScadenza = selected.getDataScadenza();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceLibretto = null;
		numero = null;
		dataRilascio = null;
		dataScadenza = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		LicenzaPortoArmiService ls = ServiceFactory.createService("LicenzaPortoArmi");

		try {
			LicenzaPortoArmi entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ls.update(
						id,
						codiceLibretto,
						numero,
						dataRilascio,
						dataScadenza,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ls.create(
						addettoId,
						codiceLibretto,
						numero,
						dataRilascio,
						dataScadenza,
						note);
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
			loadLicenze();

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
		LicenzaPortoArmiService ls = ServiceFactory.createService("LicenzaPortoArmi");

		try {
			ls.delete(selected.getId());

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
			loadLicenze();

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

	public Integer getCodiceLibretto() {
		return codiceLibretto;
	}

	public void setCodiceLibretto(Integer codiceLibretto) {
		this.codiceLibretto = codiceLibretto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataRilascio() {
		return dataRilascio;
	}

	public void setDataRilascio(Date dataRilascio) {
		this.dataRilascio = dataRilascio;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<LibrettoPortoArmi> getListLibretti() {
		return listLibretti;
	}

	public void setListLibretti(List<LibrettoPortoArmi> listLibretti) {
		this.listLibretti = listLibretti;
	}

	public List<LicenzaPortoArmi> getListLicenze() {
		return listLicenze;
	}

	public void setListLicenze(List<LicenzaPortoArmi> listLicenze) {
		this.listLicenze = listLicenze;
	}

	public LicenzaPortoArmi getSelected() {
		return selected;
	}

	public void setSelected(LicenzaPortoArmi selected) {
		this.selected = selected;
	}
}