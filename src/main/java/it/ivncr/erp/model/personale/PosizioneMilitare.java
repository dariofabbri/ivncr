package it.ivncr.erp.model.personale;

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
@Table(name = "per_posizione_militare")
public class PosizioneMilitare implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_posizione_militare_id_seq")
	@SequenceGenerator(name = "per_posizione_militare_id_seq", sequenceName = "per_posizione_militare_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_posizione_militare_id")
	private TipoPosizioneMilitare tipoPosizioneMilitare;

	@Column(name="presso")
	private String presso;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="grado_id")
	private Grado grado;

	@Column(name="data_congedo")
	private Date dataCongedo;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("tipoPosizioneMilitare", tipoPosizioneMilitare, false)
			.append("presso", presso)
			.append("grado", grado, false)
			.append("dataCongedo", dataCongedo)
			.append("note", note)
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

	public TipoPosizioneMilitare getTipoPosizioneMilitare() {
		return tipoPosizioneMilitare;
	}

	public void setTipoPosizioneMilitare(
			TipoPosizioneMilitare tipoPosizioneMilitare) {
		this.tipoPosizioneMilitare = tipoPosizioneMilitare;
	}

	public String getPresso() {
		return presso;
	}

	public void setPresso(String presso) {
		this.presso = presso;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Date getDataCongedo() {
		return dataCongedo;
	}

	public void setDataCongedo(Date dataCongedo) {
		this.dataCongedo = dataCongedo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
