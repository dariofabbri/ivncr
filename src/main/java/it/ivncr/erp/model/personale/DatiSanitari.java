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
@Table(name = "per_dati_sanitari")
public class DatiSanitari implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_dati_sanitari_id_seq")
	@SequenceGenerator(name = "per_dati_sanitari_id_seq", sequenceName = "per_dati_sanitari_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="medico_base")
	private String medicoBase;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gruppo_sanguigno_id")
	private GruppoSanguigno gruppoSanguigno;

	@Column(name="asl")
	private String asl;

	@Column(name="indirizzo_asl")
	private String indirizzoAsl;

	@Column(name="comune")
	private String comune;

	@Column(name="provincia")
	private String provincia;

	@Column(name="valido_da")
	private Date validoDa;

	@Column(name="valido_a")
	private Date validoA;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("medicoBase", medicoBase)
			.append("gruppoSanguigno", gruppoSanguigno, false)
			.append("asl", asl)
			.append("indirizzoAsl", indirizzoAsl)
			.append("comune", comune)
			.append("provincia", provincia)
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

	public String getMedicoBase() {
		return medicoBase;
	}

	public void setMedicoBase(String medicoBase) {
		this.medicoBase = medicoBase;
	}

	public GruppoSanguigno getGruppoSanguigno() {
		return gruppoSanguigno;
	}

	public void setGruppoSanguigno(GruppoSanguigno gruppoSanguigno) {
		this.gruppoSanguigno = gruppoSanguigno;
	}

	public String getAsl() {
		return asl;
	}

	public void setAsl(String asl) {
		this.asl = asl;
	}

	public String getIndirizzoAsl() {
		return indirizzoAsl;
	}

	public void setIndirizzoAsl(String indirizzoAsl) {
		this.indirizzoAsl = indirizzoAsl;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
