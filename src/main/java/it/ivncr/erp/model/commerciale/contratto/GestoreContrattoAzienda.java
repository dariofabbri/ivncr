package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.generale.Azienda;

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
@Table(name = "con_gestore_contratto_azienda")
public class GestoreContrattoAzienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_gestore_contratto_azienda_id_seq")
	@SequenceGenerator(name = "con_gestore_contratto_azienda_id_seq", sequenceName = "con_gestore_contratto_azienda_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gestore_contratto_id", referencedColumnName="id")
	GestoreContratto gestoreContratto;

	@ManyToOne
	@JoinColumn(name="azienda_id", referencedColumnName="id")
	Azienda azienda;

	@Column(name="attivo")
	private Boolean attivo;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("gestoreContratto", gestoreContratto, false)
			.append("azienda", azienda, false)
			.append("attivo", attivo)
			.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GestoreContratto getGestoreContratto() {
		return gestoreContratto;
	}

	public void setGestoreContratto(GestoreContratto gestoreContratto) {
		this.gestoreContratto = gestoreContratto;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
}
