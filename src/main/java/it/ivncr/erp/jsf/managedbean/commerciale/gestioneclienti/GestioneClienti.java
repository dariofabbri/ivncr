package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class GestioneClienti implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(GestioneClienti.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Cliente> model;
	private Cliente selected;
	private Cliente edited;


	public GestioneClienti() {

		model = new LazyDataModel<Cliente>() {

			private static final long serialVersionUID = 1L;

			private final List<Cliente> list = new ArrayList<Cliente>();

			{
				Cliente cliente = new Cliente();
				cliente.setId(1);
				cliente.setCodice("C001");
				cliente.setRagioneSociale("Ambasciata degli Stati Uniti");
				cliente.setPartitaIva("IT0000000000000");
				list.add(cliente);

				cliente = new Cliente();
				cliente.setId(2);
				cliente.setCodice("C002");
				cliente.setRagioneSociale("Metropolitane di Roma");
				cliente.setPartitaIva("IT0000000000001");
				list.add(cliente);
			}

			@Override
			public List<Cliente> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				this.setRowCount(list.size());

				return list;
			}

			@Override
			public Object getRowKey(Cliente cliente) {

				return cliente == null ? null : cliente.getId();
			}

			@Override
			public Cliente getRowData(String rowKey) {

				return null;
			}
		};
	}

	public String startCreate() {

		edited = new Cliente();

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

	public LazyDataModel<Cliente> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Cliente> model) {
		this.model = model;
	}

	public Cliente getSelected() {
		return selected;
	}

	public void setSelected(Cliente selected) {
		this.selected = selected;
	}

	public Cliente getEdited() {
		return edited;
	}

	public void setEdited(Cliente edited) {
		this.edited = edited;
	}

	public void doSearch() {

	}
}
