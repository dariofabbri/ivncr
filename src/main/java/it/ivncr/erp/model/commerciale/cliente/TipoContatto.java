package it.ivncr.erp.model.commerciale.cliente;

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
@Table(name = "com_tipo_contatto")
public class TipoContatto implements Serializable {

  private static final long serialVersionUID = 1L;

	public static final Integer REFERENTE_COMMERCIALE = 1;
	public static final Integer REFERENTE_AMMINISTRATIVO = 2;
	public static final Integer REFERENTE_OPERATIVO = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "com_tipo_contatto_id_seq")
	@SequenceGenerator(name = "com_tipo_contatto_id_seq", sequenceName = "com_tipo_contatto_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="descrizione")
	private String descrizione;

	
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
