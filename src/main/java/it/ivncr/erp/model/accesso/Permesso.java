package it.ivncr.erp.model.accesso;


import java.util.List;

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

@Entity
@Table(name = "acc_permesso")
public class Permesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "acc_permesso_id_seq")
	@SequenceGenerator(name = "acc_permesso_id_seq", sequenceName = "acc_permesso_id_seq")
	@Column(name="id")
	private Integer id;
	
	@Column(name="permesso")
	private String permesso;


	@OneToMany
	@JoinTable(
			name="acc_ruolo_prmesso",
			joinColumns = { @JoinColumn(name="permesso_id", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="ruolo_id", referencedColumnName="id")}
	)
	private List<Ruolo> ruoli;


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

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
}
