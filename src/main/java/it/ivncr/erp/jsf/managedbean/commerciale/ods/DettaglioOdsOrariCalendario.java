package it.ivncr.erp.jsf.managedbean.commerciale.ods;

import it.ivncr.erp.model.commerciale.ods.OdsOrariCalendario;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.odsoraricalendario.OdsOrariCalendarioService;

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
public class DettaglioOdsOrariCalendario implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsOrariCalendario.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioOdsGenerale}")
	private DettaglioOdsGenerale dettaglioOdsGenerale;

	private List<OdsOrariCalendario> listOdsOrariCalendario;
	private List<OdsOrariCalendario> selectedOdsOrariCalendario;

	private Date dataServizio;
	private Date dataInizioPeriodo;
	private Date dataFinePeriodo;
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

		OdsOrariCalendarioService oocs = ServiceFactory.createService("OdsOrariCalendario");
		listOdsOrariCalendario = oocs.listByOrdineServizio(dettaglioOdsGenerale.getId());
	}


	public void startAddGiorno() {

		logger.debug("Entering startAddGiorno() method.");

		// Clean up form status.
		//
		clean();
	}


	public void startAddPeriodo() {

		logger.debug("Entering startAddPeriodo() method.");

		// Clean up form status.
		//
		clean();
	}


	public void startRemovePeriodo() {

		logger.debug("Entering startRemovePeriodo() method.");

		// Clean up form status.
		//
		clean();
	}


	public void doAddGiorno() {

		OdsOrariCalendarioService oocs = ServiceFactory.createService("OdsOrariCalendario");

		// Check if the specified giorno is already present.
		//
		if(oocs.isGiornoPresent(dettaglioOdsGenerale.getId(), dataServizio)) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Giorno già presente",
					"Il giorno selezionato è già presente nella lista.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

		// Add the new record.
		//
		try {
			oocs.create(
					dettaglioOdsGenerale.getId(),
					dataServizio,
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


	public void doAddPeriodo() {

		OdsOrariCalendarioService oocs = ServiceFactory.createService("OdsOrariCalendario");

		// Check if the specified period is overlapping with already present days.
		//
		if(oocs.isPeriodoPresent(dettaglioOdsGenerale.getId(), dataInizioPeriodo, dataFinePeriodo)) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Periodo in sovrapposizione",
					"Il periodo selezionato si sovrappone a giorni già presenti nella lista.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

		// Add the new record.
		//
		try {
			oocs.createPeriodo(
					dettaglioOdsGenerale.getId(),
					dataInizioPeriodo,
					dataFinePeriodo,
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


	public void doRemovePeriodo() {

	}


	public void doRemoveSelected() {

	}

	private void clean() {

		dataServizio = null;
		dataInizioPeriodo = null;
		dataFinePeriodo = null;
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

	public List<OdsOrariCalendario> getListOdsOrariCalendario() {
		return listOdsOrariCalendario;
	}

	public void setListOdsOrariCalendario(
			List<OdsOrariCalendario> listOdsOrariCalendario) {
		this.listOdsOrariCalendario = listOdsOrariCalendario;
	}

	public List<OdsOrariCalendario> getSelectedOdsOrariCalendario() {
		return selectedOdsOrariCalendario;
	}

	public void setSelectedOdsOrariCalendario(
			List<OdsOrariCalendario> selectedOdsOrariCalendario) {
		this.selectedOdsOrariCalendario = selectedOdsOrariCalendario;
	}

	public Date getDataServizio() {
		return dataServizio;
	}

	public void setDataServizio(Date dataServizio) {
		this.dataServizio = dataServizio;
	}

	public Date getDataInizioPeriodo() {
		return dataInizioPeriodo;
	}

	public void setDataInizioPeriodo(Date dataInizioPeriodo) {
		this.dataInizioPeriodo = dataInizioPeriodo;
	}

	public Date getDataFinePeriodo() {
		return dataFinePeriodo;
	}

	public void setDataFinePeriodo(Date dataFinePeriodo) {
		this.dataFinePeriodo = dataFinePeriodo;
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