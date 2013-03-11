package it.ivncr.erp.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="matricolaAddettoValidator")
public class MatricolaAddettoValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		// Get the string value of the passed object.
		//
		String s = value.toString().trim();;

		// Check if it is a valid integer.
		//
		if(!StringUtils.isNumeric(s)) {
			FacesMessage message = new FacesMessage();
			message.setSummary("Matricola non valida");
			message.setDetail("La matricola inserita non rappresenta un numero valido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		// Matricola must be exactly 5 digit.
		//
		if(s.length() != 5) {
			FacesMessage message = new FacesMessage();
			message.setSummary("Matricola non valida");
			message.setDetail("La matricola deve essere esattamente di 5 cifre");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
