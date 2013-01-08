package it.ivncr.erp.service;

import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.utente.UtenteService;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest extends BaseServiceTest {
	
	@Test
	public void testCreate() {
		
		UtenteService us = ServiceFactory.createUtenteService();
		
		Utente utente = us.create(
				new Random().nextInt(10000),
				"gino", 
				"password", 
				"Gino", 
				"Pilota", 
				"Capoturno");
		Assert.assertNotNull(utente);
		
		System.out.println(utente.getMatricola());
	}
	
	@Test
	public void testDelete() {
		
		UtenteService us = ServiceFactory.createUtenteService();
		
		Utente utente = us.retrieveByUsername("gino");
		Assert.assertNotNull(utente);
		
		System.out.println(utente.getMatricola());
		
		us.deleteByMatricola(utente.getMatricola());
	}
}
