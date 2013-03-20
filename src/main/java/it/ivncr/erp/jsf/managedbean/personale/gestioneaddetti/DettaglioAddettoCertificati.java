package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.CertificatoMedico;
import it.ivncr.erp.model.personale.TipoCertificatoMedico;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.certificatomedico.CertificatoMedicoService;
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
public class DettaglioAddettoCertificati implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoCertificati.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoCertificato;
	private Date dataCertificato;
	private Date dataRicezione;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private String note;

	private List<TipoCertificatoMedico> listTipoCertificatoMedico;

	private List<CertificatoMedico> listCertificati;

	private CertificatoMedico selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo certificato medico LUT.
		//
		listTipoCertificatoMedico = lutService.listItems("TipoCertificatoMedico");

		// Load list for data table.
		//
		loadCertificati();

		logger.debug("Initialization performed.");
	}


	public void loadCertificati() {

		if(addettoId == null)
			return;

		CertificatoMedicoService cs = ServiceFactory.createService("CertificatoMedico");
		listCertificati = cs.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		CertificatoMedicoService cs = ServiceFactory.createService("CertificatoMedico");
		selected = cs.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoCertificato = selected.getTipoCertificato() != null ? selected.getTipoCertificato().getId() : null;
		dataCertificato = selected.getDataCertificato();
		dataRicezione = selected.getDataRicezione();
		dataInizioValidita = selected.getDataInizioValidita();
		dataFineValidita = selected.getDataFineValidita();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoCertificato = null;
		dataCertificato = null;
		dataRicezione = null;
		dataInizioValidita = null;
		dataFineValidita = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		CertificatoMedicoService cs = ServiceFactory.createService("CertificatoMedico");

		try {
			CertificatoMedico entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = cs.update(
						id,
						codiceTipoCertificato,
						dataCertificato,
						dataRicezione,
						dataInizioValidita,
						dataFineValidita,
						note);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = cs.create(
						addettoId,
						codiceTipoCertificato,
						dataCertificato,
						dataRicezione,
						dataInizioValidita,
						dataFineValidita,
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
			loadCertificati();

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
		CertificatoMedicoService cs = ServiceFactory.createService("CertificatoMedico");

		try {
			cs.delete(selected.getId());

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
			loadCertificati();

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

	public Integer getCodiceTipoCertificato() {
		return codiceTipoCertificato;
	}

	public void setCodiceTipoCertificato(Integer codiceTipoCertificato) {
		this.codiceTipoCertificato = codiceTipoCertificato;
	}

	public Date getDataCertificato() {
		return dataCertificato;
	}

	public void setDataCertificato(Date dataCertificato) {
		this.dataCertificato = dataCertificato;
	}

	public Date getDataRicezione() {
		return dataRicezione;
	}

	public void setDataRicezione(Date dataRicezione) {
		this.dataRicezione = dataRicezione;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<TipoCertificatoMedico> getListTipoCertificatoMedico() {
		return listTipoCertificatoMedico;
	}

	public void setListTipoCertificatoMedico(
			List<TipoCertificatoMedico> listTipoCertificatoMedico) {
		this.listTipoCertificatoMedico = listTipoCertificatoMedico;
	}

	public List<CertificatoMedico> getListCertificati() {
		return listCertificati;
	}

	public void setListCertificati(List<CertificatoMedico> listCertificati) {
		this.listCertificati = listCertificati;
	}

	public CertificatoMedico getSelected() {
		return selected;
	}

	public void setSelected(CertificatoMedico selected) {
		this.selected = selected;
	}
}