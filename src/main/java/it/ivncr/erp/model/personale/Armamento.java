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
@Table(name = "per_armamento")
public class Armamento implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_armamento_id_seq")
	@SequenceGenerator(name = "per_armamento_id_seq", sequenceName = "per_armamento_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_arma_id")
	private TipoArma tipoArma;

	@Column(name="modello_arma")
	private String modelloArma;

	@Column(name="calibro_arma")
	private String calibroArma;

	@Column(name="matricola")
	private String matricola;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stato_arma_id")
	private StatoArma statoArma;

	@Column(name="data_denuncia")
	private Date dataDenuncia;

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
			.append("tipoArma", tipoArma, false)
			.append("modelloArma", modelloArma)
			.append("calibroArma", calibroArma)
			.append("matricola", matricola)
			.append("statoArma", statoArma, false)
			.append("dataDenuncia", dataDenuncia)
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

	public TipoArma getTipoArma() {
		return tipoArma;
	}

	public void setTipoArma(TipoArma tipoArma) {
		this.tipoArma = tipoArma;
	}

	public String getModelloArma() {
		return modelloArma;
	}

	public void setModelloArma(String modelloArma) {
		this.modelloArma = modelloArma;
	}

	public String getCalibroArma() {
		return calibroArma;
	}

	public void setCalibroArma(String calibroArma) {
		this.calibroArma = calibroArma;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public StatoArma getStatoArma() {
		return statoArma;
	}

	public void setStatoArma(StatoArma statoArma) {
		this.statoArma = statoArma;
	}

	public Date getDataDenuncia() {
		return dataDenuncia;
	}

	public void setDataDenuncia(Date dataDenuncia) {
		this.dataDenuncia = dataDenuncia;
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
