package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.GruppoCliente;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioClienteTestata implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteTestata.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneClienti.edited.id}")
	private Integer id;

	private String codice;
	private String ragioneSociale;
	private String partitaIva;
	private String codiceFiscale;
	private Integer codiceGruppoCliente;
	private Integer codiceDivisa;
	private BigDecimal saldoContabile;

	private String nome;
	private String telefoni;
	private String fax;
	private String email;

	private List<GruppoCliente> listGruppoCliente;
	private List<Divisa> listDivisa;


	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createLUTService();

		// Load gruppo cliente LUT.
		//
		listGruppoCliente = lutService.listItems("GruppoCliente");

		// Load divisa LUT.
		//
		listDivisa = lutService.listItems("Divisa");

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			// TODO: services still missing.
			//

			// Load contact summary data.
			//
			loadRiepilogoContatto();
		}
	}

	public void loadRiepilogoContatto() {

		nome = "Nome Cognome";
		telefoni = "328.1234567 / 335.7654321";
		fax = "06.72356723";
		email = "mail@gmail.com";
	}

	public void doSave() {

		logger.debug("doSave() called!");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Integer getCodiceGruppoCliente() {
		return codiceGruppoCliente;
	}

	public void setCodiceGruppoCliente(Integer codiceGruppoCliente) {
		this.codiceGruppoCliente = codiceGruppoCliente;
	}

	public Integer getCodiceDivisa() {
		return codiceDivisa;
	}

	public void setCodiceDivisa(Integer codiceDivisa) {
		this.codiceDivisa = codiceDivisa;
	}

	public BigDecimal getSaldoContabile() {
		return saldoContabile;
	}

	public void setSaldoContabile(BigDecimal saldoContabile) {
		this.saldoContabile = saldoContabile;
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
}