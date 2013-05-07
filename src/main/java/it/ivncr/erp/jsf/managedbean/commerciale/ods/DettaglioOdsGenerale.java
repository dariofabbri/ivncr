package it.ivncr.erp.jsf.managedbean.commerciale.ods;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.commerciale.ods.TipoOrdineServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.cliente.ClienteService;
import it.ivncr.erp.service.contratto.ContrattoService;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.odsfrazionamento.OdsFrazionamentoService;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

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
public class DettaglioOdsGenerale extends Observable implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer contrattoId;
	private Boolean oneroso;
	private Integer codiceTipoOrdineServizio;

	private Contratto contratto;

	private OrdineServizio padre;
	private OrdineServizio nuovaAttivazione;
	private String codice;
	private String alias;
	private Date dataDecorrenza;
	private Date dataTermine;
	private Date dataFineValidita;
	private Date orarioFineValidita;
	private Integer codiceTipoServizio;
	private Integer codiceSpecificaServizio;
	private Integer codiceObiettivoServizio;
	private Boolean cessato;

	private LazyDataModel<Contratto> contrattoModel;

	private List<TipoOrdineServizio> listTipoOrdineServizio;
	private List<TipoServizio> listTipoServizio;
	private List<SpecificaServizio> listSpecificaServizio;
	private List<ObiettivoServizio> listObiettivoServizio;



	private Integer codiceTariffa;
	private Integer codiceCanone;
	private Integer codiceRaggruppamentoFatturazione;
	private List<OdsFrazionamento> listOdsFrazionamento;
	private String osservazioniFattura;

	private List<Tariffa> listTariffa;
	private List<Canone> listCanone;
	private List<RaggruppamentoFatturazione> listRaggruppamentoFatturazione;

	private OdsFrazionamento selectedOdsFrazionamento;

	private LazyDataModel<Cliente> clienteModel;
	private Cliente selectedCliente;

	private Integer idFrazionamento;
	private BigDecimal quota;
	private Boolean esclusioneRitenutaGaranzia;



	public DettaglioOdsGenerale() {

		contrattoModel = new RobustLazyDataModel<Contratto>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Contratto> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				// Inject codice azienda argument in applied filters map.
				//
				filters.put("codiceAzienda", Integer.toString(loginInfo.getCodiceAzienda()));

				ContrattoService cs = ServiceFactory.createService("Contratto");
				QueryResult<Contratto> result = cs.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(Contratto contratto) {

				return contratto == null ? null : contratto.getId();
			}

			@Override
			public Contratto getRowData(String rowKey) {

				ContrattoService cs = ServiceFactory.createService("Contratto");
				Contratto contratto = cs.retrieve(Integer.decode(rowKey));
				return contratto;
			}
		};


		clienteModel = new RobustLazyDataModel<Cliente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Cliente> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				// Inject codice azienda argument in applied filters map.
				//
				filters.put("codiceAzienda", Integer.toString(loginInfo.getCodiceAzienda()));

				ClienteService cs = ServiceFactory.createService("Cliente");
				QueryResult<Cliente> result = cs.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(Cliente cliente) {

				return cliente == null ? null : cliente.getId();
			}

			@Override
			public Cliente getRowData(String rowKey) {

				ClienteService cs = ServiceFactory.createService("Cliente");
				Cliente cliente = cs.retrieve(Integer.decode(rowKey));
				return cliente;
			}
		};
	}


	public void init() {

		// Skip processing for ajax calls.
		//
		if(FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo ordine servizio LUT.
		//
		listTipoOrdineServizio = lutService.listItems("TipoOrdineServizio");

		// Load tipo servizio LUT.
		//
		listTipoServizio = lutService.listItems("TipoServizio");

		// Load raggruppamento fatturazione LUT.
		//
		listRaggruppamentoFatturazione = lutService.listItems("RaggruppamentoFatturazione");


		OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			OrdineServizio ods = oss.retrieveDeep(id);

			contratto = ods.getContratto();

			codiceTipoOrdineServizio = ods.getTipoOrdineServizio() != null ? ods.getTipoOrdineServizio().getId() : null;
			padre = ods.getPadre();
			nuovaAttivazione = ods.getNuovaAttivazione();
			codice = ods.getCodice();
			alias = ods.getAlias();
			dataDecorrenza = ods.getDataDecorrenza();
			dataTermine = ods.getDataTermine();
			dataFineValidita = ods.getDataFineValidita();
			orarioFineValidita = ods.getOrarioFineValidita();
			codiceTipoServizio = ods.getTipoServizio() != null ? ods.getTipoServizio().getId() : null;
			codiceSpecificaServizio = ods.getSpecificaServizio() != null ? ods.getSpecificaServizio().getId() : null;
			codiceObiettivoServizio = ods.getObiettivoServizio() != null ? ods.getObiettivoServizio().getId() : null;
			cessato = ods.getCessato();

			oneroso = ods.getOneroso();
			codiceTariffa = ods.getTariffa() != null ? ods.getTariffa().getId() : null;
			codiceCanone = ods.getCanone() != null ? ods.getCanone().getId() : null;
			codiceRaggruppamentoFatturazione = ods.getRaggruppamentoFatturazione() != null ? ods.getRaggruppamentoFatturazione().getId() : null;
			osservazioniFattura = ods.getOsservazioniFattura();


			// After having loaded the entity, it is possible to populate the
			// lists using the current value of tipo servizio and of contratto.
			//
			populateSpecificaServizio();
			populateObiettivoServizio();


			// Load available canone and tariffa items.
			//
			listTariffa = oss.listAvailableTariffa(id);
			listCanone = oss.listAvailableCanone(id);

			// Load frazionamento.
			//
			OdsFrazionamentoService ofs = ServiceFactory.createService("OdsFrazionamento");
			listOdsFrazionamento = ofs.listByOrdineServizio(id);

			logger.debug("Initialization loaded ordine servizio details.");
		}

		// If this is a new record, the codice field gets initialized peeking the
		// next value in contatore table.
		//
		else {

			GregorianCalendar now = new GregorianCalendar();
			Integer anno = now.get(Calendar.YEAR);
			codice = oss.peekNextCodice(loginInfo.getCodiceAzienda(), anno);

			// If a contratto id has been passed, the new record must be created for the
			// specified contratto. Init required data structures.
			//
			if(contrattoId != null) {
				ContrattoService cos = ServiceFactory.createService("Contratto");
				contratto = cos.retrieve(contrattoId);
			}
		}
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


	public void populateObiettivoServizio() {

		if(contratto == null) {
			logger.error("Unexpected empty contratto after selection from picker dialog.");
			return;
		}

		// Populate obiettivo servizio drop down list, selecting those available in
		// selected contratto.
		//
		ContrattoService cs = ServiceFactory.createService("Contratto");
		listObiettivoServizio = cs.listAvailableObiettiviServizio(contratto.getId());
	}


	public void doSelectContratto() {
		populateObiettivoServizio();
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");

		try {
			OrdineServizio ods = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				ods = oss.updateTestata(
						id,
						alias,
						codiceTipoOrdineServizio,
						dataDecorrenza,
						dataTermine,
						dataFineValidita,
						orarioFineValidita,
						codiceTipoServizio,
						codiceSpecificaServizio,
						codiceObiettivoServizio,
						cessato);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				ods = oss.create(
						contratto.getId(),
						alias,
						codiceTipoOrdineServizio,
						dataDecorrenza,
						dataTermine,
						dataFineValidita,
						orarioFineValidita,
						codiceTipoServizio,
						codiceSpecificaServizio,
						codiceObiettivoServizio,
						oneroso,
						cessato);

				id = ods.getId();
				codice = ods.getCodice();

				logger.debug("Entity successfully created.");

				// Load available canone and tariffa items.
				//
				listTariffa = oss.listAvailableTariffa(id);
				listCanone = oss.listAvailableCanone(id);
			}

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

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

		// Normalize fields.
		//

		// Check that a cliente has been selected.
		//
		if(contratto == null) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il campo contratto è obbligatorio",
					"E' obbligatorio selezionare un contratto.");
			FacesContext.getCurrentInstance().addMessage("contratto", message);
			return false;
		}

		return true;
	}


	public void doSaveFatturazione() {

		// Apply form-level validations.
		//
		if(!formValidationsFatturazione())
			return;

		// Save the entity.
		//
		try {
			OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
			oss.updateFatturazione(
					id,
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


	public void startCreateFrazionamento() {

		logger.debug("Entering startCreateFrazionamento() method.");

		idFrazionamento = null;
		quota = null;
		esclusioneRitenutaGaranzia = null;
	}


	public void startUpdateFrazionamento() {

		logger.debug("Entering startUpdateFrazionamento() method.");

		// No need to reload from DB, the table is an in memory snapshot that
		// must be confirmed or canceled in a single shot.
		//
		idFrazionamento = selectedOdsFrazionamento.getId();
		selectedCliente = selectedOdsFrazionamento.getCliente();
		quota = selectedOdsFrazionamento.getQuota();
		esclusioneRitenutaGaranzia = selectedOdsFrazionamento.getEsclusioneRitenutaGaranzia();
	}


	public void doSaveFrazionamento() {

		logger.debug("Entering doSaveFrazionamento() method.");

		// Initialize in memory list, if needed.
		//
		if(listOdsFrazionamento == null) {
			listOdsFrazionamento = new ArrayList<OdsFrazionamento>();
		}

		if(idFrazionamento == null) {

			OdsFrazionamento odsFrazionamento = new OdsFrazionamento();
			odsFrazionamento.setCliente(selectedCliente);
			odsFrazionamento.setQuota(quota);
			odsFrazionamento.setEsclusioneRitenutaGaranzia(esclusioneRitenutaGaranzia);

			// Find max id.
			//
			int maxId = 0;
			for(OdsFrazionamento o : listOdsFrazionamento) {
				if(o.getId() > maxId) {
					maxId = o.getId();
				}
			}
			odsFrazionamento.setId(maxId + 1);

			listOdsFrazionamento.add(odsFrazionamento);

		} else {

			selectedOdsFrazionamento.setQuota(quota);
			selectedOdsFrazionamento.setEsclusioneRitenutaGaranzia(esclusioneRitenutaGaranzia);
		}

		// Signal to modal dialog that everything went fine.
		//
		RequestContext.getCurrentInstance().addCallbackParam("ok", true);
	}


	public void doDeleteFrazionamento() {

		logger.debug("Entering doSaveFrazionamento() method.");

		listOdsFrazionamento.remove(selectedOdsFrazionamento);
		selectedOdsFrazionamento = null;
	}


	private boolean formValidationsFatturazione() {

		// At least one between canone and tariffa must be selected.
		//
		if(codiceTariffa == null && codiceCanone == null) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Immettere canone o tariffa",
					"E' necessario selezionare un valore almeno per un campo tra canone e tariffa.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}

		// Check that the sum of quota adds up to 100 and that
		// no duplicate cliente has been specified.
		//
		if(listOdsFrazionamento != null && listOdsFrazionamento.size() > 0) {

			BigDecimal totalPercentage = BigDecimal.ZERO;
			Set<Integer> codiciClientiFrazionamento = new HashSet<Integer>();

			for(OdsFrazionamento o : listOdsFrazionamento) {

				totalPercentage = totalPercentage.add(o.getQuota());

				if(codiciClientiFrazionamento.contains(o.getCliente().getId())) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Cliente duplicato nel frazionamento",
							"Non è possibile specificare lo stesso cliente più di una volta nella tabella di frazionamento.");
					FacesContext.getCurrentInstance().addMessage(null, message);
					return false;
				}

				codiciClientiFrazionamento.add(o.getCliente().getId());
			}

			if(totalPercentage.compareTo(new BigDecimal(100)) != 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"La somma delle percentuali non fa 100",
						"La somma delle percentuali specificate nella tabella di fatturazione deve essere esattamente 100.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return false;
			}
		}

		return true;
	}


	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContrattoId() {
		return contrattoId;
	}

	public void setContrattoId(Integer contrattoId) {
		this.contrattoId = contrattoId;
	}

	public Boolean getOneroso() {
		return oneroso;
	}

	public void setOneroso(Boolean oneroso) {
		this.oneroso = oneroso;
	}

	public Integer getCodiceTipoOrdineServizio() {
		return codiceTipoOrdineServizio;
	}

	public void setCodiceTipoOrdineServizio(Integer codiceTipoOrdineServizio) {
		this.codiceTipoOrdineServizio = codiceTipoOrdineServizio;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public OrdineServizio getPadre() {
		return padre;
	}

	public void setPadre(OrdineServizio padre) {
		this.padre = padre;
	}

	public OrdineServizio getNuovaAttivazione() {
		return nuovaAttivazione;
	}

	public void setNuovaAttivazione(OrdineServizio nuovaAttivazione) {
		this.nuovaAttivazione = nuovaAttivazione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getDataDecorrenza() {
		return dataDecorrenza;
	}

	public void setDataDecorrenza(Date dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}

	public Date getDataTermine() {
		return dataTermine;
	}

	public void setDataTermine(Date dataTermine) {
		this.dataTermine = dataTermine;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getOrarioFineValidita() {
		return orarioFineValidita;
	}

	public void setOrarioFineValidita(Date orarioFineValidita) {
		this.orarioFineValidita = orarioFineValidita;
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

	public Boolean getCessato() {
		return cessato;
	}

	public void setCessato(Boolean cessato) {
		this.cessato = cessato;
	}

	public LazyDataModel<Contratto> getContrattoModel() {
		return contrattoModel;
	}

	public void setContrattoModel(LazyDataModel<Contratto> contrattoModel) {
		this.contrattoModel = contrattoModel;
	}

	public List<TipoOrdineServizio> getListTipoOrdineServizio() {
		return listTipoOrdineServizio;
	}

	public void setListTipoOrdineServizio(
			List<TipoOrdineServizio> listTipoOrdineServizio) {
		this.listTipoOrdineServizio = listTipoOrdineServizio;
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

	public void setListOdsFrazionamento(
			List<OdsFrazionamento> listOdsFrazionamento) {
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

	public LazyDataModel<Cliente> getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(LazyDataModel<Cliente> clienteModel) {
		this.clienteModel = clienteModel;
	}

	public Cliente getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(Cliente selectedCliente) {
		this.selectedCliente = selectedCliente;
	}

	public Integer getIdFrazionamento() {
		return idFrazionamento;
	}

	public void setIdFrazionamento(Integer idFrazionamento) {
		this.idFrazionamento = idFrazionamento;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public Boolean getEsclusioneRitenutaGaranzia() {
		return esclusioneRitenutaGaranzia;
	}

	public void setEsclusioneRitenutaGaranzia(Boolean esclusioneRitenutaGaranzia) {
		this.esclusioneRitenutaGaranzia = esclusioneRitenutaGaranzia;
	}
}