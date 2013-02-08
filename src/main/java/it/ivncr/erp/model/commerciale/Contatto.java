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

@Entity
@Table(name = "com_contatto")
public class Contatto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "com_contatto_id_seq")
	@SequenceGenerator(name = "com_contatto_id_seq", sequenceName = "com_contatto_id_seq")
	@Column(name="id")
	private Integer id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipo_contatto_id")
	private TipoContatto tipoContatto;

	@Column(name="titolo")
	private String titolo;

	@Column(name="nome")
	private String nome;

	@Column(name="telefono1")
	private String telefono1;

	@Column(name="telefono2")
	private String telefono2;

	@Column(name="cellulare")
	private String cellulare;

	@Column(name="fax")
	private String fax;

	@Column(name="email")
	private String email;

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

	public TipoContatto getTipoContatto() {
		return tipoContatto;
	}

	public void setTipoContatto(TipoContatto tipoContatto) {
		this.tipoContatto = tipoContatto;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
