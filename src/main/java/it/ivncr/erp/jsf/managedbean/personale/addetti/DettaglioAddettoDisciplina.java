package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.Disciplina;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.disciplina.DisciplinaService;

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
public class DettaglioAddettoDisciplina implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoDisciplina.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private String provvedimento;
	private Date dataProvvedimento;
	private String note;

	private List<Disciplina> listDisciplina;

	private Disciplina selected;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadDisciplina();

		logger.debug("Initialization performed.");
	}


	public void loadDisciplina() {

		if(addettoId == null)
			return;

		DisciplinaService ds = ServiceFactory.createService("Disciplina");
		listDisciplina = ds.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		DisciplinaService ds = ServiceFactory.createService("Disciplina");
		selected = ds.retrieve(selected.getId());

		id = selected.getId();
		provvedimento = selected.getProvvedimento();
		dataProvvedimento = selected.getDataProvvedimento();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		provvedimento = null;
		dataProvvedimento = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		DisciplinaService ds = ServiceFactory.createService("Disciplina");

		try {
			Disciplina entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ds.update(
						id,
						provvedimento,
						dataProvvedimento,
						note);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ds.create(
						addettoId,
						provvedimento,
						dataProvvedimento,
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
			loadDisciplina();

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
		DisciplinaService ds = ServiceFactory.createService("Disciplina");

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
			loadDisciplina();

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

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvvedimento() {
		return provvedimento;
	}

	public void setProvvedimento(String provvedimento) {
		this.provvedimento = provvedimento;
	}

	public Date getDataProvvedimento() {
		return dataProvvedimento;
	}

	public void setDataProvvedimento(Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Disciplina> getListDisciplina() {
		return listDisciplina;
	}

	public void setListDisciplina(List<Disciplina> listDisciplina) {
		this.listDisciplina = listDisciplina;
	}

	public Disciplina getSelected() {
		return selected;
	}

	public void setSelected(Disciplina selected) {
		this.selected = selected;
	}
}