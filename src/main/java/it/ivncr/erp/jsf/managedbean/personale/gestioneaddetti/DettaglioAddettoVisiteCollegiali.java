package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.VisitaCollegiale;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.visitacollegiale.VisitaCollegialeService;

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
public class DettaglioAddettoVisiteCollegiali implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoVisiteCollegiali.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Date dataRichiesta;
	private String motivazione;
	private Date dataEsito;
	private String esito;

	private List<VisitaCollegiale> listVisite;

	private VisitaCollegiale selected;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadVisite();

		logger.debug("Initialization performed.");
	}


	public void loadVisite() {

		if(addettoId == null)
			return;

		VisitaCollegialeService vs = ServiceFactory.createService("VisitaCollegiale");
		listVisite = vs.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		VisitaCollegialeService vs = ServiceFactory.createService("VisitaCollegiale");
		selected = vs.retrieve(selected.getId());

		id = selected.getId();
		dataRichiesta = selected.getDataRichiesta();
		motivazione = selected.getMotivazione();
		dataEsito = selected.getDataEsito();
		esito = selected.getEsito();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		dataRichiesta = null;
		motivazione = null;
		dataEsito = null;
		esito = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		VisitaCollegialeService vs = ServiceFactory.createService("VisitaCollegiale");

		try {
			VisitaCollegiale entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = vs.update(
						id,
						dataRichiesta,
						motivazione,
						dataEsito,
						esito);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = vs.create(
						addettoId,
						dataRichiesta,
						motivazione,
						dataEsito,
						esito);
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
			loadVisite();

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
		VisitaCollegialeService vs = ServiceFactory.createService("VisitaCollegiale");

		try {
			vs.delete(selected.getId());

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
			loadVisite();

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

	public Date getDataRichiesta() {
		return dataRichiesta;
	}

	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public Date getDataEsito() {
		return dataEsito;
	}

	public void setDataEsito(Date dataEsito) {
		this.dataEsito = dataEsito;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public List<VisitaCollegiale> getListVisite() {
		return listVisite;
	}

	public void setListVisite(List<VisitaCollegiale> listVisite) {
		this.listVisite = listVisite;
	}

	public VisitaCollegiale getSelected() {
		return selected;
	}

	public void setSelected(VisitaCollegiale selected) {
		this.selected = selected;
	}
}