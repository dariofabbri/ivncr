package it.ivncr.erp.model.commerciale.contratto;

import java.io.Serializable;
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
@Table(name = "con_apparecchiatura_tecnologica")
public class ApparecchiaturaTecnologica implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_apparecchiatura_tecnologica_id_seq")
	@SequenceGenerator(name = "con_apparecchiatura_tecnologica_id_seq", sequenceName = "con_apparecchiatura_tecnologica_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

    @ManyToOne
    @JoinColumn(name="tipo_apparecchiatura_id")
    private TipoApparecchiaturaTecnologica tipoApparecchiaturaTecnologica;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="matricola")
	private String matricola;

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

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("tipoApparecchiaturaTecnologica", tipoApparecchiaturaTecnologica, false)
			.append("descrizione", descrizione)
			.append("matricola", matricola)
			.append("comodatoUso", comodatoUso)
			.append("dataInstallazione", dataInstallazione)
			.append("dataFatturazione", dataFatturazione)
			.append("dataRitiro", dataRitiro)
			.append("costoUnaTantum", costoUnaTantum)
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
