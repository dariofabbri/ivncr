package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.Contatto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "con_contratto_contatto")
@IdClass(ContrattoContattoId.class)
public class ContrattoContatto {

	@Id
	@Column(name="contratto_id", insertable=false, updatable=false)
	private Integer contrattoId;

	@Id
	@Column(name="contatto_id", insertable=false, updatable=false)
	private Integer contattoId;

	@Column(name="valido_da")
	private Date validoDa;

	@Column(name="valido_a")
	private Date validoA;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contratto_id", referencedColumnName="id")
	Contratto contratto;

	@ManyToOne
	@JoinColumn(name="contatto_id", referencedColumnName="id")
	Contatto contatto;

	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("contratto", contratto, false)
			.append("contatto", contatto, false)
			.append("validoDa", validoDa)
			.append("validoA", validoA)
			.toString();
	}


	public Integer getContrattoId() {
		return contrattoId;
	}

	public void setContrattoId(Integer contrattoId) {
		this.contrattoId = contrattoId;
	}

	public Integer getContattoId() {
		return contattoId;
	}

	public void setContattoId(Integer contattoId) {
		this.contattoId = contattoId;
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
}
