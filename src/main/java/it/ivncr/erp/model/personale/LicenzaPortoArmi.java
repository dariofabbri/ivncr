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
@Table(name = "per_licenza_porto_armi")
public class LicenzaPortoArmi implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_licenza_porto_armi_id_seq")
	@SequenceGenerator(name = "per_licenza_porto_armi_id_seq", sequenceName = "per_licenza_porto_armi_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="libretto_id")
	private LibrettoPortoArmi librettoPortoArmi;

	@Column(name="numero")
	private String numero;

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
			.append("librettoPortoArmi", librettoPortoArmi, false)
			.append("numero", numero)
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

	public LibrettoPortoArmi getLibrettoPortoArmi() {
		return librettoPortoArmi;
	}

	public void setLibrettoPortoArmi(LibrettoPortoArmi librettoPortoArmi) {
		this.librettoPortoArmi = librettoPortoArmi;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
