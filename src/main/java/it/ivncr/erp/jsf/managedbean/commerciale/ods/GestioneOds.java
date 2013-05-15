package it.ivncr.erp.jsf.managedbean.commerciale.ods;

import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.commerciale.ods.TipoOrdineServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

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
public class GestioneOds implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneOds.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private LazyDataModel<OrdineServizio> model;
	private OrdineServizio selected;


	public GestioneOds() {

		model = new RobustLazyDataModel<OrdineServizio>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<OrdineServizio> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				// Inject codice azienda argument in applied filters map.
				//
				filters.put("codiceAzienda", Integer.toString(loginInfo.getCodiceAzienda()));

				OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
				QueryResult<OrdineServizio> result = oss.list(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(OrdineServizio ods) {

				return ods == null ? null : ods.getId();
			}

			@Override
			public OrdineServizio getRowData(String rowKey) {

				OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
				OrdineServizio ods = oss.retrieve(Integer.decode(rowKey));
				return ods;
			}
		};
	}

	public String startCreate(boolean oneroso) {

		logger.debug("Moving to detail page for new record creation.");
		return "detail?faces-redirect=true&oneroso=" + oneroso + "&codiceTipoOrdineServizio=" + TipoOrdineServizio.NUOVA_ATTIVAZIONE;
	}

	public String startVariazioneContrattuale() {

		logger.debug("Moving to detail page for new record creation (variazione contrattuale).");
		return "detail?faces-redirect=true&codiceTipoOrdineServizio=" + TipoOrdineServizio.VAR_CONTRATTUALE + "&parentId=" + selected.getId();
	}

	public String startUpdate() {

		if(selected == null) {
			logger.error("Invalid status. No row selected on start update request.");
			throw new RuntimeException("Invalid status. No row selected on start update request.");
		}

		logger.debug("Moving to detail page for record update.");
		return "detail?faces-redirect=true&id=" + selected.getId();
	}

	public boolean variazioneContrattualeDisabled() {

		if(selected == null) {
			return true;
		}

		return
				!selected.getTipoOrdineServizio().getId().equals(TipoOrdineServizio.NUOVA_ATTIVAZIONE) &&
				!selected.getTipoOrdineServizio().getId().equals(TipoOrdineServizio.VAR_CONTRATTUALE);
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public LazyDataModel<OrdineServizio> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<OrdineServizio> model) {
		this.model = model;
	}

	public OrdineServizio getSelected() {
		return selected;
	}

	public void setSelected(OrdineServizio selected) {
		this.selected = selected;
	}
}
