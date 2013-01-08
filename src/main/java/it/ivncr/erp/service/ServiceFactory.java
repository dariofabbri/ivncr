package it.ivncr.erp.service;

import it.ivncr.erp.service.utente.UtenteService;
import it.ivncr.erp.service.utente.UtenteServiceImpl;

public class ServiceFactory {
	
	public static UtenteService createUtenteService() {
		
		UtenteService service = SessionDecorator.<UtenteService>createProxy(new UtenteServiceImpl(), UtenteService.class);
		return service;
	}
}