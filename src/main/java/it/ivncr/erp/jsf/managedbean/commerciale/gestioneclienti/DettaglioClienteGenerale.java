package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.TipoBusinessPartner;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.cliente.ClienteService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioClienteGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneClienti.edited.id}")
	private Integer id;

	private String telefono1;
	private String telefono2;
	private String cellulare;
	private String fax;
	private String email;
	private Integer codiceTipoBusinessPartner;

	private Boolean attivo;
	private Date attivoDal;
	private Date attivoAl;
	private String attivoNote;

	private Boolean bloccato;
	private Date bloccatoDal;
	private Date bloccatoAl;
	private String bloccatoNote;

	private List<TipoBusinessPartner> listTipoBusinessPartner;

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load tipo business partner LUT.
		//
		listTipoBusinessPartner = lutService.listItems("TipoBusinessPartner");

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			ClienteService cs = ServiceFactory.createService("Cliente");
			Cliente cliente = cs.retrieveDeep(id);

			telefono1 = cliente.getTelefono1();
			telefono2 = cliente.getTelefono2();
			cellulare = cliente.getCellulare();
			fax = cliente.getFax();
			email = cliente.getEmail();
			codiceTipoBusinessPartner = cliente.getTipoBusinessPartner() != null ? cliente.getTipoBusinessPartner().getId() : null;

			attivo = cliente.isAttivo();
			attivoDal = cliente.getAttivoDal();
			attivoAl = cliente.getAttivoAl();
			attivoNote = cliente.getAttivoNote();

			bloccato = cliente.isBloccato();
			bloccatoDal = cliente.getBloccatoDal();
			bloccatoAl = cliente.getBloccatoAl();
			bloccatoNote = cliente.getBloccatoNote();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCodiceTipoBusinessPartner() {
		return codiceTipoBusinessPartner;
	}

	public void setCodiceTipoBusinessPartner(Integer codiceTipoBusinessPartner) {
		this.codiceTipoBusinessPartner = codiceTipoBusinessPartner;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public Date getAttivoDal() {
		return attivoDal;
	}

	public void setAttivoDal(Date attivoDal) {
		this.attivoDal = attivoDal;
	}

	public Date getAttivoAl() {
		return attivoAl;
	}

	public void setAttivoAl(Date attivoAl) {
		this.attivoAl = attivoAl;
	}

	public String getAttivoNote() {
		return attivoNote;
	}

	public void setAttivoNote(String attivoNote) {
		this.attivoNote = attivoNote;
	}

	public Boolean getBloccato() {
		return bloccato;
	}

	public void setBloccato(Boolean bloccato) {
		this.bloccato = bloccato;
	}

	public Date getBloccatoDal() {
		return bloccatoDal;
	}

	public void setBloccatoDal(Date bloccatoDal) {
		this.bloccatoDal = bloccatoDal;
	}

	public Date getBloccatoAl() {
		return bloccatoAl;
	}

	public void setBloccatoAl(Date bloccatoAl) {
		this.bloccatoAl = bloccatoAl;
	}

	public String getBloccatoNote() {
		return bloccatoNote;
	}

	public void setBloccatoNote(String bloccatoNote) {
		this.bloccatoNote = bloccatoNote;
	}

	public List<TipoBusinessPartner> getListTipoBusinessPartner() {
		return listTipoBusinessPartner;
	}

	public void setListTipoBusinessPartner(
			List<TipoBusinessPartner> listTipoBusinessPartner) {
		this.listTipoBusinessPartner = listTipoBusinessPartner;
	}
}
