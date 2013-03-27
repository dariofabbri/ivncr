package it.ivncr.erp.jsf.managedbean.commerciale.contratti;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.cliente.ClienteService;
import it.ivncr.erp.service.contratto.ContrattoService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioContrattoGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	@ManagedProperty("#{gestioneContratti.edited.id}")
	private Integer id;

	private Cliente cliente;

	private String codice;
	private String alias;
	private String ragioneSociale;
	private Date dataContratto;
	private Date dataDecorrenza;
	private Date dataTermine;
	private Date dataCessazione;

	private Boolean tacitoRinnovo;
	private Integer giorniPeriodoRinnovo;
	private Integer mesiPeriodoRinnovo;
	private Integer anniPeriodoRinnovo;
	private Integer giorniPreavvisoScadenza;

	private String note;

	private LazyDataModel<Cliente> clienteModel;


	public DettaglioContrattoGenerale() {

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

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			ContrattoService cs = ServiceFactory.createService("Contratto");
			Contratto contratto = cs.retrieveDeep(id);

			cliente = contratto.getCliente();

			codice = contratto.getCodice();
			alias = contratto.getAlias();
			ragioneSociale = contratto.getRagioneSociale();
			dataContratto = contratto.getDataContratto();
			dataDecorrenza = contratto.getDataDecorrenza();
			dataTermine = contratto.getDataTermine();
			dataCessazione = contratto.getDataCessazione();

			tacitoRinnovo = contratto.getTacitoRinnovo();
			giorniPeriodoRinnovo = contratto.getGiorniPeriodoRinnovo();
			mesiPeriodoRinnovo = contratto.getMesiPeriodoRinnovo();
			anniPeriodoRinnovo = contratto.getAnniPeriodoRinnovo();
			giorniPreavvisoScadenza = contratto.getGiorniPreavvisoScadenza();
		}
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Date getDataContratto() {
		return dataContratto;
	}

	public void setDataContratto(Date dataContratto) {
		this.dataContratto = dataContratto;
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

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public Boolean getTacitoRinnovo() {
		return tacitoRinnovo;
	}

	public void setTacitoRinnovo(Boolean tacitoRinnovo) {
		this.tacitoRinnovo = tacitoRinnovo;
	}

	public Integer getGiorniPeriodoRinnovo() {
		return giorniPeriodoRinnovo;
	}

	public void setGiorniPeriodoRinnovo(Integer giorniPeriodoRinnovo) {
		this.giorniPeriodoRinnovo = giorniPeriodoRinnovo;
	}

	public Integer getMesiPeriodoRinnovo() {
		return mesiPeriodoRinnovo;
	}

	public void setMesiPeriodoRinnovo(Integer mesiPeriodoRinnovo) {
		this.mesiPeriodoRinnovo = mesiPeriodoRinnovo;
	}

	public Integer getAnniPeriodoRinnovo() {
		return anniPeriodoRinnovo;
	}

	public void setAnniPeriodoRinnovo(Integer anniPeriodoRinnovo) {
		this.anniPeriodoRinnovo = anniPeriodoRinnovo;
	}

	public Integer getGiorniPreavvisoScadenza() {
		return giorniPreavvisoScadenza;
	}

	public void setGiorniPreavvisoScadenza(Integer giorniPreavvisoScadenza) {
		this.giorniPreavvisoScadenza = giorniPreavvisoScadenza;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LazyDataModel<Cliente> getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(LazyDataModel<Cliente> clienteModel) {
		this.clienteModel = clienteModel;
	}
}