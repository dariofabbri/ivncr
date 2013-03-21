package it.ivncr.erp.model.personale;

import it.ivncr.erp.model.generale.Azienda;

import java.util.Date;

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
@Table(name = "per_posizione_lavorativa")
public class PosizioneLavorativa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_posizione_lavorativa_id_seq")
	@SequenceGenerator(name = "per_posizione_lavorativa_id_seq", sequenceName = "per_posizione_lavorativa_id_seq")
	@Column(name="id")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addetto_id")
	private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_contratto_id")
	private TipoContratto tipoContratto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ccnl_id")
	private Ccnl ccnl;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="azienda_id")
	private Azienda azienda;

	@Column(name="durata_contratto")
	private Integer durataContratto;

	@Column(name="data_assunzione")
	private Date dataAssunzione;

	@Column(name="durata_prova")
	private Integer durataProva;

	@Column(name="data_primo_giorno")
	private Date dataPrimoGiorno;

	@Column(name="data_fine_prova")
	private Date dataFineProva;

	@Column(name="data_cessazione")
	private Date dataCessazione;

	@Column(name="data_fine_contratto")
	private Date dataFineContratto;

	@Column(name="motivo_dimissioni")
	private String motivoDimissioni;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("tipoContratto", tipoContratto, false)
			.append("ccnl", ccnl, false)
			.append("azienda", azienda, false)
			.append("durataContratto", durataContratto)
			.append("dataAssunzione", dataAssunzione)
			.append("durataProva", durataProva)
			.append("dataPrimoGiorno", dataPrimoGiorno)
			.append("dataFineProva", dataFineProva)
			.append("dataCessazione", dataCessazione)
			.append("dataFineContratto", dataFineContratto)
			.append("motivoDimissioni", motivoDimissioni)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Addetto getAddetto() {
		return addetto;
	}

	public void setAddetto(Addetto addetto) {
		this.addetto = addetto;
	}

	public TipoContratto getTipoContratto() {
		return tipoContratto;
	}

	public void setTipoContratto(TipoContratto tipoContratto) {
		this.tipoContratto = tipoContratto;
	}

	public Ccnl getCcnl() {
		return ccnl;
	}

	public void setCcnl(Ccnl ccnl) {
		this.ccnl = ccnl;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public Integer getDurataContratto() {
		return durataContratto;
	}

	public void setDurataContratto(Integer durataContratto) {
		this.durataContratto = durataContratto;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Integer getDurataProva() {
		return durataProva;
	}

	public void setDurataProva(Integer durataProva) {
		this.durataProva = durataProva;
	}

	public Date getDataPrimoGiorno() {
		return dataPrimoGiorno;
	}

	public void setDataPrimoGiorno(Date dataPrimoGiorno) {
		this.dataPrimoGiorno = dataPrimoGiorno;
	}

	public Date getDataFineProva() {
		return dataFineProva;
	}

	public void setDataFineProva(Date dataFineProva) {
		this.dataFineProva = dataFineProva;
	}

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public Date getDataFineContratto() {
		return dataFineContratto;
	}

	public void setDataFineContratto(Date dataFineContratto) {
		this.dataFineContratto = dataFineContratto;
	}

	public String getMotivoDimissioni() {
		return motivoDimissioni;
	}

	public void setMotivoDimissioni(String motivoDimissioni) {
		this.motivoDimissioni = motivoDimissioni;
	}
}
