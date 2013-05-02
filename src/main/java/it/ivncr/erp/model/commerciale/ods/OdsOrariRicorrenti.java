package it.ivncr.erp.model.commerciale.ods;

import it.ivncr.erp.model.generale.GiornoSettimana;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "con_ods_orari_ricorrenti")
public class OdsOrariRicorrenti implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_ods_orari_ricorrenti_id_seq")
	@SequenceGenerator(name = "con_ods_orari_ricorrenti_id_seq", sequenceName = "con_ods_orari_ricorrenti_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="ordine_servizio_id")
    private OrdineServizio ordineServizio;

    @ManyToOne
    @JoinColumn(name="giorno_settimana_id")
    private GiornoSettimana giornoSettimana;

	@Column(name="escluso_festivo")
	private Boolean esclusoFestivo;

    @Column(name="quantita_1")
	private Integer quantita1;

    @Column(name="orario_inizio_1")
	private Date orarioInizio1;

    @Column(name="orario_fine_1")
	private Date orarioFine1;

    @Column(name="quantita_2")
	private Integer quantita2;

    @Column(name="orario_inizio_2")
	private Date orarioInizio2;

    @Column(name="orario_fine_2")
	private Date orarioFine2;

    @Column(name="quantita_3")
	private Integer quantita3;

    @Column(name="orario_inizio_3")
	private Date orarioInizio3;

    @Column(name="orario_fine_3")
	private Date orarioFine3;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("ordineServizio", ordineServizio, false)
			.append("giornoSettimana", giornoSettimana, false)
			.append("esclusoFestivo", esclusoFestivo)
			.append("quantita1", quantita1)
			.append("orarioInizio1", orarioInizio1)
			.append("orarioFine1", orarioFine1)
			.append("quantita2", quantita2)
			.append("orarioInizio2", orarioInizio2)
			.append("orarioFine2", orarioFine2)
			.append("quantita3", quantita3)
			.append("orarioInizio3", orarioInizio3)
			.append("orarioFine3", orarioFine3)
			.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdineServizio getOrdineServizio() {
		return ordineServizio;
	}

	public void setOrdineServizio(OrdineServizio ordineServizio) {
		this.ordineServizio = ordineServizio;
	}

	public GiornoSettimana getGiornoSettimana() {
		return giornoSettimana;
	}

	public void setGiornoSettimana(GiornoSettimana giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}

	public Boolean getEsclusoFestivo() {
		return esclusoFestivo;
	}

	public void setEsclusoFestivo(Boolean esclusoFestivo) {
		this.esclusoFestivo = esclusoFestivo;
	}

	public Integer getQuantita1() {
		return quantita1;
	}

	public void setQuantita1(Integer quantita1) {
		this.quantita1 = quantita1;
	}

	public Date getOrarioInizio1() {
		return orarioInizio1;
	}

	public void setOrarioInizio1(Date orarioInizio1) {
		this.orarioInizio1 = orarioInizio1;
	}

	public Date getOrarioFine1() {
		return orarioFine1;
	}

	public void setOrarioFine1(Date orarioFine1) {
		this.orarioFine1 = orarioFine1;
	}

	public Integer getQuantita2() {
		return quantita2;
	}

	public void setQuantita2(Integer quantita2) {
		this.quantita2 = quantita2;
	}

	public Date getOrarioInizio2() {
		return orarioInizio2;
	}

	public void setOrarioInizio2(Date orarioInizio2) {
		this.orarioInizio2 = orarioInizio2;
	}

	public Date getOrarioFine2() {
		return orarioFine2;
	}

	public void setOrarioFine2(Date orarioFine2) {
		this.orarioFine2 = orarioFine2;
	}

	public Integer getQuantita3() {
		return quantita3;
	}

	public void setQuantita3(Integer quantita3) {
		this.quantita3 = quantita3;
	}

	public Date getOrarioInizio3() {
		return orarioInizio3;
	}

	public void setOrarioInizio3(Date orarioInizio3) {
		this.orarioInizio3 = orarioInizio3;
	}

	public Date getOrarioFine3() {
		return orarioFine3;
	}

	public void setOrarioFine3(Date orarioFine3) {
		this.orarioFine3 = orarioFine3;
	}
}
