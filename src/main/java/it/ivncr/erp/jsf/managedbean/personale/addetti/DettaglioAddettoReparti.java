package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.AddettoReparto;
import it.ivncr.erp.model.personale.Reparto;
import it.ivncr.erp.model.personale.RuoloAziendale;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.addettoreparto.AddettoRepartoService;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.reparto.RepartoService;

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
public class DettaglioAddettoReparti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoReparti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer codiceReparto;
	private Integer codiceRuolo;
	private Date validoDa;
	private Date validoA;

	private List<Reparto> listReparto;
	private List<RuoloAziendale> listRuoloAziendale;

	private List<AddettoReparto> listReparti;

	private AddettoReparto selected;


	@PostConstruct
	public void init() {

		// Load ruolo aziendale LUT.
		//
		LUTService lutService = ServiceFactory.createService("LUT");
		listRuoloAziendale = lutService.listItems("RuoloAziendale");

		// Load values for reparto filtering by current azienda.
		//
		RepartoService rs = ServiceFactory.createService("Reparto");
		listReparto = rs.listByAzienda(loginInfo.getCodiceAzienda());

		// Load list for data table.
		//
		loadReparti();

		logger.debug("Initialization performed.");
	}


	public void loadReparti() {

		if(addettoId == null)
			return;

		AddettoRepartoService as = ServiceFactory.createService("AddettoReparto");
		listReparti = as.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		AddettoRepartoService as = ServiceFactory.createService("AddettoReparto");
		selected = as.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceReparto = selected.getReparto() != null ? selected.getReparto().getId() : null;
		codiceRuolo = selected.getRuolo() != null ? selected.getRuolo().getId() : null;
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceReparto = null;
		codiceRuolo = null;
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
		AddettoRepartoService as = ServiceFactory.createService("AddettoReparto");

		try {
			AddettoReparto entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = as.update(
						id,
						codiceReparto,
						codiceRuolo,
						validoDa,
						validoA);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = as.create(
						addettoId,
						codiceReparto,
						codiceRuolo,
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
			loadReparti();

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
		AddettoRepartoService as = ServiceFactory.createService("AddettoReparto");

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
			loadReparti();

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

	public Integer getCodiceReparto() {
		return codiceReparto;
	}

	public void setCodiceReparto(Integer codiceReparto) {
		this.codiceReparto = codiceReparto;
	}

	public Integer getCodiceRuolo() {
		return codiceRuolo;
	}

	public void setCodiceRuolo(Integer codiceRuolo) {
		this.codiceRuolo = codiceRuolo;
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

	public List<Reparto> getListReparto() {
		return listReparto;
	}

	public void setListReparto(List<Reparto> listReparto) {
		this.listReparto = listReparto;
	}

	public List<RuoloAziendale> getListRuoloAziendale() {
		return listRuoloAziendale;
	}

	public void setListRuoloAziendale(List<RuoloAziendale> listRuoloAziendale) {
		this.listRuoloAziendale = listRuoloAziendale;
	}

	public List<AddettoReparto> getListReparti() {
		return listReparti;
	}

	public void setListReparti(List<AddettoReparto> listReparti) {
		this.listReparti = listReparti;
	}

	public AddettoReparto getSelected() {
		return selected;
	}

	public void setSelected(AddettoReparto selected) {
		this.selected = selected;
	}
}