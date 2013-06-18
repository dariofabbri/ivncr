package it.ivncr.erp.model.operativo;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Reparto;

import java.io.Serializable;
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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "ope_servizio")
public class Servizio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ope_servizio_id_seq")
	@SequenceGenerator(name = "ope_servizio_id_seq", sequenceName = "ope_servizio_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="azienda_id")
	private Azienda azienda;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ods_nuova_attivazione_id")
	private OrdineServizio odsNuovaAttivazione;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ods_id")
	private OrdineServizio ods;

	@Column(name="data_mattinale")
	private Date dataMattinale;

	@Column(name="data_retribuzione")
	private Date dataRetribuzione;

	@Column(name="giorno_successivo")
	private Boolean giornoSuccessivo;

	@Column(name="orario_da")
	private Date orarioDa;

	@Column(name="orario_a")
	private Date orarioA;

	@Column(name="retribuito")
	private Boolean retribuito;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="causale_ods_id")
	private CausaleOds causaleOds;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="altro_reparto_id")
	private Reparto altroReparto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_riposo_id")
	private TipoRiposo tipoRiposo;

	@Column(name="indennita_trasferta")
	private Boolean indennitaTrasferta;

	@Column(name="indennita_gra")
	private Boolean indennitaGra;

	@Column(name="indennita_poligono")
	private Boolean indennitaPoligono;

	@Column(name="affiancamento")
	private Boolean affiancamento;

	@Column(name="note")
	private String note;

	@Column(name="stato")
	private Boolean stato;

	@Column(name="inibisci_trigger_paghe")
	private Boolean inibisciTriggerPaghe;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_modifica_ts")
	private Date ultimaModifica;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("azienda", azienda)
			.append("addetto", addetto)
			.append("odsNuovaAttivazione", odsNuovaAttivazione)
			.append("ods", ods)
			.append("dataMattinale", dataMattinale)
			.append("dataRetribuzione", dataRetribuzione)
			.append("giornoSuccessivo", giornoSuccessivo)
			.append("orarioDa", orarioDa)
			.append("orarioA", orarioA)
			.append("retribuito", retribuito)
			.append("causaleOds", causaleOds)
			.append("altroReparto", altroReparto)
			.append("tipoRiposo", tipoRiposo)
			.append("indennitaTrasferta", indennitaTrasferta)
			.append("indennitaGra", indennitaGra)
			.append("indennitaPoligono", indennitaPoligono)
			.append("affiancamento", affiancamento)
			.append("note", note)
			.append("stato", stato)
			.append("inibisciTriggerPaghe", inibisciTriggerPaghe)
			.append("creazione", creazione)
			.append("ultimaModifica", ultimaModifica)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public OrdineServizio getOdsNuovaAttivazione() {
		return odsNuovaAttivazione;
	}

	public void setOdsNuovaAttivazione(OrdineServizio odsNuovaAttivazione) {
		this.odsNuovaAttivazione = odsNuovaAttivazione;
	}

	public OrdineServizio getOds() {
		return ods;
	}

	public void setOds(OrdineServizio ods) {
		this.ods = ods;
	}

	public Date getDataMattinale() {
		return dataMattinale;
	}

	public void setDataMattinale(Date dataMattinale) {
		this.dataMattinale = dataMattinale;
	}

	public Date getDataRetribuzione() {
		return dataRetribuzione;
	}

	public void setDataRetribuzione(Date dataRetribuzione) {
		this.dataRetribuzione = dataRetribuzione;
	}

	public Boolean getGiornoSuccessivo() {
		return giornoSuccessivo;
	}

	public void setGiornoSuccessivo(Boolean giornoSuccessivo) {
		this.giornoSuccessivo = giornoSuccessivo;
	}

	public Date getOrarioDa() {
		return orarioDa;
	}

	public void setOrarioDa(Date orarioDa) {
		this.orarioDa = orarioDa;
	}

	public Date getOrarioA() {
		return orarioA;
	}

	public void setOrarioA(Date orarioA) {
		this.orarioA = orarioA;
	}

	public Boolean getRetribuito() {
		return retribuito;
	}

	public void setRetribuito(Boolean retribuito) {
		this.retribuito = retribuito;
	}

	public CausaleOds getCausaleOds() {
		return causaleOds;
	}

	public void setCausaleOds(CausaleOds causaleOds) {
		this.causaleOds = causaleOds;
	}

	public Reparto getAltroReparto() {
		return altroReparto;
	}

	public void setAltroReparto(Reparto altroReparto) {
		this.altroReparto = altroReparto;
	}

	public TipoRiposo getTipoRiposo() {
		return tipoRiposo;
	}

	public void setTipoRiposo(TipoRiposo tipoRiposo) {
		this.tipoRiposo = tipoRiposo;
	}

	public Boolean getIndennitaTrasferta() {
		return indennitaTrasferta;
	}

	public void setIndennitaTrasferta(Boolean indennitaTrasferta) {
		this.indennitaTrasferta = indennitaTrasferta;
	}

	public Boolean getIndennitaGra() {
		return indennitaGra;
	}

	public void setIndennitaGra(Boolean indennitaGra) {
		this.indennitaGra = indennitaGra;
	}

	public Boolean getIndennitaPoligono() {
		return indennitaPoligono;
	}

	public void setIndennitaPoligono(Boolean indennitaPoligono) {
		this.indennitaPoligono = indennitaPoligono;
	}

	public Boolean getAffiancamento() {
		return affiancamento;
	}

	public void setAffiancamento(Boolean affiancamento) {
		this.affiancamento = affiancamento;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getStato() {
		return stato;
	}

	public void setStato(Boolean stato) {
		this.stato = stato;
	}

	public Boolean getInibisciTriggerPaghe() {
		return inibisciTriggerPaghe;
	}

	public void setInibisciTriggerPaghe(Boolean inibisciTriggerPaghe) {
		this.inibisciTriggerPaghe = inibisciTriggerPaghe;
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