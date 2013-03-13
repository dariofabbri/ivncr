package it.ivncr.erp.model.personale;

import java.math.BigDecimal;
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
@Table(name = "per_esercitazione_tiro")
public class EsercitazioneTiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_esercitazione_tiro_id_seq")
	@SequenceGenerator(name = "per_esercitazione_tiro_id_seq", sequenceName = "per_esercitazione_tiro_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@Column(name="data_tiro")
	private Date dataTiro;

	@Column(name="poligono")
	private String poligono;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_esercitazione_tiro_id")
	private TipoEsercitazioneTiro tipoEsercitazioneTiro;

	@Column(name="importo_richiesto")
	private BigDecimal importoRichiesto;

	@Column(name="importo_rimborsato")
	private BigDecimal importoRimborsato;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("dataTiro", dataTiro)
			.append("poligono", poligono)
			.append("tipoEsercitazioneTiro", tipoEsercitazioneTiro, false)
			.append("importoRichiesto", importoRichiesto)
			.append("importoRimborsato", importoRimborsato)
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

	public Date getDataTiro() {
		return dataTiro;
	}

	public void setDataTiro(Date dataTiro) {
		this.dataTiro = dataTiro;
	}

	public String getPoligono() {
		return poligono;
	}

	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}

	public TipoEsercitazioneTiro getTipoEsercitazioneTiro() {
		return tipoEsercitazioneTiro;
	}

	public void setTipoEsercitazioneTiro(
			TipoEsercitazioneTiro tipoEsercitazioneTiro) {
		this.tipoEsercitazioneTiro = tipoEsercitazioneTiro;
	}

	public BigDecimal getImportoRichiesto() {
		return importoRichiesto;
	}

	public void setImportoRichiesto(BigDecimal importoRichiesto) {
		this.importoRichiesto = importoRichiesto;
	}

	public BigDecimal getImportoRimborsato() {
		return importoRimborsato;
	}

	public void setImportoRimborsato(BigDecimal importoRimborsato) {
		this.importoRimborsato = importoRimborsato;
	}
}
