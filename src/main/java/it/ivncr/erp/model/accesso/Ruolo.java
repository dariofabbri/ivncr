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
@Table(name = "acc_ruolo")
public class Ruolo implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "acc_ruolo_id_seq")
	@SequenceGenerator(name = "acc_ruolo_id_seq", sequenceName = "acc_ruolo_id_seq")
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descrizione")
	private String descrizione;

	@OneToMany
	@JoinTable(
			name="acc_ruolo_permesso",
			joinColumns = { @JoinColumn(name="ruolo_id", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="permesso_id", referencedColumnName="id")}
	)
	private Set<Permesso> permessi;

	@OneToMany
	@JoinTable(
			name="acc_utente_ruolo",
			joinColumns = { @JoinColumn(name="ruolo_id", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="utente_id", referencedColumnName="id")}
	)
	private Set<Utente> utenti;

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("nome", nome)
			.append("descrizione", descrizione)
			.append("permessi", permessi, false)
			.append("utenti", utenti, false)
			.toString();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<Permesso> getPermessi() {
		return permessi;
	}

	public void setPermessi(Set<Permesso> permessi) {
		this.permessi = permessi;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}
}
