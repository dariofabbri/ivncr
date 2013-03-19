package it.ivncr.erp.jsf.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="currencyConverter")
public class CurrencyConverter implements Converter {

	private final DecimalFormat df;

	public CurrencyConverter() {

		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		df = new DecimalFormat("0.##", dfs);
		df.setGroupingUsed(false);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {

			return df.parse(value);

		} catch (ParseException e) {

			throw new RuntimeException("Unable to convert specified string to currency: " + value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		return df.format(object);

	}

}
