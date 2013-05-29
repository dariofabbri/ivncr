package it.ivncr.erp.model.operativo;

import it.ivncr.erp.model.personale.Addetto;

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
@Table(name = "ope_recupero_riposo")
public class RecuperoRiposo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ope_recupero_riposo_id_seq")
	@SequenceGenerator(name = "ope_recupero_riposo_id_seq", sequenceName = "ope_recupero_riposo_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="data_riposo_spostato")
	private Date dataRiposoSpostato;

	@Column(name="data_recupero_riposo")
	private Date dataRecuperoRiposo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_riposo_id")
	private TipoRiposo tipoRiposo;

	@Column(name="attuale")
	private Boolean attuale;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_modifica_ts")
	private Date ultimaModifica;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("dataRiposoSpostato", dataRiposoSpostato)
			.append("dataRecuperoRiposo", dataRecuperoRiposo)
			.append("tipoRiposo", tipoRiposo, false)
			.append("attuale", attuale)
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

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public Date getDataRiposoSpostato() {
		return dataRiposoSpostato;
	}

	public void setDataRiposoSpostato(Date dataRiposoSpostato) {
		this.dataRiposoSpostato = dataRiposoSpostato;
	}

	public Date getDataRecuperoRiposo() {
		return dataRecuperoRiposo;
	}

	public void setDataRecuperoRiposo(Date dataRecuperoRiposo) {
		this.dataRecuperoRiposo = dataRecuperoRiposo;
	}

	public TipoRiposo getTipoRiposo() {
		return tipoRiposo;
	}

	public void setTipoRiposo(TipoRiposo tipoRiposo) {
		this.tipoRiposo = tipoRiposo;
	}

	public Boolean getAttuale() {
		return attuale;
	}

	public void setAttuale(Boolean attuale) {
		this.attuale = attuale;
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