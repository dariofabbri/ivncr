package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.Contatto;

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
@Table(name = "con_contratto_contatto")
public class ContrattoContatto implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_contratto_contatto_id_seq")
	@SequenceGenerator(name = "con_contratto_contatto_id_seq", sequenceName = "con_contratto_contatto_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contratto_id", referencedColumnName="id")
	Contratto contratto;

	@ManyToOne
	@JoinColumn(name="contatto_id", referencedColumnName="id")
	Contatto contatto;

	@Column(name="valido_da")
	private Date validoDa;

	@Column(name="valido_a")
	private Date validoA;

	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("contatto", contatto, false)
			.append("validoDa", validoDa)
			.append("validoA", validoA)
			.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}

	public Date getValidoDa() {
		return validoDa;
	}

	public void setValidoDa(Date validoDa) {
		this.validoDa = validoDa;
	}

	public Date getValidoA() {
		return validoA;
	}

	public void setValidoA(Date validoA) {
		this.validoA = validoA;
	}
}
