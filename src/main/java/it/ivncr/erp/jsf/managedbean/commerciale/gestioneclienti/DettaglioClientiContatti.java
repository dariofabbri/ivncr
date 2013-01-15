package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;


import it.ivncr.erp.model.commerciale.Contatti;
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
public class DettaglioClientiContatti implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(DettaglioClientiContatti.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Contatti> model;
	private Contatti selected;
	
	public DettaglioClientiContatti(){
		model = new LazyDataModel<Contatti>() {

			private static final long serialVersionUID = 1L;
			@Override
			public List<Contatti> load(
					int first, 
					int pageSize, 
					String sortField,
					SortOrder sortOrder, 
					Map<String, String> filters) {
				logger.debug("Fetching data model.");

				List<Contatti> list = new ArrayList<Contatti>();

				Contatti contatti = new Contatti();
				contatti.setId(1);
				contatti.setCodice("C001");
				contatti.setRagioneSociale("Ambasciata degli Stati Uniti");
				list.add(contatti);
				
				contatti = new Contatti();
				contatti.setId(2);
				contatti.setCodice("C003");
				contatti.setRagioneSociale("Banca Di Italia");
				list.add(contatti);
				
				this.setRowCount(list.size());

				return list;
			}
			
			@Override
			public Object getRowKey(Contatti contatti) {
				
				return contatti == null ? null : contatti.getId();
			}

			@Override
			public Contatti getRowData(String rowKey) {
				
				return null;
			}
		};
	}
	
public String startCreate() {
		
		logger.debug("Moving to detail page for new record creation.");
		return "detail?faces-redirect=true";
	}


	public LazyDataModel<Contatti> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Contatti> model) {
		this.model = model;
	}

	public Contatti getSelected() {
		return selected;
	}

	public void setSelected(Contatti selected) {
		this.selected = selected;
	}
}
