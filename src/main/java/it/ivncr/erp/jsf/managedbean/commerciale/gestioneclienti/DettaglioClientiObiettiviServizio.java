package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;


import it.ivncr.erp.jsf.RobustLazyDataModel;
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
public class DettaglioClientiObiettiviServizio implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClientiObiettiviServizio.class);

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Contatto> model;
	private Contatto selected;

	private String alias;
	private String toponimo;
	private String indirizzo;
	private String numeroCivico;
	private String edificio;
	private String scala;
	private String piano;
	private String interno;
	private String localita;
	private String cap;
	private String provincia;
	private String comune;
	private String paese;
	private String note;


	public DettaglioClientiObiettiviServizio(){
		model = new RobustLazyDataModel<Contatto>() {

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getToponimo() {
		return toponimo;
	}

	public void setToponimo(String toponimo) {
		this.toponimo = toponimo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getPiano() {
		return piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
