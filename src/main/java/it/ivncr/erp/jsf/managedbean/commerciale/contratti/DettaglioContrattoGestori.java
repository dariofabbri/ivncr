package it.ivncr.erp.jsf.managedbean.commerciale.contratti;

import it.ivncr.erp.model.commerciale.contratto.ContrattoGestore;
import it.ivncr.erp.model.commerciale.contratto.GestoreContratto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.contrattogestore.ContrattoGestoreService;
import it.ivncr.erp.service.gestorecontratto.GestoreContrattoService;

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
public class DettaglioContrattoGestori implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoGestori.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private Integer id;
	private Date validoDa;
	private Date validoA;

	private List<ContrattoGestore> listGestori;
	private ContrattoGestore selected;

	private List<GestoreContratto> listGestoriDisponibili;
	private GestoreContratto selectedGestore;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadGestori();

		logger.debug("Initialization performed.");
	}


	public void loadGestori() {

		if(dettaglioContrattoGenerale.getId() == null)
			return;

		ContrattoGestoreService cgs = ServiceFactory.createService("ContrattoGestore");
		listGestori = cgs.listByContratto(dettaglioContrattoGenerale.getId());
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		ContrattoGestoreService cgs = ServiceFactory.createService("ContrattoGestore");
		selected = cgs.retrieveDeep(selected.getId());

		id = selected.getId();

		selectedGestore = selected.getGestore();
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();

		// Load list of available contatti records.
		//
		GestoreContrattoService gcs = ServiceFactory.createService("GestoreContratto");
		listGestoriDisponibili = gcs.listDisponibiliPerContratto(
				dettaglioContrattoGenerale.getId(),
				selectedGestore.getId());
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		selectedGestore = null;
		validoDa = null;
		validoA = null;

		// Load list of available contatti records.
		//
		GestoreContrattoService gcs = ServiceFactory.createService("GestoreContratto");
		listGestoriDisponibili = gcs.listDisponibiliPerContratto(
				dettaglioContrattoGenerale.getId());
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		ContrattoGestoreService cgs = ServiceFactory.createService("ContrattoGestore");

		try {
			ContrattoGestore entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = cgs.update(
						id,
						selectedGestore.getId(),
						validoDa,
						validoA);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = cgs.create(
						dettaglioContrattoGenerale.getId(),
						selectedGestore.getId(),
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
			loadGestori();

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
		ContrattoGestoreService cgs = ServiceFactory.createService("ContrattoGestore");

		try {
			cgs.delete(selected.getId());

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
			loadGestori();

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

	public List<ContrattoGestore> getListGestori() {
		return listGestori;
	}

	public void setListGestori(List<ContrattoGestore> listGestori) {
		this.listGestori = listGestori;
	}

	public ContrattoGestore getSelected() {
		return selected;
	}

	public void setSelected(ContrattoGestore selected) {
		this.selected = selected;
	}

	public List<GestoreContratto> getListGestoriDisponibili() {
		return listGestoriDisponibili;
	}

	public void setListGestoriDisponibili(
			List<GestoreContratto> listGestoriDisponibili) {
		this.listGestoriDisponibili = listGestoriDisponibili;
	}

	public GestoreContratto getSelectedGestore() {
		return selectedGestore;
	}

	public void setSelectedGestore(GestoreContratto selectedGestore) {
		this.selectedGestore = selectedGestore;
	}

}