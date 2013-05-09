package it.ivncr.erp.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter(value="stringShortener")
public class StringShortenerConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {

		String s = null;
		
		if(object instanceof String) {
			s = (String)object;
		} else {
			s = object.toString();
		}

		String length = (String)(component.getAttributes().get("length"));
		if(length == null) {
			return s;
		}
		
		int l = Integer.parseInt(length);
		return StringUtils.abbreviate(s, l);
	}
}
