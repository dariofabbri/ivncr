package it.ivncr.erp.jsf.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="currencyValidator")
public class CurrencyValidator implements Validator {

	private final Pattern pattern = Pattern.compile("^[0-9]*(,[0-9][0-9])?$", Pattern.CASE_INSENSITIVE);

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		// Get the string value of the passed object.
		//
		String s = value.toString();

		// If a regex pattern has been specified, apply it.
		//
		if(!pattern.matcher(s).matches()) {
			FacesMessage message = new FacesMessage();
			message.setSummary("Importo errato");
			message.setDetail("L'importo immesso non è valido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}
