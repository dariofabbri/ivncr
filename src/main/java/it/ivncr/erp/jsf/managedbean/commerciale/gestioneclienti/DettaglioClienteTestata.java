package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

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
	private Integer codiceGruppoCliente;
	private List<TipoCliente> listTipoCliente;

	public DettaglioClienteTestata() {

		listTipoCliente = new ArrayList<TipoCliente>();

		TipoCliente tc = new TipoCliente();
		tc.setId(1);
		tc.setDescrizione("Grande utenza");
		listTipoCliente.add(tc);

		tc = new TipoCliente();
		tc.setId(2);
		tc.setDescrizione("Piccola utenza");
		listTipoCliente.add(tc);
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


	public List<TipoCliente> getListTipoCliente() {
		return listTipoCliente;
	}

	public void setListTipoCliente(List<TipoCliente> listTipoCliente) {
		this.listTipoCliente = listTipoCliente;
	}

	public Integer getCodiceGruppoCliente() {
		return codiceGruppoCliente;
	}

	public void setCodiceGruppoCliente(Integer codiceGruppoCliente) {
		this.codiceGruppoCliente = codiceGruppoCliente;
	}
}