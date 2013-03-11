package it.ivncr.erp.jsf.managedbean.personale.gestioneaddetti;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.StatoCivile;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.addetto.AddettoService;
import it.ivncr.erp.service.lut.LUTService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class DettaglioAddettoGenerale implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(DettaglioAddettoGenerale.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	@ManagedProperty("#{gestioneAddetti.edited.id}")
	private Integer id;

	private String matricola;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String luogoNascita;
	private String codiceFiscale;
	private String sesso;
	private Integer codiceStatoCivile;
	private Date dataGiuramento;

	private List<StatoCivile> listStatoCivile;

	private String note;

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load stato civile LUT.
		//
		listStatoCivile = lutService.listItems("StatoCivile");

		// If we are editing an existing record, it is time to fetch
		// it from the database and fill in the bean fields.
		//
		if(id != null) {

			AddettoService as = ServiceFactory.createService("Addetto");
			Addetto addetto = as.retrieveDeep(id);

			matricola = addetto.getMatricola();
			nome = addetto.getNome();
			cognome = addetto.getCognome();
			dataNascita = addetto.getDataNascita();
			luogoNascita = addetto.getLuogoNascita();
			codiceFiscale = addetto.getCodiceFiscale();
			dataGiuramento = addetto.getDataGiuramento();
			codiceStatoCivile = addetto.getStatoCivile() != null ? addetto.getStatoCivile().getId() : null;
		}
	}


	private boolean formValidations() {

		// Normalize fields.
		//
		codiceFiscale = codiceFiscale.toUpperCase();

		return true;
	}


	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public Integer getCodiceStatoCivile() {
		return codiceStatoCivile;
	}

	public void setCodiceStatoCivile(Integer codiceStatoCivile) {
		this.codiceStatoCivile = codiceStatoCivile;
	}

	public Date getDataGiuramento() {
		return dataGiuramento;
	}

	public void setDataGiuramento(Date dataGiuramento) {
		this.dataGiuramento = dataGiuramento;
	}

	public List<StatoCivile> getListStatoCivile() {
		return listStatoCivile;
	}

	public void setListStatoCivile(List<StatoCivile> listStatoCivile) {
		this.listStatoCivile = listStatoCivile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}