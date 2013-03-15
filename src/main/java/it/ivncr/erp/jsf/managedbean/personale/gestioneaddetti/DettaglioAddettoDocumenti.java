package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.Documento;
import it.ivncr.erp.model.personale.TipoDocumento;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.documento.DocumentoService;
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
public class DettaglioAddettoDocumenti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoDocumenti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoDocumento;
	private String numero;
	private String rilasciatoDa;
	private Date dataRilascio;
	private Date dataScadenza;

	private List<TipoDocumento> listTipoDocumento;

	private List<Documento> listDocumenti;

	private Documento selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo documento LUT.
		//
		listTipoDocumento = lutService.listItems("TipoDocumento");

		// Load list for data table.
		//
		loadDocumenti();

		logger.debug("Initialization performed.");
	}


	public void loadDocumenti() {

		if(addettoId == null)
			return;

		DocumentoService ds = ServiceFactory.createService("Documento");
		listDocumenti = ds.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		DocumentoService ds = ServiceFactory.createService("Documento");
		selected = ds.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoDocumento = selected.getTipoDocumento() != null ? selected.getTipoDocumento().getId() : null;
		numero = selected.getNumero();
		rilasciatoDa = selected.getRilasciatoDa();
		dataRilascio = selected.getDataRilascio();
		dataScadenza = selected.getDataScadenza();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoDocumento = null;
		numero = null;
		rilasciatoDa = null;
		dataRilascio = null;
		dataScadenza = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		DocumentoService ds = ServiceFactory.createService("Documento");

		try {
			Documento entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ds.update(
						id,
						codiceTipoDocumento,
						numero,
						rilasciatoDa,
						dataRilascio,
						dataScadenza);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ds.create(
						addettoId,
						codiceTipoDocumento,
						numero,
						rilasciatoDa,
						dataRilascio,
						dataScadenza);
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
			loadDocumenti();

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
		DocumentoService ds = ServiceFactory.createService("Documento");

		try {
			ds.delete(selected.getId());

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
			loadDocumenti();

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

	public Integer getCodiceTipoDocumento() {
		return codiceTipoDocumento;
	}

	public void setCodiceTipoDocumento(Integer codiceTipoDocumento) {
		this.codiceTipoDocumento = codiceTipoDocumento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRilasciatoDa() {
		return rilasciatoDa;
	}

	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}

	public Date getDataRilascio() {
		return dataRilascio;
	}

	public void setDataRilascio(Date dataRilascio) {
		this.dataRilascio = dataRilascio;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public List<TipoDocumento> getListTipoDocumento() {
		return listTipoDocumento;
	}

	public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
		this.listTipoDocumento = listTipoDocumento;
	}

	public List<Documento> getListDocumenti() {
		return listDocumenti;
	}

	public void setListDocumenti(List<Documento> listDocumenti) {
		this.listDocumenti = listDocumenti;
	}

	public Documento getSelected() {
		return selected;
	}

	public void setSelected(Documento selected) {
		this.selected = selected;
	}
}