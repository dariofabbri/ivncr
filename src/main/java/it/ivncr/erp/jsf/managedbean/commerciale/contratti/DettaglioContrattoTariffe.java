package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.tariffa.TariffaService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@ViewScoped
public class DettaglioContrattoTariffe implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoTariffe.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private LazyDataModel<Tariffa> model;
	private Tariffa selected;

	private Integer id;
	private String alias;
	private Integer codiceTipoServizio;
	private Integer codiceSpecificaServizio;
	private BigDecimal costoOrario;
	private BigDecimal costoOperazione;
	private BigDecimal costoFissoUnaTantum;
	private BigDecimal costoFissoATempo;
	private Integer costoFissoMesi;
	private Integer franchigieTotali;
	private Integer franchigieATempo;
	private Integer franchigieMesi;
	private BigDecimal ritenutaGaranzia;
	private Integer ritenutaGaranziaGiorni;
	private Date dataInizioValidita;
	private Date dataCessazione;
	private Boolean fatturazioneAnticipata;
	private Boolean extraFatturatoAParte;
	private Boolean fatturaSpezzata;
	private Integer fatturaOgniMesi;
	private Boolean fatturaMinimoUnMese;
	private String note;

	private List<TipoServizio> listTipoServizio;
	private List<SpecificaServizio> listSpecificaServizio;


	public DettaglioContrattoTariffe() {

		model = new RobustLazyDataModel<Tariffa>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Tariffa> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				if(dettaglioContrattoGenerale.getId() == null) {
					return null;
				}

				// Inject codice contratto argument in applied filters map.
				//
				filters.put("codiceContratto", Integer.toString(dettaglioContrattoGenerale.getId()));

				TariffaService ts = ServiceFactory.createService("Tariffa");
				QueryResult<Tariffa> result = ts.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(Tariffa tariffa) {

				return tariffa == null ? null : tariffa.getId();
			}

			@Override
			public Tariffa getRowData(String rowKey) {

				TariffaService ts = ServiceFactory.createService("Tariffa");
				Tariffa tariffa = ts.retrieveDeep(Integer.decode(rowKey));
				return tariffa;
			}
		};
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo servizio LUT.
		//
		listTipoServizio = lutService.listItems("TipoServizio");

		logger.debug("Initialization performed.");
	}

	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		alias = null;
		codiceTipoServizio = null;
		codiceSpecificaServizio = null;
		costoOrario = null;
		costoOperazione = null;
		costoFissoUnaTantum = null;
		costoFissoATempo = null;
		costoFissoMesi = null;
		franchigieTotali = null;
		franchigieATempo = null;
		franchigieMesi = null;
		ritenutaGaranzia = null;
		ritenutaGaranziaGiorni = null;
		dataInizioValidita = null;
		dataCessazione = null;
		fatturazioneAnticipata = null;
		extraFatturatoAParte = null;
		fatturaSpezzata = null;
		fatturaOgniMesi = null;
		fatturaMinimoUnMese = null;
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
		TariffaService ts = ServiceFactory.createService("Tariffa");
		selected = ts.retrieveDeep(selected.getId());

		id = selected.getId();
		alias = selected.getAlias();
		codiceTipoServizio = selected.getTipoServizio() != null ? selected.getTipoServizio().getId() : null;
		codiceSpecificaServizio = selected.getSpecificaServizio() != null ? selected.getSpecificaServizio().getId() : null;
		costoOrario = selected.getCostoOrario();
		costoOperazione = selected.getCostoOperazione();
		costoFissoUnaTantum = selected.getCostoFissoUnaTantum();
		costoFissoATempo = selected.getCostoFissoATempo();
		costoFissoMesi = selected.getCostoFissoMesi();
		franchigieTotali = selected.getFranchigieTotali();
		franchigieATempo = selected.getFranchigieATempo();
		franchigieMesi = selected.getFranchigieMesi();
		ritenutaGaranzia = selected.getRitenutaGaranzia();
		ritenutaGaranziaGiorni = selected.getRitenutaGaranziaGiorni();
		dataInizioValidita = selected.getDataInizioValidita();
		dataCessazione = selected.getDataCessazione();
		fatturazioneAnticipata = selected.getFatturazioneAnticipata();
		extraFatturatoAParte = selected.getExtraFatturatoAParte();
		fatturaSpezzata = selected.getFatturaSpezzata();
		fatturaOgniMesi = selected.getFatturaOgniMesi();
		fatturaMinimoUnMese = selected.getFatturaMinimoUnMese();
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
			TariffaService ts = ServiceFactory.createService("Tariffa");

			if(id == null) {
				ts.create(
						dettaglioContrattoGenerale.getId(),
						alias,
						codiceTipoServizio,
						codiceSpecificaServizio,
						costoOrario,
						costoOperazione,
						costoFissoUnaTantum,
						costoFissoATempo,
						costoFissoMesi,
						franchigieTotali,
						franchigieATempo,
						franchigieMesi,
						ritenutaGaranzia,
						ritenutaGaranziaGiorni,
						dataInizioValidita,
						dataCessazione,
						fatturazioneAnticipata,
						extraFatturatoAParte,
						fatturaSpezzata,
						fatturaOgniMesi,
						fatturaMinimoUnMese,
						note);

				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione della nuova tariffa si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				ts.update(
						id,
						alias,
						codiceTipoServizio,
						codiceSpecificaServizio,
						costoOrario,
						costoOperazione,
						costoFissoUnaTantum,
						costoFissoATempo,
						costoFissoMesi,
						franchigieTotali,
						franchigieATempo,
						franchigieMesi,
						ritenutaGaranzia,
						ritenutaGaranziaGiorni,
						dataInizioValidita,
						dataCessazione,
						fatturazioneAnticipata,
						extraFatturatoAParte,
						fatturaSpezzata,
						fatturaOgniMesi,
						fatturaMinimoUnMese,
						note);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica della tariffa si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

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
			TariffaService ts = ServiceFactory.createService("Tariffa");
			ts.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione della tariffa si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

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

	public LazyDataModel<Tariffa> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Tariffa> model) {
		this.model = model;
	}

	public Tariffa getSelected() {
		return selected;
	}

	public void setSelected(Tariffa selected) {
		this.selected = selected;
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

	public BigDecimal getCostoOrario() {
		return costoOrario;
	}

	public void setCostoOrario(BigDecimal costoOrario) {
		this.costoOrario = costoOrario;
	}

	public BigDecimal getCostoOperazione() {
		return costoOperazione;
	}

	public void setCostoOperazione(BigDecimal costoOperazione) {
		this.costoOperazione = costoOperazione;
	}

	public BigDecimal getCostoFissoUnaTantum() {
		return costoFissoUnaTantum;
	}

	public void setCostoFissoUnaTantum(BigDecimal costoFissoUnaTantum) {
		this.costoFissoUnaTantum = costoFissoUnaTantum;
	}

	public BigDecimal getCostoFissoATempo() {
		return costoFissoATempo;
	}

	public void setCostoFissoATempo(BigDecimal costoFissoATempo) {
		this.costoFissoATempo = costoFissoATempo;
	}

	public Integer getCostoFissoMesi() {
		return costoFissoMesi;
	}

	public void setCostoFissoMesi(Integer costoFissoMesi) {
		this.costoFissoMesi = costoFissoMesi;
	}

	public Integer getFranchigieTotali() {
		return franchigieTotali;
	}

	public void setFranchigieTotali(Integer franchigieTotali) {
		this.franchigieTotali = franchigieTotali;
	}

	public Integer getFranchigieATempo() {
		return franchigieATempo;
	}

	public void setFranchigieATempo(Integer franchigieATempo) {
		this.franchigieATempo = franchigieATempo;
	}

	public Integer getFranchigieMesi() {
		return franchigieMesi;
	}

	public void setFranchigieMesi(Integer franchigieMesi) {
		this.franchigieMesi = franchigieMesi;
	}

	public BigDecimal getRitenutaGaranzia() {
		return ritenutaGaranzia;
	}

	public void setRitenutaGaranzia(BigDecimal ritenutaGaranzia) {
		this.ritenutaGaranzia = ritenutaGaranzia;
	}

	public Integer getRitenutaGaranziaGiorni() {
		return ritenutaGaranziaGiorni;
	}

	public void setRitenutaGaranziaGiorni(Integer ritenutaGaranziaGiorni) {
		this.ritenutaGaranziaGiorni = ritenutaGaranziaGiorni;
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

	public Boolean getFatturazioneAnticipata() {
		return fatturazioneAnticipata;
	}

	public void setFatturazioneAnticipata(Boolean fatturazioneAnticipata) {
		this.fatturazioneAnticipata = fatturazioneAnticipata;
	}

	public Boolean getExtraFatturatoAParte() {
		return extraFatturatoAParte;
	}

	public void setExtraFatturatoAParte(Boolean extraFatturatoAParte) {
		this.extraFatturatoAParte = extraFatturatoAParte;
	}

	public Boolean getFatturaSpezzata() {
		return fatturaSpezzata;
	}

	public void setFatturaSpezzata(Boolean fatturaSpezzata) {
		this.fatturaSpezzata = fatturaSpezzata;
	}

	public Integer getFatturaOgniMesi() {
		return fatturaOgniMesi;
	}

	public void setFatturaOgniMesi(Integer fatturaOgniMesi) {
		this.fatturaOgniMesi = fatturaOgniMesi;
	}

	public Boolean getFatturaMinimoUnMese() {
		return fatturaMinimoUnMese;
	}

	public void setFatturaMinimoUnMese(Boolean fatturaMinimoUnMese) {
		this.fatturaMinimoUnMese = fatturaMinimoUnMese;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
