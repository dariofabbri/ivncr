package it.ivncr.erp.jsf.managedbean.application;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(eager=true)
@ApplicationScoped
public class AppConfig {

	String timeZone;

	public AppConfig() {
		
		timeZone = "Europe/Rome";
	}
	
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}
