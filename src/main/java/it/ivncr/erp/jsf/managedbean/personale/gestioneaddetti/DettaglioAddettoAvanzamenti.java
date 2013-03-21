package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.AvanzamentoCarriera;
import it.ivncr.erp.model.personale.LivelloCcnl;
import it.ivncr.erp.model.personale.Qualifica;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.avanzamentocarriera.AvanzamentoCarrieraService;
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
public class DettaglioAddettoAvanzamenti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoAvanzamenti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer codiceQualifica;
	private Integer codiceLivelloCcnl;
	private Date validoDa;
	private Date validoA;

	private List<Qualifica> listQualifica;
	private List<LivelloCcnl> listLivelloCcnl;

	private List<AvanzamentoCarriera> listAvanzamenti;

	private AvanzamentoCarriera selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load qualifica LUT.
		//
		listQualifica = lutService.listItems("Qualifica");

		// Load livello CCNL LUT.
		//
		listLivelloCcnl = lutService.listItems("LivelloCcnl");

		// Load list for data table.
		//
		loadAvanzamenti();

		logger.debug("Initialization performed.");
	}


	public void loadAvanzamenti() {

		if(addettoId == null)
			return;

		AvanzamentoCarrieraService as = ServiceFactory.createService("AvanzamentoCarriera");
		listAvanzamenti = as.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		AvanzamentoCarrieraService as = ServiceFactory.createService("AvanzamentoCarriera");
		selected = as.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceQualifica = selected.getQualifica() != null ? selected.getQualifica().getId() : null;
		codiceLivelloCcnl = selected.getLivelloCcnl() != null ? selected.getLivelloCcnl().getId() : null;
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceQualifica = null;
		codiceLivelloCcnl = null;
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
		AvanzamentoCarrieraService as = ServiceFactory.createService("AvanzamentoCarriera");

		try {
			AvanzamentoCarriera entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = as.update(
						id,
						codiceQualifica,
						codiceLivelloCcnl,
						validoDa,
						validoA);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = as.create(
						addettoId,
						codiceQualifica,
						codiceLivelloCcnl,
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
			loadAvanzamenti();

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
		AvanzamentoCarrieraService as = ServiceFactory.createService("AvanzamentoCarriera");

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
			loadAvanzamenti();

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

	public Integer getCodiceQualifica() {
		return codiceQualifica;
	}

	public void setCodiceQualifica(Integer codiceQualifica) {
		this.codiceQualifica = codiceQualifica;
	}

	public Integer getCodiceLivelloCcnl() {
		return codiceLivelloCcnl;
	}

	public void setCodiceLivelloCcnl(Integer codiceLivelloCcnl) {
		this.codiceLivelloCcnl = codiceLivelloCcnl;
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

	public List<Qualifica> getListQualifica() {
		return listQualifica;
	}

	public void setListQualifica(List<Qualifica> listQualifica) {
		this.listQualifica = listQualifica;
	}

	public List<LivelloCcnl> getListLivelloCcnl() {
		return listLivelloCcnl;
	}

	public void setListLivelloCcnl(List<LivelloCcnl> listLivelloCcnl) {
		this.listLivelloCcnl = listLivelloCcnl;
	}

	public List<AvanzamentoCarriera> getListAvanzamenti() {
		return listAvanzamenti;
	}

	public void setListAvanzamenti(List<AvanzamentoCarriera> listAvanzamenti) {
		this.listAvanzamenti = listAvanzamenti;
	}

	public AvanzamentoCarriera getSelected() {
		return selected;
	}

	public void setSelected(AvanzamentoCarriera selected) {
		this.selected = selected;
	}
}