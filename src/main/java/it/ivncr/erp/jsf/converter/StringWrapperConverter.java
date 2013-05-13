package it.ivncr.erp.jsf.converter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="stringWrapper")
public class StringWrapperConverter implements Converter {

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


		List<String> list = new ArrayList<String>();
		while(s.length() > l) {
			list.add(s.substring(0, l));
			s = s.substring(l);
		}
		if(s.length() > 0) {
			list.add(s);
		}

		StringBuilder sb = new StringBuilder();
		for(String line : list) {

			try {
				line = URLEncoder.encode(line, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("Unsupported encoding detected.", e);
			}

			sb.append(line).append("&lt;br&gt;");
		}

		return sb.toString();
	}
}
