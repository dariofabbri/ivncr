package it.ivncr.erp.model.commerciale.contratto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "con_esattore")
public class Esattore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_esattore_id_seq")
	@SequenceGenerator(name = "con_esattore_id_seq", sequenceName = "con_esattore_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="nome")
	private String nome;

	@Column(name="cognome")
	private String cognome;

	@Column(name="data_nascita")
	private Date dataNascita;

	@Column(name="luogo_nascita")
	private String luogoNascita;

	@Column(name="codice_fiscale")
	private String codiceFiscale;

	@Column(name="sesso")
	private String sesso;

	@Lob
	@Column(name="foto")
	private byte[] foto;

	@Column(name="note")
	private String note;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="esattore")
	@OrderBy
	private Set<EsattoreAzienda> aziende;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("nome", nome)
			.append("cognome", cognome)
			.append("dataNascita", dataNascita)
			.append("luogoNascita", luogoNascita)
			.append("codiceFiscale", codiceFiscale)
			.append("sesso", sesso)
			.append("foto", foto, false)
			.append("note", note)
			.append("aziende", aziende, false)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<EsattoreAzienda> getAziende() {
		return aziende;
	}

	public void setAziende(Set<EsattoreAzienda> aziende) {
		this.aziende = aziende;
	}
}
