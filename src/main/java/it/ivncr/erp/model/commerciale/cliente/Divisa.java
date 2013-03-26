package it.ivncr.erp.model.commerciale.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "com_divisa")
public class Divisa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "com_divisa_id_seq")
	@SequenceGenerator(name = "com_divisa_id_seq", sequenceName = "com_divisa_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="simbolo")
	private String simbolo;

	@Column(name="iso_alpha")
	private String isoAlphaCode;

	@Column(name="iso_num")
	private Integer isoNumericCode;

	@Column(name="descrizione")
	private String descrizione;

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("simbolo", simbolo)
			.append("isoAlphaCode", isoAlphaCode)
			.append("isoNumericCode", isoNumericCode)
			.append("descrizione", descrizione)
			.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getIsoAlphaCode() {
		return isoAlphaCode;
	}

	public void setIsoAlphaCode(String isoAlphaCode) {
		this.isoAlphaCode = isoAlphaCode;
	}

	public Integer getIsoNumericCode() {
		return isoNumericCode;
	}

	public void setIsoNumericCode(Integer isoNumericCode) {
		this.isoNumericCode = isoNumericCode;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
