package it.ivncr.erp.model.operativo;

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
@Table(name = "ope_causale_ods")
public class CausaleOds implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ope_causale_ods_id_seq")
	@SequenceGenerator(name = "ope_causale_ods_id_seq", sequenceName = "ope_causale_ods_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="codice")
	private String codice;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="retribuito")
	private Boolean retribuito;

	@Column(name="orario")
	private Boolean orario;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gruppo_causale_ods_id")
	private GruppoCausaleOds gruppoCausaleOds;

	@Column(name="visibile")
	private Boolean visibile;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("codice", codice)
			.append("descrizione", descrizione)
			.append("retribuito", retribuito)
			.append("orario", orario)
			.append("gruppoCausaleOds", gruppoCausaleOds, false)
			.append("visibile", visibile)
			.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Boolean getRetribuito() {
		return retribuito;
	}

	public void setRetribuito(Boolean retribuito) {
		this.retribuito = retribuito;
	}

	public Boolean getOrario() {
		return orario;
	}

	public void setOrario(Boolean orario) {
		this.orario = orario;
	}

	public GruppoCausaleOds getGruppoCausaleOds() {
		return gruppoCausaleOds;
	}

	public void setGruppoCausaleOds(GruppoCausaleOds gruppoCausaleOds) {
		this.gruppoCausaleOds = gruppoCausaleOds;
	}

	public Boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}
}