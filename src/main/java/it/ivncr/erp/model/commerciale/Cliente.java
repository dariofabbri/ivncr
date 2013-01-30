package it.ivncr.erp.model.commerciale;

import it.ivncr.erp.model.generale.Azienda;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "com_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "com_cliente_id_seq")
	@SequenceGenerator(name = "com_cliente_id_seq", sequenceName = "com_cliente_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="codice")
	private String codice;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="azienda_id")
	private Azienda azienda;

	@Column(name="ragione_sociale")
	private String ragioneSociale;

	@Column(name="partita_iva")
	private String partitaIva;

	@Column(name="codice_fiscale")
	private String codiceFiscale;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gruppo_cliente_id")
	private GruppoCliente gruppoCliente;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="divisa_id")
	private Divisa divisa;

	@Column(name="telefono_1")
	private String telefono1;

	@Column(name="telefono_2")
	private String telefono2;

	@Column(name="cellulare")
	private String cellulare;

	@Column(name="fax")
	private String fax;

	@Column(name="email")
	private String email;

	@Column(name="attivo")
	private Boolean attivo;

	@Column(name="attivo_dal_ts")
	private Date attivoDal;

	@Column(name="attivo_al_ts")
	private Date attivoAl;

	@Column(name="attivo_note")
	private String attivoNote;

	@Column(name="bloccato")
	private Boolean bloccato;

	@Column(name="bloccato_dal_ts")
	private Date bloccatoDal;

	@Column(name="bloccato_al_ts")
	private Date bloccatoAl;

	@Column(name="bloccato_note")
	private String bloccatoNote;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_business_partner_id")
	private TipoBusinessPartner tipoBusinessPartner;

	@Column(name="saldo_contabile")
	private BigDecimal saldoContabile;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_modifica_ts")
	private Date ultimaModifica;

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

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
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

	public GruppoCliente getGruppoCliente() {
		return gruppoCliente;
	}

	public void setGruppoCliente(GruppoCliente gruppoCliente) {
		this.gruppoCliente = gruppoCliente;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
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

	public TipoBusinessPartner getTipoBusinessPartner() {
		return tipoBusinessPartner;
	}

	public void setTipoBusinessPartner(TipoBusinessPartner tipoBusinessPartner) {
		this.tipoBusinessPartner = tipoBusinessPartner;
	}

	public BigDecimal getSaldoContabile() {
		return saldoContabile;
	}

	public void setSaldoContabile(BigDecimal saldoContabile) {
		this.saldoContabile = saldoContabile;
	}

	public Date getCreazione() {
		return creazione;
	}

	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}

	public Date getUltimaModifica() {
		return ultimaModifica;
	}

	public void setUltimaModifica(Date ultimaModifica) {
		this.ultimaModifica = ultimaModifica;
	}
}
