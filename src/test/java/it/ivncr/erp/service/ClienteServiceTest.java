package it.ivncr.erp.service;

import it.ivncr.erp.service.cliente.ClienteService;

import org.junit.Assert;
import org.junit.Test;

public class ClienteServiceTest extends BaseServiceTest {

	@Test
	public void testRetrieveNextCodiceAppend() {

		ClienteService cs = ServiceFactory.createService("Cliente");

		String codice = cs.retrieveNextCodiceAppend();
		Assert.assertNotNull(codice);

		System.out.println(codice);
	}

	@Test
	public void testRetrieveNextCodice() {

		ClienteService cs = ServiceFactory.createService("Cliente");

		String[] codici = cs.retrieveNextCodice();
		Assert.assertNotNull(codici);
		Assert.assertTrue(codici.length == 2);

		System.out.println(codici[0]);
		System.out.println(codici[1]);
	}
}
