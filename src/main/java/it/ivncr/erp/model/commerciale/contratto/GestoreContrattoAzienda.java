package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.generale.Azienda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "con_gestore_contratto_azienda")
@IdClass(GestoreContrattoAziendaId.class)
public class GestoreContrattoAzienda {

	@Id
	@Column(name="gestore_contratto_id", insertable=false, updatable=false)
	private Integer gestoreContrattoId;

	@Id
	@Column(name="azienda_id", insertable=false, updatable=false)
	private Integer aziendaId;

	@Column(name="attivo")
	private Boolean attivo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gestore_contratto_id", referencedColumnName="id")
	GestoreContratto gestoreContratto;

	@ManyToOne
	@JoinColumn(name="azienda_id", referencedColumnName="id")
	Azienda azienda;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("gestoreContratto", gestoreContratto, false)
			.append("azienda", azienda, false)
			.toString();
	}


	public Integer getGestoreContrattoId() {
		return gestoreContrattoId;
	}

	public void setGestoreContrattoId(Integer gestoreContrattoId) {
		this.gestoreContrattoId = gestoreContrattoId;
	}

	public Integer getAziendaId() {
		return aziendaId;
	}

	public void setAziendaId(Integer aziendaId) {
		this.aziendaId = aziendaId;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
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
}
