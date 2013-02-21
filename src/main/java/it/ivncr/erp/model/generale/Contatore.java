package it.ivncr.erp.model.generale;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="azienda_id")
	private Azienda azienda;

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("codice", codice)
			.append("descrizione", descrizione)
			.append("contatore", contatore)
			.append("azienda", azienda, false)
			.toString();
	}

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

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
}
