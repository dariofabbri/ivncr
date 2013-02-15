package it.ivncr.erp.jsf.converter;

import it.ivncr.erp.model.generale.Provincia;
import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.lut.LUTService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="provinciaConverter")
public class ProvinciaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		LUTService lutService = ServiceFactory.createService("LUT");

		// Find specified item by sigla.
		//
		Provincia provincia = lutService.retrieveItemByDescrizione("Provincia", "sigla", value);
		return provincia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		if(object instanceof String) {
			return (String)object;
		}
		
		Provincia provincia = (Provincia)object;
		return provincia.getSigla();
	}

}
