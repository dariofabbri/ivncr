package it.ivncr.erp.model.personale;

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
@Table(name = "per_tipo_documento")
public class TipoDocumento implements Serializable {

  private static final long serialVersionUID = 1L;

	public static final Integer CARTA_IDENTITA = 1;
	public static final Integer LIBRETTO_LAVORO = 2;
	public static final Integer NON_RICONOSCIUTO = 3;
	public static final Integer NULLA_OSTA = 4;
	public static final Integer PASSAPORTO = 5;
	public static final Integer PATENTE = 6;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_tipo_documento_id_seq")
	@SequenceGenerator(name = "per_tipo_documento_id_seq", sequenceName = "per_tipo_documento_id_seq")
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
