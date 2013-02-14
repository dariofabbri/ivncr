package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.ObiettivoServizio;
import it.ivncr.erp.model.generale.Provincia;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.obiettivoservizio.ObiettivoServizioService;

import java.io.Serializable;
import java.util.ArrayList;
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
public class DettaglioClienteObiettivi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteObiettivi.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioClienteGenerale}")
	private DettaglioClienteGenerale dettaglioClienteGenerale;

	private LazyDataModel<ObiettivoServizio> model;
	private ObiettivoServizio selected;

	private Integer id;
	private String alias;
	private String toponimo;
	private String indirizzo;
	private String civico;
	private String edificio;
	private String scala;
	private String piano;
	private String interno;
	private String localita;
	private String cap;
	private String provincia;
	private String paese;
	private String note;

	private List<Provincia> listProvincia;


	public DettaglioClienteObiettivi() {
		model = new RobustLazyDataModel<ObiettivoServizio>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<ObiettivoServizio> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				if(dettaglioClienteGenerale.getId() == null) {
					return null;
				}

				// Inject codice cliente argument in applied filters map.
				//
				filters.put("codiceCliente", Integer.toString(dettaglioClienteGenerale.getId()));

				ObiettivoServizioService oss = ServiceFactory.createService("ObiettivoServizio");
				QueryResult<ObiettivoServizio> result = oss.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(ObiettivoServizio obiettivoServizio) {

				return obiettivoServizio == null ? null : obiettivoServizio.getId();
			}

			@Override
			public ObiettivoServizio getRowData(String rowKey) {

				ObiettivoServizioService oss = ServiceFactory.createService("ObiettivoServizio");
				ObiettivoServizio obiettivoServizio = oss.retrieve(Integer.decode(rowKey));
				return obiettivoServizio;
			}
		};
	}


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load province LUT and extract short codes.
		//
		listProvincia = lutService.listItems("Provincia");

		logger.debug("Initialization performed.");
	}


	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		alias = null;
		toponimo = null;
		indirizzo = null;
		civico = null;
		edificio = null;
		scala = null;
		piano = null;
		interno = null;
		localita = null;
		cap = null;
		provincia = null;
		paese = null;
		note = null;
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

		id = selected.getId();
		alias = selected.getAlias();
		toponimo = selected.getToponimo();
		indirizzo = selected.getIndirizzo();
		civico = selected.getCivico();
		edificio = selected.getEdificio();
		scala = selected.getScala();
		piano = selected.getPiano();
		interno = selected.getInterno();
		localita = selected.getLocalita();
		cap = selected.getCap();
		provincia = selected.getProvincia();
		paese = selected.getPaese();
		note = selected.getNote();
	}


	public void doSave() {

		// Save the entity.
		//
		try {
			ObiettivoServizioService oss = ServiceFactory.createService("ObiettivoServizio");

			if(id == null) {
				oss.create(
						dettaglioClienteGenerale.getId(),
						alias,
						toponimo,
						indirizzo,
						civico,
						edificio,
						scala,
						piano,
						interno,
						localita,
						cap,
						provincia,
						paese,
						note);
				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione del nuovo obiettivo di servizio si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				oss.update(
						id,
						alias,
						toponimo,
						indirizzo,
						civico,
						edificio,
						scala,
						piano,
						interno,
						localita,
						cap,
						provincia,
						paese,
						note);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica dell'obiettivo di servizio si è conclusa con successo.");
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
			ObiettivoServizioService oss = ServiceFactory.createService("ObiettivoServizio");
			oss.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione dell'obiettivo di servizio si è conclusa con successo.");
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

	public List<Provincia> completeProvincia(String query) {

		String q = query.toUpperCase();

		List<Provincia> result = new ArrayList<Provincia>();

		for(Provincia provincia : listProvincia) {
			if(provincia.getSigla().contains(q)) {
				result.add(provincia);
			}
		}

		return result;
	}

	public DettaglioClienteGenerale getDettaglioClienteGenerale() {
		return dettaglioClienteGenerale;
	}

	public void setDettaglioClienteGenerale(
			DettaglioClienteGenerale dettaglioClienteGenerale) {
		this.dettaglioClienteGenerale = dettaglioClienteGenerale;
	}

	public LazyDataModel<ObiettivoServizio> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ObiettivoServizio> model) {
		this.model = model;
	}

	public ObiettivoServizio getSelected() {
		return selected;
	}

	public void setSelected(ObiettivoServizio selected) {
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

	public String getToponimo() {
		return toponimo;
	}

	public void setToponimo(String toponimo) {
		this.toponimo = toponimo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getPiano() {
		return piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Provincia> getListProvincia() {
		return listProvincia;
	}

	public void setListProvincia(List<Provincia> listProvincia) {
		this.listProvincia = listProvincia;
	}
}
