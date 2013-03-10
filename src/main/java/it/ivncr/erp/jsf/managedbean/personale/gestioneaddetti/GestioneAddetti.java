package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.addetto.AddettoService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class GestioneAddetti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneAddetti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private LazyDataModel<Addetto> model;
	private Addetto selected;
	private Addetto edited;


	public GestioneAddetti() {

		model = new RobustLazyDataModel<Addetto>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Addetto> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				// Inject codice azienda argument in applied filters map.
				//
				filters.put("codiceAzienda", Integer.toString(loginInfo.getCodiceAzienda()));

				AddettoService as = ServiceFactory.createService("Addetto");
				QueryResult<Addetto> result = as.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(Addetto addetto) {

				return addetto == null ? null : addetto.getId();
			}

			@Override
			public Addetto getRowData(String rowKey) {

				AddettoService as = ServiceFactory.createService("Addetto");
				Addetto addetto = as.retrieve(Integer.decode(rowKey));
				return addetto;
			}
		};
	}

	public String startCreate() {

		edited = new Addetto();

		logger.debug("Moving to detail page for new record creation.");
		return "detail?faces-redirect=true";
	}

	public String startUpdate() {

		if(selected == null) {
			logger.error("Invalid status. No row selected on start update request.");
			throw new RuntimeException("Invalid status. No row selected on start update request.");
		}

		edited = selected;

		logger.debug("Moving to detail page for record update.");
		return "detail?faces-redirect=true";
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public LazyDataModel<Addetto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Addetto> model) {
		this.model = model;
	}

	public Addetto getSelected() {
		return selected;
	}

	public void setSelected(Addetto selected) {
		this.selected = selected;
	}

	public Addetto getEdited() {
		return edited;
	}

	public void setEdited(Addetto edited) {
		this.edited = edited;
	}
}
