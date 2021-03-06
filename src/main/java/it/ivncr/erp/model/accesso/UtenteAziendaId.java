package it.ivncr.erp.model.accesso;

import it.ivncr.erp.model.generale.Azienda;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class UtenteAziendaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="utente_id")
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name="azienda_id")
	private Azienda azienda;
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 23)
				.append(utente.getId())
				.append(azienda.getId())
				.toHashCode();
	}

	@Override
	public boolean equals(Object object) {

		if (object == null) {
			return false;
		}

		if (object == this) {
			return true;
		}

		if (object.getClass() != getClass()) {
			return false;
		}

		UtenteAziendaId rhs = (UtenteAziendaId) object;

		return new EqualsBuilder()
				.appendSuper(super.equals(object))
				.append(utente.getId(), rhs.utente.getId())
				.append(azienda.getId(), rhs.azienda.getId())
				.isEquals();
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