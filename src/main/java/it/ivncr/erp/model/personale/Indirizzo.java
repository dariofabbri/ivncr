package it.ivncr.erp.model.personale;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "per_indirizzo")
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_indirizzo_id_seq")
	@SequenceGenerator(name = "per_indirizzo_id_seq", sequenceName = "per_indirizzo_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="addetto_id")
    private Addetto addetto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_indirizzo_id")
	private TipoIndirizzo tipoIndirizzo;

	@Column(name="destinatario1")
	private String destinatario1;

	@Column(name="destinatario2")
	private String destinatario2;

	@Column(name="toponimo")
	private String toponimo;

	@Column(name="indirizzo")
	private String indirizzo;

	@Column(name="civico")
	private String civico;

	@Column(name="localita")
	private String localita;

	@Column(name="cap")
	private String cap;

	@Column(name="provincia")
	private String provincia;

	@Column(name="paese")
	private String paese;

	@Column(name="valido_da")
	private Date validoDa;

	@Column(name="valido_a")
	private Date validoA;


	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("addetto", addetto, false)
			.append("tipoIndirizzo", tipoIndirizzo, false)
			.append("destinatario1", destinatario1)
			.append("destinatario2", destinatario2)
			.append("toponimo", toponimo)
			.append("indirizzo", indirizzo)
			.append("civico", civico)
			.append("localita", localita)
			.append("cap", cap)
			.append("provincia", provincia)
			.append("paese", paese)
			.append("validoDa", validoDa)
			.append("validoA", validoA)
			.toString();
	}


	public String getIndirizzoComposto() {

		return String.format("%s %s %s",
				StringUtils.stripToEmpty(toponimo),
				StringUtils.stripToEmpty(indirizzo),
				StringUtils.stripToEmpty(civico)).trim();
	}

	public String getDestinatarioComposto() {

		return String.format("%s %s",
				StringUtils.stripToEmpty(destinatario1),
				StringUtils.stripToEmpty(destinatario2)).trim();
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

	public TipoIndirizzo getTipoIndirizzo() {
		return tipoIndirizzo;
	}

	public void setTipoIndirizzo(TipoIndirizzo tipoIndirizzo) {
		this.tipoIndirizzo = tipoIndirizzo;
	}

	public String getDestinatario1() {
		return destinatario1;
	}

	public void setDestinatario1(String destinatario1) {
		this.destinatario1 = destinatario1;
	}

	public String getDestinatario2() {
		return destinatario2;
	}

	public void setDestinatario2(String destinatario2) {
		this.destinatario2 = destinatario2;
	}

	public String getToponimo() {
		return toponimo;
	}

	public void setToponimo(String toponimo) {
		this.toponimo = toponimo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public Date getValidoDa() {
		return validoDa;
	}

	public void setValidoDa(Date validoDa) {
		this.validoDa = validoDa;
	}

	public Date getValidoA() {
		return validoA;
	}

	public void setValidoA(Date validoA) {
		this.validoA = validoA;
	}
}
