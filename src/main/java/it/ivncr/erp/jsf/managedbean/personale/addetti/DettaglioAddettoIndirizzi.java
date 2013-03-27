package it.ivncr.erp.jsf.managedbean.personale.addetti;

import it.ivncr.erp.model.commerciale.cliente.TipoIndirizzo;
import it.ivncr.erp.model.generale.Provincia;
import it.ivncr.erp.model.personale.IndirizzoAddetto;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.indirizzoaddetto.IndirizzoAddettoService;
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
public class DettaglioAddettoIndirizzi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoIndirizzi.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioAddettoGenerale.id}")
	private Integer addettoId;

	private Integer id;
	private Integer codiceTipoIndirizzo;
	private String presso;
	private String toponimo;
	private String indirizzo;
	private String civico;
	private String localita;
	private String cap;
	private Provincia provincia;
	private String paese;
	private Date validoDa;
	private Date validoA;

	private List<TipoIndirizzo> listTipoIndirizzo;
	private List<Provincia> listProvincia;
	private List<String> listPaese;

	private List<IndirizzoAddetto> listIndirizzi;

	private IndirizzoAddetto selected;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo indirizzo LUT.
		//
		listTipoIndirizzo = lutService.listItems("TipoIndirizzoAddetto");

		// Load province LUT.
		//
		listProvincia = lutService.listItems("Provincia");

		// Load paese LUT.
		//
		listPaese = lutService.listItemsSingleColumn("Paese", "descrizione");

		// Load list for data table.
		//
		loadIndirizzi();

		logger.debug("Initialization performed.");
	}


	public void loadIndirizzi() {

		if(addettoId == null)
			return;

		IndirizzoAddettoService ias = ServiceFactory.createService("IndirizzoAddetto");
		listIndirizzi = ias.listByAddetto(addettoId);
	}


	public void startUpdate() {

		logger.debug("Entering startUpdate() method.");

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		IndirizzoAddettoService ias = ServiceFactory.createService("IndirizzoAddetto");
		selected = ias.retrieveDeep(selected.getId());

		Provincia provinciaEntity = new Provincia();
		provinciaEntity.setSigla(selected.getProvincia());

		id = selected.getId();
		codiceTipoIndirizzo = selected.getTipoIndirizzo().getId();
		presso = selected.getPresso();
		toponimo = selected.getToponimo();
		indirizzo = selected.getIndirizzo();
		civico = selected.getCivico();
		localita = selected.getLocalita();
		cap = selected.getCap();
		provincia = provinciaEntity;
		paese = selected.getPaese();
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}


	public void startCreate() {

		logger.debug("Entering startCreate() method.");

		id = null;
		codiceTipoIndirizzo = null;
		presso = null;
		toponimo = null;
		indirizzo = null;
		civico = null;
		localita = null;
		cap = null;
		provincia = null;
		paese = null;
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
		IndirizzoAddettoService ias = ServiceFactory.createService("IndirizzoAddetto");

		try {
			IndirizzoAddetto entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = ias.update(
						id,
						codiceTipoIndirizzo,
						presso,
						toponimo,
						indirizzo,
						civico,
						localita,
						cap,
						provincia != null ? provincia.getSigla() : null,
						paese,
						validoDa,
						validoA);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = ias.create(
						addettoId,
						codiceTipoIndirizzo,
						presso,
						toponimo,
						indirizzo,
						civico,
						localita,
						cap,
						provincia != null ? provincia.getSigla() : null,
						paese,
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
			loadIndirizzi();

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
		IndirizzoAddettoService ias = ServiceFactory.createService("IndirizzoAddetto");

		try {
			ias.delete(selected.getId());

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
			loadIndirizzi();

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


	public List<String> completePaese(String query) {

		String q = query.toUpperCase();

		List<String> result = new ArrayList<String>();

		for(String s : listPaese) {
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

	public Integer getCodiceTipoIndirizzo() {
		return codiceTipoIndirizzo;
	}

	public void setCodiceTipoIndirizzo(Integer codiceTipoIndirizzo) {
		this.codiceTipoIndirizzo = codiceTipoIndirizzo;
	}

	public String getPresso() {
		return presso;
	}

	public void setPresso(String presso) {
		this.presso = presso;
	}

	public String getToponimo() {
		return toponimo;
	}

	public void setToponimo(String toponimo) {
		this.toponimo = toponimo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
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

	public List<TipoIndirizzo> getListTipoIndirizzo() {
		return listTipoIndirizzo;
	}

	public void setListTipoIndirizzo(List<TipoIndirizzo> listTipoIndirizzo) {
		this.listTipoIndirizzo = listTipoIndirizzo;
	}

	public List<Provincia> getListProvincia() {
		return listProvincia;
	}

	public void setListProvincia(List<Provincia> listProvincia) {
		this.listProvincia = listProvincia;
	}

	public List<String> getListPaese() {
		return listPaese;
	}

	public void setListPaese(List<String> listPaese) {
		this.listPaese = listPaese;
	}

	public List<IndirizzoAddetto> getListIndirizzi() {
		return listIndirizzi;
	}

	public void setListIndirizzi(List<IndirizzoAddetto> listIndirizzi) {
		this.listIndirizzi = listIndirizzi;
	}

	public IndirizzoAddetto getSelected() {
		return selected;
	}

	public void setSelected(IndirizzoAddetto selected) {
		this.selected = selected;
	}
}