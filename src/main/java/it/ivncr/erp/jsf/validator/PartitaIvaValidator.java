package it.ivncr.erp.jsf.validator;

import it.ivncr.erp.util.ValidationUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator(value="partitaIvaValidator")
public class PartitaIvaValidator implements Validator {

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
		if(!ValidationUtil.isPartitaIva(s)) {

			FacesMessage message = new FacesMessage();
			message.setSummary("Partita IVA non valida");
			message.setDetail("Il codice di partita IVA inserito non è valido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
