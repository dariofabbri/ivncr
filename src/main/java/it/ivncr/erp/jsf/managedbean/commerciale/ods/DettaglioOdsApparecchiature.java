package it.ivncr.erp.jsf.managedbean.commerciale.ods;

import it.ivncr.erp.model.commerciale.contratto.ApparecchiaturaTecnologica;
import it.ivncr.erp.model.commerciale.ods.OdsApparecchiatura;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.apparecchiaturatecnologica.ApparecchiaturaTecnologicaService;
import it.ivncr.erp.service.odsapparecchiatura.OdsApparecchiaturaService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioOdsApparecchiature implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioOdsApparecchiature.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioOdsGenerale}")
	private DettaglioOdsGenerale dettaglioOdsGenerale;

	private List<OdsApparecchiatura> listOdsApparecchiatura;
	private OdsApparecchiatura selected;

	private List<ApparecchiaturaTecnologica> listApparecchiatureDisponibili;
	private ApparecchiaturaTecnologica selectedApparecchiatura;


	@PostConstruct
	public void init() {

		// Load list for data table.
		//
		loadApparecchiature();

		logger.debug("Initialization performed.");
	}


	public void loadApparecchiature() {

		if(dettaglioOdsGenerale.getId() == null)
			return;

		OdsApparecchiaturaService oas = ServiceFactory.createService("OdsApparecchiatura");
		listOdsApparecchiatura = oas.listByOrdineServizio(dettaglioOdsGenerale.getId());
	}


	public void startAdd() {

		logger.debug("Entering startAdd() method.");

		// Load list of available contatti records.
		//
		ApparecchiaturaTecnologicaService ats = ServiceFactory.createService("ApparecchiaturaTecnologica");
		listApparecchiatureDisponibili = ats.listDisponibiliPerOrdineServizio(dettaglioOdsGenerale.getId());
	}


	public DettaglioOdsGenerale getDettaglioOdsGenerale() {
		return dettaglioOdsGenerale;
	}


	public void setDettaglioOdsGenerale(DettaglioOdsGenerale dettaglioOdsGenerale) {
		this.dettaglioOdsGenerale = dettaglioOdsGenerale;
	}


	public List<OdsApparecchiatura> getListOdsApparecchiatura() {
		return listOdsApparecchiatura;
	}


	public void setListOdsApparecchiatura(
			List<OdsApparecchiatura> listOdsApparecchiatura) {
		this.listOdsApparecchiatura = listOdsApparecchiatura;
	}


	public OdsApparecchiatura getSelected() {
		return selected;
	}


	public void setSelected(OdsApparecchiatura selected) {
		this.selected = selected;
	}


	public List<ApparecchiaturaTecnologica> getListApparecchiatureDisponibili() {
		return listApparecchiatureDisponibili;
	}


	public void setListApparecchiatureDisponibili(
			List<ApparecchiaturaTecnologica> listApparecchiatureDisponibili) {
		this.listApparecchiatureDisponibili = listApparecchiatureDisponibili;
	}


	public ApparecchiaturaTecnologica getSelectedApparecchiatura() {
		return selectedApparecchiatura;
	}


	public void setSelectedApparecchiatura(
			ApparecchiaturaTecnologica selectedApparecchiatura) {
		this.selectedApparecchiatura = selectedApparecchiatura;
	}


	/*
	public void doAdd() {

		// Apply form-level validations.
		//
		if(!formValidations())
			return;

		// Create service to persist data.
		//
		ContrattoGestoreService cgs = ServiceFactory.createService("ContrattoGestore");

		try {
			ContrattoGestore entity = null;

			// If the record already exists, just update it.
			//
			if(id != null) {

				entity = cgs.update(
						id,
						selectedGestore.getId(),
						validoDa,
						validoA);

				logger.debug("Entity successfully updated.");
			}

			// Otherwise create a new record.
			//
			else {

				entity = cgs.create(
						dettaglioContrattoGenerale.getId(),
						selectedGestore.getId(),
						validoDa,
						validoA);
				id = entity.getId();

				logger.debug("Entity successfully created.");

			}

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Refresh list.
			//
			loadGestori();

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

		// Create service to persist data.
		//
		ContrattoGestoreService cgs = ServiceFactory.createService("ContrattoGestore");

		try {
			cgs.delete(selected.getId());

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"L'eliminazione del record selezionato si è conclusa con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

			// Reset selection.
			//
			selected = null;

			// Refresh list.
			//
			loadGestori();

		} catch(Exception e) {

			logger.warn("Exception caught while deleting entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di eliminazione del record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	*/

}