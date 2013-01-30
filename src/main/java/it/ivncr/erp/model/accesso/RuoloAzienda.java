package it.ivncr.erp.model.accesso;

import it.ivncr.erp.model.generale.Azienda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "acc_utente_azienda")
@IdClass(RuoloAziendaId.class)
public class RuoloAzienda {

	@Id
	@Column(name="utente_id", updatable=false, insertable=false)
	private Integer idUtente;

	@Id
	@Column(name="azienda_id", updatable=false, insertable=false)
	private Integer idAzienda;

	@Column(name = "preferita")
	private Boolean preferita;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "utente_id", referencedColumnName = "id")
	private Utente utente;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "azienda_id", referencedColumnName = "id")
	private Azienda azienda;

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Integer getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(Integer idAzienda) {
		this.idAzienda = idAzienda;
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
