package it.ivncr.erp.model.generale;


import it.ivncr.erp.model.accesso.UtenteAzienda;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "gen_azienda")
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_azienda_id_seq")
	@SequenceGenerator(name = "gen_azienda_id_seq", sequenceName = "gen_azienda_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="codice")
	private String codice;

	@Column(name="descrizione")
	private String descrizione;

	@OneToMany(mappedBy="azienda")
	@OrderBy
	private Set<UtenteAzienda> utenti;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<UtenteAzienda> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<UtenteAzienda> utenti) {
		this.utenti = utenti;
	}
}
