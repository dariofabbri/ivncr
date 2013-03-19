package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.EsercitazioneTiro;
import it.ivncr.erp.model.personale.TipoEsercitazioneTiro;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.esercitazionetiro.EsercitazioneTiroService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class DettaglioAddettoEsercitazioni implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoEsercitazioni.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Date dataTiro;
	private String poligono;
	private Integer codiceTipoEsercitazione;
	private BigDecimal importoRichiesto;
	private BigDecimal importoRimborsato;

	private List<TipoEsercitazioneTiro> listTipoEsercitazione;

	private List<EsercitazioneTiro> listEsercitazioni;

	private EsercitazioneTiro selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo esercitazione tiro LUT.
		//
		listTipoEsercitazione = lutService.listItems("TipoEsercitazioneTiro");

		// Load list for data table.
		//
		loadEsercitazioni();

		logger.debug("Initialization performed.");
	}


	public void loadEsercitazioni() {

		if(addettoId == null)
			return;

		EsercitazioneTiroService es = ServiceFactory.createService("EsercitazioneTiro");
		listEsercitazioni = es.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		EsercitazioneTiroService es = ServiceFactory.createService("EsercitazioneTiro");
		selected = es.retrieveDeep(selected.getId());

		id = selected.getId();
		dataTiro = selected.getDataTiro();
		poligono = selected.getPoligono();
		codiceTipoEsercitazione = selected.getTipoEsercitazioneTiro() != null ? selected.getTipoEsercitazioneTiro().getId() : null;
		importoRichiesto = selected.getImportoRichiesto();
		importoRimborsato = selected.getImportoRimborsato();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		dataTiro = null;
		poligono = null;
		codiceTipoEsercitazione = null;
		importoRichiesto = null;
		importoRimborsato = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		EsercitazioneTiroService es = ServiceFactory.createService("EsercitazioneTiro");

		try {
			EsercitazioneTiro entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = es.update(
						id,
						dataTiro,
						poligono,
						codiceTipoEsercitazione,
						importoRichiesto,
						importoRimborsato);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = es.create(
						addettoId,
						dataTiro,
						poligono,
						codiceTipoEsercitazione,
						importoRichiesto,
						importoRimborsato);
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
			loadEsercitazioni();

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
		EsercitazioneTiroService es = ServiceFactory.createService("EsercitazioneTiro");

		try {
			es.delete(selected.getId());

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
			loadEsercitazioni();

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

	public Date getDataTiro() {
		return dataTiro;
	}

	public void setDataTiro(Date dataTiro) {
		this.dataTiro = dataTiro;
	}

	public String getPoligono() {
		return poligono;
	}

	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}

	public Integer getCodiceTipoEsercitazione() {
		return codiceTipoEsercitazione;
	}

	public void setCodiceTipoEsercitazione(Integer codiceTipoEsercitazione) {
		this.codiceTipoEsercitazione = codiceTipoEsercitazione;
	}

	public BigDecimal getImportoRichiesto() {
		return importoRichiesto;
	}

	public void setImportoRichiesto(BigDecimal importoRichiesto) {
		this.importoRichiesto = importoRichiesto;
	}

	public BigDecimal getImportoRimborsato() {
		return importoRimborsato;
	}

	public void setImportoRimborsato(BigDecimal importoRimborsato) {
		this.importoRimborsato = importoRimborsato;
	}

	public List<TipoEsercitazioneTiro> getListTipoEsercitazione() {
		return listTipoEsercitazione;
	}

	public void setListTipoEsercitazione(
			List<TipoEsercitazioneTiro> listTipoEsercitazione) {
		this.listTipoEsercitazione = listTipoEsercitazione;
	}

	public List<EsercitazioneTiro> getListEsercitazioni() {
		return listEsercitazioni;
	}

	public void setListEsercitazioni(List<EsercitazioneTiro> listEsercitazioni) {
		this.listEsercitazioni = listEsercitazioni;
	}

	public EsercitazioneTiro getSelected() {
		return selected;
	}

	public void setSelected(EsercitazioneTiro selected) {
		this.selected = selected;
	}
}