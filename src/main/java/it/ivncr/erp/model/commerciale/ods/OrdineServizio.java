package it.ivncr.erp.model.commerciale.ods;

import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;

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
@Table(name = "con_ordine_servizio")
public class OrdineServizio implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_ordine_servizio_id_seq")
	@SequenceGenerator(name = "con_ordine_servizio_id_seq", sequenceName = "con_ordine_servizio_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

    @ManyToOne
    @JoinColumn(name="tipo_ordine_servizio_id")
    private TipoOrdineServizio tipoOrdineServizio;

    @ManyToOne
    @JoinColumn(name="padre_id")
    private OrdineServizio padre;

    @ManyToOne
    @JoinColumn(name="nuova_attivazione_id")
    private OrdineServizio nuovaAttivazione;

    @Column(name="codice")
	private String codice;

    @Column(name="alias")
	private String alias;

	@Column(name="data_decorrenza")
	private Date dataDecorrenza;

	@Column(name="data_termine")
	private Date dataTermine;

	@Column(name="data_fine_validita")
	private Date dataFineValidita;

	@Column(name="orario_fine_validita")
	private Date orarioFineValidita;

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
    @JoinColumn(name="tariffa_id")
    private Tariffa tariffa;

    @ManyToOne
    @JoinColumn(name="canone_id")
    private Canone canone;

	@Column(name="oneroso")
	private Boolean oneroso;

    @ManyToOne
    @JoinColumn(name="raggruppamento_fatturazione_id")
    private RaggruppamentoFatturazione raggruppamentoFatturazione;

	@Column(name="cessato")
	private Boolean cessato;

	@Column(name="note")
	private String note;

	@Column(name="modalita_operative")
	private String modalitaOperative;

	@Column(name="osservazioni_fattura")
	private String osservazioniFattura;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_modifica_ts")
	private Date ultimaModifica;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("tipoOrdineServizio", tipoOrdineServizio, false)
			.append("padre", padre, false)
			.append("nuovaAttivazione", nuovaAttivazione, false)
			.append("codice", codice)
			.append("alias", alias)
			.append("dataDecorrenza", dataDecorrenza)
			.append("dataTermine", dataTermine)
			.append("dataFineValidita", dataFineValidita)
			.append("orarioFineValidita", orarioFineValidita)
			.append("tipoServizio", tipoServizio, false)
			.append("specificaServizio", specificaServizio, false)
			.append("obiettivoServizio", obiettivoServizio, false)
			.append("tariffa", tariffa, false)
			.append("canone", canone, false)
			.append("oneroso", oneroso)
			.append("raggruppamentoFatturazione", raggruppamentoFatturazione, false)
			.append("cessato", cessato)
			.append("note", note)
			.append("modalitaOperative", modalitaOperative)
			.append("osservazioniFattura", osservazioniFattura)
			.append("creazione", creazione)
			.append("ultimaModifica", ultimaModifica)
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

	public TipoOrdineServizio getTipoOrdineServizio() {
		return tipoOrdineServizio;
	}

	public void setTipoOrdineServizio(TipoOrdineServizio tipoOrdineServizio) {
		this.tipoOrdineServizio = tipoOrdineServizio;
	}

	public OrdineServizio getPadre() {
		return padre;
	}

	public void setPadre(OrdineServizio padre) {
		this.padre = padre;
	}

	public OrdineServizio getNuovaAttivazione() {
		return nuovaAttivazione;
	}

	public void setNuovaAttivazione(OrdineServizio nuovaAttivazione) {
		this.nuovaAttivazione = nuovaAttivazione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getDataDecorrenza() {
		return dataDecorrenza;
	}

	public void setDataDecorrenza(Date dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}

	public Date getDataTermine() {
		return dataTermine;
	}

	public void setDataTermine(Date dataTermine) {
		this.dataTermine = dataTermine;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public Date getOrarioFineValidita() {
		return orarioFineValidita;
	}

	public void setOrarioFineValidita(Date orarioFineValidita) {
		this.orarioFineValidita = orarioFineValidita;
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

	public Tariffa getTariffa() {
		return tariffa;
	}

	public void setTariffa(Tariffa tariffa) {
		this.tariffa = tariffa;
	}

	public Canone getCanone() {
		return canone;
	}

	public void setCanone(Canone canone) {
		this.canone = canone;
	}

	public Boolean getOneroso() {
		return oneroso;
	}

	public void setOneroso(Boolean oneroso) {
		this.oneroso = oneroso;
	}

	public RaggruppamentoFatturazione getRaggruppamentoFatturazione() {
		return raggruppamentoFatturazione;
	}

	public void setRaggruppamentoFatturazione(
			RaggruppamentoFatturazione raggruppamentoFatturazione) {
		this.raggruppamentoFatturazione = raggruppamentoFatturazione;
	}

	public Boolean getCessato() {
		return cessato;
	}

	public void setCessato(Boolean cessato) {
		this.cessato = cessato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getModalitaOperative() {
		return modalitaOperative;
	}

	public void setModalitaOperative(String modalitaOperative) {
		this.modalitaOperative = modalitaOperative;
	}

	public String getOsservazioniFattura() {
		return osservazioniFattura;
	}

	public void setOsservazioniFattura(String osservazioniFattura) {
		this.osservazioniFattura = osservazioniFattura;
	}

	public Date getCreazione() {
		return creazione;
	}

	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}

	public Date getUltimaModifica() {
		return ultimaModifica;
	}

	public void setUltimaModifica(Date ultimaModifica) {
		this.ultimaModifica = ultimaModifica;
	}
}
