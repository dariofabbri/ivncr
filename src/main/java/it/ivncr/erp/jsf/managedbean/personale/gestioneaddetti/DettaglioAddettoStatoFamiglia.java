package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.model.personale.GradoParentela;
import it.ivncr.erp.model.personale.StatoFamiglia;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.statofamiglia.StatoFamigliaService;

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
public class DettaglioAddettoStatoFamiglia implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoStatoFamiglia.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceGradoParentela;
	private String nome;
	private Date dataNascita;
	private Date validoDa;
	private Date validoA;

	private List<GradoParentela> listGradoParentela;

	private List<StatoFamiglia> listStatoFamiglia;

	private StatoFamiglia selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load grado parentela LUT.
		//
		listGradoParentela = lutService.listItems("GradoParentela");

		// Load list for data table.
		//
		loadStatoFamiglia();

		logger.debug("Initialization performed.");
	}


	public void loadStatoFamiglia() {

		if(addettoId == null)
			return;

		StatoFamigliaService sfs = ServiceFactory.createService("StatoFamiglia");
		listStatoFamiglia = sfs.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		StatoFamigliaService sfs = ServiceFactory.createService("StatoFamiglia");
		selected = sfs.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceGradoParentela = selected.getGradoParentela() != null ? selected.getGradoParentela().getId() : null;
		nome = selected.getNome();
		dataNascita = selected.getDataNascita();
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceGradoParentela = null;
		nome = null;
		dataNascita = null;
		validoDa = null;
		validoA = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		StatoFamigliaService sfs = ServiceFactory.createService("StatoFamiglia");

		try {
			StatoFamiglia entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = sfs.update(
						id,
						codiceGradoParentela,
						nome,
						dataNascita,
						validoDa,
						validoA);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = sfs.create(
						addettoId,
						codiceGradoParentela,
						nome,
						dataNascita,
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
			loadStatoFamiglia();

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
		StatoFamigliaService sfs = ServiceFactory.createService("StatoFamiglia");

		try {
			sfs.delete(selected.getId());

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
			loadStatoFamiglia();

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

	public Integer getCodiceGradoParentela() {
		return codiceGradoParentela;
	}

	public void setCodiceGradoParentela(Integer codiceGradoParentela) {
		this.codiceGradoParentela = codiceGradoParentela;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
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

	public List<GradoParentela> getListGradoParentela() {
		return listGradoParentela;
	}

	public void setListGradoParentela(List<GradoParentela> listGradoParentela) {
		this.listGradoParentela = listGradoParentela;
	}

	public List<StatoFamiglia> getListStatoFamiglia() {
		return listStatoFamiglia;
	}

	public void setListStatoFamiglia(List<StatoFamiglia> listStatoFamiglia) {
		this.listStatoFamiglia = listStatoFamiglia;
	}

	public StatoFamiglia getSelected() {
		return selected;
	}

	public void setSelected(StatoFamiglia selected) {
		this.selected = selected;
	}
}