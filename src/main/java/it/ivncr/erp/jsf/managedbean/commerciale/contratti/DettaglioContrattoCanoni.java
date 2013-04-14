package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.canone.CanoneService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class DettaglioContrattoCanoni implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoCanoni.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private Integer id;
	private String alias;
	private Integer codiceTipoServizio;
	private Integer codiceSpecificaServizio;
	private Date dataInizioValidita;
	private Date dataCessazione;
	private Boolean fatturaMinimoUnMese;
	private Boolean fatturazioneAnticipata;
	private Integer fatturaOgniMesi;
	private BigDecimal canoneMensile;
	private String note;

	private List<Canone> listCanoni;
	private Canone selected;

	private List<TipoServizio> listTipoServizio;
	private List<SpecificaServizio> listSpecificaServizio;


	public DettaglioContrattoCanoni() {
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo servizio LUT.
		//
		listTipoServizio = lutService.listItems("TipoServizio");

		// Load list for data table.
		//
		loadCanoni();

		logger.debug("Initialization performed.");
	}


	public void loadCanoni() {

		if(dettaglioContrattoGenerale.getId() == null)
			return;

		CanoneService cs = ServiceFactory.createService("Canone");
		listCanoni = cs.listByContratto(dettaglioContrattoGenerale.getId());
	}


	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		alias = null;
		codiceTipoServizio = null;
		codiceSpecificaServizio = null;
		dataInizioValidita = null;
		dataCessazione = null;
		fatturaMinimoUnMese = null;
		fatturazioneAnticipata = null;
		fatturaOgniMesi = null;
		canoneMensile = null;
		note = null;
	}

	public void populateSpecificaServizio() {

		logger.debug("Entering populateSpecificaServizio method.");

		if(codiceTipoServizio == null) {
			listSpecificaServizio = null;
		}

		// Load specifica servizio LUT.
		//
		LUTService lutService = ServiceFactory.createService("LUT");
		listSpecificaServizio = lutService.listItems("SpecificaServizio", "id", "tipoServizio.id", codiceTipoServizio);
	}

	public void startCreate() {

		logger.debug("Entering startCreate method.");

		clean();
	}

	public void startUpdate() {

		logger.debug("Entering startUpdate method.");

		if(selected == null) {
			String msg = "Unexpected null value detected for selected row.";
			logger.error(msg);
			throw new RuntimeException(msg);
		}

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		CanoneService cs = ServiceFactory.createService("Canone");
		selected = cs.retrieveDeep(selected.getId());

		id = selected.getId();
		alias = selected.getAlias();
		codiceTipoServizio = selected.getTipoServizio() != null ? selected.getTipoServizio().getId() : null;
		codiceSpecificaServizio = selected.getSpecificaServizio() != null ? selected.getSpecificaServizio().getId() : null;
		dataInizioValidita = selected.getDataInizioValidita();
		dataCessazione = selected.getDataCessazione();
		fatturaMinimoUnMese = selected.getFatturaMinimoUnMese();
		fatturazioneAnticipata = selected.getFatturazioneAnticipata();
		fatturaOgniMesi = selected.getFatturaOgniMesi();
		canoneMensile = selected.getCanoneMensile();
		note = selected.getNote();

		// After having loaded the entity, it is possible to populate the
		// list using the current value of tipo servizio.
		//
		populateSpecificaServizio();
	}

	public void doSave() {

		// Save the entity.
		//
		try {
			CanoneService cs = ServiceFactory.createService("Canone");

			if(id == null) {
				cs.create(
						dettaglioContrattoGenerale.getId(),
						alias,
						codiceTipoServizio,
						codiceSpecificaServizio,
						dataInizioValidita,
						dataCessazione,
						fatturaMinimoUnMese,
						fatturazioneAnticipata,
						fatturaOgniMesi,
						canoneMensile,
						note);
				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione del nuovo canone si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				cs.update(
						id,
						alias,
						codiceTipoServizio,
						codiceSpecificaServizio,
						dataInizioValidita,
						dataCessazione,
						fatturaMinimoUnMese,
						fatturazioneAnticipata,
						fatturaOgniMesi,
						canoneMensile,
						note);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica del canone si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

			// Refresh list.
			//
			loadCanoni();

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

		if(selected == null || selected.getId() == null) {
			String msg = "Unexpected null id detected.";
			logger.error(msg);
			throw new RuntimeException(msg);
		}

		// Delete the entity.
		//
		try {
			CanoneService cs = ServiceFactory.createService("Canone");
			cs.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione del canone si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Reset selection.
			//
			selected = null;

			// Refresh list.
			//
			loadCanoni();

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getCodiceTipoServizio() {
		return codiceTipoServizio;
	}

	public void setCodiceTipoServizio(Integer codiceTipoServizio) {
		this.codiceTipoServizio = codiceTipoServizio;
	}

	public Integer getCodiceSpecificaServizio() {
		return codiceSpecificaServizio;
	}

	public void setCodiceSpecificaServizio(Integer codiceSpecificaServizio) {
		this.codiceSpecificaServizio = codiceSpecificaServizio;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public Boolean getFatturaMinimoUnMese() {
		return fatturaMinimoUnMese;
	}

	public void setFatturaMinimoUnMese(Boolean fatturaMinimoUnMese) {
		this.fatturaMinimoUnMese = fatturaMinimoUnMese;
	}

	public Boolean getFatturazioneAnticipata() {
		return fatturazioneAnticipata;
	}

	public void setFatturazioneAnticipata(Boolean fatturazioneAnticipata) {
		this.fatturazioneAnticipata = fatturazioneAnticipata;
	}

	public Integer getFatturaOgniMesi() {
		return fatturaOgniMesi;
	}

	public void setFatturaOgniMesi(Integer fatturaOgniMesi) {
		this.fatturaOgniMesi = fatturaOgniMesi;
	}

	public BigDecimal getCanoneMensile() {
		return canoneMensile;
	}

	public void setCanoneMensile(BigDecimal canoneMensile) {
		this.canoneMensile = canoneMensile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Canone> getListCanoni() {
		return listCanoni;
	}

	public void setListCanoni(List<Canone> listCanoni) {
		this.listCanoni = listCanoni;
	}

	public Canone getSelected() {
		return selected;
	}

	public void setSelected(Canone selected) {
		this.selected = selected;
	}

	public List<TipoServizio> getListTipoServizio() {
		return listTipoServizio;
	}

	public void setListTipoServizio(List<TipoServizio> listTipoServizio) {
		this.listTipoServizio = listTipoServizio;
	}

	public List<SpecificaServizio> getListSpecificaServizio() {
		return listSpecificaServizio;
	}

	public void setListSpecificaServizio(
			List<SpecificaServizio> listSpecificaServizio) {
		this.listSpecificaServizio = listSpecificaServizio;
	}
}
