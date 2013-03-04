package it.ivncr.erp.model.personale;

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
@Table(name = "per_recapito_telefonico")
public class RecapitoTelefonico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_recapito_telefonico_id_seq")
	@SequenceGenerator(name = "per_recapito_telefonico_id_seq", sequenceName = "per_recapito_telefonico_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_recapito_telefonico_id")
	private TipoRecapitoTelefonico tipoRecapitoTelefonico;

	@Column(name="recapito")
	private String recapito;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("tipoRecapitoTelefonico", tipoRecapitoTelefonico, false)
			.append("recapito", recapito)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public TipoRecapitoTelefonico getTipoRecapitoTelefonico() {
		return tipoRecapitoTelefonico;
	}

	public void setTipoRecapitoTelefonico(
			TipoRecapitoTelefonico tipoRecapitoTelefonico) {
		this.tipoRecapitoTelefonico = tipoRecapitoTelefonico;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}
}
