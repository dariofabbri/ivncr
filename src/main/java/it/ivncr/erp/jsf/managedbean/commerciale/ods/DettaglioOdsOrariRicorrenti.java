package it.ivncr.erp.jsf.managedbean.commerciale.ods;

import it.ivncr.erp.model.commerciale.ods.OdsOrariRicorrenti;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.odsorariricorrenti.OdsOrariRicorrentiService;

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
public class DettaglioOdsOrariRicorrenti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsOrariRicorrenti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioOdsGenerale}")
	private DettaglioOdsGenerale dettaglioOdsGenerale;

	private List<OdsOrariRicorrenti> listOdsOrariRicorrenti;
	private List<OdsOrariRicorrenti> selectedOdsOrariRicorrenti;

	private Integer codiceGiornoSettimana;
	private Boolean esclusoFestivo;
	private Integer quantita1;
	private Date orarioInizio1;
	private Date orarioFine1;
	private Integer quantita2;
	private Date orarioInizio2;
	private Date orarioFine2;
	private Integer quantita3;
	private Date orarioInizio3;
	private Date orarioFine3;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadOrari();

		logger.debug("Initialization performed.");
	}


	public void loadOrari() {

		if(dettaglioOdsGenerale.getId() == null)
			return;

		OdsOrariRicorrentiService oors = ServiceFactory.createService("OdsOrariRicorrenti");
		listOdsOrariRicorrenti = oors.listByOrdineServizio(dettaglioOdsGenerale.getId());
	}


	public void startAdd() {

		logger.debug("Entering startAdd() method.");

		// Clean up form status.
		//
		clean();
	}


	public void doAddGiorno(int codiceGiornoSettimana) {

		OdsOrariRicorrentiService oors = ServiceFactory.createService("OdsOrariRicorrenti");

		// Add the new record.
		//
		try {
			oors.setGiorno(
					dettaglioOdsGenerale.getId(),
					codiceGiornoSettimana,
					esclusoFestivo,
					quantita1,
					orarioInizio1,
					orarioFine1,
					quantita2,
					orarioInizio2,
					orarioFine2,
					quantita3,
					orarioInizio3,
					orarioFine3);

			logger.debug("Entity successfully created.");

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Refresh list.
			//
			loadOrari();

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


	public void doAddGiorni(Integer... codiciGiornoSettimana) {

		OdsOrariRicorrentiService oors = ServiceFactory.createService("OdsOrariRicorrenti");

		// Add the new record.
		//
		try {
			oors.setGiorni(
					dettaglioOdsGenerale.getId(),
					codiciGiornoSettimana,
					esclusoFestivo,
					quantita1,
					orarioInizio1,
					orarioFine1,
					quantita2,
					orarioInizio2,
					orarioFine2,
					quantita3,
					orarioInizio3,
					orarioFine3);

			logger.debug("Entity successfully created.");

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Refresh list.
			//
			loadOrari();

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


	public void doRemoveSelected() {

		// Create service to delete the record.
		//
		OdsOrariRicorrentiService oors = ServiceFactory.createService("OdsOrariRicorrenti");

		try {
			// Extract ids from selection data model.
			//
			List<Integer> ids = new ArrayList<Integer>();
			for(OdsOrariRicorrenti oor : selectedOdsOrariRicorrenti) {
				ids.add(oor.getId());
			}

			// Delete extracted list of ids.
			//
			oors.delete(ids);

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"L'eliminazione dei record selezionati si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Reset selection.
			//
			selectedOdsOrariRicorrenti = null;

			// Refresh list.
			//
			loadOrari();

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione dei record selezionati.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public void doRemoveAll() {

		// Create service to delete the record.
		//
		OdsOrariRicorrentiService oors = ServiceFactory.createService("OdsOrariRicorrenti");

		try {
			// Delete all.
			//
			oors.deleteByOrdineServizio(dettaglioOdsGenerale.getId());

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"L'eliminazione dei record si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Refresh list.
			//
			loadOrari();

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione dei record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	private void clean() {

		codiceGiornoSettimana = null;
		esclusoFestivo = null;
		quantita1 = null;
		orarioInizio1 = null;
		orarioFine1 = null;
		quantita2 = null;
		orarioInizio2 = null;
		orarioFine2 = null;
		quantita3 = null;
		orarioInizio3 = null;
		orarioFine3 = null;
	}


	public DettaglioOdsGenerale getDettaglioOdsGenerale() {
		return dettaglioOdsGenerale;
	}

	public void setDettaglioOdsGenerale(
			DettaglioOdsGenerale dettaglioOdsGenerale) {
		this.dettaglioOdsGenerale = dettaglioOdsGenerale;
	}

	public List<OdsOrariRicorrenti> getListOdsOrariRicorrenti() {
		return listOdsOrariRicorrenti;
	}

	public void setListOdsOrariRicorrenti(
			List<OdsOrariRicorrenti> listOdsOrariRicorrenti) {
		this.listOdsOrariRicorrenti = listOdsOrariRicorrenti;
	}

	public List<OdsOrariRicorrenti> getSelectedOdsOrariRicorrenti() {
		return selectedOdsOrariRicorrenti;
	}

	public void setSelectedOdsOrariRicorrenti(
			List<OdsOrariRicorrenti> selectedOdsOrariRicorrenti) {
		this.selectedOdsOrariRicorrenti = selectedOdsOrariRicorrenti;
	}

	public Integer getCodiceGiornoSettimana() {
		return codiceGiornoSettimana;
	}

	public void setCodiceGiornoSettimana(Integer codiceGiornoSettimana) {
		this.codiceGiornoSettimana = codiceGiornoSettimana;
	}

	public Boolean getEsclusoFestivo() {
		return esclusoFestivo;
	}

	public void setEsclusoFestivo(Boolean esclusoFestivo) {
		this.esclusoFestivo = esclusoFestivo;
	}

	public Integer getQuantita1() {
		return quantita1;
	}

	public void setQuantita1(Integer quantita1) {
		this.quantita1 = quantita1;
	}

	public Date getOrarioInizio1() {
		return orarioInizio1;
	}

	public void setOrarioInizio1(Date orarioInizio1) {
		this.orarioInizio1 = orarioInizio1;
	}

	public Date getOrarioFine1() {
		return orarioFine1;
	}

	public void setOrarioFine1(Date orarioFine1) {
		this.orarioFine1 = orarioFine1;
	}

	public Integer getQuantita2() {
		return quantita2;
	}

	public void setQuantita2(Integer quantita2) {
		this.quantita2 = quantita2;
	}

	public Date getOrarioInizio2() {
		return orarioInizio2;
	}

	public void setOrarioInizio2(Date orarioInizio2) {
		this.orarioInizio2 = orarioInizio2;
	}

	public Date getOrarioFine2() {
		return orarioFine2;
	}

	public void setOrarioFine2(Date orarioFine2) {
		this.orarioFine2 = orarioFine2;
	}

	public Integer getQuantita3() {
		return quantita3;
	}

	public void setQuantita3(Integer quantita3) {
		this.quantita3 = quantita3;
	}

	public Date getOrarioInizio3() {
		return orarioInizio3;
	}

	public void setOrarioInizio3(Date orarioInizio3) {
		this.orarioInizio3 = orarioInizio3;
	}

	public Date getOrarioFine3() {
		return orarioFine3;
	}

	public void setOrarioFine3(Date orarioFine3) {
		this.orarioFine3 = orarioFine3;
	}
}