package it.ivncr.erp.model.commerciale.contratto;

import it.ivncr.erp.model.commerciale.cliente.Cliente;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "con_contratto")
public class Contratto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "con_contratto_id_seq")
	@SequenceGenerator(name = "con_contratto_id_seq", sequenceName = "con_contratto_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

	@Column(name="ragione_sociale")
	private String ragioneSociale;

	@Column(name="codice")
	private String codice;

	@Column(name="alias")
	private String alias;

	@Column(name="data_contratto")
	private Date dataContratto;

	@Column(name="data_decorrenza")
	private Date dataDecorrenza;

	@Column(name="data_termine")
	private Date dataTermine;

	@Column(name="data_cessazione")
	private Date dataCessazione;

	@Column(name="tacito_rinnovo")
	private Boolean tacitoRinnovo;

	@Column(name="giorni_periodo_rinnovo")
	private Integer giorniPeriodoRinnovo;

	@Column(name="mesi_periodo_rinnovo")
	private Integer mesiPeriodoRinnovo;

	@Column(name="anni_periodo_rinnovo")
	private Integer anniPeriodoRinnovo;

	@Column(name="giorni_preavviso_scadenza")
	private Integer giorniPreavvisoScadenza;

	@Column(name="note")
	private String note;

	@Column(name="creazione_ts")
	private Date creazione;

	@Column(name="ultima_modifica_ts")
	private Date ultimaModifica;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="contratto")
	@OrderBy
	private Set<ContrattoContatto> contatti;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="contratto")
	@OrderBy
	private Set<ContrattoGestore> gestori;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="contratto")
	@OrderBy
	private Set<ContrattoEsattore> esattori;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("cliente", cliente, false)
			.append("ragioneSociale", ragioneSociale)
			.append("codice", codice)
			.append("alias", alias)
			.append("dataContratto", dataContratto)
			.append("dataDecorrenza", dataDecorrenza)
			.append("dataTermine", dataTermine)
			.append("dataCessazione", dataCessazione)
			.append("tacitoRinnovo", tacitoRinnovo)
			.append("giorniPeriodoRinnovo", giorniPeriodoRinnovo)
			.append("mesiPeriodoRinnovo", mesiPeriodoRinnovo)
			.append("anniPeriodoRinnovo", anniPeriodoRinnovo)
			.append("giorniPreavvisoScadenza", giorniPreavvisoScadenza)
			.append("note", note)
			.append("creazione", creazione)
			.append("ultimaModifica", ultimaModifica)
			.append("contatti", contatti, false)
			.append("gestori", gestori, false)
			.append("esattori", esattori, false)
			.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
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

	public Date getDataContratto() {
		return dataContratto;
	}

	public void setDataContratto(Date dataContratto) {
		this.dataContratto = dataContratto;
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

	public Date getDataCessazione() {
		return dataCessazione;
	}

	public void setDataCessazione(Date dataCessazione) {
		this.dataCessazione = dataCessazione;
	}

	public Boolean getTacitoRinnovo() {
		return tacitoRinnovo;
	}

	public void setTacitoRinnovo(Boolean tacitoRinnovo) {
		this.tacitoRinnovo = tacitoRinnovo;
	}

	public Integer getGiorniPeriodoRinnovo() {
		return giorniPeriodoRinnovo;
	}

	public void setGiorniPeriodoRinnovo(Integer giorniPeriodoRinnovo) {
		this.giorniPeriodoRinnovo = giorniPeriodoRinnovo;
	}

	public Integer getMesiPeriodoRinnovo() {
		return mesiPeriodoRinnovo;
	}

	public void setMesiPeriodoRinnovo(Integer mesiPeriodoRinnovo) {
		this.mesiPeriodoRinnovo = mesiPeriodoRinnovo;
	}

	public Integer getAnniPeriodoRinnovo() {
		return anniPeriodoRinnovo;
	}

	public void setAnniPeriodoRinnovo(Integer anniPeriodoRinnovo) {
		this.anniPeriodoRinnovo = anniPeriodoRinnovo;
	}

	public Integer getGiorniPreavvisoScadenza() {
		return giorniPreavvisoScadenza;
	}

	public void setGiorniPreavvisoScadenza(Integer giorniPreavvisoScadenza) {
		this.giorniPreavvisoScadenza = giorniPreavvisoScadenza;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Set<ContrattoContatto> getContatti() {
		return contatti;
	}

	public void setContatti(Set<ContrattoContatto> contatti) {
		this.contatti = contatti;
	}

	public Set<ContrattoGestore> getGestori() {
		return gestori;
	}

	public void setGestori(Set<ContrattoGestore> gestori) {
		this.gestori = gestori;
	}

	public Set<ContrattoEsattore> getEsattori() {
		return esattori;
	}

	public void setEsattori(Set<ContrattoEsattore> esattori) {
		this.esattori = esattori;
	}
}
