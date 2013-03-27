package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.Contatto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class ContrattoContattoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="contratto_id")
	private Contratto contratto;

	@ManyToOne
	@JoinColumn(name="contatto_id")
	private Contatto contatto;

	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 23)
				.append(contratto.getId())
				.append(contatto.getId())
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

		ContrattoContattoId rhs = (ContrattoContattoId) object;

		return new EqualsBuilder()
				.appendSuper(super.equals(object))
				.append(contratto.getId(), rhs.contratto.getId())
				.append(contatto.getId(), rhs.contatto.getId())
				.isEquals();
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}
}