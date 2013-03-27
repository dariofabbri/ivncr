package it.ivncr.erp.model.commerciale.contratto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "con_apparecchiature_tecnologiche")
public class ApparecchiatureTecnologiche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_apparecchiature_tecnologiche_id_seq")
	@SequenceGenerator(name = "con_apparecchiature_tecnologiche_id_seq", sequenceName = "con_apparecchiature_tecnologiche_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

    @ManyToOne
    @JoinColumn(name="tipo_apparecchiatura_id")
    private TipoApparecchiaturaTecnologica tipoApparecchiaturaTecnologica;

	@Column(name="comodato_uso")
	private Boolean comodatoUso;

	@Column(name="data_installazione")
	private Date dataInstallazione;

	@Column(name="data_fatturazione")
	private Date dataFatturazione;

	@Column(name="data_ritiro")
	private Date dataRitiro;

	@Column(name="costo_una_tantum")
	private BigDecimal costoUnaTantum;

	@Column(name="numero_telecamere")
	private Integer numeroTelecamere;

	@Column(name="numero_periferiche")
	private Integer numeroPeriferiche;

	@Column(name="numero_sensori")
	private Integer numeroSensori;

	@Column(name="numero_centrali")
	private Integer numeroCentrali;

	@Column(name="numero_telecomandi")
	private Integer numeroTelecomandi;

	@Column(name="numero_tastiere")
	private Integer numeroTastiere;

	@Column(name="numero_videoregistratori")
	private Integer numeroVideoregistratori;

	@Column(name="numero_datix")
	private Integer numeroDatix;

	@Column(name="numero_metal_detector")
	private Integer numeroMetalDetector;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("tipoApparecchiaturaTecnologica", tipoApparecchiaturaTecnologica, false)
			.append("comodatoUso", comodatoUso)
			.append("dataInstallazione", dataInstallazione)
			.append("dataFatturazione", dataFatturazione)
			.append("dataRitiro", dataRitiro)
			.append("costoUnaTantum", costoUnaTantum)
			.append("numeroTelecamere", numeroTelecamere)
			.append("numeroPeriferiche", numeroPeriferiche)
			.append("numeroSensori", numeroSensori)
			.append("numeroCentrali", numeroCentrali)
			.append("numeroTelecomandi", numeroTelecomandi)
			.append("numeroTastiere", numeroTastiere)
			.append("numeroVideoregistratori", numeroVideoregistratori)
			.append("numeroDatix", numeroDatix)
			.append("numeroMetalDetector", numeroMetalDetector)
			.append("note", note)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public TipoApparecchiaturaTecnologica getTipoApparecchiaturaTecnologica() {
		return tipoApparecchiaturaTecnologica;
	}

	public void setTipoApparecchiaturaTecnologica(
			TipoApparecchiaturaTecnologica tipoApparecchiaturaTecnologica) {
		this.tipoApparecchiaturaTecnologica = tipoApparecchiaturaTecnologica;
	}

	public Boolean getComodatoUso() {
		return comodatoUso;
	}

	public void setComodatoUso(Boolean comodatoUso) {
		this.comodatoUso = comodatoUso;
	}

	public Date getDataInstallazione() {
		return dataInstallazione;
	}

	public void setDataInstallazione(Date dataInstallazione) {
		this.dataInstallazione = dataInstallazione;
	}

	public Date getDataFatturazione() {
		return dataFatturazione;
	}

	public void setDataFatturazione(Date dataFatturazione) {
		this.dataFatturazione = dataFatturazione;
	}

	public Date getDataRitiro() {
		return dataRitiro;
	}

	public void setDataRitiro(Date dataRitiro) {
		this.dataRitiro = dataRitiro;
	}

	public BigDecimal getCostoUnaTantum() {
		return costoUnaTantum;
	}

	public void setCostoUnaTantum(BigDecimal costoUnaTantum) {
		this.costoUnaTantum = costoUnaTantum;
	}

	public Integer getNumeroTelecamere() {
		return numeroTelecamere;
	}

	public void setNumeroTelecamere(Integer numeroTelecamere) {
		this.numeroTelecamere = numeroTelecamere;
	}

	public Integer getNumeroPeriferiche() {
		return numeroPeriferiche;
	}

	public void setNumeroPeriferiche(Integer numeroPeriferiche) {
		this.numeroPeriferiche = numeroPeriferiche;
	}

	public Integer getNumeroSensori() {
		return numeroSensori;
	}

	public void setNumeroSensori(Integer numeroSensori) {
		this.numeroSensori = numeroSensori;
	}

	public Integer getNumeroCentrali() {
		return numeroCentrali;
	}

	public void setNumeroCentrali(Integer numeroCentrali) {
		this.numeroCentrali = numeroCentrali;
	}

	public Integer getNumeroTelecomandi() {
		return numeroTelecomandi;
	}

	public void setNumeroTelecomandi(Integer numeroTelecomandi) {
		this.numeroTelecomandi = numeroTelecomandi;
	}

	public Integer getNumeroTastiere() {
		return numeroTastiere;
	}

	public void setNumeroTastiere(Integer numeroTastiere) {
		this.numeroTastiere = numeroTastiere;
	}

	public Integer getNumeroVideoregistratori() {
		return numeroVideoregistratori;
	}

	public void setNumeroVideoregistratori(Integer numeroVideoregistratori) {
		this.numeroVideoregistratori = numeroVideoregistratori;
	}

	public Integer getNumeroDatix() {
		return numeroDatix;
	}

	public void setNumeroDatix(Integer numeroDatix) {
		this.numeroDatix = numeroDatix;
	}

	public Integer getNumeroMetalDetector() {
		return numeroMetalDetector;
	}

	public void setNumeroMetalDetector(Integer numeroMetalDetector) {
		this.numeroMetalDetector = numeroMetalDetector;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
