package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class DettaglioClientiContatti {

	private String telefono1;

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
}
