package it.ivncr.erp.model.accesso;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class UtenteAziendaId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idUtente;
	private Integer idAzienda;

	public int hashCode() {
		return new HashCodeBuilder(11, 23)
				.append(idUtente)
				.append(idAzienda)
				.toHashCode();
	}

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
				.append(idUtente, rhs.idUtente)
				.append(idAzienda, rhs.idAzienda)
				.isEquals();
	}

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
}