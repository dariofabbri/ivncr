package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.contratto.ApparecchiaturaTecnologica;
import it.ivncr.erp.model.commerciale.contratto.GruppoApparecchiaturaTecnologica;
import it.ivncr.erp.model.commerciale.contratto.TipoApparecchiaturaTecnologica;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.apparecchiaturatecnologica.ApparecchiaturaTecnologicaService;
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
public class DettaglioContrattoApparecchiature implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoApparecchiature.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private LazyDataModel<ApparecchiaturaTecnologica> model;
	private ApparecchiaturaTecnologica selected;

	private Integer id;
	private Integer codiceGruppoApparecchiatura;
	private Integer codiceTipoApparecchiatura;
	private String descrizione;
	private String matricola;
	private Boolean comodatoUso;
	private Date dataInstallazione;
	private Date dataFatturazione;
	private Date dataRitiro;
	private BigDecimal costoUnaTantum;
	private String note;

	private List<GruppoApparecchiaturaTecnologica> listGruppoApparecchiaturaTecnologica;
	private List<TipoApparecchiaturaTecnologica> listTipoApparecchiaturaTecnologica;


	public DettaglioContrattoApparecchiature() {

		model = new RobustLazyDataModel<ApparecchiaturaTecnologica>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<ApparecchiaturaTecnologica> load(
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

				ApparecchiaturaTecnologicaService ats = ServiceFactory.createService("ApparecchiaturaTecnologica");
				QueryResult<ApparecchiaturaTecnologica> result = ats.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(ApparecchiaturaTecnologica apparecchiaturaTecnologica) {

				return apparecchiaturaTecnologica == null ? null : apparecchiaturaTecnologica.getId();
			}

			@Override
			public ApparecchiaturaTecnologica getRowData(String rowKey) {

				ApparecchiaturaTecnologicaService ats = ServiceFactory.createService("ApparecchiaturaTecnologica");
				ApparecchiaturaTecnologica apparecchiaturaTecnologica = ats.retrieveDeep(Integer.decode(rowKey));
				return apparecchiaturaTecnologica;
			}
		};
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load gruppo apparecchiatura tecnologica LUT.
		//
		listGruppoApparecchiaturaTecnologica = lutService.listItems("GruppoApparecchiaturaTecnologica");

		logger.debug("Initialization performed.");
	}

	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		codiceGruppoApparecchiatura = null;
		codiceTipoApparecchiatura = null;
		descrizione = null;
		matricola = null;
		comodatoUso = null;
		dataInstallazione = null;
		dataFatturazione = null;
		dataRitiro = null;
		costoUnaTantum = null;
		note = null;
	}

	public void populateTipoApparecchiatura() {

		logger.debug("Entering populateTipoApparecchiatura method.");

		if(codiceGruppoApparecchiatura == null) {
			listTipoApparecchiaturaTecnologica = null;
		}

		// Load tipo apparecchiatura tecnologica LUT.
		//
		LUTService lutService = ServiceFactory.createService("LUT");
		listTipoApparecchiaturaTecnologica = lutService.listItems("TipoApparecchiaturaTecnologica", "id", "gruppoApparecchiatura.id", codiceGruppoApparecchiatura);
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
		ApparecchiaturaTecnologicaService ats = ServiceFactory.createService("ApparecchiaturaTecnologica");
		selected = ats.retrieveDeep(selected.getId());

		id = selected.getId();
		codiceGruppoApparecchiatura = selected.getTipoApparecchiaturaTecnologica() != null ? selected.getTipoApparecchiaturaTecnologica().getGruppoApparecchiatura().getId() : null;
		codiceTipoApparecchiatura =  selected.getTipoApparecchiaturaTecnologica() != null ? selected.getTipoApparecchiaturaTecnologica().getId() : null;;
		descrizione =  selected.getDescrizione();
		matricola =  selected.getMatricola();
		comodatoUso =  selected.getComodatoUso();
		dataInstallazione =  selected.getDataInstallazione();
		dataFatturazione =  selected.getDataFatturazione();
		dataRitiro =  selected.getDataRitiro();
		costoUnaTantum = selected.getCostoUnaTantum();
		note =  selected.getNote();

		// After having loaded the entity, it is possible to populate the
		// list using the current value of gruppo apparecchiatura.
		//
		populateTipoApparecchiatura();
	}

	public void doSave() {

		// Save the entity.
		//
		try {
			ApparecchiaturaTecnologicaService ats = ServiceFactory.createService("ApparecchiaturaTecnologica");

			if(id == null) {
				ats.create(
						dettaglioContrattoGenerale.getId(),
						codiceTipoApparecchiatura,
						descrizione,
						matricola,
						comodatoUso,
						dataInstallazione,
						dataFatturazione,
						dataRitiro,
						costoUnaTantum,
						note);
				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione della nuova apparecchiatura si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				ats.update(
						id,
						codiceTipoApparecchiatura,
						descrizione,
						matricola,
						comodatoUso,
						dataInstallazione,
						dataFatturazione,
						dataRitiro,
						costoUnaTantum,
						note);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica dell'apparecchiatura si è conclusa con successo.");
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
			ApparecchiaturaTecnologicaService ats = ServiceFactory.createService("ApparecchiaturaTecnologica");
			ats.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione dell'apparecchiatura si è conclusa con successo.");
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

	public LazyDataModel<ApparecchiaturaTecnologica> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ApparecchiaturaTecnologica> model) {
		this.model = model;
	}

	public ApparecchiaturaTecnologica getSelected() {
		return selected;
	}

	public void setSelected(ApparecchiaturaTecnologica selected) {
		this.selected = selected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodiceGruppoApparecchiatura() {
		return codiceGruppoApparecchiatura;
	}

	public void setCodiceGruppoApparecchiatura(Integer codiceGruppoApparecchiatura) {
		this.codiceGruppoApparecchiatura = codiceGruppoApparecchiatura;
	}

	public Integer getCodiceTipoApparecchiatura() {
		return codiceTipoApparecchiatura;
	}

	public void setCodiceTipoApparecchiatura(Integer codiceTipoApparecchiatura) {
		this.codiceTipoApparecchiatura = codiceTipoApparecchiatura;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Boolean getComodatoUso() {
		return comodatoUso;
	}

	public void setComodatoUso(Boolean comodatoUso) {
		this.comodatoUso = comodatoUso;
	}

	public Date getDataInstallazione() {
		return dataInstallazione;
	}

	public void setDataInstallazione(Date dataInstallazione) {
		this.dataInstallazione = dataInstallazione;
	}

	public Date getDataFatturazione() {
		return dataFatturazione;
	}

	public void setDataFatturazione(Date dataFatturazione) {
		this.dataFatturazione = dataFatturazione;
	}

	public Date getDataRitiro() {
		return dataRitiro;
	}

	public void setDataRitiro(Date dataRitiro) {
		this.dataRitiro = dataRitiro;
	}

	public BigDecimal getCostoUnaTantum() {
		return costoUnaTantum;
	}

	public void setCostoUnaTantum(BigDecimal costoUnaTantum) {
		this.costoUnaTantum = costoUnaTantum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<GruppoApparecchiaturaTecnologica> getListGruppoApparecchiaturaTecnologica() {
		return listGruppoApparecchiaturaTecnologica;
	}

	public void setListGruppoApparecchiaturaTecnologica(
			List<GruppoApparecchiaturaTecnologica> listGruppoApparecchiaturaTecnologica) {
		this.listGruppoApparecchiaturaTecnologica = listGruppoApparecchiaturaTecnologica;
	}

	public List<TipoApparecchiaturaTecnologica> getListTipoApparecchiaturaTecnologica() {
		return listTipoApparecchiaturaTecnologica;
	}

	public void setListTipoApparecchiaturaTecnologica(
			List<TipoApparecchiaturaTecnologica> listTipoApparecchiaturaTecnologica) {
		this.listTipoApparecchiaturaTecnologica = listTipoApparecchiaturaTecnologica;
	}
}
