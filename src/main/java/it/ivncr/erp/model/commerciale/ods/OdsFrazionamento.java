package it.ivncr.erp.model.commerciale.ods;

import it.ivncr.erp.model.commerciale.cliente.Cliente;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "con_ods_frazionamento")
public class OdsFrazionamento implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_ods_frazionamento_id_seq")
	@SequenceGenerator(name = "con_ods_frazionamento_id_seq", sequenceName = "con_ods_frazionamento_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="ordine_servizio_id")
    private OrdineServizio ordineServizio;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @Column(name="quota")
	private BigDecimal quota;

	@Column(name="esclusione_ritenuta_garanzia")
	private Boolean esclusioneRitenutaGaranzia;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("ordineServizio", ordineServizio, false)
			.append("cliente", cliente, false)
			.append("quota", quota)
			.append("esclusioneRitenutaGaranzia", esclusioneRitenutaGaranzia)
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public Boolean getEsclusioneRitenutaGaranzia() {
		return esclusioneRitenutaGaranzia;
	}

	public void setEsclusioneRitenutaGaranzia(Boolean esclusioneRitenutaGaranzia) {
		this.esclusioneRitenutaGaranzia = esclusioneRitenutaGaranzia;
	}
}
