package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.GruppoCliente;
import it.ivncr.erp.model.commerciale.TipoCliente;

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
	private String codicefiscale;
	
	private Integer codiceGruppoCliente;
	private Integer divisa;
	
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

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public Integer getDivisa() {
		return divisa;
	}

	public void setDivisa(Integer divisa) {
		this.divisa = divisa;
	}
}