package it.ivncr.erp.model.personale;

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
@Table(name = "per_certificato_medico")
public class CertificatoMedico implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_certificato_medico_id_seq")
	@SequenceGenerator(name = "per_certificato_medico_id_seq", sequenceName = "per_certificato_medico_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_certificato_medico_id")
	private TipoCertificatoMedico tipoCertificatoMedico;

	@Column(name="data_certificato")
	private Date dataCertificato;

	@Column(name="data_ricezione")
	private Date dataRicezione;

	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("tipoCertificato", tipoCertificatoMedico, false)
			.append("dataCertificato", dataCertificato)
			.append("dataRicezione", dataRicezione)
			.append("dataInizioValidita", dataInizioValidita)
			.append("dataFineValidita", dataFineValidita)
			.append("note", note)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public TipoCertificatoMedico getTipoCertificato() {
		return tipoCertificatoMedico;
	}

	public void setTipoCertificato(TipoCertificatoMedico tipoCertificatoMedico) {
		this.tipoCertificatoMedico = tipoCertificatoMedico;
	}

	public Date getDataCertificato() {
		return dataCertificato;
	}

	public void setDataCertificato(Date dataCertificato) {
		this.dataCertificato = dataCertificato;
	}

	public Date getDataRicezione() {
		return dataRicezione;
	}

	public void setDataRicezione(Date dataRicezione) {
		this.dataRicezione = dataRicezione;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
