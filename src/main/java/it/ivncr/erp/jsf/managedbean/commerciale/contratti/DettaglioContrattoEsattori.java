package it.ivncr.erp.jsf.managedbean.commerciale.contratti;

import it.ivncr.erp.model.commerciale.contratto.ContrattoEsattore;
import it.ivncr.erp.model.commerciale.contratto.Esattore;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.contrattoesattore.ContrattoEsattoreService;
import it.ivncr.erp.service.esattore.EsattoreService;

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
public class DettaglioContrattoEsattori implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoEsattori.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private Integer id;
	private Date validoDa;
	private Date validoA;

	private List<ContrattoEsattore> listEsattori;
	private ContrattoEsattore selected;

	private List<Esattore> listEsattoriDisponibili;
	private Esattore selectedEsattore;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadEsattori();

		logger.debug("Initialization performed.");
	}


	public void loadEsattori() {

		if(dettaglioContrattoGenerale.getId() == null)
			return;

		ContrattoEsattoreService ces = ServiceFactory.createService("ContrattoEsattore");
		listEsattori = ces.listByContratto(dettaglioContrattoGenerale.getId());
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		ContrattoEsattoreService ces = ServiceFactory.createService("ContrattoEsattore");
		selected = ces.retrieveDeep(selected.getId());

		id = selected.getId();

		selectedEsattore = selected.getEsattore();
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();

		// Load list of available contatti records.
		//
		EsattoreService es = ServiceFactory.createService("Esattore");
		listEsattoriDisponibili = es.listDisponibiliPerContratto(
				dettaglioContrattoGenerale.getId(),
				selectedEsattore.getId());
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		selectedEsattore = null;
		validoDa = null;
		validoA = null;

		// Load list of available contatti records.
		//
		EsattoreService es = ServiceFactory.createService("Esattore");
		listEsattoriDisponibili = es.listDisponibiliPerContratto(
				dettaglioContrattoGenerale.getId());
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		ContrattoEsattoreService ces = ServiceFactory.createService("ContrattoEsattore");

		try {
			ContrattoEsattore entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ces.update(
						id,
						selectedEsattore.getId(),
						validoDa,
						validoA);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ces.create(
						dettaglioContrattoGenerale.getId(),
						selectedEsattore.getId(),
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
			loadEsattori();

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

		// Create service to persist data.
		//
		ContrattoEsattoreService ces = ServiceFactory.createService("ContrattoEsattore");

		try {
			ces.delete(selected.getId());

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
			loadEsattori();

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

	public DettaglioContrattoGenerale getDettaglioContrattoGenerale() {
		return dettaglioContrattoGenerale;
	}

	public void setDettaglioContrattoGenerale(
			DettaglioContrattoGenerale dettaglioContrattoGenerale) {
		this.dettaglioContrattoGenerale = dettaglioContrattoGenerale;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ContrattoEsattore> getListEsattori() {
		return listEsattori;
	}

	public void setListEsattori(List<ContrattoEsattore> listEsattori) {
		this.listEsattori = listEsattori;
	}

	public ContrattoEsattore getSelected() {
		return selected;
	}

	public void setSelected(ContrattoEsattore selected) {
		this.selected = selected;
	}

	public List<Esattore> getListEsattoriDisponibili() {
		return listEsattoriDisponibili;
	}

	public void setListEsattoriDisponibili(
			List<Esattore> listEsattoriDisponibili) {
		this.listEsattoriDisponibili = listEsattoriDisponibili;
	}

	public Esattore getSelectedEsattore() {
		return selectedEsattore;
	}

	public void setSelectedEsattore(Esattore selectedEsattore) {
		this.selectedEsattore = selectedEsattore;
	}
}