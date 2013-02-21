package it.ivncr.erp.model.accesso;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "acc_account_email")
public class AccountEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "acc_account_email_id_seq")
	@SequenceGenerator(name = "acc_account_email_id_seq", sequenceName = "acc_account_email_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="account")
	private String account;

	@Column(name="password")
	private String password;

    @OneToOne
    @JoinColumn(name="utente_id")
    private Utente utente;

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("account", account)
			.append("password", password)
			.append("utente", utente, false)
			.toString();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}
