package it.ivncr.erp.jsf.managedbean.commerciale.ods;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.cliente.ClienteService;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.odsfrazionamento.OdsFrazionamentoService;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class DettaglioOdsFatturazione implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsFatturazione.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioOdsGenerale}")
	private DettaglioOdsGenerale dettaglioOdsGenerale;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
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

	private LazyDataModel<Cliente> clienteModel;
	private Cliente selectedCliente;

	private Integer idFrazionamento;
	private BigDecimal quota;
	private Boolean esclusioneRitenutaGaranzia;


	public DettaglioOdsFatturazione() {

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


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load raggruppamento fatturazione LUT.
		//
		listRaggruppamentoFatturazione = lutService.listItems("RaggruppamentoFatturazione");

		if(dettaglioOdsGenerale == null || dettaglioOdsGenerale.getId() == null) {
			return;
		}
		
		// Load available canone and tariffa items.
		//
		OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
		listTariffa = oss.listAvailableTariffa(dettaglioOdsGenerale.getId());
		listCanone = oss.listAvailableCanone(dettaglioOdsGenerale.getId());

		// Reloading the entity is required to be sure that the value has not changed since it was
		// read in the data table list of values.
		//
		OrdineServizio entity = oss.retrieveDeep(dettaglioOdsGenerale.getId());
		id = entity.getId();
		oneroso = entity.getOneroso();
		codiceTariffa = entity.getTariffa() != null ? entity.getTariffa().getId() : null;
		codiceCanone = entity.getCanone() != null ? entity.getCanone().getId() : null;
		codiceRaggruppamentoFatturazione = entity.getRaggruppamentoFatturazione() != null ? entity.getRaggruppamentoFatturazione().getId() : null;
		osservazioniFattura = entity.getOsservazioniFattura();

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


	private boolean formValidations() {

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
		BigDecimal totalPercentage = BigDecimal.ZERO;
		Set<Integer> codiciClientiFrazionamento = new HashSet<Integer>();
		if(listOdsFrazionamento != null && listOdsFrazionamento.size() > 0) {
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
		}
		if(totalPercentage.compareTo(new BigDecimal(100)) != 0) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"La somma delle percentuali non fa 100",
					"La somma delle percentuali specificate nella tabella di fatturazione deve essere esattamente 100.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}

		return true;
	}


	public DettaglioOdsGenerale getDettaglioOdsGenerale() {
		return dettaglioOdsGenerale;
	}

	public void setDettaglioOdsGenerale(
			DettaglioOdsGenerale dettaglioOdsGenerale) {
		this.dettaglioOdsGenerale = dettaglioOdsGenerale;
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
