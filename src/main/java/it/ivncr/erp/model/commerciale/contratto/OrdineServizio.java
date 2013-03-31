package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;

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
public class OrdineServizio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_ordine_servizio_id_seq")
	@SequenceGenerator(name = "con_ordine_servizio_id_seq", sequenceName = "con_ordine_servizio_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="contratto_id")
    private Contratto contratto;

	@Column(name="data_decorrenza")
	private Date dataDecorrenza;

	@Column(name="data_termine")
	private Date dataTermine;

    @ManyToOne
    @JoinColumn(name="obiettivo_servizio_id")
    private ObiettivoServizio obiettivoServizio;

    @ManyToOne
    @JoinColumn(name="tipo_ordine_servizio_id")
    private TipoOrdineServizio tipoOrdineServizio;

    @ManyToOne
    @JoinColumn(name="raggruppamento_fatturazione_id")
    private RaggruppamentoFatturazione raggruppamentoFatturazione;

	@Column(name="numero")
	private Integer numero;

	@Column(name="orario_inizio")
	private Date orarioInizio;

	@Column(name="orario_fine")
	private Date orarioFine;

	@Column(name="lunedi")
	private Boolean lunedi;

	@Column(name="martedi")
	private Boolean martedi;

	@Column(name="mercoledi")
	private Boolean mercoledi;

	@Column(name="giovedi")
	private Boolean giovedi;

	@Column(name="venerdi")
	private Boolean venerdi;

	@Column(name="sabato")
	private Boolean sabato;

	@Column(name="domenica")
	private Boolean domenica;

	@Column(name="festivi")
	private Boolean festivi;

	@Column(name="note")
	private String note;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("contratto", contratto, false)
			.append("dataDecorrenza", dataDecorrenza)
			.append("dataTermine", dataTermine)
			.append("obiettivoServizio", obiettivoServizio, false)
			.append("tipoOrdineServizio", tipoOrdineServizio, false)
			.append("raggruppamentoFatturazione", raggruppamentoFatturazione, false)
			.append("numero", numero)
			.append("orarioInizio", orarioInizio)
			.append("orarioFine", orarioFine)
			.append("lunedi", lunedi)
			.append("martedi", martedi)
			.append("mercoledi", mercoledi)
			.append("giovedi", giovedi)
			.append("venerdi", venerdi)
			.append("sabato", sabato)
			.append("domenica", domenica)
			.append("festivi", festivi)
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

	public ObiettivoServizio getObiettivoServizio() {
		return obiettivoServizio;
	}

	public void setObiettivoServizio(ObiettivoServizio obiettivoServizio) {
		this.obiettivoServizio = obiettivoServizio;
	}

	public TipoOrdineServizio getTipoOrdineServizio() {
		return tipoOrdineServizio;
	}

	public void setTipoOrdineServizio(TipoOrdineServizio tipoOrdineServizio) {
		this.tipoOrdineServizio = tipoOrdineServizio;
	}

	public RaggruppamentoFatturazione getRaggruppamentoFatturazione() {
		return raggruppamentoFatturazione;
	}

	public void setRaggruppamentoFatturazione(
			RaggruppamentoFatturazione raggruppamentoFatturazione) {
		this.raggruppamentoFatturazione = raggruppamentoFatturazione;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(Date orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public Date getOrarioFine() {
		return orarioFine;
	}

	public void setOrarioFine(Date orarioFine) {
		this.orarioFine = orarioFine;
	}

	public Boolean getLunedi() {
		return lunedi;
	}

	public void setLunedi(Boolean lunedi) {
		this.lunedi = lunedi;
	}

	public Boolean getMartedi() {
		return martedi;
	}

	public void setMartedi(Boolean martedi) {
		this.martedi = martedi;
	}

	public Boolean getMercoledi() {
		return mercoledi;
	}

	public void setMercoledi(Boolean mercoledi) {
		this.mercoledi = mercoledi;
	}

	public Boolean getGiovedi() {
		return giovedi;
	}

	public void setGiovedi(Boolean giovedi) {
		this.giovedi = giovedi;
	}

	public Boolean getVenerdi() {
		return venerdi;
	}

	public void setVenerdi(Boolean venerdi) {
		this.venerdi = venerdi;
	}

	public Boolean getSabato() {
		return sabato;
	}

	public void setSabato(Boolean sabato) {
		this.sabato = sabato;
	}

	public Boolean getDomenica() {
		return domenica;
	}

	public void setDomenica(Boolean domenica) {
		this.domenica = domenica;
	}

	public Boolean getFestivi() {
		return festivi;
	}

	public void setFestivi(Boolean festivi) {
		this.festivi = festivi;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
