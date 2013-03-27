package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.Lingua;
import it.ivncr.erp.model.personale.LinguaConosciuta;
import it.ivncr.erp.model.personale.LivelloLingua;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.linguaconosciuta.LinguaConosciutaService;
import it.ivncr.erp.service.lut.LUTService;

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
public class DettaglioAddettoLingue implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoLingue.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceLingua;
	private Integer codiceLivelloLingua;
	private String note;

	private List<Lingua> listLingua;
	private List<LivelloLingua> listLivelloLingua;

	private List<LinguaConosciuta> listLingue;

	private LinguaConosciuta selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load lingua LUT.
		//
		listLingua = lutService.listItems("Lingua");

		// Load livello lingua LUT.
		//
		listLivelloLingua = lutService.listItems("LivelloLingua");

		// Load list for data table.
		//
		loadLingue();

		logger.debug("Initialization performed.");
	}


	public void loadLingue() {

		if(addettoId == null)
			return;

		LinguaConosciutaService ls = ServiceFactory.createService("LinguaConosciuta");
		listLingue = ls.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		LinguaConosciutaService ls = ServiceFactory.createService("LinguaConosciuta");
		selected = ls.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceLingua = selected.getLingua() != null ? selected.getLingua().getId() : null;
		codiceLivelloLingua = selected.getLivelloLingua() != null ? selected.getLivelloLingua().getId() : null;
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceLingua = null;
		codiceLivelloLingua = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		LinguaConosciutaService ls = ServiceFactory.createService("LinguaConosciuta");

		try {
			LinguaConosciuta entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ls.update(
						id,
						codiceLingua,
						codiceLivelloLingua,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ls.create(
						addettoId,
						codiceLingua,
						codiceLivelloLingua,
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
			loadLingue();

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
		LinguaConosciutaService ls = ServiceFactory.createService("LinguaConosciuta");

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
			loadLingue();

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

	public Integer getCodiceLingua() {
		return codiceLingua;
	}

	public void setCodiceLingua(Integer codiceLingua) {
		this.codiceLingua = codiceLingua;
	}

	public Integer getCodiceLivelloLingua() {
		return codiceLivelloLingua;
	}

	public void setCodiceLivelloLingua(Integer codiceLivelloLingua) {
		this.codiceLivelloLingua = codiceLivelloLingua;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Lingua> getListLingua() {
		return listLingua;
	}

	public void setListLingua(List<Lingua> listLingua) {
		this.listLingua = listLingua;
	}

	public List<LivelloLingua> getListLivelloLingua() {
		return listLivelloLingua;
	}

	public void setListLivelloLingua(List<LivelloLingua> listLivelloLingua) {
		this.listLivelloLingua = listLivelloLingua;
	}

	public List<LinguaConosciuta> getListLingue() {
		return listLingue;
	}

	public void setListLingue(List<LinguaConosciuta> listLingue) {
		this.listLingue = listLingue;
	}

	public LinguaConosciuta getSelected() {
		return selected;
	}

	public void setSelected(LinguaConosciuta selected) {
		this.selected = selected;
	}
}