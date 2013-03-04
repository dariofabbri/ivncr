package it.ivncr.erp.model.personale;

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
@Table(name = "per_stato_arma")
public class StatoArma {

	public static final Integer CEDUTA = 1;
	public static final Integer DA_VERIFICARE = 2;
	public static final Integer IN_POSSESSO = 3;
	public static final Integer RAPINATA = 4;
	public static final Integer RUBATA = 5;
	public static final Integer SEQUESTRATA = 6;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_stato_arma_id_seq")
	@SequenceGenerator(name = "per_stato_arma_id_seq", sequenceName = "per_stato_arma_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="descrizione")
	private String descrizione;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("descrizione", descrizione)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
