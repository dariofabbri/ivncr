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


	public GestioneClienti() {

		model = new LazyDataModel<Cliente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Cliente> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				logger.debug("Fetching data model.");

				List<Cliente> list = new ArrayList<Cliente>();

				Cliente cliente = new Cliente();
				cliente.setId(1);
				cliente.setCodice("C001");
				cliente.setRagioneSociale("Ambasciata degli Stati Uniti");
				list.add(cliente);

				cliente = new Cliente();
				cliente.setId(2);
				cliente.setCodice("C002");
				cliente.setRagioneSociale("Metropolitane di Roma");
				list.add(cliente);

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

		logger.debug("Moving to detail page for new record creation.");
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

}
