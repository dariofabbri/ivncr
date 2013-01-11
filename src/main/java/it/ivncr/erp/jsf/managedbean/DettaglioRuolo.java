package it.ivncr.erp.jsf.managedbean;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.ruolo.RuoloService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioRuolo implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioRuolo.class);

	private static final long serialVersionUID = 1L;

	private List<Permesso> permessi;
	private List<Permesso> filteredPermessi;
	private Permesso[] selected;
	

	public List<Permesso> getPermessi() {
		return permessi;
	}

	public void setPermessi(List<Permesso> permessi) {
		this.permessi = permessi;
	}

	public List<Permesso> getFilteredPermessi() {
		return filteredPermessi;
	}

	public void setFilteredPermessi(List<Permesso> filteredPermessi) {
		this.filteredPermessi = filteredPermessi;
	}

	public Permesso[] getSelected() {
		return selected;
	}

	public void setSelected(Permesso[] selected) {
		this.selected = selected;
	}
}
