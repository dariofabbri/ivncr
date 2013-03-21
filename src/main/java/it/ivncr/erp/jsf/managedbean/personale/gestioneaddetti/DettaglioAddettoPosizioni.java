package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.personale.Ccnl;
import it.ivncr.erp.model.personale.PosizioneLavorativa;
import it.ivncr.erp.model.personale.TipoContratto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.posizionelavorativa.PosizioneLavorativaService;

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
public class DettaglioAddettoPosizioni implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoPosizioni.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer codiceTipoContratto;
	private Integer codiceCcnl;
	private Integer codiceAzienda;
	private Integer durataContratto;
	private Date dataAssunzione;
	private Integer durataProva;
	private Date dataPrimoGiorno;
	private Date dataFineProva;
	private Date dataCessazione;
	private Date dataFineContratto;
	private String motivoDimissioni;

	private List<TipoContratto> listTipoContratto;
	private List<Ccnl> listCcnl;
	private List<Azienda> listAzienda;

	private List<PosizioneLavorativa> listPosizioni;

	private PosizioneLavorativa selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo contratto LUT.
		//
		listTipoContratto = lutService.listItems("TipoContratto");

		// Load CCNL LUT.
		//
		listCcnl = lutService.listItems("Ccnl");

		// Load Azienda LUT.
		//
		listAzienda = lutService.listItems("Azienda");

		// Load list for data table.
		//
		loadPosizioni();

		logger.debug("Initialization performed.");
	}


	public void loadPosizioni() {

		if(addettoId == null)
			return;

		PosizioneLavorativaService ps = ServiceFactory.createService("PosizioneLavorativa");
		listPosizioni = ps.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		PosizioneLavorativaService ps = ServiceFactory.createService("PosizioneLavorativa");
		selected = ps.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoContratto = selected.getTipoContratto() != null ? selected.getTipoContratto().getId() : null;
		codiceCcnl = selected.getCcnl() != null ? selected.getCcnl().getId() : null;
		codiceAzienda = selected.getAzienda() != null ? selected.getAzienda().getId() : null;
		durataContratto = selected.getDurataContratto();
		dataAssunzione = selected.getDataAssunzione();
		durataProva = selected.getDurataProva();
		dataPrimoGiorno = selected.getDataPrimoGiorno();
		dataFineProva = selected.getDataFineProva();
		dataCessazione = selected.getDataCessazione();
		dataFineContratto = selected.getDataFineContratto();
		motivoDimissioni = selected.getMotivoDimissioni();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoContratto = null;
		codiceCcnl = null;
		codiceAzienda = null;
		durataContratto = null;
		dataAssunzione = null;
		durataProva = null;
		dataPrimoGiorno = null;
		dataFineProva = null;
		dataCessazione = null;
		dataFineContratto = null;
		motivoDimissioni = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		PosizioneLavorativaService ps = ServiceFactory.createService("PosizioneLavorativa");

		try {
			PosizioneLavorativa entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ps.update(
						id,
						codiceTipoContratto,
						codiceCcnl,
						codiceAzienda,
						durataContratto,
						dataAssunzione,
						durataProva,
						dataPrimoGiorno,
						dataFineProva,
						dataCessazione,
						dataFineContratto,
						motivoDimissioni);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ps.create(
						addettoId,
						codiceTipoContratto,
						codiceCcnl,
						codiceAzienda,
						durataContratto,
						dataAssunzione,
						durataProva,
						dataPrimoGiorno,
						dataFineProva,
						dataCessazione,
						dataFineContratto,
						motivoDimissioni);
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
			loadPosizioni();

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
		PosizioneLavorativaService ps = ServiceFactory.createService("PosizioneLavorativa");

		try {
			ps.delete(selected.getId());

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
			loadPosizioni();

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

	public Integer getCodiceTipoContratto() {
		return codiceTipoContratto;
	}

	public void setCodiceTipoContratto(Integer codiceTipoContratto) {
		this.codiceTipoContratto = codiceTipoContratto;
	}

	public Integer getCodiceCcnl() {
		return codiceCcnl;
	}

	public void setCodiceCcnl(Integer codiceCcnl) {
		this.codiceCcnl = codiceCcnl;
	}

	public Integer getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(Integer codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public Integer getDurataContratto() {
		return durataContratto;
	}

	public void setDurataContratto(Integer durataContratto) {
		this.durataContratto = durataContratto;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Integer getDurataProva() {
		return durataProva;
	}

	public void setDurataProva(Integer durataProva) {
		this.durataProva = durataProva;
	}

	public Date getDataPrimoGiorno() {
		return dataPrimoGiorno;
	}

	public void setDataPrimoGiorno(Date dataPrimoGiorno) {
		this.dataPrimoGiorno = dataPrimoGiorno;
	}

	public Date getDataFineProva() {
		return dataFineProva;
	}

	public void setDataFineProva(Date dataFineProva) {
		this.dataFineProva = dataFineProva;
	}

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public Date getDataFineContratto() {
		return dataFineContratto;
	}

	public void setDataFineContratto(Date dataFineContratto) {
		this.dataFineContratto = dataFineContratto;
	}

	public String getMotivoDimissioni() {
		return motivoDimissioni;
	}

	public void setMotivoDimissioni(String motivoDimissioni) {
		this.motivoDimissioni = motivoDimissioni;
	}

	public List<TipoContratto> getListTipoContratto() {
		return listTipoContratto;
	}

	public void setListTipoContratto(List<TipoContratto> listTipoContratto) {
		this.listTipoContratto = listTipoContratto;
	}

	public List<Ccnl> getListCcnl() {
		return listCcnl;
	}

	public void setListCcnl(List<Ccnl> listCcnl) {
		this.listCcnl = listCcnl;
	}

	public List<Azienda> getListAzienda() {
		return listAzienda;
	}

	public void setListAzienda(List<Azienda> listAzienda) {
		this.listAzienda = listAzienda;
	}

	public List<PosizioneLavorativa> getListPosizioni() {
		return listPosizioni;
	}

	public void setListPosizioni(List<PosizioneLavorativa> listPosizioni) {
		this.listPosizioni = listPosizioni;
	}

	public PosizioneLavorativa getSelected() {
		return selected;
	}

	public void setSelected(PosizioneLavorativa selected) {
		this.selected = selected;
	}
}