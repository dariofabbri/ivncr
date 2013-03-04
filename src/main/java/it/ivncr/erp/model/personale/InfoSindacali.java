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
@Table(name = "per_info_sindacali")
public class InfoSindacali {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_info_sindacali_id_seq")
	@SequenceGenerator(name = "per_info_sindacali_id_seq", sequenceName = "per_info_sindacali_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sigla_sindacale_id")
	private SiglaSindacale siglaSindacale;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carica_sindacale_id")
	private CaricaSindacale caricaSindacale;

	@Column(name="valido_da")
	private Date validoDa;

	@Column(name="valido_a")
	private Date validoA;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("siglaSindacale", siglaSindacale, false)
			.append("caricaSindacale", caricaSindacale, false)
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

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public SiglaSindacale getSiglaSindacale() {
		return siglaSindacale;
	}

	public void setSiglaSindacale(SiglaSindacale siglaSindacale) {
		this.siglaSindacale = siglaSindacale;
	}

	public CaricaSindacale getCaricaSindacale() {
		return caricaSindacale;
	}

	public void setCaricaSindacale(CaricaSindacale caricaSindacale) {
		this.caricaSindacale = caricaSindacale;
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
