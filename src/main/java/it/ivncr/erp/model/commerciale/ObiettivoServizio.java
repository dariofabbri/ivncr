package it.ivncr.erp.model.commerciale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "com_obiettivo_servizio")
public class ObiettivoServizio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "com_obiettivo_servizio_id_seq")
	@SequenceGenerator(name = "com_obiettivo_servizio_id_seq", sequenceName = "com_obiettivo_servizio_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

	@Column(name="alias")
	private String alias;

	@Column(name="toponimo")
	private String toponimo;

	@Column(name="indirizzo")
	private String indirizzo;

	@Column(name="civico")
	private String civico;

	@Column(name="edificio")
	private String edificio;

	@Column(name="scala")
	private String scala;

	@Column(name="piano")
	private String piano;

	@Column(name="interno")
	private String interno;

	@Column(name="localita")
	private String localita;

	@Column(name="cap")
	private String cap;

	@Column(name="provincia")
	private String provincia;

	@Column(name="paese")
	private String paese;

	@Column(name="note")
	private String note;


	public String getIndirizzoComposto() {

		return String.format("%s %s %s",
				StringUtils.stripToEmpty(toponimo),
				StringUtils.stripToEmpty(indirizzo),
				StringUtils.stripToEmpty(civico)).trim();
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getPiano() {
		return piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
