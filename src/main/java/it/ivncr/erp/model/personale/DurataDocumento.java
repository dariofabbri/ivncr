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
@Table(name = "per_durata_documento")
public class DurataDocumento implements Serializable {

  private static final long serialVersionUID = 1L;

	public static final Integer LIBRETTO_PORTO_ARMI = 1;
	public static final Integer LICENZA_PORTO_ARMI = 2;
	public static final Integer DECRETO_GPG = 3;
	public static final Integer ESTENSIONE_DECRETO_GPG = 4;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_durata_documento_id_seq")
	@SequenceGenerator(name = "per_durata_documento_id_seq", sequenceName = "per_durata_documento_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="tipo_documento")
	private String tipoDocumento;

	@Column(name="durata_mesi")
	private Integer durataMesi;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("tipoDocumento", tipoDocumento)
			.append("durataMesi", durataMesi)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getDurataMesi() {
		return durataMesi;
	}

	public void setDurataMesi(Integer durataMesi) {
		this.durataMesi = durataMesi;
	}
}
