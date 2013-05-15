package it.ivncr.erp.model.generale;

import java.io.Serializable;

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
@Table(name = "gen_giorno_settimana")
public class GiornoSettimana implements Serializable {

  private static final long serialVersionUID = 1L;

	public static final Integer LUNEDI = 1;
	public static final Integer MARTEDI = 2;
	public static final Integer MERCOLEDI = 3;
	public static final Integer GIOVEDI = 4;
	public static final Integer VENERDI = 5;
	public static final Integer SABATO = 6;
	public static final Integer DOMENICA = 7;
	public static final Integer FESTIVI = 8;
	public static final Integer PREFESTIVI = 9;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_giorno_settimana_id_seq")
	@SequenceGenerator(name = "gen_giorno_settimana_id_seq", sequenceName = "gen_giorno_settimana_id_seq")
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
