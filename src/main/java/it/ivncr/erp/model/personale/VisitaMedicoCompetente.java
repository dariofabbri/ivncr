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
@Table(name = "per_visita_medico_competente")
public class VisitaMedicoCompetente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_visita_medico_competente_id_seq")
	@SequenceGenerator(name = "per_visita_medico_competente_id_seq", sequenceName = "per_visita_medico_competente_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="medico")
	private String medico;

	@Column(name="data_visita")
	private Date dataVisita;

	@Column(name="esito")
	private String esito;

	@Column(name="data_visita_successiva")
	private Date dataVisitaSuccessiva;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("medico", medico)
			.append("dataVisita", dataVisita)
			.append("esito", esito)
			.append("dataVisitaSuccessiva", dataVisitaSuccessiva)
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

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public Date getDataVisitaSuccessiva() {
		return dataVisitaSuccessiva;
	}

	public void setDataVisitaSuccessiva(Date dataVisitaSuccessiva) {
		this.dataVisitaSuccessiva = dataVisitaSuccessiva;
	}
}
