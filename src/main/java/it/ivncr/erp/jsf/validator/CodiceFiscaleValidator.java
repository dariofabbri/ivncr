package it.ivncr.erp.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="codiceFiscaleValidator")
public class CodiceFiscaleValidator implements Validator {

	private static int odd[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3,
		6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };

	public static boolean isCodiceFiscale(String s) {

		if (s.length() == 0)
			return false;

		if (s.length() != 16)
			return false;

		s = s.toUpperCase();

		for (int i = 0; i < 16; i++) {
			int c = s.charAt(i);
			if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z'))
				return false;
		}

		int sum = 0;

		for (int i = 1; i <= 13; i += 2) {
			int c = s.charAt(i);
			if (c >= '0' && c <= '9')
				sum = sum + c - '0';
			else
				sum = sum + c - 'A';
		}

		for (int i = 0; i <= 14; i += 2) {
			int c = s.charAt(i);
			if (c >= '0' && c <= '9')
				c = c - '0' + 'A';
			sum = sum + odd[c - 'A'];
		}

		return (sum % 26 + 'A' == s.charAt(15));
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		// Get the string value of the passed object.
		// If the value is not present, the check on the code does not proceed and
		// the validator does not fail. To have a required codice fiscale field
		// it is necessary to combine the required attribute in the UI.
		//
		String s = value.toString();
		if(StringUtils.isEmpty(s))
			return;

		// Check if it is a valid CF.
		//
		if(!isCodiceFiscale(s)) {

			FacesMessage message = new FacesMessage();
			message.setSummary("Codice fiscale non valido");
			message.setDetail("Il codice fiscale inserito non è valido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
