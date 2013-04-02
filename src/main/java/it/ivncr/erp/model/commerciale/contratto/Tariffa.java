package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;

import java.math.BigDecimal;
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
@Table(name = "con_tariffa")
public class Tariffa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_tariffa_id_seq")
	@SequenceGenerator(name = "con_tariffa_id_seq", sequenceName = "con_tariffa_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

	@Column(name="descrizione")
	private String descrizione;

    @ManyToOne
    @JoinColumn(name="tipo_servizio_id")
    private TipoServizio tipoServizio;

    @ManyToOne
    @JoinColumn(name="specifica_servizio_id")
    private SpecificaServizio specificaServizio;

    @ManyToOne
    @JoinColumn(name="obiettivo_servizio_id")
    private ObiettivoServizio obiettivoServizio;

    @ManyToOne
    @JoinColumn(name="tipo_tariffa_id")
    private TipoTariffa tipoTariffa;

	@Column(name="costo")
	private BigDecimal costo;

	@Column(name="numero")
	private Integer numero;

	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

    @ManyToOne
    @JoinColumn(name="tipo_fatturazione_id")
    private TipoFatturazione tipoFatturazione;

	@Column(name="data_cessazione")
	private Date dataCessazione;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("descrizione", descrizione)
			.append("tipoServizio", tipoServizio, false)
			.append("specificaServizio", specificaServizio, false)
			.append("obiettivoServizio", obiettivoServizio, false)
			.append("tipoTariffa", tipoTariffa, false)
			.append("costo", costo)
			.append("numero", numero)
			.append("dataInizioValidita", dataInizioValidita)
			.append("tipoFatturazione", tipoFatturazione, false)
			.append("dataCessazione", dataCessazione)
			.append("note", note)
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TipoServizio getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(TipoServizio tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public SpecificaServizio getSpecificaServizio() {
		return specificaServizio;
	}

	public void setSpecificaServizio(SpecificaServizio specificaServizio) {
		this.specificaServizio = specificaServizio;
	}

	public ObiettivoServizio getObiettivoServizio() {
		return obiettivoServizio;
	}

	public void setObiettivoServizio(ObiettivoServizio obiettivoServizio) {
		this.obiettivoServizio = obiettivoServizio;
	}

	public TipoTariffa getTipoTariffa() {
		return tipoTariffa;
	}

	public void setTipoTariffa(TipoTariffa tipoTariffa) {
		this.tipoTariffa = tipoTariffa;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public TipoFatturazione getTipoFatturazione() {
		return tipoFatturazione;
	}

	public void setTipoFatturazione(TipoFatturazione tipoFatturazione) {
		this.tipoFatturazione = tipoFatturazione;
	}

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}