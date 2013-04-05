package it.ivncr.erp.model.commerciale.contratto;

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
@Table(name = "con_ods_apparecchiatura")
public class OdsApparecchiatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_ods_apparecchiatura_id_seq")
	@SequenceGenerator(name = "con_ods_apparecchiatura_id_seq", sequenceName = "con_ods_apparecchiatura_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="ordine_servizio_id")
    private OrdineServizio ordineServizio;

    @ManyToOne
    @JoinColumn(name="apparecchiatura_tecnologica_id")
    private ApparecchiaturaTecnologica apparecchiaturaTecnologica;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("ordineServizio", ordineServizio, false)
			.append("apparecchiaturaTecnologica", apparecchiaturaTecnologica, false)
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

	public ApparecchiaturaTecnologica getApparecchiaturaTecnologica() {
		return apparecchiaturaTecnologica;
	}

	public void setApparecchiaturaTecnologica(
			ApparecchiaturaTecnologica apparecchiaturaTecnologica) {
		this.apparecchiaturaTecnologica = apparecchiaturaTecnologica;
	}
}
