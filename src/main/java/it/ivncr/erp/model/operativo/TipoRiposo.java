package it.ivncr.erp.model.operativo;

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
@Table(name = "ope_tipo_riposo")
public class TipoRiposo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer RIPOSO_POSTICIPATO = 1;
	public static final Integer RIPOSO_SPOSTATO = 2;
	public static final Integer RIPOSO_LAVORATO = 3;
	public static final Integer RIPOSO_RECUPERATO = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ope_tipo_riposo_id_seq")
	@SequenceGenerator(name = "ope_tipo_riposo_id_seq", sequenceName = "ope_tipo_riposo_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="codice")
	private String codice;

	@Column(name="descrizione")
	private String descrizione;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("codice", codice)
			.append("descrizione", descrizione)
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
}