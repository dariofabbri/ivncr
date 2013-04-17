package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.contratto.DocumentoContratto;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.documentocontratto.DocumentoContrattoService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@ViewScoped
public class DettaglioContrattoDocumenti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoDocumenti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private LazyDataModel<DocumentoContratto> model;
	private DocumentoContratto selected;

	private Integer id;
	private String descrizione;
	private String filename;
	private String mimeType;
	private String note;

	private UploadedFile file;

	private StreamedContent streamedContent;


	public DettaglioContrattoDocumenti() {

		model = new RobustLazyDataModel<DocumentoContratto>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<DocumentoContratto> load(
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

				DocumentoContrattoService dcs = ServiceFactory.createService("DocumentoContratto");
				QueryResult<DocumentoContratto> result = dcs.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(DocumentoContratto documento) {

				return documento == null ? null : documento.getId();
			}

			@Override
			public DocumentoContratto getRowData(String rowKey) {

				DocumentoContrattoService dcs = ServiceFactory.createService("DocumentoContratto");
				DocumentoContratto documento = dcs.retrieve(Integer.decode(rowKey));
				return documento;
			}
		};
	}


	@PostConstruct
	public void init() {

		logger.debug("Initialization performed.");
	}


	private void clean() {

		logger.debug("Cleaning form state.");

		id = null;
		descrizione = null;
		filename = null;
		mimeType = null;
		note = null;
	}


	public void prepareDownload(DocumentoContratto documento) {

		if(documento == null || documento.getDocumento() == null) {
			streamedContent = null;
			return;
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(documento.getDocumento());

		streamedContent = new DefaultStreamedContent(
				bais,
				documento.getMimeType() != null ? documento.getMimeType() : "application/octet-stream",
				documento.getFilename() != null ? documento.getFilename() : "downloaded-file");

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
		DocumentoContrattoService dcs = ServiceFactory.createService("DocumentoContratto");
		selected = dcs.retrieve(selected.getId());

		id = selected.getId();
		descrizione =  selected.getDescrizione();
		filename = selected.getFilename();
		mimeType = selected.getMimeType();
		note =  selected.getNote();


		// Prepare the download (that can be requested from the link in the dialog).
		//
		prepareDownload(selected);
	}


	public void doSave() {

		// Save the entity.
		//
		try {
			DocumentoContrattoService dcs = ServiceFactory.createService("DocumentoContratto");

			if(id == null) {

				if(file == null) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Errore",
							"Nessun file selezionato.");
					FacesContext.getCurrentInstance().addMessage("file", message);
					return;
				}

				dcs.create(
						dettaglioContrattoGenerale.getId(),
						descrizione,
						filename,
						mimeType,
						file.getContents(),
						note);
				logger.debug("Entity successfully created.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record creato",
						"La creazione del nuovo documento si è conclusa con successo.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {

				dcs.update(
						id,
						descrizione,
						filename,
						mimeType,
						note);
				logger.debug("Entity successfully updated.");

				// Add a message.
				//
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Record aggiornato",
						"La modifica del documento si è conclusa con successo.");
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
			DocumentoContrattoService dcs = ServiceFactory.createService("DocumentoContratto");
			dcs.delete(selected.getId());

			logger.debug("Entity successfully deleted.");

			// Add a message.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Record eliminato",
					"L'eliminazione del documento si è conclusa con successo.");
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


	public void handleFileUpload(FileUploadEvent event) {

		file = event.getFile();

		filename = event.getFile().getFileName();
		mimeType = event.getFile().getContentType();
	}


	public DettaglioContrattoGenerale getDettaglioContrattoGenerale() {
		return dettaglioContrattoGenerale;
	}

	public void setDettaglioContrattoGenerale(
			DettaglioContrattoGenerale dettaglioContrattoGenerale) {
		this.dettaglioContrattoGenerale = dettaglioContrattoGenerale;
	}

	public LazyDataModel<DocumentoContratto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<DocumentoContratto> model) {
		this.model = model;
	}

	public DocumentoContratto getSelected() {
		return selected;
	}

	public void setSelected(DocumentoContratto selected) {
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}
}
