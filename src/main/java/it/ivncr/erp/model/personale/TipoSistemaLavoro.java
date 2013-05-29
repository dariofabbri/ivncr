package it.ivncr.erp.model.personale;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "per_tipo_sistema_lavoro")
public class TipoSistemaLavoro implements Serializable {

  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_tipo_sistema_lavoro_id_seq")
	@SequenceGenerator(name = "per_tipo_sistema_lavoro_id_seq", sequenceName = "per_tipo_sistema_lavoro_id_seq")
	@Column(name="id")
	private Integer id;

	@Column(name="descrizione")
	private String descrizione;

	@Column(name="giorni_lavorativi")
	private Integer giorniLavorativi;

	@Column(name="giorni_riposo")
	private Integer giorniRiposo;

	@Column(name="giorni_permesso")
	private Integer giorniPermesso;

	@Column(name="riposo_sabato")
	private Boolean riposoSabato;

	@Column(name="riposo_domenica")
	private Boolean riposoDomenica;

	@Column(name="ferie")
	private Integer ferie;

	@Column(name="permessi_annuali")
	private BigDecimal permessiAnnuali;

	@Column(name="permessi_a_conguaglio")
	private BigDecimal permessiAConguaglio;

	@Column(name="sistema")
	private Boolean sistema;

	@Column(name="ore_ordinarie_giornaliere")
	private BigDecimal oreOrdinarieGiornaliere;

	@Column(name="domeniche_festive")
	private Boolean domenicheFestive;

	@Column(name="giorni_semirip")
	private Integer giorniSemirip;

	@Column(name="percentuale_part_time")
	private BigDecimal percentualePartTime;

	@Column(name="apprendistato")
	private Boolean apprendistato;

	@Column(name="max_straordinari")
	private BigDecimal maxStraordinari;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_soglia_id")
	private TipoSoglia tipoSoglia;

	@Column(name="ordinario_settimanale")
	private BigDecimal ordinarioSettimanale;

	@Column(name="mesi_recupero_flessibilita")
	private Integer mesiRecuperoFlessibilita;

	@Column(name="ore_flessibilita_carico")
	private BigDecimal oreFlessibilitaCarico;

	@Column(name="ore_flessibilita_scarico")
	private BigDecimal oreFlessibilitaScarico;

	@Column(name="settimana_statica")
	private Boolean settimanaStatica;

	@Column(name="giorni_standard_annuali")
	private Integer giorniStandardAnnuali;

	@Column(name="buono_pasto")
	private Boolean buonoPasto;

	@Column(name="ore_minime_lavoro_buono_pasto")
	private BigDecimal oreMinimeLavoroBuonoPasto;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("descrizione", descrizione)
			.append("giorniLavorativi", giorniLavorativi)
			.append("giorniRiposo", giorniRiposo)
			.append("giorniPermesso", giorniPermesso)
			.append("riposoSabato", riposoSabato)
			.append("riposoDomenica", riposoDomenica)
			.append("ferie", ferie)
			.append("permessiAnnuali", permessiAnnuali)
			.append("permessiAConguaglio", permessiAConguaglio)
			.append("sistema", sistema)
			.append("oreOrdinarieGiornaliere", oreOrdinarieGiornaliere)
			.append("domenicheFestive", domenicheFestive)
			.append("giorniSemirip", giorniSemirip)
			.append("percentualePartTime", percentualePartTime)
			.append("apprendistato", apprendistato)
			.append("maxStraordinari", maxStraordinari)
			.append("tipoSoglia", tipoSoglia, false)
			.append("ordinarioSettimanale", ordinarioSettimanale)
			.append("mesiRecuperoFlessibilita", mesiRecuperoFlessibilita)
			.append("oreFlessibilitaCarico", oreFlessibilitaCarico)
			.append("oreFlessibilitaScarico", oreFlessibilitaScarico)
			.append("settimanaStatica", settimanaStatica)
			.append("giorniStandardAnnuali", giorniStandardAnnuali)
			.append("buonoPasto", buonoPasto)
			.append("oreMinimeLavoroBuonoPasto", oreMinimeLavoroBuonoPasto)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
