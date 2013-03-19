package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.Corso;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.corso.CorsoService;

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
public class DettaglioAddettoCorsi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoCorsi.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private String ente;
	private String abilitazione;
	private Integer oreCorso;
	private String valutazione;
	private Date dataConseguimento;
	private Date dataInizio;
	private Date dataFine;
	private String note;

	private List<Corso> listCorsi;

	private Corso selected;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadCorsi();

		logger.debug("Initialization performed.");
	}


	public void loadCorsi() {

		if(addettoId == null)
			return;

		CorsoService cs = ServiceFactory.createService("Corso");
		listCorsi = cs.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		CorsoService cs = ServiceFactory.createService("Corso");
		selected = cs.retrieve(selected.getId());

		id = selected.getId();
		ente = selected.getEnte();
		abilitazione = selected.getAbilitazione();
		oreCorso = selected.getOreCorso();
		valutazione = selected.getValutazione();
		dataConseguimento = selected.getDataConseguimento();
		dataInizio = selected.getDataInizio();
		dataFine = selected.getDataInizio();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		ente = null;
		abilitazione = null;
		oreCorso = null;
		valutazione = null;
		dataConseguimento = null;
		dataInizio = null;
		dataFine = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		CorsoService cs = ServiceFactory.createService("Corso");

		try {
			Corso entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = cs.update(
						id,
						ente,
						abilitazione,
						oreCorso,
						valutazione,
						dataConseguimento,
						dataInizio,
						dataFine,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = cs.create(
						addettoId,
						ente,
						abilitazione,
						oreCorso,
						valutazione,
						dataConseguimento,
						dataInizio,
						dataFine,
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
			loadCorsi();

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
		CorsoService cs = ServiceFactory.createService("Corso");

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
			loadCorsi();

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

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getAbilitazione() {
		return abilitazione;
	}

	public void setAbilitazione(String abilitazione) {
		this.abilitazione = abilitazione;
	}

	public Integer getOreCorso() {
		return oreCorso;
	}

	public void setOreCorso(Integer oreCorso) {
		this.oreCorso = oreCorso;
	}

	public String getValutazione() {
		return valutazione;
	}

	public void setValutazione(String valutazione) {
		this.valutazione = valutazione;
	}

	public Date getDataConseguimento() {
		return dataConseguimento;
	}

	public void setDataConseguimento(Date dataConseguimento) {
		this.dataConseguimento = dataConseguimento;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Corso> getListCorsi() {
		return listCorsi;
	}

	public void setListCorsi(List<Corso> listCorsi) {
		this.listCorsi = listCorsi;
	}

	public Corso getSelected() {
		return selected;
	}

	public void setSelected(Corso selected) {
		this.selected = selected;
	}
}