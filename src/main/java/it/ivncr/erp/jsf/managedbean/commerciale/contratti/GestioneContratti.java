package it.ivncr.erp.jsf.managedbean.commerciale.contratti;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.contratto.ContrattoService;

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
public class GestioneContratti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneContratti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private LazyDataModel<Contratto> model;
	private Contratto selected;
	private Contratto edited;


	public GestioneContratti() {

		model = new RobustLazyDataModel<Contratto>() {

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

	public String startCreate() {

		edited = new Contratto();

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

	public LazyDataModel<Contratto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Contratto> model) {
		this.model = model;
	}

	public Contratto getSelected() {
		return selected;
	}

	public void setSelected(Contratto selected) {
		this.selected = selected;
	}

	public Contratto getEdited() {
		return edited;
	}

	public void setEdited(Contratto edited) {
		this.edited = edited;
	}
}
