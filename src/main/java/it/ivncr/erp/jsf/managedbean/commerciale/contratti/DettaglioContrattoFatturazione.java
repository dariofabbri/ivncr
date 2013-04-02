package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.cliente.Indirizzo;
import it.ivncr.erp.model.commerciale.contratto.CondizioniPagamento;
import it.ivncr.erp.model.commerciale.contratto.DettaglioFatturazione;
import it.ivncr.erp.model.commerciale.contratto.LayoutStampa;
import it.ivncr.erp.model.commerciale.contratto.MetodoPagamento;
import it.ivncr.erp.model.commerciale.contratto.TipoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.TipoFrazionamentoFatturazione;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.contratto.ContrattoService;
import it.ivncr.erp.service.dettagliofatturazione.DettaglioFatturazioneService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
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
public class DettaglioContrattoFatturazione implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoFatturazione.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private LazyDataModel<DettaglioFatturazione> model;
	private DettaglioFatturazione selected;

	private Integer id;
	private Integer codiceTipoFatturazione;
	private Integer codiceTipoFrazionamentoFatturazione;
	private Integer codiceCondizioniPagamento;
	private Integer codiceMetodoPagamento;
	private Integer codiceIndirizzo;
	private Integer codiceLayoutStampa;
	private Date validoDa;
	private Date validoA;

	private List<TipoFatturazione> listTipoFatturazione;
	private List<TipoFrazionamentoFatturazione> listTipoFrazionamentoFatturazione;
	private List<CondizioniPagamento> listCondizioniPagamento;
	private List<MetodoPagamento> listMetodoPagamento;
	private List<Indirizzo> listIndirizzo;
	private List<LayoutStampa> listLayoutStampa;


	public DettaglioContrattoFatturazione() {

		model = new RobustLazyDataModel<DettaglioFatturazione>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<DettaglioFatturazione> load(
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

				DettaglioFatturazioneService dfs = ServiceFactory.createService("DettaglioFatturazione");
				QueryResult<DettaglioFatturazione> result = dfs.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(DettaglioFatturazione dettaglioFatturazione) {

				return dettaglioFatturazione == null ? null : dettaglioFatturazione.getId();
			}

			@Override
			public DettaglioFatturazione getRowData(String rowKey) {

				DettaglioFatturazioneService dfs = ServiceFactory.createService("DettaglioFatturazione");
				DettaglioFatturazione dettagliofatturazione = dfs.retrieveDeep(Integer.decode(rowKey));
				return dettagliofatturazione;
			}
		};
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo fatturazione LUT.
		//
		listTipoFatturazione = lutService.listItems("TipoFatturazione");

		// Load tipo frazionamento fatturazione LUT.
		//
		listTipoFrazionamentoFatturazione = lutService.listItems("TipoFrazionamentoFatturazione");

		// Load condizioni pagamento LUT.
		//
		listCondizioniPagamento = lutService.listItems("CondizioniPagamento");

		// Load metodo pagamento LUT.
		//
		listMetodoPagamento = lutService.listItems("MetodoPagamento");

		// Load layout stampa LUT.
		//
		listLayoutStampa = lutService.listItems("LayoutStampa");

		// Load available indirizzi.
		//
		ContrattoService cs = ServiceFactory.createService("Contratto");
		listIndirizzo = cs.listAvailableIndirizzi(dettaglioContrattoGenerale.getId());

		logger.debug("Initialization performed.");
	}

	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		codiceTipoFatturazione = null;
		codiceTipoFrazionamentoFatturazione = null;
		codiceCondizioniPagamento = null;
		codiceMetodoPagamento = null;
		codiceIndirizzo = null;
		codiceLayoutStampa = null;
		validoDa = null;
		validoA = null;
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
		DettaglioFatturazioneService dfs = ServiceFactory.createService("DettaglioFatturazione");
		selected = dfs.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceTipoFatturazione = selected.getTipoFatturazione() != null ? selected.getTipoFatturazione().getId() : null;
		codiceTipoFrazionamentoFatturazione = selected.getTipoFrazionamentoFatturazione() != null ? selected.getTipoFrazionamentoFatturazione().getId() : null;
		codiceCondizioniPagamento = selected.getCondizioniPagamento() != null ? selected.getCondizioniPagamento().getId() : null;
		codiceMetodoPagamento = selected.getMetodoPagamento() != null ? selected.getMetodoPagamento().getId() : null;
		codiceIndirizzo = selected.getIndirizzo() != null ? selected.getIndirizzo().getId() : null;
		codiceLayoutStampa = selected.getLayoutStampa() != null ? selected.getLayoutStampa().getId() : null;
		validoDa = selected.getValidoDa();
		validoA = selected.getValidoA();
	}

	public void doSave() {

		// Save the entity.
		//
		try {
			DettaglioFatturazioneService dfs = ServiceFactory.createService("DettaglioFatturazione");

			if(id == null) {
				dfs.create(
						dettaglioContrattoGenerale.getId(),
						codiceTipoFatturazione,
						codiceTipoFrazionamentoFatturazione,
						codiceCondizioniPagamento,
						codiceMetodoPagamento,
						codiceIndirizzo,
						codiceLayoutStampa,
						validoDa,
						validoA);
				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione del nuovo dettaglio di fatturazione si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				dfs.update(
						id,
						codiceTipoFatturazione,
						codiceTipoFrazionamentoFatturazione,
						codiceCondizioniPagamento,
						codiceMetodoPagamento,
						codiceIndirizzo,
						codiceLayoutStampa,
						validoDa,
						validoA);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica del dettaglio di fatturazione si è conclusa con successo.");
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
			DettaglioFatturazioneService dfs = ServiceFactory.createService("DettaglioFatturazione");
			dfs.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione del dettaglio di fatturazione si è conclusa con successo.");
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

	public LazyDataModel<DettaglioFatturazione> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<DettaglioFatturazione> model) {
		this.model = model;
	}

	public DettaglioFatturazione getSelected() {
		return selected;
	}

	public void setSelected(DettaglioFatturazione selected) {
		this.selected = selected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodiceTipoFatturazione() {
		return codiceTipoFatturazione;
	}

	public void setCodiceTipoFatturazione(Integer codiceTipoFatturazione) {
		this.codiceTipoFatturazione = codiceTipoFatturazione;
	}

	public Integer getCodiceTipoFrazionamentoFatturazione() {
		return codiceTipoFrazionamentoFatturazione;
	}

	public void setCodiceTipoFrazionamentoFatturazione(
			Integer codiceTipoFrazionamentoFatturazione) {
		this.codiceTipoFrazionamentoFatturazione = codiceTipoFrazionamentoFatturazione;
	}

	public Integer getCodiceCondizioniPagamento() {
		return codiceCondizioniPagamento;
	}

	public void setCodiceCondizioniPagamento(Integer codiceCondizioniPagamento) {
		this.codiceCondizioniPagamento = codiceCondizioniPagamento;
	}

	public Integer getCodiceMetodoPagamento() {
		return codiceMetodoPagamento;
	}

	public void setCodiceMetodoPagamento(Integer codiceMetodoPagamento) {
		this.codiceMetodoPagamento = codiceMetodoPagamento;
	}

	public Integer getCodiceIndirizzo() {
		return codiceIndirizzo;
	}

	public void setCodiceIndirizzo(Integer codiceIndirizzo) {
		this.codiceIndirizzo = codiceIndirizzo;
	}

	public Integer getCodiceLayoutStampa() {
		return codiceLayoutStampa;
	}

	public void setCodiceLayoutStampa(Integer codiceLayoutStampa) {
		this.codiceLayoutStampa = codiceLayoutStampa;
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

	public List<TipoFatturazione> getListTipoFatturazione() {
		return listTipoFatturazione;
	}

	public void setListTipoFatturazione(List<TipoFatturazione> listTipoFatturazione) {
		this.listTipoFatturazione = listTipoFatturazione;
	}

	public List<TipoFrazionamentoFatturazione> getListTipoFrazionamentoFatturazione() {
		return listTipoFrazionamentoFatturazione;
	}

	public void setListTipoFrazionamentoFatturazione(
			List<TipoFrazionamentoFatturazione> listTipoFrazionamentoFatturazione) {
		this.listTipoFrazionamentoFatturazione = listTipoFrazionamentoFatturazione;
	}

	public List<CondizioniPagamento> getListCondizioniPagamento() {
		return listCondizioniPagamento;
	}

	public void setListCondizioniPagamento(
			List<CondizioniPagamento> listCondizioniPagamento) {
		this.listCondizioniPagamento = listCondizioniPagamento;
	}

	public List<MetodoPagamento> getListMetodoPagamento() {
		return listMetodoPagamento;
	}

	public void setListMetodoPagamento(List<MetodoPagamento> listMetodoPagamento) {
		this.listMetodoPagamento = listMetodoPagamento;
	}

	public List<Indirizzo> getListIndirizzo() {
		return listIndirizzo;
	}

	public void setListIndirizzo(List<Indirizzo> listIndirizzo) {
		this.listIndirizzo = listIndirizzo;
	}

	public List<LayoutStampa> getListLayoutStampa() {
		return listLayoutStampa;
	}

	public void setListLayoutStampa(List<LayoutStampa> listLayoutStampa) {
		this.listLayoutStampa = listLayoutStampa;
	}
}
