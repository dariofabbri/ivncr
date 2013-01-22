package it.ivncr.erp.jsf.managedbean.commerciale.gestioneclienti;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.TipoBusinessPartner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioClienteGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioClienteGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestioneClienti.edited}")
	private Cliente edited;

	private List<TipoBusinessPartner> listTipoBusinessPartner;

	@PostConstruct
	public void init() {

		System.out.println(">>>>>>>>>>>>>>>>>>>" + edited);

		listTipoBusinessPartner = new ArrayList<TipoBusinessPartner>();

		TipoBusinessPartner tbp = new TipoBusinessPartner();
		tbp.setId(1);
		tbp.setDescrizione("Società");
		listTipoBusinessPartner.add(tbp);

		tbp = new TipoBusinessPartner();
		tbp.setId(2);
		tbp.setDescrizione("Privato");
		listTipoBusinessPartner.add(tbp);
	}

	public Cliente getEdited() {
		return edited;
	}

	public void setEdited(Cliente edited) {
		this.edited = edited;
	}

	public List<TipoBusinessPartner> getListTipoBusinessPartner() {
		return listTipoBusinessPartner;
	}

	public void setListTipoBusinessPartner(
			List<TipoBusinessPartner> listTipoBusinessPartner) {
		this.listTipoBusinessPartner = listTipoBusinessPartner;
	}
}
