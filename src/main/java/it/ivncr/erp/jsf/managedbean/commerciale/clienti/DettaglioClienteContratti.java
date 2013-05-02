package it.ivncr.erp.jsf.managedbean.commerciale.clienti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.service.contratto.ContrattoService;

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
public class DettaglioClienteContratti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteContratti.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{dettaglioClienteGenerale}")
	private DettaglioClienteGenerale dettaglioClienteGenerale;

	private LazyDataModel<Contratto> model;
	private Contratto selected;


	public DettaglioClienteContratti() {

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

				// Inject codice cliente argument in applied filters map.
				//
				filters.put("codiceCliente", Integer.toString(dettaglioClienteGenerale.getId()));

				ContrattoService cs = ServiceFactory.createService("Contratto");
				QueryResult<Contratto> result = cs.listFromCliente(
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

	@PostConstruct
	public void init() {
	}

	public String startCreate() {

		logger.debug("Moving to detail page for new record creation.");
		return "/faces/commerciale/contratti/detail?faces-redirect=true&clienteId=" + dettaglioClienteGenerale.getId();
	}

	public String startUpdate() {

		if(selected == null) {
			logger.error("Invalid status. No row selected on start update request.");
			throw new RuntimeException("Invalid status. No row selected on start update request.");
		}

		logger.debug("Moving to detail page for record update.");
		return "/faces/commerciale/contratti/detail?faces-redirect=true&id=" + selected.getId();
	}

	public DettaglioClienteGenerale getDettaglioClienteGenerale() {
		return dettaglioClienteGenerale;
	}

	public void setDettaglioClienteGenerale(
			DettaglioClienteGenerale dettaglioClienteGenerale) {
		this.dettaglioClienteGenerale = dettaglioClienteGenerale;
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
}
