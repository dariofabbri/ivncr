package it.ivncr.erp.model.accesso;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "acc_utente")
public class Utente implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "acc_utente_id_seq")
	@SequenceGenerator(name = "acc_utente_id_seq", sequenceName = "acc_utente_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="username")
	private String username;

	@Column(name="nome")
	private String nome;

	@Column(name="cognome")
	private String cognome;

	@Column(name="note")
	private String note;

	@Column(name="digest")
	private String digest;

	@Column(name="salt")
	private String salt;

	@Column(name="iterazioni")
	private Integer iterazioni;

	@Column(name="ultimo_login_ts")
	private Date ultimoLogin;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_attivazione_ts")
	private Date ultimaAttivazione;

	@Column(name="ultima_disattivazione_ts")
	private Date ultimaDisattivazione;

	@Column(name="attivo")
	private Boolean attivo;

	@Lob
	@Column(name="foto")
	private byte[] foto;

	@OneToMany
	@JoinTable(
			name="acc_utente_ruolo",
			joinColumns = { @JoinColumn(name="utente_id", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="ruolo_id", referencedColumnName="id")}
	)
	private Set<Ruolo> ruoli;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="utente")
	@OrderBy
	private Set<UtenteAzienda> aziende;

	@OneToOne(fetch=FetchType.LAZY, mappedBy="utente")
	private AccountEmail accountEmail;

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("username", username)
			.append("nome", nome)
			.append("cognome", cognome)
			.append("note", note)
			.append("digest", digest)
			.append("salt", salt)
			.append("iterazioni", iterazioni)
			.append("ultimoLogin", ultimoLogin)
			.append("creazione", creazione)
			.append("ultimaAttivazione", ultimaAttivazione)
			.append("ultimaDisattivazione", ultimaDisattivazione)
			.append("attivo", attivo)
			.append("foto", foto, false)
			.append("ruoli", ruoli, false)
			.append("aziende", aziende, false)
			.append("accountEmail", accountEmail, false)
			.toString();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getIterazioni() {
		return iterazioni;
	}

	public void setIterazioni(Integer iterazioni) {
		this.iterazioni = iterazioni;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public Date getCreazione() {
		return creazione;
	}

	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}

	public Date getUltimaAttivazione() {
		return ultimaAttivazione;
	}

	public void setUltimaAttivazione(Date ultimaAttivazione) {
		this.ultimaAttivazione = ultimaAttivazione;
	}

	public Date getUltimaDisattivazione() {
		return ultimaDisattivazione;
	}

	public void setUltimaDisattivazione(Date ultimaDisattivazione) {
		this.ultimaDisattivazione = ultimaDisattivazione;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public AccountEmail getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(AccountEmail accountEmail) {
		this.accountEmail = accountEmail;
	}

	public Set<UtenteAzienda> getAziende() {
		return aziende;
	}

	public void setAziende(Set<UtenteAzienda> aziende) {
		this.aziende = aziende;
	}
}
