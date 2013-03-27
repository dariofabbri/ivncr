package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.Familiare;
import it.ivncr.erp.model.personale.TipoFamiliare;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.familiare.FamiliareService;
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
public class DettaglioAddettoFamiliari implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoFamiliari.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoFamiliare;
	private String nome;
	private String cognome;
	private String luogoNascita;
	private Date dataNascita;
	private String note;

	private List<TipoFamiliare> listTipoFamiliare;

	private List<Familiare> listFamiliari;

	private Familiare selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo familiare LUT.
		//
		listTipoFamiliare = lutService.listItems("TipoFamiliare");

		// Load list for data table.
		//
		loadFamiliari();
	}


	public void loadFamiliari() {

		if(addettoId == null)
			return;

		FamiliareService fs = ServiceFactory.createService("Familiare");
		listFamiliari = fs.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		FamiliareService fs = ServiceFactory.createService("FamiliareService");
		selected = fs.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoFamiliare = selected.getTipoFamiliare().getId();
		nome = selected.getNome();
		cognome = selected.getCognome();
		luogoNascita = selected.getLuogoNascita();
		dataNascita = selected.getDataNascita();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoFamiliare = null;
		nome = null;
		cognome = null;
		luogoNascita = null;
		dataNascita = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		FamiliareService fs = ServiceFactory.createService("Familiare");

		try {
			Familiare familiare = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				familiare = fs.update(
						id,
						codiceTipoFamiliare,
						nome,
						cognome,
						luogoNascita,
						dataNascita,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				familiare = fs.create(
						addettoId,
						codiceTipoFamiliare,
						nome,
						cognome,
						luogoNascita,
						dataNascita,
						note);
				id = familiare.getId();

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
			loadFamiliari();

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

		// Create service to delete specified record.
		//
		FamiliareService fs = ServiceFactory.createService("Familiare");

		try {
			fs.delete(selected.getId());

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
			loadFamiliari();

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

	public Integer getCodiceTipoFamiliare() {
		return codiceTipoFamiliare;
	}

	public void setCodiceTipoFamiliare(Integer codiceTipoFamiliare) {
		this.codiceTipoFamiliare = codiceTipoFamiliare;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<TipoFamiliare> getListTipoFamiliare() {
		return listTipoFamiliare;
	}

	public void setListTipoFamiliare(List<TipoFamiliare> listTipoFamiliare) {
		this.listTipoFamiliare = listTipoFamiliare;
	}

	public List<Familiare> getListFamiliari() {
		return listFamiliari;
	}

	public void setListFamiliari(List<Familiare> listFamiliari) {
		this.listFamiliari = listFamiliari;
	}

	public Familiare getSelected() {
		return selected;
	}

	public void setSelected(Familiare selected) {
		this.selected = selected;
	}
}