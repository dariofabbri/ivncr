package it.ivncr.erp.model.operativo;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.personale.LivelloCcnl;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "ope_indennita")
public class Indennita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ope_indennita_id_seq")
	@SequenceGenerator(name = "ope_indennita_id_seq", sequenceName = "ope_indennita_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ods_id")
	private OrdineServizio ods;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_indennita_id")
	private TipoIndennita tipoIndennita;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="livello_id")
	private LivelloCcnl livello;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_modifica_ts")
	private Date ultimaModifica;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("ods", ods, false)
			.append("tipoIndennita", tipoIndennita, false)
			.append("livello", livello, false)
			.append("creazione", creazione)
			.append("ultimaModifica", ultimaModifica)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdineServizio getOds() {
		return ods;
	}

	public void setOds(OrdineServizio ods) {
		this.ods = ods;
	}

	public TipoIndennita getTipoIndennita() {
		return tipoIndennita;
	}

	public void setTipoIndennita(TipoIndennita tipoIndennita) {
		this.tipoIndennita = tipoIndennita;
	}

	public LivelloCcnl getLivello() {
		return livello;
	}

	public void setLivello(LivelloCcnl livello) {
		this.livello = livello;
	}

	public Date getCreazione() {
		return creazione;
	}

	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}

	public Date getUltimaModifica() {
		return ultimaModifica;
	}

	public void setUltimaModifica(Date ultimaModifica) {
		this.ultimaModifica = ultimaModifica;
	}
}