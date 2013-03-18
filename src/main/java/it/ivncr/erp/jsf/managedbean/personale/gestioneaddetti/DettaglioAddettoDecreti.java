package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.DecretoGpg;
import it.ivncr.erp.model.personale.TipoRinnovoDecretoGpg;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.decretogpg.DecretoGpgService;
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
public class DettaglioAddettoDecreti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoDecreti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoRinnovo;
	private String numero;
	private Date dataRilascio;
	private Date dataScadenza;
	private String note;

	private List<TipoRinnovoDecretoGpg> listTipoRinnovo;

	private List<DecretoGpg> listDecreti;

	private DecretoGpg selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo rinnovo decreto LUT.
		//
		listTipoRinnovo = lutService.listItems("TipoRinnovoDecretoGpg");

		// Load list for data table.
		//
		loadDecreti();

		logger.debug("Initialization performed.");
	}


	public void loadDecreti() {

		if(addettoId == null)
			return;

		DecretoGpgService ds = ServiceFactory.createService("DecretoGpg");
		listDecreti = ds.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		DecretoGpgService ds = ServiceFactory.createService("DecretoGpg");
		selected = ds.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoRinnovo = selected.getTipoRinnovo() != null ? selected.getTipoRinnovo().getId() : null;
		numero = selected.getNumero();
		dataRilascio = selected.getDataRilascio();
		dataScadenza = selected.getDataScadenza();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoRinnovo = null;
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
		DecretoGpgService ds = ServiceFactory.createService("DecretoGpg");

		try {
			DecretoGpg entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ds.update(
						id,
						codiceTipoRinnovo,
						numero,
						dataRilascio,
						dataScadenza,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ds.create(
						addettoId,
						codiceTipoRinnovo,
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
			loadDecreti();

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
		DecretoGpgService ds = ServiceFactory.createService("DecretoGpg");

		try {
			ds.delete(selected.getId());

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
			loadDecreti();

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

	public Integer getCodiceTipoRinnovo() {
		return codiceTipoRinnovo;
	}

	public void setCodiceTipoRinnovo(Integer codiceTipoRinnovo) {
		this.codiceTipoRinnovo = codiceTipoRinnovo;
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

	public List<TipoRinnovoDecretoGpg> getListTipoRinnovo() {
		return listTipoRinnovo;
	}

	public void setListTipoRinnovo(List<TipoRinnovoDecretoGpg> listTipoRinnovo) {
		this.listTipoRinnovo = listTipoRinnovo;
	}

	public List<DecretoGpg> getListDecreti() {
		return listDecreti;
	}

	public void setListDecreti(List<DecretoGpg> listDecreti) {
		this.listDecreti = listDecreti;
	}

	public DecretoGpg getSelected() {
		return selected;
	}

	public void setSelected(DecretoGpg selected) {
		this.selected = selected;
	}
}