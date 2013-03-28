package it.ivncr.erp.jsf.managedbean.commerciale.contratti;

import it.ivncr.erp.model.commerciale.cliente.Contatto;
import it.ivncr.erp.model.commerciale.contratto.ContrattoContatto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.contatto.ContattoService;
import it.ivncr.erp.service.contrattocontatto.ContrattoContattoService;

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
public class DettaglioContrattoContatti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoContatti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale.id}")
	private Integer contrattoId;

	private Integer id;
	private Date validoDa;
	private Date validoA;

	private List<ContrattoContatto> listContatti;
	private ContrattoContatto selected;

	private List<Contatto> listContattiDisponibili;
	private Contatto selectedContatto;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadContatti();

		logger.debug("Initialization performed.");
	}


	public void loadContatti() {

		if(contrattoId == null)
			return;

		ContrattoContattoService cs = ServiceFactory.createService("ContrattoContatto");
		listContatti = cs.listByContratto(contrattoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		ContrattoContattoService ccs = ServiceFactory.createService("ContrattoContatto");
		selected = ccs.retrieveDeep(selected.getId());

		id = selected.getId();

		selectedContatto = selected.getContatto();
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();

		// Load list of available contatti records.
		//
		ContattoService cs = ServiceFactory.createService("Contatto");
		listContattiDisponibili = cs.listDisponibiliPerContratto(contrattoId, selectedContatto.getId());
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		selectedContatto = null;
		validoDa = null;
		validoA = null;

		// Load list of available contatti records.
		//
		ContattoService cs = ServiceFactory.createService("Contatto");
		listContattiDisponibili = cs.listDisponibiliPerContratto(contrattoId);
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		ContrattoContattoService cs = ServiceFactory.createService("ContrattoContatto");

		try {
			ContrattoContatto entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = cs.update(
						id,
						selectedContatto.getId(),
						validoDa,
						validoA);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = cs.create(
						contrattoId,
						selectedContatto.getId(),
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
			loadContatti();

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
		ContrattoContattoService cs = ServiceFactory.createService("ContrattoContatto");

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
			loadContatti();

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


	public Integer getContrattoId() {
		return contrattoId;
	}

	public void setContrattoId(Integer contrattoId) {
		this.contrattoId = contrattoId;
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

	public List<ContrattoContatto> getListContatti() {
		return listContatti;
	}

	public void setListContatti(List<ContrattoContatto> listContatti) {
		this.listContatti = listContatti;
	}

	public ContrattoContatto getSelected() {
		return selected;
	}

	public void setSelected(ContrattoContatto selected) {
		this.selected = selected;
	}

	public List<Contatto> getListContattiDisponibili() {
		return listContattiDisponibili;
	}

	public void setListContattiDisponibili(
			List<Contatto> listContattiDisponibili) {
		this.listContattiDisponibili = listContattiDisponibili;
	}

	public Contatto getSelectedContatto() {
		return selectedContatto;
	}

	public void setSelectedContatto(Contatto selectedContatto) {
		this.selectedContatto = selectedContatto;
	}
}