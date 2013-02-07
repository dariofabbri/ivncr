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

	private String tipoContatto;
	private String titolo;
	private String nome;
	private String telefono1;
	private String telefono2;
	private String cellulare;
	private String fax;
	private String email;


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
				contatto.setNome("Mario Rossi");
				contatto.setTelefono1("0612345678");
				list.add(contatto);

				contatto = new Contatto();
				contatto.setId(2);
				contatto.setNome("Giulio Cesare");
				contatto.setTelefono1("06126723782");
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



	public String getTipoContatto() {
		return tipoContatto;
	}

	public void setTipoContatto(String tipoContatto) {
		this.tipoContatto = tipoContatto;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
