package it.ivncr.erp.model.commerciale.contratto;

import java.io.Serializable;

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
@Table(name = "con_tipo_apparecchiatura_tecnologica")
public class TipoApparecchiaturaTecnologica implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_tipo_apparecchiatura_tecnologica_id_seq")
	@SequenceGenerator(name = "con_tipo_apparecchiatura_tecnologica_id_seq", sequenceName = "con_tipo_apparecchiatura_tecnologica_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gruppo_apparecchiatura_id")
	private GruppoApparecchiaturaTecnologica gruppoApparecchiatura;

	@Column(name="descrizione")
	private String descrizione;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("gruppoApparecchiatura", gruppoApparecchiatura)
			.append("descrizione", descrizione)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GruppoApparecchiaturaTecnologica getGruppoApparecchiatura() {
		return gruppoApparecchiatura;
	}

	public void setGruppoApparecchiatura(
			GruppoApparecchiaturaTecnologica gruppoApparecchiatura) {
		this.gruppoApparecchiatura = gruppoApparecchiatura;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
