package it.ivncr.erp.model.personale;

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
@Table(name = "per_libretto_porto_armi")
public class LibrettoPortoArmi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_libretto_porto_armi_id_seq")
	@SequenceGenerator(name = "per_libretto_porto_armi_id_seq", sequenceName = "per_libretto_porto_armi_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="numero")
	private String numero;

	@Column(name="fucile")
	private Boolean fucile;

	@Column(name="data_rilascio")
	private Date dataRilascio;

	@Column(name="data_scadenza")
	private Date dataScadenza;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("numero", numero)
			.append("fucile", fucile)
			.append("dataRilascio", dataRilascio)
			.append("dataScadenza", dataScadenza)
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getFucile() {
		return fucile;
	}

	public void setFucile(Boolean fucile) {
		this.fucile = fucile;
	}

	public Date getDataRilascio() {
		return dataRilascio;
	}

	public void setDataRilascio(Date dataRilascio) {
		this.dataRilascio = dataRilascio;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
