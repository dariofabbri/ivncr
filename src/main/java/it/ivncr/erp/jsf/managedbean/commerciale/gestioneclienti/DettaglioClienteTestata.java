package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.GruppoCliente;

import java.io.Serializable;
import java.util.ArrayList;
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

	@ManagedProperty("#{gestioneClienti.edited}")
	private Cliente edited;

	private String nome;
	private String telefoni;
	private String fax;
	private String email;

	private List<GruppoCliente> listGruppoCliente;
	private List<Divisa> listDivisa;


	@PostConstruct
	public void init() {

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

		loadRiepilogoContatto();
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

	public Cliente getEdited() {
		return edited;
	}

	public void setEdited(Cliente edited) {
		this.edited = edited;
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