package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.GruppoCliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioClienteTestata implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteTestata.class);

	private static final long serialVersionUID = 1L;

	private String codice;
	private String ragioneSociale;
	private String partitaIva;
	private String codiceFiscale;
	
	private String nome;
	private String telefoni;
	private String fax;
	private String email;

	private Integer codiceGruppoCliente;
	private Integer codiceDivisa;

	private List<GruppoCliente> listGruppoCliente;
	private List<Divisa> listDivisa;

	public DettaglioClienteTestata() {

		listDivisa = new ArrayList<Divisa>();

		Divisa dv = new Divisa();
		dv.setId(1);
		dv.setDescrizione("€");
		listDivisa.add(dv);

		dv = new Divisa();
		dv.setId(1);
		dv.setDescrizione("$");
		listDivisa.add(dv);


		listGruppoCliente = new ArrayList<GruppoCliente>();

		GruppoCliente gc = new GruppoCliente();
		gc.setId(1);
		gc.setDescrizione("Grande Utenza");
		listGruppoCliente.add(gc);

		gc = new GruppoCliente();
		gc.setId(2);
		gc.setDescrizione("Piccola Utenza");
		listGruppoCliente.add(gc);
		
		
		nome = "Nome Cognome";
		telefoni = "328.1234567 / 335.7654321";
		fax = "06.72356723";
		email = "mail@gmail.com";
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public List<GruppoCliente> getListGruppoCliente() {
		return listGruppoCliente;
	}

	public void setListGruppoCliente(List<GruppoCliente> listGruppoCliente) {
		this.listGruppoCliente = listGruppoCliente;
	}

	public List<Divisa> getListDivisa() {
		return listDivisa;
	}

	public void setListDivisa(List<Divisa> listDivisa) {
		this.listDivisa = listDivisa;
	}

	public Integer getCodiceGruppoCliente() {
		return codiceGruppoCliente;
	}

	public void setCodiceGruppoCliente(Integer codiceGruppoCliente) {
		this.codiceGruppoCliente = codiceGruppoCliente;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Integer getCodiceDivisa() {
		return codiceDivisa;
	}

	public void setCodiceDivisa(Integer codiceDivisa) {
		this.codiceDivisa = codiceDivisa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoni() {
		return telefoni;
	}

	public void setTelefoni(String telefoni) {
		this.telefoni = telefoni;
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