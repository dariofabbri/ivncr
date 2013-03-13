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
@Table(name = "per_familiare")
public class Familiare {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_familiare_id_seq")
	@SequenceGenerator(name = "per_familiare_id_seq", sequenceName = "per_familiare_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_familiare_id")
	private TipoFamiliare tipoFamiliare;

	@Column(name="nome")
	private String nome;

	@Column(name="cognome")
	private String cognome;

	@Column(name="luogo_nascita")
	private Date luogoNascita;

	@Column(name="data_nascita")
	private Date dataNascita;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("tipoFamiliare", tipoFamiliare, false)
			.append("nome", nome)
			.append("cognome", cognome)
			.append("luogoNascita", luogoNascita)
			.append("dataNascita", dataNascita)
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

	public TipoFamiliare getTipoFamiliare() {
		return tipoFamiliare;
	}

	public void setTipoFamiliare(TipoFamiliare tipoFamiliare) {
		this.tipoFamiliare = tipoFamiliare;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(Date luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
