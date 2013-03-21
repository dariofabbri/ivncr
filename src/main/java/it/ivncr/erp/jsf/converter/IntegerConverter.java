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

@FacesConverter(value="integerConverter")
public class IntegerConverter implements Converter {

	private final Pattern pattern = Pattern.compile("^[0-9]*$", Pattern.CASE_INSENSITIVE);
	private final DecimalFormat inputDf;
	private final DecimalFormat outputDf;

	public IntegerConverter() {

		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');

		inputDf = new DecimalFormat("#", dfs);
		inputDf.setGroupingUsed(false);

		outputDf = new DecimalFormat("#", dfs);
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
			message.setSummary("Intero errato");
			message.setDetail("L'intero immesso non è valido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(message);
		}

		try {

			return inputDf.parse(value);

		} catch (ParseException e) {

			throw new RuntimeException("Exception caught while parsing: " + value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		return outputDf.format(object);

	}
}
