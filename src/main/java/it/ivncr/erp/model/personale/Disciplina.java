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
@Table(name = "per_disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_disciplina_id_seq")
	@SequenceGenerator(name = "per_disciplina_id_seq", sequenceName = "per_disciplina_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="provvedimento")
	private String provvedimento;

	@Column(name="data_provvedimento")
	private Date dataProvvedimento;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("provvedimento", provvedimento)
			.append("dataProvvedimento", dataProvvedimento)
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

	public String getProvvedimento() {
		return provvedimento;
	}

	public void setProvvedimento(String provvedimento) {
		this.provvedimento = provvedimento;
	}

	public Date getDataProvvedimento() {
		return dataProvvedimento;
	}

	public void setDataProvvedimento(Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
