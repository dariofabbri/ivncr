package it.ivncr.erp.jsf.managedbean.commerciale.contratti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.commerciale.ods.TipoOrdineServizio;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.ordineservizio.OrdineServizioService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@ViewScoped
public class DettaglioContrattoOnerosi implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioContrattoOnerosi.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioContrattoGenerale}")
	private DettaglioContrattoGenerale dettaglioContrattoGenerale;

	private LazyDataModel<OrdineServizio> model;
	private OrdineServizio selected;


	public DettaglioContrattoOnerosi() {

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

				// Inject codice contratto argument in applied filters map.
				//
				filters.put("codiceContratto", Integer.toString(dettaglioContrattoGenerale.getId()));

				// Inject oneroso argument in applied filters map.
				//
				filters.put("oneroso", Boolean.toString(true));

				OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
				QueryResult<OrdineServizio> result = oss.listFromContratto(
						first,
						pageSize,
						sortField,
						SortDirection.fromSortOrder(sortOrder),
						filters);

				this.setRowCount(result.getRecords());

				return result.getResults();
			}

			@Override
			public Object getRowKey(OrdineServizio ordineServizio) {

				return ordineServizio == null ? null : ordineServizio.getId();
			}

			@Override
			public OrdineServizio getRowData(String rowKey) {

				OrdineServizioService oss = ServiceFactory.createService("OrdineServizio");
				OrdineServizio ordineServizio = oss.retrieve(Integer.decode(rowKey));
				return ordineServizio;
			}
		};
	}

	@PostConstruct
	public void init() {
	}

	public String startCreate() {

		logger.debug("Moving to detail page for new record creation.");
		return "/faces/commerciale/ods/detail?faces-redirect=true&oneroso=true&codiceTipoOrdineServizio=" +
			TipoOrdineServizio.NUOVA_ATTIVAZIONE +
			"&contrattoId=" + dettaglioContrattoGenerale.getId();
	}

	public String startUpdate() {

		if(selected == null) {
			logger.error("Invalid status. No row selected on start update request.");
			throw new RuntimeException("Invalid status. No row selected on start update request.");
		}

		logger.debug("Moving to detail page for record update.");
		return "/faces/commerciale/ods/detail?faces-redirect=true&id=" + selected.getId();
	}

	public DettaglioContrattoGenerale getDettaglioContrattoGenerale() {
		return dettaglioContrattoGenerale;
	}

	public void setDettaglioContrattoGenerale(
			DettaglioContrattoGenerale dettaglioContrattoGenerale) {
		this.dettaglioContrattoGenerale = dettaglioContrattoGenerale;
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
