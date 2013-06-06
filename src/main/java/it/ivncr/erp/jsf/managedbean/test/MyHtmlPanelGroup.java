package it.ivncr.erp.jsf.managedbean.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class MyHtmlPanelGroup extends HtmlPanelGroup implements
		ClientBehaviorHolder {

	private static final Collection<String> EVENT_NAMES = Collections
			.unmodifiableCollection(Arrays.asList("blur", "change",
					"valueChange", "click", "dblclick", "focus", "keydown",
					"keypress", "keyup", "mousedown", "mousemove", "mouseout",
					"mouseover", "mouseup", "select"));

	@Override
	public Collection<String> getEventNames() {
		return EVENT_NAMES;
	}

	@Override
	public String getDefaultEventName() {
		return "click";
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {

		super.encodeBegin(context);

		ClientBehaviorContext behaviorContext = ClientBehaviorContext
				.createClientBehaviorContext(context, this, "click",
						getClientId(context), null);

		ResponseWriter responseWriter = context.getResponseWriter();

		Map<String, List<ClientBehavior>> behaviors = getClientBehaviors();
		if (behaviors.containsKey("click")) {
			String click = behaviors.get("click").get(0)
					.getScript(behaviorContext);
			responseWriter.writeAttribute("onclick", click, null);
		}

		responseWriter.writeAttribute("id", getClientId(context), "id");
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		// TODO Auto-generated method stub
		super.encodeEnd(context);
	}
}
