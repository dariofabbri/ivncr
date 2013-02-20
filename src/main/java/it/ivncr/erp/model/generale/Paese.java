package it.ivncr.erp.model.generale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "gen_paese")
public class Paese {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_paese_id_seq")
	@SequenceGenerator(name = "gen_paese_id_seq", sequenceName = "gen_paese_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="iso_alpha2")
	private String alpha2;

	@Column(name="iso_alpha3")
	private String alpha3;

	@Column(name="iso_num")
	private String numeric;

	@Column(name="descrizione")
	private String descrizione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	public String getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

	public String getNumeric() {
		return numeric;
	}

	public void setNumeric(String numeric) {
		this.numeric = numeric;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
