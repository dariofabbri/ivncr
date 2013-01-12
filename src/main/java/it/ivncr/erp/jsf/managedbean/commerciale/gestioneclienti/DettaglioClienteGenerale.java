package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.TipoBusinessPartner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioClienteGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteGenerale.class);

	private static final long serialVersionUID = 1L;

	private String telefono1;
	private String telefono2;
	private Integer codiceTipoBusinessPartner;
	private List<TipoBusinessPartner> listTipoBusinessPartner;

	public DettaglioClienteGenerale() {
		
		listTipoBusinessPartner = new ArrayList<TipoBusinessPartner>();
		
		TipoBusinessPartner tbp = new TipoBusinessPartner();
		tbp.setId(1);
		tbp.setDescrizione("Società");
		listTipoBusinessPartner.add(tbp);
		
		tbp = new TipoBusinessPartner();
		tbp.setId(2);
		tbp.setDescrizione("Privato");
		listTipoBusinessPartner.add(tbp);
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

	public Integer getCodiceTipoBusinessPartner() {
		return codiceTipoBusinessPartner;
	}

	public void setCodiceTipoBusinessPartner(Integer codiceTipoBusinessPartner) {
		this.codiceTipoBusinessPartner = codiceTipoBusinessPartner;
	}

	public List<TipoBusinessPartner> getListTipoBusinessPartner() {
		return listTipoBusinessPartner;
	}

	public void setListTipoBusinessPartner(
			List<TipoBusinessPartner> listTipoBusinessPartner) {
		this.listTipoBusinessPartner = listTipoBusinessPartner;
	}
}
