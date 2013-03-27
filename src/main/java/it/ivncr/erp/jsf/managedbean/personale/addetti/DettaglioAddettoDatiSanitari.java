package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.generale.Provincia;
import it.ivncr.erp.model.personale.DatiSanitari;
import it.ivncr.erp.model.personale.GruppoSanguigno;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.datisanitari.DatiSanitariService;
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
public class DettaglioAddettoDatiSanitari implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoDatiSanitari.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private String medicoBase;
	private Integer codiceGruppoSanguigno;
	private String asl;
	private String indirizzoAsl;
	private String comune;
	private Provincia provincia;
	private Date validoDa;
	private Date validoA;

	private List<GruppoSanguigno> listGruppoSanguigno;
	private List<Provincia> listProvincia;
	private List<String> listComune;

	private List<DatiSanitari> listDatiSanitari;

	private DatiSanitari selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load gruppo sanguigno LUT.
		//
		listGruppoSanguigno = lutService.listItems("GruppoSanguigno");

		// Load province LUT.
		//
		listProvincia = lutService.listItems("Provincia");

		// Load distinct values for comune autocomplete.
		//
		DatiSanitariService ds = ServiceFactory.createService("DatiSanitari");
		listComune = ds.listDistinctComune();

		// Load list for data table.
		//
		loadDatiSanitari();

		logger.debug("Initialization performed.");
	}


	public void loadDatiSanitari() {

		if(addettoId == null)
			return;

		DatiSanitariService ds = ServiceFactory.createService("DatiSanitari");
		listDatiSanitari = ds.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		DatiSanitariService ds = ServiceFactory.createService("DatiSanitari");
		selected = ds.retrieveDeep(selected.getId());

		Provincia provinciaEntity = new Provincia();
		provinciaEntity.setSigla(selected.getProvincia());

		id = selected.getId();
		medicoBase = selected.getMedicoBase();
		codiceGruppoSanguigno = selected.getGruppoSanguigno() != null ? selected.getGruppoSanguigno().getId() : null;
		asl = selected.getAsl();
		indirizzoAsl = selected.getIndirizzoAsl();
		comune = selected.getComune();
		provincia = provinciaEntity;
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		medicoBase = null;
		codiceGruppoSanguigno = null;
		asl = null;
		indirizzoAsl = null;
		comune = null;
		provincia = null;
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
		DatiSanitariService ds = ServiceFactory.createService("DatiSanitari");

		try {
			DatiSanitari entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ds.update(
						id,
						medicoBase,
						codiceGruppoSanguigno,
						asl,
						indirizzoAsl,
						comune,
						provincia != null ? provincia.getSigla() : null,
						validoDa,
						validoA);
				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ds.create(
						addettoId,
						medicoBase,
						codiceGruppoSanguigno,
						asl,
						indirizzoAsl,
						comune,
						provincia != null ? provincia.getSigla() : null,
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
			loadDatiSanitari();

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
		DatiSanitariService ds = ServiceFactory.createService("DatiSanitari");

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
			loadDatiSanitari();

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


	public List<String> completeComune(String query) {

		String q = query.toUpperCase();

		List<String> result = new ArrayList<String>();

		for(String s : listComune) {
			if(s.toUpperCase().contains(q)) {
				result.add(s);
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


	public String getMedicoBase() {
		return medicoBase;
	}


	public void setMedicoBase(String medicoBase) {
		this.medicoBase = medicoBase;
	}


	public Integer getCodiceGruppoSanguigno() {
		return codiceGruppoSanguigno;
	}


	public void setCodiceGruppoSanguigno(Integer codiceGruppoSanguigno) {
		this.codiceGruppoSanguigno = codiceGruppoSanguigno;
	}


	public String getAsl() {
		return asl;
	}


	public void setAsl(String asl) {
		this.asl = asl;
	}


	public String getIndirizzoAsl() {
		return indirizzoAsl;
	}


	public void setIndirizzoAsl(String indirizzoAsl) {
		this.indirizzoAsl = indirizzoAsl;
	}


	public String getComune() {
		return comune;
	}


	public void setComune(String comune) {
		this.comune = comune;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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


	public List<GruppoSanguigno> getListGruppoSanguigno() {
		return listGruppoSanguigno;
	}


	public void setListGruppoSanguigno(List<GruppoSanguigno> listGruppoSanguigno) {
		this.listGruppoSanguigno = listGruppoSanguigno;
	}


	public List<DatiSanitari> getListDatiSanitari() {
		return listDatiSanitari;
	}


	public void setListDatiSanitari(List<DatiSanitari> listDatiSanitari) {
		this.listDatiSanitari = listDatiSanitari;
	}


	public DatiSanitari getSelected() {
		return selected;
	}


	public void setSelected(DatiSanitari selected) {
		this.selected = selected;
	}
}