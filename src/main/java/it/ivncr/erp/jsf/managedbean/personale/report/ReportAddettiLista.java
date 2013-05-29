package it.ivncr.erp.jsf.managedbean.personale.report;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;
import it.ivncr.erp.model.personale.StatoCivile;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.report.ReportService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class ReportAddettiLista implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(ReportAddettiLista.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginInfo}")
    private LoginInfo loginInfo;

	private String matricola;
	private String nome;
	private String cognome;
	private Date dataNascitaDa;
	private Date dataNascitaA;
	private String luogoNascita;
	private String codiceFiscale;
	private String sesso;
	private Integer codiceStatoCivile;

	private List<StatoCivile> listStatoCivile;

	private byte[] report;
	private boolean collapseSearch;
	private boolean collapseReport;

	@PostConstruct
	public void init() {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Load stato civile LUT.
		//
		listStatoCivile = lutService.listItems("StatoCivile");

		// Initialize collapsed status.
		//
		collapseSearch = false;
		collapseReport = true;
	}


	public void doGenerate() {

		logger.debug("Entering doGenerate method.");

		ReportService rs = ServiceFactory.createServiceNoSession("Report");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("azienda_id", loginInfo.getAzienda().getId());
		parameters.put("matricola", matricola);
		parameters.put("nome", nome);
		parameters.put("cognome",cognome);
		parameters.put("dataNascitaDa", dataNascitaDa);
		parameters.put("dataNascitaA", dataNascitaA);
		parameters.put("luogoNascita", luogoNascita);
		parameters.put("sesso", sesso);
		parameters.put("codiceStatoCivile", codiceStatoCivile);

		report = rs.generateReport("reports/lista-addetti.jasper", parameters);
		logger.debug("Report succesfully generated.");

		collapseSearch = true;
		collapseReport = false;
	}

	public StreamedContent getPdf() {

		logger.debug("Entering getPdf method.");

		if(report == null) {
			return null;
		}

		logger.debug("Returning new streamed content created from PDF report.");
		return new DefaultStreamedContent(new ByteArrayInputStream(report), "application/pdf", "report.pdf");
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
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

	public Date getDataNascitaDa() {
		return dataNascitaDa;
	}

	public void setDataNascitaDa(Date dataNascitaDa) {
		this.dataNascitaDa = dataNascitaDa;
	}

	public Date getDataNascitaA() {
		return dataNascitaA;
	}


	public void setDataNascitaA(Date dataNascitaA) {
		this.dataNascitaA = dataNascitaA;
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

	public List<StatoCivile> getListStatoCivile() {
		return listStatoCivile;
	}

	public void setListStatoCivile(List<StatoCivile> listStatoCivile) {
		this.listStatoCivile = listStatoCivile;
	}

	public byte[] getReport() {
		return report;
	}

	public void setReport(byte[] report) {
		this.report = report;
	}

	public boolean isCollapseSearch() {
		return collapseSearch;
	}

	public void setCollapseSearch(boolean collapseSearch) {
		this.collapseSearch = collapseSearch;
	}

	public boolean isCollapseReport() {
		return collapseReport;
	}

	public void setCollapseReport(boolean collapseReport) {
		this.collapseReport = collapseReport;
	}
}