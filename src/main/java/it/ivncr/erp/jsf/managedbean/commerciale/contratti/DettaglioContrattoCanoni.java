package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.canone.CanoneService;
import it.ivncr.erp.service.contratto.ContrattoService;
import it.ivncr.erp.service.lut.LUTService;

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
public class DettaglioContrattoCanoni implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoCanoni.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private LazyDataModel<Canone> model;
	private Canone selected;

	private Integer id;
	private String descrizione;
	private Integer codiceTipoServizio;
	private Integer codiceSpecificaServizio;
	private Integer codiceObiettivoServizio;
	private Date dataInizioValidita;
	private Integer codiceRaggruppamentoFatturazione;
	private BigDecimal importoMensile;
	private Integer codiceTipoFatturazione;
	private Date dataCessazione;
	private String note;

	private List<TipoServizio> listTipoServizio;
	private List<SpecificaServizio> listSpecificaServizio;
	private List<ObiettivoServizio> listObiettivoServizio;
	private List<RaggruppamentoFatturazione> listRaggruppamentoFatturazione;
	private List<TipoFatturazione> listTipoFatturazione;


	public DettaglioContrattoCanoni() {

		model = new RobustLazyDataModel<Canone>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Canone> load(
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

				CanoneService cs = ServiceFactory.createService("Canone");
				QueryResult<Canone> result = cs.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(Canone canone) {

				return canone == null ? null : canone.getId();
			}

			@Override
			public Canone getRowData(String rowKey) {

				CanoneService cs = ServiceFactory.createService("Canone");
				Canone canone = cs.retrieveDeep(Integer.decode(rowKey));
				return canone;
			}
		};
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo servizio LUT.
		//
		listTipoServizio = lutService.listItems("TipoServizio");

		// Load tipo fatturazione LUT.
		//
		listTipoFatturazione = lutService.listItems("TipoFatturazione");

		// Load raggruppamento fatturazione LUT.
		//
		listRaggruppamentoFatturazione = lutService.listItems("RaggruppamentoFatturazione");

		// Load available obiettivi servizio.
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
		dataInizioValidita = null;
		codiceRaggruppamentoFatturazione = null;
		importoMensile = null;
		codiceTipoFatturazione = null;
		dataCessazione = null;
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
		descrizione = selected.getDescrizione();
		codiceTipoServizio = selected.getTipoServizio() != null ? selected.getTipoServizio().getId() : null;
		codiceSpecificaServizio = selected.getSpecificaServizio() != null ? selected.getSpecificaServizio().getId() : null;
		codiceObiettivoServizio = selected.getObiettivoServizio() != null ? selected.getObiettivoServizio().getId() : null;
		dataInizioValidita = selected.getDataInizioValidita();
		codiceRaggruppamentoFatturazione = selected.getRaggruppamentoFatturazione() != null ? selected.getRaggruppamentoFatturazione().getId() : null;
		importoMensile = selected.getImportoMensile();
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
			CanoneService cs = ServiceFactory.createService("Canone");

			if(id == null) {
				cs.create(
						dettaglioContrattoGenerale.getId(),
						descrizione,
						codiceTipoServizio,
						codiceSpecificaServizio,
						codiceObiettivoServizio,
						dataInizioValidita,
						codiceRaggruppamentoFatturazione,
						importoMensile,
						codiceTipoFatturazione,
						dataCessazione,
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
						descrizione,
						codiceTipoServizio,
						codiceSpecificaServizio,
						codiceObiettivoServizio,
						dataInizioValidita,
						codiceRaggruppamentoFatturazione,
						importoMensile,
						codiceTipoFatturazione,
						dataCessazione,
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

	public LazyDataModel<Canone> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Canone> model) {
		this.model = model;
	}

	public Canone getSelected() {
		return selected;
	}

	public void setSelected(Canone selected) {
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

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Integer getCodiceRaggruppamentoFatturazione() {
		return codiceRaggruppamentoFatturazione;
	}

	public void setCodiceRaggruppamentoFatturazione(
			Integer codiceRaggruppamentoFatturazione) {
		this.codiceRaggruppamentoFatturazione = codiceRaggruppamentoFatturazione;
	}

	public BigDecimal getImportoMensile() {
		return importoMensile;
	}

	public void setImportoMensile(BigDecimal importoMensile) {
		this.importoMensile = importoMensile;
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

	public List<RaggruppamentoFatturazione> getListRaggruppamentoFatturazione() {
		return listRaggruppamentoFatturazione;
	}

	public void setListRaggruppamentoFatturazione(
			List<RaggruppamentoFatturazione> listRaggruppamentoFatturazione) {
		this.listRaggruppamentoFatturazione = listRaggruppamentoFatturazione;
	}

	public List<TipoFatturazione> getListTipoFatturazione() {
		return listTipoFatturazione;
	}

	public void setListTipoFatturazione(List<TipoFatturazione> listTipoFatturazione) {
		this.listTipoFatturazione = listTipoFatturazione;
	}
}
