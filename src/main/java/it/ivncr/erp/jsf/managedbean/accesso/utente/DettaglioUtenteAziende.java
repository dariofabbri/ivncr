package it.ivncr.erp.jsf.managedbean.accesso.utente;

import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.utente.UtenteService;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioUtenteAziende implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioUtenteAziende.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneUtenti.edited.id}")
	private Integer id;

	private List<Azienda> aziende;
	private List<Azienda> filtered;
	private Azienda[] selected;


	public void onTabChange(TabChangeEvent event) {

		// If aziende tab has been selected it is necessary to initialize
		// the aziende list in the bean.
		//
		if(event.getTab().getId().equals("aziendeTab")) {

			logger.debug("Selected tab changed to aziende.");

			// Id should never be null, otherwise we are in for some kind
			// of flow-of-pages or DI problem...
			//
			if(id == null) {
				String msg = "Unexpected null id detected.";
				logger.error(msg);
				throw new RuntimeException(msg);
			}

			logger.debug("Loading aziende list.");
			LUTService ls = ServiceFactory.createService("LUT");
			aziende = ls.listItems("Azienda", "codice");

			UtenteService us = ServiceFactory.createService("Utente");
			List<Azienda> list = us.listAziende(id);
			selected = list.toArray(new Azienda[0]);
		}
	}


	public void doUpdateAziende() {

		logger.debug("Entering doUpdateAziende() method.");

		// Get selected ids.
		//
		Integer[] ids = new Integer[selected.length];
		for(int i = 0; i < selected.length; ++i) {
			ids[i] = selected[i].getId();
		}

		// Set the aziende.
		//
		try {
			UtenteService us = ServiceFactory.createService("Utente");
			us.setAziende(
				id,
				ids);

			// Everything went fine.
			//
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Successo",
					"Il salvataggio dei dati si è concluso con successo.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch(Exception e) {

			logger.warn("Exception caught while updating entity.", e);

			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Errore di sistema",
					"Si è verificato un errore in fase di aggiornamento dei record.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Azienda> getAziende() {
		return aziende;
	}

	public void setAziende(List<Azienda> aziende) {
		this.aziende = aziende;
	}

	public List<Azienda> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Azienda> filtered) {
		this.filtered = filtered;
	}

	public Azienda[] getSelected() {
		return selected;
	}

	public void setSelected(Azienda[] selected) {
		this.selected = selected;
	}
}
