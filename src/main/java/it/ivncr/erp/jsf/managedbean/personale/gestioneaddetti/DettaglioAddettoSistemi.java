package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.SistemaLavoro;
import it.ivncr.erp.model.personale.TipoSistemaLavoro;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.sistemalavoro.SistemaLavoroService;

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
public class DettaglioAddettoSistemi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoSistemi.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer codiceTipoSistemaLavoro;
	private Date validoDa;
	private Date validoA;

	private List<TipoSistemaLavoro> listTipoSistemaLavoro;

	private List<SistemaLavoro> listSistemi;

	private SistemaLavoro selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load ruolo aziendale LUT.
		//
		listTipoSistemaLavoro = lutService.listItems("TipoSistemaLavoro");

		// Load list for data table.
		//
		loadSistemi();

		logger.debug("Initialization performed.");
	}


	public void loadSistemi() {

		if(addettoId == null)
			return;

		SistemaLavoroService ss = ServiceFactory.createService("SistemaLavoro");
		listSistemi = ss.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		SistemaLavoroService ss = ServiceFactory.createService("SistemaLavoro");
		selected = ss.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoSistemaLavoro = selected.getTipoSistemaLavoro() != null ? selected.getTipoSistemaLavoro().getId() : null;
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoSistemaLavoro = null;
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
		SistemaLavoroService ss = ServiceFactory.createService("SistemaLavoro");

		try {
			SistemaLavoro entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ss.update(
						id,
						codiceTipoSistemaLavoro,
						validoDa,
						validoA);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ss.create(
						addettoId,
						codiceTipoSistemaLavoro,
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
			loadSistemi();

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
		SistemaLavoroService ss = ServiceFactory.createService("SistemaLavoro");

		try {
			ss.delete(selected.getId());

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
			loadSistemi();

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

	public Integer getCodiceTipoSistemaLavoro() {
		return codiceTipoSistemaLavoro;
	}

	public void setCodiceTipoSistemaLavoro(Integer codiceTipoSistemaLavoro) {
		this.codiceTipoSistemaLavoro = codiceTipoSistemaLavoro;
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

	public List<TipoSistemaLavoro> getListTipoSistemaLavoro() {
		return listTipoSistemaLavoro;
	}

	public void setListTipoSistemaLavoro(
			List<TipoSistemaLavoro> listTipoSistemaLavoro) {
		this.listTipoSistemaLavoro = listTipoSistemaLavoro;
	}

	public List<SistemaLavoro> getListSistemi() {
		return listSistemi;
	}

	public void setListSistemi(List<SistemaLavoro> listSistemi) {
		this.listSistemi = listSistemi;
	}

	public SistemaLavoro getSelected() {
		return selected;
	}

	public void setSelected(SistemaLavoro selected) {
		this.selected = selected;
	}
}