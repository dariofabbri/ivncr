package it.ivncr.erp.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="partitaIvaValidator")
public class PartitaIvaValidator implements Validator {

	private static final String ITALIA = "(IT){0,1}(\\d{11})";

	private static boolean isPartitaIva(String s) {

		// Must be numeric, 11 digits.
		//
		Pattern p = Pattern.compile(ITALIA);
		Matcher m = p.matcher(s);
		if (!m.matches())
			return false;

		if (m.group(1) != null) {
			int index = m.end(1);
			s = s.substring(index);
		}

		// Verify check digit.
		//

		// Sum odd digits (including check digit).
		//
		int odd = 0;
		for (int i = 0; i < 11; i += 2) {
			int digit = s.getBytes()[i] - '0';
			odd += digit;
		}

		// Sum doubled even digits counting digits >= 5.
		//
		int upcnt = 0;
		int even = 0;
		for (int i = 1; i < 11; i += 2) {
			int digit = s.getBytes()[i] - '0';
			upcnt += digit >= 5 ? 1 : 0;
			even += digit * 2;
		}

		// Sum up everything.
		//
		int t = odd + even + upcnt;

		// Mod 10 must be zero.
		//
		return (t % 10) == 0;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		// Get the string value of the passed object.
		// If the value is not present, the check on the code does not proceed and
		// the validator does not fail. To have a required partita IVA field
		// it is necessary to combine the required attribute in the UI.
		//
		String s = value.toString();
		if(StringUtils.isEmpty(s))
			return;

		// Check if it is a valid partita IVA.
		//
		if(!isPartitaIva(s)) {

			FacesMessage message = new FacesMessage();
			message.setSummary("Partita IVA non valida");
			message.setDetail("Il codice di partita IVA inserito non è valido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
