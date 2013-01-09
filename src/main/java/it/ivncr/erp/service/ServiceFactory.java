package it.ivncr.erp.service;

import it.ivncr.erp.service.permesso.PermessoService;
import it.ivncr.erp.service.permesso.PermessoServiceImpl;
import it.ivncr.erp.service.ruolo.RuoloService;
import it.ivncr.erp.service.ruolo.RuoloServiceImpl;
import it.ivncr.erp.service.security.SecurityService;
import it.ivncr.erp.service.security.SecurityServiceImpl;
import it.ivncr.erp.service.utente.UtenteService;
import it.ivncr.erp.service.utente.UtenteServiceImpl;

public class ServiceFactory {
	
	public static SecurityService createSecurityService() {
		
		SecurityService service = SessionDecorator.<SecurityService>createProxy(new SecurityServiceImpl(), SecurityService.class);
		return service;
	}
	
	public static UtenteService createUtenteService() {
		
		UtenteService service = SessionDecorator.<UtenteService>createProxy(new UtenteServiceImpl(), UtenteService.class);
		return service;
	}
	
	public static RuoloService createRuoloService() {
		
		RuoloService service = SessionDecorator.<RuoloService>createProxy(new RuoloServiceImpl(), RuoloService.class);
		return service;
	}
	
	public static PermessoService createPermessoService() {
		
		PermessoService service = SessionDecorator.<PermessoService>createProxy(new PermessoServiceImpl(), PermessoService.class);
		return service;
	}
}