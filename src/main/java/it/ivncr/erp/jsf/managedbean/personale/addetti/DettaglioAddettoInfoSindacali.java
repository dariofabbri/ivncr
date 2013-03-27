package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.CaricaSindacale;
import it.ivncr.erp.model.personale.InfoSindacali;
import it.ivncr.erp.model.personale.SiglaSindacale;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.infosindacali.InfoSindacaliService;
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
public class DettaglioAddettoInfoSindacali implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoInfoSindacali.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer codiceSiglaSindacale;
	private Integer codiceCaricaSindacale;
	private Date validoDa;
	private Date validoA;

	private List<SiglaSindacale> listSiglaSindacale;
	private List<CaricaSindacale> listCaricaSindacale;

	private List<InfoSindacali> listInfoSindacali;

	private InfoSindacali selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load sigla sindacale LUT.
		//
		listSiglaSindacale = lutService.listItems("SiglaSindacale");

		// Load livello CCNL LUT.
		//
		listCaricaSindacale = lutService.listItems("CaricaSindacale");

		// Load list for data table.
		//
		loadInfoSindacali();

		logger.debug("Initialization performed.");
	}


	public void loadInfoSindacali() {

		if(addettoId == null)
			return;

		InfoSindacaliService is = ServiceFactory.createService("InfoSindacali");
		listInfoSindacali = is.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		InfoSindacaliService is = ServiceFactory.createService("InfoSindacali");
		selected = is.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceSiglaSindacale = selected.getSiglaSindacale() != null ? selected.getSiglaSindacale().getId() : null;
		codiceCaricaSindacale = selected.getCaricaSindacale() != null ? selected.getCaricaSindacale().getId() : null;
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceSiglaSindacale = null;
		codiceCaricaSindacale = null;
		validoDa = null;
		validoA = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		InfoSindacaliService is = ServiceFactory.createService("InfoSindacali");

		try {
			InfoSindacali entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = is.update(
						id,
						codiceSiglaSindacale,
						codiceCaricaSindacale,
						validoDa,
						validoA);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = is.create(
						addettoId,
						codiceSiglaSindacale,
						codiceCaricaSindacale,
						validoDa,
						validoA);
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
			loadInfoSindacali();

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
		InfoSindacaliService is = ServiceFactory.createService("InfoSindacali");

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
			loadInfoSindacali();

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

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodiceSiglaSindacale() {
		return codiceSiglaSindacale;
	}

	public void setCodiceSiglaSindacale(Integer codiceSiglaSindacale) {
		this.codiceSiglaSindacale = codiceSiglaSindacale;
	}

	public Integer getCodiceCaricaSindacale() {
		return codiceCaricaSindacale;
	}

	public void setCodiceCaricaSindacale(Integer codiceCaricaSindacale) {
		this.codiceCaricaSindacale = codiceCaricaSindacale;
	}

	public Date getValidoDa() {
		return validoDa;
	}

	public void setValidoDa(Date validoDa) {
		this.validoDa = validoDa;
	}

	public Date getValidoA() {
		return validoA;
	}

	public void setValidoA(Date validoA) {
		this.validoA = validoA;
	}

	public List<SiglaSindacale> getListSiglaSindacale() {
		return listSiglaSindacale;
	}

	public void setListSiglaSindacale(List<SiglaSindacale> listSiglaSindacale) {
		this.listSiglaSindacale = listSiglaSindacale;
	}

	public List<CaricaSindacale> getListCaricaSindacale() {
		return listCaricaSindacale;
	}

	public void setListCaricaSindacale(List<CaricaSindacale> listCaricaSindacale) {
		this.listCaricaSindacale = listCaricaSindacale;
	}

	public List<InfoSindacali> getListInfoSindacali() {
		return listInfoSindacali;
	}

	public void setListInfoSindacali(List<InfoSindacali> listInfoSindacali) {
		this.listInfoSindacali = listInfoSindacali;
	}

	public InfoSindacali getSelected() {
		return selected;
	}

	public void setSelected(InfoSindacali selected) {
		this.selected = selected;
	}
}