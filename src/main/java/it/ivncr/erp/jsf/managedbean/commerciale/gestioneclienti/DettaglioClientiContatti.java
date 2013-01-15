package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;


import it.ivncr.erp.model.commerciale.Contatto;
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

	private LazyDataModel<Contatto> model;
	private Contatto selected;
	
	public DettaglioClientiContatti(){
		model = new LazyDataModel<Contatto>() {

			private static final long serialVersionUID = 1L;
			@Override
			public List<Contatto> load(
					int first, 
					int pageSize, 
					String sortField,
					SortOrder sortOrder, 
					Map<String, String> filters) {
				logger.debug("Fetching data model.");

				List<Contatto> list = new ArrayList<Contatto>();

				Contatto contatto = new Contatto();
				contatto.setId(1);
				contatto.setCodice("C001");
				contatto.setRagioneSociale("Ambasciata degli Stati Uniti");
				list.add(contatto);
				
				contatto = new Contatto();
				contatto.setId(2);
				contatto.setCodice("C003");
				contatto.setRagioneSociale("Banca Di Italia");
				list.add(contatto);
				
				this.setRowCount(list.size());

				return list;
			}
			
			@Override
			public Object getRowKey(Contatto contatto) {
				
				return contatto == null ? null : contatto.getId();
			}

			@Override
			public Contatto getRowData(String rowKey) {
				
				return null;
			}
		};
	}
	
public String startCreate() {
		
		logger.debug("Moving to detail page for new record creation.");
		return "detail?faces-redirect=true";
	}


	public LazyDataModel<Contatto> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Contatto> model) {
		this.model = model;
	}

	public Contatto getSelected() {
		return selected;
	}

	public void setSelected(Contatto selected) {
		this.selected = selected;
	}
}
