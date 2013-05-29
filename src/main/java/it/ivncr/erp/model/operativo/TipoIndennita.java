package it.ivncr.erp.model.operativo;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "ope_tipo_indennita")
public class TipoIndennita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ope_tipo_indennita_id_seq")
	@SequenceGenerator(name = "ope_tipo_indennita_id_seq", sequenceName = "ope_tipo_indennita_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="codice")
	private String codice;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="notturno")
	private BigDecimal notturno;

	@Column(name="diurno")
	private BigDecimal diurno;

	@Column(name="oraria")
	private Boolean oraria;

	@Column(name="visibile")
	private Boolean visibile;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="riferimento_id")
	private TipoIndennita riferimento;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aggiuntivo_id")
	private TipoIndennita aggiuntivo;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("codice", codice)
			.append("descrizione", descrizione)
			.append("notturno", notturno)
			.append("diurno", diurno)
			.append("oraria", oraria)
			.append("visibile", visibile)
			.append("riferimento", riferimento, false)
			.append("aggiuntivo", aggiuntivo, false)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getNotturno() {
		return notturno;
	}

	public void setNotturno(BigDecimal notturno) {
		this.notturno = notturno;
	}

	public BigDecimal getDiurno() {
		return diurno;
	}

	public void setDiurno(BigDecimal diurno) {
		this.diurno = diurno;
	}

	public Boolean getOraria() {
		return oraria;
	}

	public void setOraria(Boolean oraria) {
		this.oraria = oraria;
	}

	public Boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}

	public TipoIndennita getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(TipoIndennita riferimento) {
		this.riferimento = riferimento;
	}

	public TipoIndennita getAggiuntivo() {
		return aggiuntivo;
	}

	public void setAggiuntivo(TipoIndennita aggiuntivo) {
		this.aggiuntivo = aggiuntivo;
	}
}