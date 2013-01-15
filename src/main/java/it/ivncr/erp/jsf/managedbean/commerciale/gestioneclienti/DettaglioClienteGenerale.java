package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.TipoBusinessPartner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
	private String cellulare;
	private String fax;
	private String email;
	private String osservazioniAttivo;
	private String osservazioniBloccato;
	
	
	private boolean attivo;	  
	private boolean bloccato;
	
	private Date dateAttiveDal; 
	private Date dateAttiveAl; 
	
	private Date dateBloccatoDal; 
	private Date dateBloccatoAl;
	
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


	public Date getDateAttiveDal() {
		return dateAttiveDal;
	}

	public void setDateAttiveDal(Date dateAttiveDal) {
		this.dateAttiveDal = dateAttiveDal;
	}

	public Date getDateAttiveAl() {
		return dateAttiveAl;
	}

	public void setDateAttiveAl(Date dateAttiveAl) {
		this.dateAttiveAl = dateAttiveAl;
	}

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public Date getDateBloccatoAl() {
		return dateBloccatoAl;
	}

	public void setDateBloccatoAl(Date dateBloccatoAl) {
		this.dateBloccatoAl = dateBloccatoAl;
	}

	public Date getDateBloccatoDal() {
		return dateBloccatoDal;
	}

	public void setDateBloccatoDal(Date dateBloccatoDal) {
		this.dateBloccatoDal = dateBloccatoDal;
	}

	public String getOsservazioniAttivo() {
		return osservazioniAttivo;
	}

	public void setOsservazioniAttivo(String osservazioniAttivo) {
		this.osservazioniAttivo = osservazioniAttivo;
	}

	public String getOsservazioniBloccato() {
		return osservazioniBloccato;
	}

	public void setOsservazioniBloccato(String osservazioniBloccato) {
		this.osservazioniBloccato = osservazioniBloccato;
	}
}
