package it.ivncr.erp.jsf.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="percentageConverter")
public class PercentageConverter implements Converter {

	private final Pattern pattern = Pattern.compile("^[0-9]*(,[0-9][0-9])?$", Pattern.CASE_INSENSITIVE);
	private final DecimalFormat inputDf;
	private final DecimalFormat outputDf;

	public PercentageConverter() {

		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');

		inputDf = new DecimalFormat("0.##", dfs);
		inputDf.setGroupingUsed(false);

		outputDf = new DecimalFormat("0.00", dfs);
		outputDf.setGroupingUsed(false);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if(value == null) {
			return null;
		}

		// If a regex pattern has been specified, apply it.
		//
		if(!pattern.matcher(value).matches()) {
			FacesMessage message = new FacesMessage();
			message.setSummary("Percentuale errata");
			message.setDetail("La percentuale immessa non è valida");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}

		Number number = null;
		try {
			number = inputDf.parse(value);
		} catch (ParseException e) {
			throw new RuntimeException("Exception caught while parsing: " + value);
		}

		if(number.doubleValue() > 100.0) {
			FacesMessage message = new FacesMessage();
			message.setSummary("Percentuale maggiore di cento");
			message.setDetail("La percentuale immessa supera il massimo valore ammesso");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}

		return number;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		if(object == null) {
			return null;
		}

		return outputDf.format(object);

	}
}
