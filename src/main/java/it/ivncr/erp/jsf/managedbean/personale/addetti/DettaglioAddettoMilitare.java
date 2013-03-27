package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.Grado;
import it.ivncr.erp.model.personale.PosizioneMilitare;
import it.ivncr.erp.model.personale.TipoPosizioneMilitare;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.posizionemilitare.PosizioneMilitareService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioAddettoMilitare implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoMilitare.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoPosizione;
	private String presso;
	private Integer codiceGrado;
	private Date dataCongedo;
	private String note;

	private List<TipoPosizioneMilitare> listTipoPosizione;
	private List<Grado> listGrado;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo posizione militare LUT.
		//
		listTipoPosizione = lutService.listItems("TipoPosizioneMilitare");

		// Load grado LUT.
		//
		listGrado = lutService.listItems("Grado");

		// Load form data.
		//
		loadPosizioneMilitare();

		logger.debug("Initialization performed.");
	}


	public void loadPosizioneMilitare() {

		id = null;
		codiceTipoPosizione = null;
		presso = null;
		codiceGrado = null;
		dataCongedo = null;
		note = null;

		if(addettoId != null) {

			PosizioneMilitareService ps = ServiceFactory.createService("PosizioneMilitare");
			PosizioneMilitare entity = ps.retrieveByAddettoId(addettoId);

			if(entity != null) {
				id = entity.getId();
				codiceTipoPosizione = entity.getTipoPosizioneMilitare() != null ? entity.getTipoPosizioneMilitare().getId() : null;
				presso = entity.getPresso();
				codiceGrado = entity.getGrado() != null ? entity.getGrado().getId() : null;;
				dataCongedo = entity.getDataCongedo();
				note = entity.getNote();
			}
		}
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		PosizioneMilitareService ps = ServiceFactory.createService("PosizioneMilitare");

		try {
			PosizioneMilitare entity = null;
			entity = ps.setForAddetto(
					addettoId,
					codiceTipoPosizione,
					presso,
					codiceGrado,
					dataCongedo,
					note);
			id = entity.getId();

			logger.debug("Entity successfully saved.");

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch(Exception e) {

			logger.warn("Exception caught while saving entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di salvataggio del record.");
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

	public Integer getCodiceTipoPosizione() {
		return codiceTipoPosizione;
	}

	public void setCodiceTipoPosizione(Integer codiceTipoPosizione) {
		this.codiceTipoPosizione = codiceTipoPosizione;
	}

	public String getPresso() {
		return presso;
	}

	public void setPresso(String presso) {
		this.presso = presso;
	}

	public Integer getCodiceGrado() {
		return codiceGrado;
	}

	public void setCodiceGrado(Integer codiceGrado) {
		this.codiceGrado = codiceGrado;
	}

	public Date getDataCongedo() {
		return dataCongedo;
	}

	public void setDataCongedo(Date dataCongedo) {
		this.dataCongedo = dataCongedo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<TipoPosizioneMilitare> getListTipoPosizione() {
		return listTipoPosizione;
	}

	public void setListTipoPosizione(
			List<TipoPosizioneMilitare> listTipoPosizione) {
		this.listTipoPosizione = listTipoPosizione;
	}

	public List<Grado> getListGrado() {
		return listGrado;
	}

	public void setListGrado(List<Grado> listGrado) {
		this.listGrado = listGrado;
	}
}