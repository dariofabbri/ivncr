package it.ivncr.erp.service;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.service.lut.LUTService;
import it.ivncr.erp.service.utente.UtenteService;

import java.util.List;

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

        LUTService ls = ServiceFactory.createService("LUT");
        List<Azienda> list = ls.listItems("Azienda");
        Integer[] aziendeId = new Integer[list.size()];
        for(int i = 0; i < list.size(); ++i) {
        	aziendeId[i] = list.get(i).getId();
        }
        us.setAziende(utente.getId(), aziendeId, null);
	}

	@Test
	public void testDelete() {

		UtenteService us = ServiceFactory.createService("Utente");

		Utente utente = us.retrieveByUsername("gino");
		Assert.assertNotNull(utente);

		System.out.println(utente.getId());

		us.delete(utente.getId());
	}

	@Test
	public void testRetrieveDefaultAzienda() {

		UtenteService us = ServiceFactory.createService("Utente");

		Utente utente = us.retrieveByUsername("gino");
		Assert.assertNotNull(utente);

		Azienda azienda = us.retrieveDefaultAzienda(utente.getId(), true);
		Assert.assertNotNull(azienda);

		System.out.println(azienda);
	}
}
