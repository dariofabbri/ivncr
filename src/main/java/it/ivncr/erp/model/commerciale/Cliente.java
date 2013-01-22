package it.ivncr.erp.model.commerciale;

import java.math.BigDecimal;
import java.util.Date;

public class Cliente {

	private Integer id;
	private String codice;
	private String ragioneSociale;
	private String partitaIva;
	private String codiceFiscale;
	private BigDecimal saldoContabile;
	private Integer codiceGruppoCliente;
	private Integer codiceDivisa;

	private String telefono1;
	private String telefono2;
	private String cellulare;
	private String fax;
	private String email;

	private boolean attivo;
	private Date attivoDal;
	private Date attivoAl;
	private String attivoOsservazioni;

	private boolean bloccato;
	private Date bloccatoDal;
	private Date bloccatoAl;
	private String bloccatoOsservazioni;

	private Integer codiceTipoBusinessPartner;

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

	public BigDecimal getSaldoContabile() {
		return saldoContabile;
	}

	public void setSaldoContabile(BigDecimal saldoContabile) {
		this.saldoContabile = saldoContabile;
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

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
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

	public String getAttivoOsservazioni() {
		return attivoOsservazioni;
	}

	public void setAttivoOsservazioni(String attivoOsservazioni) {
		this.attivoOsservazioni = attivoOsservazioni;
	}

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
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

	public String getBloccatoOsservazioni() {
		return bloccatoOsservazioni;
	}

	public void setBloccatoOsservazioni(String bloccatoOsservazioni) {
		this.bloccatoOsservazioni = bloccatoOsservazioni;
	}

	public Integer getCodiceTipoBusinessPartner() {
		return codiceTipoBusinessPartner;
	}

	public void setCodiceTipoBusinessPartner(Integer codiceTipoBusinessPartner) {
		this.codiceTipoBusinessPartner = codiceTipoBusinessPartner;
	}
}
