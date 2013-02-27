package it.ivncr.erp.jsf.managedbean.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


@ManagedBean
@ViewScoped
public class CustomRadioInDatatableTest implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<TestEntity> model;
	private TestEntity selected;


	// Used for preferred selection.
	//
	private List<TestEntity> currentListContent;
	private Integer preferred = 2;



	private List<TestEntity> data;


	public CustomRadioInDatatableTest() {

		// Initialize fake data.
		//
		data = new ArrayList<TestEntity>();
		for(int i = 1; i <= 100; ++i) {

			TestEntity te = new TestEntity();
			te.setId(i);
			te.setDescription(String.format("Item #%d", i));

			data.add(te);
		}


		model = new LazyDataModel<TestEntity>() {

			private static final long serialVersionUID = 1L;
			@Override
			public List<TestEntity> load(
					int first,
					int pageSize,
					String sortField,
					SortOrder sortOrder,
					Map<String, String> filters) {

				// Apply specified filter.
				//
				List<TestEntity> result = new ArrayList<TestEntity>();
				String filter = filters.get("description");
				for(TestEntity te : data) {

					if(filter == null || te.getDescription().contains(filter)) {
						result.add(te);
					}
				}

				this.setRowCount(result.size());

				currentListContent = result;
				return result;
			}

			@Override
			public Object getRowKey(TestEntity te) {

				return te == null ? null : te.getId();
			}

			@Override
			public TestEntity getRowData(String rowKey) {

				int index = Integer.decode(rowKey) - 1;
				return data.get(index);
			}
		};
	}

	public void doIt(ActionEvent e) {
		
        String param = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clicked");
        if(param != null) {
        	preferred = Integer.parseInt(param);
        }
	}


	public LazyDataModel<TestEntity> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<TestEntity> model) {
		this.model = model;
	}

	public TestEntity getSelected() {
		return selected;
	}

	public void setSelected(TestEntity selected) {
		this.selected = selected;
	}

	public List<TestEntity> getCurrentListContent() {
		return currentListContent;
	}

	public void setCurrentListContent(List<TestEntity> currentListContent) {
		this.currentListContent = currentListContent;
	}

	public Integer getPreferred() {
		return preferred;
	}

	public void setPreferred(Integer preferred) {
		this.preferred = preferred;
	}

	public List<TestEntity> getData() {
		return data;
	}

	public void setData(List<TestEntity> data) {
		this.data = data;
	}
}
