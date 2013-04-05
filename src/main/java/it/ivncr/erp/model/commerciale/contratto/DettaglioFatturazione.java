package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.Indirizzo;

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
@Table(name = "con_dettaglio_fatturazione")
public class DettaglioFatturazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_dettaglio_fatturazione_id_seq")
	@SequenceGenerator(name = "con_dettaglio_fatturazione_id_seq", sequenceName = "con_dettaglio_fatturazione_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

    @ManyToOne
    @JoinColumn(name="condizioni_pagamento_id")
    private CondizioniPagamento condizioniPagamento;

    @ManyToOne
    @JoinColumn(name="metodo_pagamento_id")
    private MetodoPagamento metodoPagamento;

    @ManyToOne
    @JoinColumn(name="indirizzo_id")
    private Indirizzo indirizzo;

    @ManyToOne
    @JoinColumn(name="layout_stampa_id")
    private LayoutStampa layoutStampa;

	@Column(name="valido_da")
	private Date validoDa;

	@Column(name="valido_a")
	private Date validoA;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("condizioniPagamento", condizioniPagamento, false)
			.append("metodoPagamento", metodoPagamento, false)
			.append("indirizzo", indirizzo, false)
			.append("layoutStampa", layoutStampa, false)
			.append("validoDa", validoDa)
			.append("validoA", validoA)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contratto getContratto() {
		return contratto;
	}

	public void setContratto(Contratto contratto) {
		this.contratto = contratto;
	}

	public CondizioniPagamento getCondizioniPagamento() {
		return condizioniPagamento;
	}

	public void setCondizioniPagamento(CondizioniPagamento condizioniPagamento) {
		this.condizioniPagamento = condizioniPagamento;
	}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public LayoutStampa getLayoutStampa() {
		return layoutStampa;
	}

	public void setLayoutStampa(LayoutStampa layoutStampa) {
		this.layoutStampa = layoutStampa;
	}

	public Date getValidoDa() {
		return validoDa;
	}

	public void setValidoDa(Date validoDa) {
		this.validoDa = validoDa;
	}

	public Date getValidoA() {
		return validoA;
	}

	public void setValidoA(Date validoA) {
		this.validoA = validoA;
	}
}
