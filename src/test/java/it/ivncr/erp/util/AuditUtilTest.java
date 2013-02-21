package it.ivncr.erp.util;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.Indirizzo;

import org.junit.Test;

public class AuditUtilTest {

	@Test
	public void testLogCliente() {

		Divisa divisa = new Divisa();
		divisa.setId(1);
		divisa.setDescrizione("EUR");

		Cliente before = new Cliente();
		before.setId(1);
		before.setCodice("C000001");
		before.setRagioneSociale("Pippo");
		before.setDivisa(divisa);

		Object after = null;

		AuditUtil.log(before, after);
	}

	@Test
	public void testLogIndirizzo() {

		Divisa divisa = new Divisa();
		divisa.setId(1);
		divisa.setDescrizione("EUR");

		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setCodice("C000001");
		cliente.setRagioneSociale("Pippo");
		cliente.setDivisa(divisa);

		Indirizzo before = new Indirizzo();
		before.setCliente(cliente);
		before.setId(1);
		before.setDestinatario1("Pluto");

		Object after = null;

		AuditUtil.log(before, after);
	}
}
