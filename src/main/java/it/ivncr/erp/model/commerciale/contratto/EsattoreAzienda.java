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
@Table(name = "con_esattore_azienda")
@IdClass(EsattoreAziendaId.class)
public class EsattoreAzienda {

	@Id
	@Column(name="esattore_id", insertable=false, updatable=false)
	private Integer esattoreId;

	@Id
	@Column(name="azienda_id", insertable=false, updatable=false)
	private Integer aziendaId;

	@Column(name="attivo")
	private Boolean attivo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="esattore_id", referencedColumnName="id")
	Esattore esattore;

	@ManyToOne
	@JoinColumn(name="azienda_id", referencedColumnName="id")
	Azienda azienda;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("esattore", esattore, false)
			.append("azienda", azienda, false)
			.toString();
	}


	public Integer getEsattoreId() {
		return esattoreId;
	}

	public void setEsattoreId(Integer esattoreId) {
		this.esattoreId = esattoreId;
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

	public Esattore getEsattore() {
		return esattore;
	}

	public void setEsattore(Esattore esattore) {
		this.esattore = esattore;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
}
