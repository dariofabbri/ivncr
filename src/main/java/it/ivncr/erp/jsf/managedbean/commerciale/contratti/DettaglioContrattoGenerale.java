package it.ivncr.erp.jsf.managedbean.commerciale.contratti;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RinnovoContrattuale;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.cliente.ClienteService;
import it.ivncr.erp.service.contratto.ContrattoService;
import it.ivncr.erp.service.rinnovocontrattuale.RinnovoContrattualeService;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
public class DettaglioContrattoGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
	private Integer clienteId;

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

	private String noteRinnovo;

	private LazyDataModel<Cliente> clienteModel;

	private List<RinnovoContrattuale> listRinnovi;
	private RinnovoContrattuale selectedRinnovoContrattuale;


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


	public void init() {

		// Skip processing for ajax calls.
		//
		if(FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		ContrattoService cs = ServiceFactory.createService("Contratto");

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

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

			loadRinnovi();

			logger.debug("Initialization loaded contratto details.");
		}

		// If this is a new record, the codice field gets initialized peeking the
		// next value in contatore table.
		//
		else {

			GregorianCalendar now = new GregorianCalendar();
			Integer anno = now.get(Calendar.YEAR);
			codice = cs.peekNextCodice(loginInfo.getCodiceAzienda(), anno);

			// If a cliente id has been passed, the new record must be created for the
			// specified cliente. Init required data structures.
			//
			if(clienteId != null) {
				ClienteService cls = ServiceFactory.createService("Cliente");
				cliente = cls.retrieve(clienteId);
			}
		}
	}


	private void loadRinnovi() {

		if(id == null) {
			return;
		}

		RinnovoContrattualeService rcs = ServiceFactory.createService("RinnovoContrattuale");
		listRinnovi = rcs.listByContratto(id);
	}


	public void doSelectCliente() {

		if(cliente == null) {
			logger.error("Unexpected empty cliente after selection from picker dialog.");
			return;
		}

		ragioneSociale = cliente.getRagioneSociale();
	}


	public void doSave() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		ContrattoService cs = ServiceFactory.createService("Contratto");

		try {
			Contratto contratto = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				contratto = cs.update(
						id,
						alias,
						ragioneSociale,
						dataContratto,
						dataDecorrenza,
						dataTermine,
						dataCessazione,
						tacitoRinnovo,
						giorniPeriodoRinnovo,
						mesiPeriodoRinnovo,
						anniPeriodoRinnovo,
						giorniPreavvisoScadenza);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				contratto = cs.create(
						cliente.getId(),
						alias,
						ragioneSociale,
						dataContratto,
						dataDecorrenza,
						dataTermine,
						dataCessazione);
				id = contratto.getId();
				codice = contratto.getCodice();

				logger.debug("Entity successfully created.");

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
		if(cliente == null) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Il campo cliente è obbligatorio",
					"E' obbligatorio selezionare un cliente.");
			FacesContext.getCurrentInstance().addMessage("detailForm:cliente", message);
			return false;
		}

		return true;
	}


	public void startRinnovo() {

		if(
				giorniPeriodoRinnovo == null &&
				mesiPeriodoRinnovo == null &&
				anniPeriodoRinnovo == null) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"E' necessario specificare un periodo di rinnovo",
					"E' necessario specificare un periodo di rinnovo (almeno un campo tra giorni, mesi ed anni del periodo di rinnovo).");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

		if(dataTermine == null) {

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"E' necessario specificare una data di termine del contratto",
					"Nessuna data di termine del contratto è stata specificata, impossibile applicare le condizioni di rinnovo.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}

		// Signal to modal dialog that everything went fine.
		//
		RequestContext.getCurrentInstance().addCallbackParam("ok", true);
	}


	public void doRinnovo() {

		// Create service to persist data.
		//
		ContrattoService cs = ServiceFactory.createService("Contratto");

		try {
			Contratto contratto = null;

			contratto = cs.applicaRinnovo(id, noteRinnovo);
			dataDecorrenza = contratto.getDataDecorrenza();
			dataTermine = contratto.getDataTermine();

			logger.debug("Entity successfully updated.");

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il rinnovo del contratto si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Refresh list.
			//
			loadRinnovi();

			// Signal to modal dialog that everything went fine.
			//
			RequestContext.getCurrentInstance().addCallbackParam("ok", true);

		} catch(Exception e) {

			logger.warn("Exception caught while saving entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di rinnovo del contratto.");
			FacesContext.getCurrentInstance().addMessage(null, message);
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

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
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

	public String getNoteRinnovo() {
		return noteRinnovo;
	}

	public void setNoteRinnovo(String noteRinnovo) {
		this.noteRinnovo = noteRinnovo;
	}

	public LazyDataModel<Cliente> getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(LazyDataModel<Cliente> clienteModel) {
		this.clienteModel = clienteModel;
	}

	public List<RinnovoContrattuale> getListRinnovi() {
		return listRinnovi;
	}

	public void setListRinnovi(List<RinnovoContrattuale> listRinnovi) {
		this.listRinnovi = listRinnovi;
	}

	public RinnovoContrattuale getSelectedRinnovoContrattuale() {
		return selectedRinnovoContrattuale;
	}

	public void setSelectedRinnovoContrattuale(
			RinnovoContrattuale selectedRinnovoContrattuale) {
		this.selectedRinnovoContrattuale = selectedRinnovoContrattuale;
	}
}