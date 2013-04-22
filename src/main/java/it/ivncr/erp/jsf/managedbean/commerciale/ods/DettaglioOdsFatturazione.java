package it.ivncr.erp.jsf.managedbean.commerciale.ods;


import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.odsfrazionamento.OdsFrazionamentoService;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

import java.io.Serializable;
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
public class DettaglioOdsFatturazione implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsFatturazione.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioOdsGenerale}")
	private DettaglioOdsGenerale dettaglioOdsGenerale;

	private Boolean oneroso;
	private Integer codiceTariffa;
	private Integer codiceCanone;
	private Integer codiceRaggruppamentoFatturazione;
	private List<OdsFrazionamento> listOdsFrazionamento;
	private String osservazioniFattura;

	private List<Tariffa> listTariffa;
	private List<Canone> listCanone;
	private List<RaggruppamentoFatturazione> listRaggruppamentoFatturazione;

	private OdsFrazionamento selectedOdsFrazionamento;


	public DettaglioOdsFatturazione() {
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load raggruppamento fatturazione LUT.
		//
		listRaggruppamentoFatturazione = lutService.listItems("RaggruppamentoFatturazione");

		// Load available canone and tariffa items.
		//
		OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
		listTariffa = oss.listAvailableTariffa(dettaglioOdsGenerale.getId());
		listCanone = oss.listAvailableCanone(dettaglioOdsGenerale.getId());

		// Load frazionamento.
		//
		OdsFrazionamentoService ofs = ServiceFactory.createService("OdsFrazionamento");
		listOdsFrazionamento = ofs.listByOrdineServizio(dettaglioOdsGenerale.getId());

		logger.debug("Initialization performed.");
	}

	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Save the entity.
		//
		try {
			OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
			oss.updateFatturazione(
					dettaglioOdsGenerale.getId(),
					oneroso,
					codiceTariffa,
					codiceCanone,
					codiceRaggruppamentoFatturazione,
					osservazioniFattura,
					listOdsFrazionamento);
			logger.debug("Entity successfully updated.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record aggiornato",
					"La modifica dell'ordine di servizio si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

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


	private boolean formValidations() {

		// At least one between canone and tariffa must be selected.
		//

		// Check that the sum of quota adds up to 100.
		//

		return true;
	}

	public DettaglioOdsGenerale getDettaglioOdsGenerale() {
		return dettaglioOdsGenerale;
	}

	public void setDettaglioOdsGenerale(DettaglioOdsGenerale dettaglioOdsGenerale) {
		this.dettaglioOdsGenerale = dettaglioOdsGenerale;
	}

	public Boolean getOneroso() {
		return oneroso;
	}

	public void setOneroso(Boolean oneroso) {
		this.oneroso = oneroso;
	}

	public Integer getCodiceTariffa() {
		return codiceTariffa;
	}

	public void setCodiceTariffa(Integer codiceTariffa) {
		this.codiceTariffa = codiceTariffa;
	}

	public Integer getCodiceCanone() {
		return codiceCanone;
	}

	public void setCodiceCanone(Integer codiceCanone) {
		this.codiceCanone = codiceCanone;
	}

	public Integer getCodiceRaggruppamentoFatturazione() {
		return codiceRaggruppamentoFatturazione;
	}

	public void setCodiceRaggruppamentoFatturazione(
			Integer codiceRaggruppamentoFatturazione) {
		this.codiceRaggruppamentoFatturazione = codiceRaggruppamentoFatturazione;
	}

	public List<OdsFrazionamento> getListOdsFrazionamento() {
		return listOdsFrazionamento;
	}

	public void setListOdsFrazionamento(List<OdsFrazionamento> listOdsFrazionamento) {
		this.listOdsFrazionamento = listOdsFrazionamento;
	}

	public String getOsservazioniFattura() {
		return osservazioniFattura;
	}

	public void setOsservazioniFattura(String osservazioniFattura) {
		this.osservazioniFattura = osservazioniFattura;
	}

	public List<Tariffa> getListTariffa() {
		return listTariffa;
	}

	public void setListTariffa(List<Tariffa> listTariffa) {
		this.listTariffa = listTariffa;
	}

	public List<Canone> getListCanone() {
		return listCanone;
	}

	public void setListCanone(List<Canone> listCanone) {
		this.listCanone = listCanone;
	}

	public List<RaggruppamentoFatturazione> getListRaggruppamentoFatturazione() {
		return listRaggruppamentoFatturazione;
	}

	public void setListRaggruppamentoFatturazione(
			List<RaggruppamentoFatturazione> listRaggruppamentoFatturazione) {
		this.listRaggruppamentoFatturazione = listRaggruppamentoFatturazione;
	}

	public OdsFrazionamento getSelectedOdsFrazionamento() {
		return selectedOdsFrazionamento;
	}

	public void setSelectedOdsFrazionamento(
			OdsFrazionamento selectedOdsFrazionamento) {
		this.selectedOdsFrazionamento = selectedOdsFrazionamento;
	}
}
