package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.personale.VisitaMedicoCompetente;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.visitamedicocompetente.VisitaMedicoCompetenteService;

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
public class DettaglioAddettoVisitePeriodiche implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoVisitePeriodiche.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private String medico;
	private Date dataVisita;
	private String esito;
	private Date dataVisitaSuccessiva;

	private List<VisitaMedicoCompetente> listVisite;

	private VisitaMedicoCompetente selected;


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

		VisitaMedicoCompetenteService vs = ServiceFactory.createService("VisitaMedicoCompetente");
		listVisite = vs.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		VisitaMedicoCompetenteService vs = ServiceFactory.createService("VisitaMedicoCompetente");
		selected = vs.retrieve(selected.getId());

		id = selected.getId();
		medico = selected.getMedico();
		dataVisita = selected.getDataVisita();
		esito = selected.getEsito();
		dataVisitaSuccessiva = selected.getDataVisitaSuccessiva();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		medico = null;
		dataVisita = null;
		esito = null;
		dataVisitaSuccessiva = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		VisitaMedicoCompetenteService vs = ServiceFactory.createService("VisitaMedicoCompetente");

		try {
			VisitaMedicoCompetente entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = vs.update(
						id,
						medico,
						dataVisita,
						esito,
						dataVisitaSuccessiva);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = vs.create(
						addettoId,
						medico,
						dataVisita,
						esito,
						dataVisitaSuccessiva);
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
		VisitaMedicoCompetenteService vs = ServiceFactory.createService("VisitaMedicoCompetente");

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

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public Date getDataVisitaSuccessiva() {
		return dataVisitaSuccessiva;
	}

	public void setDataVisitaSuccessiva(Date dataVisitaSuccessiva) {
		this.dataVisitaSuccessiva = dataVisitaSuccessiva;
	}

	public List<VisitaMedicoCompetente> getListVisite() {
		return listVisite;
	}

	public void setListVisite(List<VisitaMedicoCompetente> listVisite) {
		this.listVisite = listVisite;
	}

	public VisitaMedicoCompetente getSelected() {
		return selected;
	}

	public void setSelected(VisitaMedicoCompetente selected) {
		this.selected = selected;
	}
}