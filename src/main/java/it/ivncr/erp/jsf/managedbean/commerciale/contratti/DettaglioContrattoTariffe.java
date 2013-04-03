package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.contratto.TipoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoTariffa;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.contratto.ContrattoService;
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
	private String descrizione;
	private Integer codiceTipoServizio;
	private Integer codiceSpecificaServizio;
	private Integer codiceObiettivoServizio;
	private Integer codiceTipoTariffa;
	private BigDecimal costo;
	private Integer numero;
	private Date dataInizioValidita;
	private Integer codiceTipoFatturazione;
	private Date dataCessazione;
	private String note;

	private List<TipoServizio> listTipoServizio;
	private List<SpecificaServizio> listSpecificaServizio;
	private List<ObiettivoServizio> listObiettivoServizio;
	private List<TipoTariffa> listTipoTariffa;
	private List<TipoFatturazione> listTipoFatturazione;


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

		// Load tipo tariffa LUT.
		//
		listTipoTariffa = lutService.listItems("TipoTariffa");

		// Load tipo fatturazione LUT.
		//
		listTipoFatturazione = lutService.listItems("TipoFatturazione");

		// Load available indirizzi.
		//
		ContrattoService cs = ServiceFactory.createService("Contratto");
		listObiettivoServizio = cs.listAvailableObiettiviServizio(dettaglioContrattoGenerale.getId());

		logger.debug("Initialization performed.");
	}

	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		descrizione = null;
		codiceTipoServizio = null;
		codiceSpecificaServizio = null;
		codiceObiettivoServizio = null;
		codiceTipoTariffa = null;
		costo = null;
		numero = null;
		dataInizioValidita = null;
		codiceTipoFatturazione = null;
		dataCessazione = null;
		note = null;
	}

	public void populateSpecificaServizio() {

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
		descrizione = selected.getDescrizione();
		codiceTipoServizio = selected.getTipoServizio() != null ? selected.getTipoServizio().getId() : null;
		codiceSpecificaServizio = selected.getSpecificaServizio() != null ? selected.getSpecificaServizio().getId() : null;
		codiceObiettivoServizio = selected.getObiettivoServizio() != null ? selected.getObiettivoServizio().getId() : null;
		codiceTipoTariffa = selected.getTipoTariffa() != null ? selected.getTipoTariffa().getId() : null;
		costo = selected.getCosto();
		numero = selected.getNumero();
		dataInizioValidita = selected.getDataInizioValidita();
		codiceTipoFatturazione = selected.getTipoFatturazione() != null ? selected.getTipoFatturazione().getId() : null;
		dataCessazione = selected.getDataCessazione();
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
						descrizione,
						codiceTipoServizio,
						codiceSpecificaServizio,
						codiceObiettivoServizio,
						codiceTipoTariffa,
						costo,
						numero,
						dataInizioValidita,
						codiceTipoFatturazione,
						dataCessazione,
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
						descrizione,
						codiceTipoServizio,
						codiceSpecificaServizio,
						codiceObiettivoServizio,
						codiceTipoTariffa,
						costo,
						numero,
						dataInizioValidita,
						codiceTipoFatturazione,
						dataCessazione,
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public Integer getCodiceObiettivoServizio() {
		return codiceObiettivoServizio;
	}

	public void setCodiceObiettivoServizio(Integer codiceObiettivoServizio) {
		this.codiceObiettivoServizio = codiceObiettivoServizio;
	}

	public Integer getCodiceTipoTariffa() {
		return codiceTipoTariffa;
	}

	public void setCodiceTipoTariffa(Integer codiceTipoTariffa) {
		this.codiceTipoTariffa = codiceTipoTariffa;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Integer getCodiceTipoFatturazione() {
		return codiceTipoFatturazione;
	}

	public void setCodiceTipoFatturazione(Integer codiceTipoFatturazione) {
		this.codiceTipoFatturazione = codiceTipoFatturazione;
	}

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
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

	public List<ObiettivoServizio> getListObiettivoServizio() {
		return listObiettivoServizio;
	}

	public void setListObiettivoServizio(
			List<ObiettivoServizio> listObiettivoServizio) {
		this.listObiettivoServizio = listObiettivoServizio;
	}

	public List<TipoTariffa> getListTipoTariffa() {
		return listTipoTariffa;
	}

	public void setListTipoTariffa(List<TipoTariffa> listTipoTariffa) {
		this.listTipoTariffa = listTipoTariffa;
	}

	public List<TipoFatturazione> getListTipoFatturazione() {
		return listTipoFatturazione;
	}

	public void setListTipoFatturazione(List<TipoFatturazione> listTipoFatturazione) {
		this.listTipoFatturazione = listTipoFatturazione;
	}
}
