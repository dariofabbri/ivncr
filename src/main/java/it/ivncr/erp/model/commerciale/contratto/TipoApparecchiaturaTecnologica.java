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
@Table(name = "con_tipo_apparecchiatura_tecnologica")
public class TipoApparecchiaturaTecnologica {

	public static final Integer ALLARME = 1;
	public static final Integer TVCC = 2;
	public static final Integer PERIFERICA = 3;
	public static final Integer APPARATI_DI_CONTROLLO = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_tipo_apparecchiatura_tecnologica_id_seq")
	@SequenceGenerator(name = "con_tipo_apparecchiatura_tecnologica_id_seq", sequenceName = "con_tipo_apparecchiatura_tecnologica_id_seq")
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
