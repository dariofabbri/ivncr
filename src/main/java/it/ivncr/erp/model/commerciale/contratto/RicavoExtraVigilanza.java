package it.ivncr.erp.model.commerciale.contratto;

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
@Table(name = "con_ricavo_extra_vigilanza")
public class RicavoExtraVigilanza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_ricavo_extra_vigilanza_id_seq")
	@SequenceGenerator(name = "con_ricavo_extra_vigilanza_id_seq", sequenceName = "con_ricavo_extra_vigilanza_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

	@Column(name="alias")
	private String alias;

    @ManyToOne
    @JoinColumn(name="tipo_servizio_id")
    private TipoServizio tipoServizio;

    @ManyToOne
    @JoinColumn(name="specifica_servizio_id")
    private SpecificaServizio specificaServizio;

    @ManyToOne
    @JoinColumn(name="raggruppamento_fatturazione_id")
    private RaggruppamentoFatturazione raggruppamentoFatturazione;

	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	@Column(name="data_cessazione")
	private Date dataCessazione;

	@Column(name="fattura_minimo_un_mese")
	private Boolean fatturaMinimoUnMese;

	@Column(name="fatturazione_anticipata")
	private Boolean fatturazioneAnticipata;

	@Column(name="fattura_ogni_mesi")
	private Integer fatturaOgniMesi;

	@Column(name="canone_mensile")
	private BigDecimal canoneMensile;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("alias", alias)
			.append("tipoServizio", tipoServizio, false)
			.append("specificaServizio", specificaServizio, false)
			.append("raggruppamentoFatturazione", raggruppamentoFatturazione, false)
			.append("dataInizioValidita", dataInizioValidita)
			.append("dataCessazione", dataCessazione)
			.append("fatturaMinimoUnMese", fatturaMinimoUnMese)
			.append("fatturazioneAnticipata", fatturazioneAnticipata)
			.append("fatturaOgniMesi", fatturaOgniMesi)
			.append("canoneMensile", canoneMensile)
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	public RaggruppamentoFatturazione getRaggruppamentoFatturazione() {
		return raggruppamentoFatturazione;
	}

	public void setRaggruppamentoFatturazione(
			RaggruppamentoFatturazione raggruppamentoFatturazione) {
		this.raggruppamentoFatturazione = raggruppamentoFatturazione;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public Boolean getFatturaMinimoUnMese() {
		return fatturaMinimoUnMese;
	}

	public void setFatturaMinimoUnMese(Boolean fatturaMinimoUnMese) {
		this.fatturaMinimoUnMese = fatturaMinimoUnMese;
	}

	public Boolean getFatturazioneAnticipata() {
		return fatturazioneAnticipata;
	}

	public void setFatturazioneAnticipata(Boolean fatturazioneAnticipata) {
		this.fatturazioneAnticipata = fatturazioneAnticipata;
	}

	public Integer getFatturaOgniMesi() {
		return fatturaOgniMesi;
	}

	public void setFatturaOgniMesi(Integer fatturaOgniMesi) {
		this.fatturaOgniMesi = fatturaOgniMesi;
	}

	public BigDecimal getCanoneMensile() {
		return canoneMensile;
	}

	public void setCanoneMensile(BigDecimal canoneMensile) {
		this.canoneMensile = canoneMensile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
