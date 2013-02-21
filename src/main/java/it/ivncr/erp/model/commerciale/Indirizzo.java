package it.ivncr.erp.model.commerciale;

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
@Table(name = "com_indirizzo")
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "com_indirizzo_id_seq")
	@SequenceGenerator(name = "com_indirizzo_id_seq", sequenceName = "com_indirizzo_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

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

	
	public String toString() {
		
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("cliente", cliente, false)
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
}
