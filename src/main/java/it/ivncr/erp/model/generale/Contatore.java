package it.ivncr.erp.model.generale;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "gen_contatore")
public class Contatore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_contatore_id_seq")
	@SequenceGenerator(name = "gen_contatore_id_seq", sequenceName = "gen_contatore_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="codice")
	private String codice;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="contatore")
	private Integer contatore;

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

	public Integer getContatore() {
		return contatore;
	}

	public void setContatore(Integer contatore) {
		this.contatore = contatore;
	}

	public void incrementContatore() {
		++contatore;
	}
}
