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
@Table(name = "per_corso")
public class Corso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_corso_id_seq")
	@SequenceGenerator(name = "per_corso_id_seq", sequenceName = "per_corso_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="ente")
	private String ente;

	@Column(name="abilitazione")
	private String abilitazione;

	@Column(name="ore_corso")
	private Integer oreCorso;

	@Column(name="valutazione")
	private String valutazione;

	@Column(name="data_conseguimento")
	private Date dataConseguimento;

	@Column(name="data_inizio")
	private Date dataInizio;

	@Column(name="data_fine")
	private Date dataFine;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("ente", ente)
			.append("abilitazione", abilitazione)
			.append("oreCorso", oreCorso)
			.append("valutazione", valutazione)
			.append("dataConseguimento", dataConseguimento)
			.append("dataInizio", dataInizio)
			.append("dataFine", dataFine)
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

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getAbilitazione() {
		return abilitazione;
	}

	public void setAbilitazione(String abilitazione) {
		this.abilitazione = abilitazione;
	}

	public Integer getOreCorso() {
		return oreCorso;
	}

	public void setOreCorso(Integer oreCorso) {
		this.oreCorso = oreCorso;
	}

	public String getValutazione() {
		return valutazione;
	}

	public void setValutazione(String valutazione) {
		this.valutazione = valutazione;
	}

	public Date getDataConseguimento() {
		return dataConseguimento;
	}

	public void setDataConseguimento(Date dataConseguimento) {
		this.dataConseguimento = dataConseguimento;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
