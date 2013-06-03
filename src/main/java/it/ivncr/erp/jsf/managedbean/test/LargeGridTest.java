package it.ivncr.erp.jsf.managedbean.test;


import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.operativo.Servizio;
import it.ivncr.erp.model.personale.Addetto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;

import com.sun.faces.facelets.component.UIRepeat;


@ManagedBean
@ViewScoped
public class LargeGridTest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LargeGridRow> rows;
	private int noOfAddetti;
	private DataTable datatable;

	private final Random rnd = new Random();

	@PostConstruct
	public void init() {

		buildSampleData();

		datatable = new DataTable();

		Column column = buildServizioColumn();
		datatable.getColumns().add(column);

		column = buildOrarioColumn();
		datatable.getColumns().add(column);

		for(int i = 0; i < noOfAddetti; ++i) {
			column = buildAddettoColumn(i);
			datatable.getColumns().add(column);
		}
	}

	private ValueExpression createValueExpression(String expression, Class<?> expectedType) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    return context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), expression, expectedType);
	}

	private Column buildServizioColumn() {

		Column column = new Column();
		column.setHeaderText("Servizio");
		column.setStyle("width: 200px;");

		HtmlPanelGroup panelGroup = new HtmlPanelGroup();
		panelGroup.setStyle("height: 6em; vertical-align: top; font-size: 0.8em;");
		panelGroup.setLayout("block");
		column.getChildren().add(panelGroup);

		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.ods.alias}", String.class));
		panelGroup.getChildren().add(outputText);

		return column;
	}


	private Column buildOrarioColumn() {

		DateTimeConverter dtc = (DateTimeConverter)FacesContext.getCurrentInstance().getApplication().createConverter("javax.faces.DateTime");
		dtc.setPattern("HH:mm");
		dtc.setTimeZone(
				TimeZone.getTimeZone(
						(String)createValueExpression("#{appConfig.timeZone}", String.class)
							.getValue(FacesContext.getCurrentInstance().getELContext())));

		Column column = new Column();
		column.setHeaderText("Orario");
		column.setStyle("width: 150px;");

		HtmlPanelGroup panelGroup = new HtmlPanelGroup();
		panelGroup.setStyle("height: 6em; vertical-align: top; font-size: 0.8em;");
		panelGroup.setLayout("block");
		column.getChildren().add(panelGroup);

		HtmlPanelGroup block = new HtmlPanelGroup();
		block.setValueExpression("rendered", createValueExpression("#{not empty row.orario.quantita1}", Boolean.class));
		panelGroup.getChildren().add(block);

		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValue("n.&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.quantita1}", String.class));
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;&nbsp;&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.orarioInizio1}", Date.class));
		outputText.setConverter(dtc);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;-&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.orarioFine1}", Date.class));
		outputText.setConverter(dtc);
		block.getChildren().add(outputText);


		block = new HtmlPanelGroup();
		block.setValueExpression("rendered", createValueExpression("#{not empty row.orario.quantita2}", Boolean.class));
		panelGroup.getChildren().add(block);

		outputText = new HtmlOutputText();
		outputText.setValue("<br>n.&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.quantita2}", String.class));
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;&nbsp;&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.orarioInizio2}", Date.class));
		outputText.setConverter(dtc);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;-&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.orarioFine2}", Date.class));
		outputText.setConverter(dtc);
		block.getChildren().add(outputText);


		block = new HtmlPanelGroup();
		block.setValueExpression("rendered", createValueExpression("#{not empty row.orario.quantita3}", Boolean.class));
		panelGroup.getChildren().add(block);

		outputText = new HtmlOutputText();
		outputText.setValue("<br>n.&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.quantita3}", String.class));
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;&nbsp;&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.orarioInizio3}", Date.class));
		outputText.setConverter(dtc);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;-&nbsp;");
		outputText.setEscape(false);
		block.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.orario.orarioFine3}", Date.class));
		outputText.setConverter(dtc);
		block.getChildren().add(outputText);

		return column;
	}


	private Column buildAddettoColumn(int addetto) {

		DateTimeConverter dtc = (DateTimeConverter)FacesContext.getCurrentInstance().getApplication().createConverter("javax.faces.DateTime");
		dtc.setPattern("HH:mm");
		dtc.setTimeZone(
				TimeZone.getTimeZone(
						(String)createValueExpression("#{appConfig.timeZone}", String.class)
							.getValue(FacesContext.getCurrentInstance().getELContext())));

		Column column = new Column();
		column.setHeaderText("Addetto #" + (addetto + 1));
		column.setStyle("width: 200px;");

		HtmlPanelGroup panelGroup = new HtmlPanelGroup();
		panelGroup.setStyle("height: 6em; vertical-align: top; font-size: 0.8em;");
		panelGroup.setLayout("block");
		column.getChildren().add(panelGroup);

		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.addetti[" + addetto + "].addetto.matricola}", String.class));
		panelGroup.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;");
		outputText.setEscape(false);
		panelGroup.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.addetti[" + addetto + "].addetto.cognome}", String.class));
		panelGroup.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;");
		outputText.setEscape(false);
		panelGroup.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{row.addetti[" + addetto + "].addetto.nome}", String.class));
		panelGroup.getChildren().add(outputText);

		UIRepeat repeat = new UIRepeat();
		repeat.setVar("servizio");
		repeat.setValueExpression("value", createValueExpression("#{row.addetti[" + addetto + "].servizi}", List.class));
		panelGroup.getChildren().add(repeat);

		outputText = new HtmlOutputText();
		outputText.setValue("<br>");
		outputText.setEscape(false);
		repeat.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{servizio.orarioDa}", Date.class));
		outputText.setConverter(dtc);
		repeat.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValue("&nbsp;-&nbsp;");
		outputText.setEscape(false);
		repeat.getChildren().add(outputText);

		outputText = new HtmlOutputText();
		outputText.setValueExpression("value", createValueExpression("#{servizio.orarioA}", Date.class));
		outputText.setConverter(dtc);
		repeat.getChildren().add(outputText);

		return column;
	}


	private void buildSampleData() {

		rows = new ArrayList<LargeGridRow>();

		noOfAddetti = rnd.nextInt(20);

		for(int i = 0; i < 20; ++i) {

			LargeGridRow row = new LargeGridRow();

			OrdineServizio ods = new OrdineServizio();
			ods.setAlias("Servizio #" + (i + 1));
			row.setOds(ods);

			row.setOrario(buildRandomOrario());

			for(int j = 0; j < noOfAddetti; ++j) {
				row.getAddetti().add(buildRandomAddettoCell());
			}

			rows.add(row);
		}
	}

	private AddettoCell buildRandomAddettoCell() {

		AddettoCell addettoCell = new AddettoCell();
		Addetto addetto = new Addetto();
		addetto.setMatricola(RandomStringUtils.randomNumeric(5));
		addetto.setCognome(RandomStringUtils.randomAlphabetic(rnd.nextInt(30)));
		addetto.setNome(RandomStringUtils.randomAlphabetic(rnd.nextInt(20)));
		addettoCell.setAddetto(addetto);

		int noOfServizi = rnd.nextInt(3) + 1;
		for(int j = 0; j < noOfServizi; ++j) {
			addettoCell.getServizi().add(buildRandomServizio());
		}

		return addettoCell;
	}

	private Servizio buildRandomServizio() {

		Servizio servizio = new Servizio();

		servizio.setOrarioDa(buildRandomTime());
		servizio.setOrarioA(buildRandomTime());

		return servizio;
	}

	private Orario buildRandomOrario() {

		Orario orario = new Orario();

		orario.setQuantita1(rnd.nextInt(3) + 1);
		orario.setOrarioInizio1(buildRandomTime());
		orario.setOrarioFine1(buildRandomTime());

		if(rnd.nextDouble() < .4) {
			return orario;
		}
		orario.setQuantita2(rnd.nextInt(3) + 1);
		orario.setOrarioInizio2(buildRandomTime());
		orario.setOrarioFine2(buildRandomTime());

		if(rnd.nextDouble() < .8) {
			return orario;
		}
		orario.setQuantita3(rnd.nextInt(3) + 1);
		orario.setOrarioInizio3(buildRandomTime());
		orario.setOrarioFine3(buildRandomTime());

		return orario;
	}

	private Date buildRandomTime() {

		GregorianCalendar gc = new GregorianCalendar();
		gc.clear();
		gc.set(Calendar.HOUR_OF_DAY, rnd.nextInt(24));
		gc.set(Calendar.MINUTE, rnd.nextInt(60));

		return gc.getTime();
	}

	public List<LargeGridRow> getRows() {
		return rows;
	}

	public void setRows(List<LargeGridRow> rows) {
		this.rows = rows;
	}

	public DataTable getDatatable() {
		return datatable;
	}

	public void setDatatable(DataTable datatable) {
		this.datatable = datatable;
	}
}
