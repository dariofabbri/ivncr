package it.ivncr.erp.jsf.managedbean.commerciale.ods;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.commerciale.ods.TipoOrdineServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.contratto.ContrattoService;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioOdsGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private Integer id;
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

			// After having loaded the entity, it is possible to populate the
			// lists using the current value of tipo servizio and of contratto.
			//
			populateSpecificaServizio();
			populateObiettivoServizio();

			logger.debug("Initialization loaded ordine servizio details.");
		}

		// If this is a new record, the codice field gets initialized peeking the
		// next value in contatore table.
		//
		else {

			GregorianCalendar now = new GregorianCalendar();
			Integer anno = now.get(Calendar.YEAR);
			codice = oss.peekNextCodice(loginInfo.getCodiceAzienda(), anno);
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

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public Integer getCodiceTipoOrdineServizio() {
		return codiceTipoOrdineServizio;
	}

	public void setCodiceTipoOrdineServizio(Integer codiceTipoOrdineServizio) {
		this.codiceTipoOrdineServizio = codiceTipoOrdineServizio;
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
}