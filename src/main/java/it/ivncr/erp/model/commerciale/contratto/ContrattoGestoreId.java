package it.ivncr.erp.model.commerciale.contratto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class ContrattoGestoreId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="contratto_id")
	private Contratto contratto;

	@ManyToOne
	@JoinColumn(name="gestore_contratto_id")
	private GestoreContratto gestore;

	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 23)
				.append(contratto.getId())
				.append(gestore.getId())
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

		ContrattoGestoreId rhs = (ContrattoGestoreId) object;

		return new EqualsBuilder()
				.appendSuper(super.equals(object))
				.append(contratto.getId(), rhs.contratto.getId())
				.append(gestore.getId(), rhs.gestore.getId())
				.isEquals();
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public GestoreContratto getGestore() {
		return gestore;
	}

	public void setGestore(GestoreContratto gestore) {
		this.gestore = gestore;
	}
}