package it.ivncr.erp.jsf.managedbean.accesso.utente;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.ruolo.RuoloService;
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
public class DettaglioUtenteRuoli implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioUtenteRuoli.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneUtenti.edited.id}")
	private Integer id;

	private List<Ruolo> ruoli;
	private List<Ruolo> filtered;
	private Ruolo[] selected;


	public void onTabChange(TabChangeEvent event) {

		// If ruoli tab has been selected it is necessary to initialize
		// the ruoli list in the bean.
		//
		if(event.getTab().getId().equals("ruoliTab")) {

			logger.debug("Selected tab changed to ruoli.");

			// Id should never be null, otherwise we are in for some kind
			// of flow-of-pages or DI problem...
			//
			if(id == null) {
				String msg = "Unexpected null id detected.";
				logger.error(msg);
				throw new RuntimeException(msg);
			}

			logger.debug("Loading ruoli list.");
			RuoloService rs = ServiceFactory.createService("Ruolo");
			QueryResult<Ruolo> result = rs.list(null, null, null, null);
			ruoli = result.getResults();

			UtenteService us = ServiceFactory.createService("Utente");
			List<Ruolo> list = us.listRuoli(id);
			selected = list.toArray(new Ruolo[0]);
		}
	}

	public void doUpdateRuoli() {

		logger.debug("Entering doUpdateRuoli() method.");

		// Get selected ids.
		//
		Integer[] ids = new Integer[selected.length];
		for(int i = 0; i < selected.length; ++i) {
			ids[i] = selected[i].getId();
		}

		// Set the roles.
		//
		try {
			UtenteService us = ServiceFactory.createService("Utente");
			us.setRuoli(
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

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public List<Ruolo> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Ruolo> filtered) {
		this.filtered = filtered;
	}

	public Ruolo[] getSelected() {
		return selected;
	}

	public void setSelected(Ruolo[] selected) {
		this.selected = selected;
	}
}
