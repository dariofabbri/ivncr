package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.Armamento;
import it.ivncr.erp.model.personale.StatoArma;
import it.ivncr.erp.model.personale.TipoArma;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.armamento.ArmamentoService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.util.ArrayList;
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
public class DettaglioAddettoArmamenti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoArmamenti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoArma;
	private String modelloArma;
	private String calibroArma;
	private String matricola;
	private Integer codiceStatoArma;
	private Date dataDenuncia;
	private Date dataInizio;
	private Date dataFine;
	private String note;

	private List<TipoArma> listTipoArma;
	private List<StatoArma> listStatoArma;
	private List<String> listModelloArma;
	private List<String> listCalibroArma;

	private List<Armamento> listArmamenti;

	private Armamento selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo arma LUT.
		//
		listTipoArma = lutService.listItems("TipoArma");

		// Load stato arma LUT.
		//
		listStatoArma = lutService.listItems("StatoArma");

		// Load modello arma LUT.
		//
		listModelloArma = lutService.listItemsSingleColumn("ModelloArma", "descrizione");

		// Load calibro arma LUT.
		//
		listCalibroArma = lutService.listItemsSingleColumn("CalibroArma", "descrizione");

		// Load list for data table.
		//
		loadArmamenti();

		logger.debug("Initialization performed.");
	}


	public void loadArmamenti() {

		if(addettoId == null)
			return;

		ArmamentoService as = ServiceFactory.createService("Armamento");
		listArmamenti = as.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		ArmamentoService as = ServiceFactory.createService("Armamento");
		selected = as.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoArma = selected.getTipoArma() != null ? selected.getTipoArma().getId() : null;
		modelloArma = selected.getModelloArma();
		calibroArma = selected.getCalibroArma();
		matricola = selected.getMatricola();
		codiceStatoArma = selected.getStatoArma() != null ? selected.getStatoArma().getId() : null;
		dataDenuncia = selected.getDataDenuncia();
		dataInizio = selected.getDataInizio();
		dataFine = selected.getDataFine();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoArma = null;
		modelloArma = null;
		calibroArma = null;
		matricola = null;
		codiceStatoArma = null;
		dataDenuncia = null;
		dataInizio = null;
		dataFine = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		ArmamentoService as = ServiceFactory.createService("Armamento");

		try {
			Armamento entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = as.update(
						id,
						codiceTipoArma,
						modelloArma,
						calibroArma,
						matricola,
						codiceStatoArma,
						dataDenuncia,
						dataInizio,
						dataFine,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = as.create(
						addettoId,
						codiceTipoArma,
						modelloArma,
						calibroArma,
						matricola,
						codiceStatoArma,
						dataDenuncia,
						dataInizio,
						dataFine,
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
			loadArmamenti();

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
		ArmamentoService as = ServiceFactory.createService("Armamento");

		try {
			as.delete(selected.getId());

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
			loadArmamenti();

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public List<String> completeModelloArma(String query) {

		String q = query.toUpperCase();

		List<String> result = new ArrayList<String>();

		for(String s : listModelloArma) {
			if(s.toUpperCase().contains(q)) {
				result.add(s);
			}
		}

		return result;
	}


	public List<String> completeCalibroArma(String query) {

		String q = query.toUpperCase();

		List<String> result = new ArrayList<String>();

		for(String s : listCalibroArma) {
			if(s.toUpperCase().contains(q)) {
				result.add(s);
			}
		}

		return result;
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

	public Integer getCodiceTipoArma() {
		return codiceTipoArma;
	}

	public void setCodiceTipoArma(Integer codiceTipoArma) {
		this.codiceTipoArma = codiceTipoArma;
	}

	public String getModelloArma() {
		return modelloArma;
	}

	public void setModelloArma(String modelloArma) {
		this.modelloArma = modelloArma;
	}

	public String getCalibroArma() {
		return calibroArma;
	}

	public void setCalibroArma(String calibroArma) {
		this.calibroArma = calibroArma;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Integer getCodiceStatoArma() {
		return codiceStatoArma;
	}

	public void setCodiceStatoArma(Integer codiceStatoArma) {
		this.codiceStatoArma = codiceStatoArma;
	}

	public Date getDataDenuncia() {
		return dataDenuncia;
	}

	public void setDataDenuncia(Date dataDenuncia) {
		this.dataDenuncia = dataDenuncia;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<TipoArma> getListTipoArma() {
		return listTipoArma;
	}

	public void setListTipoArma(List<TipoArma> listTipoArma) {
		this.listTipoArma = listTipoArma;
	}

	public List<StatoArma> getListStatoArma() {
		return listStatoArma;
	}

	public void setListStatoArma(List<StatoArma> listStatoArma) {
		this.listStatoArma = listStatoArma;
	}

	public List<Armamento> getListArmamenti() {
		return listArmamenti;
	}

	public void setListArmamenti(List<Armamento> listArmamenti) {
		this.listArmamenti = listArmamenti;
	}

	public Armamento getSelected() {
		return selected;
	}

	public void setSelected(Armamento selected) {
		this.selected = selected;
	}
}