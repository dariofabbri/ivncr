package it.ivncr.erp.service;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.utente.UtenteService;

import org.junit.Assert;
import org.junit.Test;

public class UtenteServiceTest extends BaseServiceTest {
	
	@Test
	public void testCreate() {
		
		UtenteService us = ServiceFactory.createService("Utente");
		
		Utente utente = us.create(
				"gino", 
				"password", 
				"Gino", 
				"Pilota", 
				"Utente di prova");
		Assert.assertNotNull(utente);
		System.out.println(utente.getId());

		Ruolo ruolo = us.addRuolo(utente.getId(), 1);
        Assert.assertNotNull(ruolo);
	}
	
	@Test
	public void testDelete() {
		
		UtenteService us = ServiceFactory.createService("Utente");
		
		Utente utente = us.retrieveByUsername("gino");
		Assert.assertNotNull(utente);
		
		System.out.println(utente.getId());
		
		us.delete(utente.getId());
	}
}
