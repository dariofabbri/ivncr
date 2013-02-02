package it.ivncr.erp.service;

import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.service.cliente.ClienteService;
import it.ivncr.erp.service.lut.LUTService;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ClienteServiceTest extends BaseServiceTest {

	@Test
	public void testRetrieveNextCodiceAppend() {

		LUTService ls = ServiceFactory.createService("LUT");
		List<Azienda> list = ls.listItems("Azienda");
		Azienda azienda = list.get(0);

		ClienteService cs = ServiceFactory.createService("Cliente");
		String codice = cs.retrieveNextCodiceAppend(azienda.getId());
		Assert.assertNotNull(codice);

		System.out.println(codice);
	}

	@Test
	public void testRetrieveNextCodice() {

		LUTService ls = ServiceFactory.createService("LUT");
		List<Azienda> list = ls.listItems("Azienda");
		Azienda azienda = list.get(0);

		ClienteService cs = ServiceFactory.createService("Cliente");
		String[] codici = cs.retrieveNextCodice(azienda.getId());
		Assert.assertNotNull(codici);
		Assert.assertTrue(codici.length == 2);

		System.out.println(codici[0]);
		System.out.println(codici[1]);
	}
}
