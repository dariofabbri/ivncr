package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.generale.Provincia;
import it.ivncr.erp.model.personale.EstensioneDecreto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.estensionedecreto.EstensioneDecretoService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.util.ArrayList;
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
public class DettaglioAddettoEstensioni implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoEstensioni.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Provincia provincia;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private String note;

	private List<Provincia> listProvincia;

	private List<EstensioneDecreto> listEstensioni;

	private EstensioneDecreto selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load province LUT.
		//
		listProvincia = lutService.listItems("Provincia");

		// Load list for data table.
		//
		loadEstensioni();

		logger.debug("Initialization performed.");
	}


	public void loadEstensioni() {

		if(addettoId == null)
			return;

		EstensioneDecretoService es = ServiceFactory.createService("EstensioneDecreto");
		listEstensioni = es.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		EstensioneDecretoService es = ServiceFactory.createService("EstensioneDecreto");
		selected = es.retrieve(selected.getId());

		Provincia provinciaEntity = new Provincia();
		provinciaEntity.setSigla(selected.getProvincia());

		id = selected.getId();
		provincia = provinciaEntity;
		dataInizioValidita = selected.getDataInizioValidita();
		dataFineValidita = selected.getDataFineValidita();
		note = selected.getNote();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		provincia = null;
		dataInizioValidita = null;
		dataFineValidita = null;
		note = null;
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		EstensioneDecretoService es = ServiceFactory.createService("EstensioneDecreto");

		try {
			EstensioneDecreto entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = es.update(
						id,
						provincia != null ? provincia.getSigla() : null,
						dataInizioValidita,
						dataFineValidita,
						note);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = es.create(
						addettoId,
						provincia != null ? provincia.getSigla() : null,
						dataInizioValidita,
						dataFineValidita,
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
			loadEstensioni();

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
		EstensioneDecretoService es = ServiceFactory.createService("EstensioneDecreto");

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
			loadEstensioni();

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public List<Provincia> completeProvincia(String query) {

		String q = query.toUpperCase();

		List<Provincia> result = new ArrayList<Provincia>();

		for(Provincia provincia : listProvincia) {
			if(provincia.getSigla().contains(q)) {
				result.add(provincia);
			}
		}

		return result;
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Provincia> getListProvincia() {
		return listProvincia;
	}

	public void setListProvincia(List<Provincia> listProvincia) {
		this.listProvincia = listProvincia;
	}

	public List<EstensioneDecreto> getListEstensioni() {
		return listEstensioni;
	}

	public void setListEstensioni(List<EstensioneDecreto> listEstensioni) {
		this.listEstensioni = listEstensioni;
	}

	public EstensioneDecreto getSelected() {
		return selected;
	}

	public void setSelected(EstensioneDecreto selected) {
		this.selected = selected;
	}
}