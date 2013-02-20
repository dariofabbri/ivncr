package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.Indirizzo;
import it.ivncr.erp.model.commerciale.TipoIndirizzo;
import it.ivncr.erp.model.generale.Provincia;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.indirizzo.IndirizzoService;
import it.ivncr.erp.service.lut.LUTService;

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
public class DettaglioClienteIndirizzi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteIndirizzi.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioClienteGenerale}")
	private DettaglioClienteGenerale dettaglioClienteGenerale;

	private LazyDataModel<Indirizzo> model;
	private Indirizzo selected;

	private Integer id;
	private Integer codiceTipoIndirizzo;
	private String destinatario1;
	private String destinatario2;
	private String toponimo;
	private String indirizzo;
	private String civico;
	private String localita;
	private String cap;
	private Provincia provincia;
	private String paese;

	private List<TipoIndirizzo> listTipoIndirizzo;
	private List<Provincia> listProvincia;
	private List<String> listPaese;


	public DettaglioClienteIndirizzi() {

		model = new RobustLazyDataModel<Indirizzo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Indirizzo> load(
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

				IndirizzoService is = ServiceFactory.createService("Indirizzo");
				QueryResult<Indirizzo> result = is.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(Indirizzo indirizzo) {

				return indirizzo == null ? null : indirizzo.getId();
			}

			@Override
			public Indirizzo getRowData(String rowKey) {

				IndirizzoService is = ServiceFactory.createService("Indirizzo");
				Indirizzo indirizzo = is.retrieveDeep(Integer.decode(rowKey));
				return indirizzo;
			}
		};
	}

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo indirizzo LUT.
		//
		listTipoIndirizzo = lutService.listItems("TipoIndirizzo");

		// Load province LUT.
		//
		listProvincia = lutService.listItems("Provincia");

		// Load paese LUT.
		//
		listPaese = lutService.listItemsSingleColumn("Paese", "descrizione");

		logger.debug("Initialization performed.");
	}

	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		codiceTipoIndirizzo = null;
		destinatario1 = null;
		destinatario2 = null;
		toponimo = null;
		indirizzo = null;
		civico = null;
		localita = null;
		cap = null;
		provincia = null;
		paese = null;
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
		IndirizzoService is = ServiceFactory.createService("Indirizzo");
		selected = is.retrieveDeep(selected.getId());

		Provincia provinciaEntity = new Provincia();
		provinciaEntity.setSigla(selected.getProvincia());

		id = selected.getId();
		codiceTipoIndirizzo = selected.getTipoIndirizzo() != null ? selected.getTipoIndirizzo().getId() : null;
		destinatario1 = selected.getDestinatario1();
		destinatario2 = selected.getDestinatario2();
		toponimo = selected.getToponimo();
		indirizzo = selected.getIndirizzo();
		civico = selected.getCivico();
		localita = selected.getLocalita();
		cap = selected.getCap();
		provincia = provinciaEntity;
		paese = selected.getPaese();
	}

	public void doSave() {

		// Save the entity.
		//
		try {
			IndirizzoService is = ServiceFactory.createService("Indirizzo");

			if(id == null) {
				is.create(
						dettaglioClienteGenerale.getId(),
						codiceTipoIndirizzo,
						destinatario1,
						destinatario2,
						toponimo,
						indirizzo,
						civico,
						localita,
						cap,
						provincia != null ? provincia.getSigla() : null,
						paese);
				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione del nuovo indirizzo si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				is.update(
						id,
						codiceTipoIndirizzo,
						destinatario1,
						destinatario2,
						toponimo,
						indirizzo,
						civico,
						localita,
						cap,
						provincia != null ? provincia.getSigla() : null,
						paese);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica dell'indirizzo si è conclusa con successo.");
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
			IndirizzoService is = ServiceFactory.createService("Indirizzo");
			is.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione dell'indirizzo si è conclusa con successo.");
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


	public List<String> completePaese(String query) {

		String q = query.toUpperCase();

		List<String> result = new ArrayList<String>();

		for(String s : listPaese) {
			if(s.toUpperCase().contains(q)) {
				result.add(s);
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

	public LazyDataModel<Indirizzo> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Indirizzo> model) {
		this.model = model;
	}

	public Indirizzo getSelected() {
		return selected;
	}

	public void setSelected(Indirizzo selected) {
		this.selected = selected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodiceTipoIndirizzo() {
		return codiceTipoIndirizzo;
	}

	public void setCodiceTipoIndirizzo(Integer codiceTipoIndirizzo) {
		this.codiceTipoIndirizzo = codiceTipoIndirizzo;
	}

	public String getDestinatario1() {
		return destinatario1;
	}

	public void setDestinatario1(String destinatario1) {
		this.destinatario1 = destinatario1;
	}

	public String getDestinatario2() {
		return destinatario2;
	}

	public void setDestinatario2(String destinatario2) {
		this.destinatario2 = destinatario2;
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public List<TipoIndirizzo> getListTipoIndirizzo() {
		return listTipoIndirizzo;
	}

	public void setListTipoIndirizzo(List<TipoIndirizzo> listTipoIndirizzo) {
		this.listTipoIndirizzo = listTipoIndirizzo;
	}
}
