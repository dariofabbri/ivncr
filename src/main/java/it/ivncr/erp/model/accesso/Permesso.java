package it.ivncr.erp.model.accesso;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "acc_permesso")
public class Permesso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "acc_permesso_id_seq")
	@SequenceGenerator(name = "acc_permesso_id_seq", sequenceName = "acc_permesso_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="permesso")
	private String permesso;

	@Column(name="descrizione")
	private String descrizione;

	@OneToMany
	@JoinTable(
			name="acc_ruolo_permesso",
			joinColumns = { @JoinColumn(name="permesso_id", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="ruolo_id", referencedColumnName="id")}
	)
	private Set<Ruolo> ruoli;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("permesso", permesso)
			.append("descrizione", descrizione)
			.append("ruoli", ruoli, false)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermesso() {
		return permesso;
	}

	public void setPermesso(String permesso) {
		this.permesso = permesso;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
}
