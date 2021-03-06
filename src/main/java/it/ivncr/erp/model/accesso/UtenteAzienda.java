package it.ivncr.erp.model.accesso;

import it.ivncr.erp.model.generale.Azienda;

import java.io.Serializable;

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
@Table(name = "acc_utente_azienda")
@IdClass(UtenteAziendaId.class)
public class UtenteAzienda implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@Column(name="utente_id", insertable=false, updatable=false)
	private Integer utenteId;
	
	@Id
	@Column(name="azienda_id", insertable=false, updatable=false)
	private Integer aziendaId;

	@Column(name="preferita")
	private Boolean preferita;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="utente_id", referencedColumnName="id")
	Utente utente;
	
	@ManyToOne
	@JoinColumn(name="azienda_id", referencedColumnName="id")
	Azienda azienda;

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("utente", utente)
			.append("azienda", azienda)
			.toString();
	}

	
	public Integer getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(Integer utenteId) {
		this.utenteId = utenteId;
	}

	public Integer getAziendaId() {
		return aziendaId;
	}

	public void setAziendaId(Integer aziendaId) {
		this.aziendaId = aziendaId;
	}

	public Boolean getPreferita() {
		return preferita;
	}

	public void setPreferita(Boolean preferita) {
		this.preferita = preferita;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
}
