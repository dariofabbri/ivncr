package it.ivncr.erp.model.commerciale.contratto;

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
@Table(name = "con_raggruppamento_fatturazione")
public class RaggruppamentoFatturazione {

	public static final Integer GRUPPO_A = 1;
	public static final Integer GRUPPO_B = 2;
	public static final Integer GRUPPO_C = 3;
	public static final Integer GRUPPO_D = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_raggruppamento_fatturazione_id_seq")
	@SequenceGenerator(name = "con_raggruppamento_fatturazione_id_seq", sequenceName = "con_raggruppamento_fatturazione_id_seq")
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
