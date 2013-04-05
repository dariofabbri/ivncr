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

	@Column(name="alias")
	private String alias;

    @ManyToOne
    @JoinColumn(name="tipo_servizio_id")
    private TipoServizio tipoServizio;

    @ManyToOne
    @JoinColumn(name="specifica_servizio_id")
    private SpecificaServizio specificaServizio;

	@Column(name="costo_orario")
	private BigDecimal costoOrario;

	@Column(name="costo_operazione")
	private BigDecimal costoOperazione;

	@Column(name="costo_fisso_una_tantum")
	private BigDecimal costoFissoUnaTantum;

	@Column(name="costo_fisso_a_tempo")
	private BigDecimal costoFissoATempo;

	@Column(name="costo_fisso_mesi")
	private Integer costoFissoMesi;

	@Column(name="franchigie_totali")
	private Integer franchigieTotali;

	@Column(name="franchigie_a_tempo")
	private Integer franchigieATempo;

	@Column(name="franchigie_mesi")
	private Integer franchigieMesi;

	@Column(name="ritenuta_garanzia")
	private BigDecimal ritenutaGaranzia;

	@Column(name="ritenuta_garanzia_giorni")
	private Integer ritenutaGaranziaGiorni;

	@Column(name="data_inizio_validita")
	private Date dataInizioValidita;

	@Column(name="data_cessazione")
	private Date dataCessazione;

	@Column(name="fatturazione_anticipata")
	private Boolean fatturazioneAnticipata;

	@Column(name="extra_fatturato_a_parte")
	private Boolean extraFatturatoAParte;

	@Column(name="fattura_spezzata")
	private Boolean fatturaSpezzata;

	@Column(name="fattura_ogni_mesi")
	private Integer fatturaOgniMesi;

	@Column(name="fattura_minimo_un_mese")
	private Boolean fatturaMinimoUnMese;

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
			.append("costoOrario", costoOrario)
			.append("costoOperazione", costoOperazione)
			.append("costoFissoUnaTantum", costoFissoUnaTantum)
			.append("costoFissoATempo", costoFissoATempo)
			.append("costoFissoMesi", costoFissoMesi)
			.append("franchigieTotali", franchigieTotali)
			.append("franchigieATempo", franchigieATempo)
			.append("franchigieMesi", franchigieMesi)
			.append("ritenutaGaranzia", ritenutaGaranzia)
			.append("ritenutaGaranziaGiorni", ritenutaGaranziaGiorni)
			.append("dataInizioValidita", dataInizioValidita)
			.append("dataCessazione", dataCessazione)
			.append("fatturazioneAnticipata", fatturazioneAnticipata)
			.append("extraFatturatoAParte", extraFatturatoAParte)
			.append("fatturaSpezzata", fatturaSpezzata)
			.append("fatturaOgniMesi", fatturaOgniMesi)
			.append("fatturaMinimoUnMese", fatturaMinimoUnMese)
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

	public BigDecimal getCostoOrario() {
		return costoOrario;
	}

	public void setCostoOrario(BigDecimal costoOrario) {
		this.costoOrario = costoOrario;
	}

	public BigDecimal getCostoOperazione() {
		return costoOperazione;
	}

	public void setCostoOperazione(BigDecimal costoOperazione) {
		this.costoOperazione = costoOperazione;
	}

	public BigDecimal getCostoFissoUnaTantum() {
		return costoFissoUnaTantum;
	}

	public void setCostoFissoUnaTantum(BigDecimal costoFissoUnaTantum) {
		this.costoFissoUnaTantum = costoFissoUnaTantum;
	}

	public BigDecimal getCostoFissoATempo() {
		return costoFissoATempo;
	}

	public void setCostoFissoATempo(BigDecimal costoFissoATempo) {
		this.costoFissoATempo = costoFissoATempo;
	}

	public Integer getCostoFissoMesi() {
		return costoFissoMesi;
	}

	public void setCostoFissoMesi(Integer costoFissoMesi) {
		this.costoFissoMesi = costoFissoMesi;
	}

	public Integer getFranchigieTotali() {
		return franchigieTotali;
	}

	public void setFranchigieTotali(Integer franchigieTotali) {
		this.franchigieTotali = franchigieTotali;
	}

	public Integer getFranchigieATempo() {
		return franchigieATempo;
	}

	public void setFranchigieATempo(Integer franchigieATempo) {
		this.franchigieATempo = franchigieATempo;
	}

	public Integer getFranchigieMesi() {
		return franchigieMesi;
	}

	public void setFranchigieMesi(Integer franchigieMesi) {
		this.franchigieMesi = franchigieMesi;
	}

	public BigDecimal getRitenutaGaranzia() {
		return ritenutaGaranzia;
	}

	public void setRitenutaGaranzia(BigDecimal ritenutaGaranzia) {
		this.ritenutaGaranzia = ritenutaGaranzia;
	}

	public Integer getRitenutaGaranziaGiorni() {
		return ritenutaGaranziaGiorni;
	}

	public void setRitenutaGaranziaGiorni(Integer ritenutaGaranziaGiorni) {
		this.ritenutaGaranziaGiorni = ritenutaGaranziaGiorni;
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

	public Boolean getFatturazioneAnticipata() {
		return fatturazioneAnticipata;
	}

	public void setFatturazioneAnticipata(Boolean fatturazioneAnticipata) {
		this.fatturazioneAnticipata = fatturazioneAnticipata;
	}

	public Boolean getExtraFatturatoAParte() {
		return extraFatturatoAParte;
	}

	public void setExtraFatturatoAParte(Boolean extraFatturatoAParte) {
		this.extraFatturatoAParte = extraFatturatoAParte;
	}

	public Boolean getFatturaSpezzata() {
		return fatturaSpezzata;
	}

	public void setFatturaSpezzata(Boolean fatturaSpezzata) {
		this.fatturaSpezzata = fatturaSpezzata;
	}

	public Integer getFatturaOgniMesi() {
		return fatturaOgniMesi;
	}

	public void setFatturaOgniMesi(Integer fatturaOgniMesi) {
		this.fatturaOgniMesi = fatturaOgniMesi;
	}

	public Boolean getFatturaMinimoUnMese() {
		return fatturaMinimoUnMese;
	}

	public void setFatturaMinimoUnMese(Boolean fatturaMinimoUnMese) {
		this.fatturaMinimoUnMese = fatturaMinimoUnMese;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}