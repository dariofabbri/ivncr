package it.ivncr.erp.util;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.Indirizzo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

	@Test
	public void testLogRuolo() {

		Set<Utente> utenti = new HashSet<Utente>();
		
		Utente utente = new Utente();
		utente.setId(1);
		utente.setNome("nome #1");
		utente.setCognome("cognome #1");
		utenti.add(utente);
		
		utente = new Utente();
		utente.setId(2);
		utente.setNome("nome #2");
		utente.setCognome("cognome #2");
		utente.setUltimoLogin(new Date());
		utenti.add(utente);
		
		
		Set<Permesso> permessi = new HashSet<Permesso>();
		
		Permesso permesso = new Permesso();
		permesso.setId(1);
		permesso.setPermesso("permesso #1");
		permesso.setDescrizione("descrizione #1");
		permessi.add(permesso);

		permesso = new Permesso();
		permesso.setId(2);
		permesso.setPermesso("permesso #2");
		permesso.setDescrizione("descrizione #2");
		permessi.add(permesso);
		
		Ruolo before = new Ruolo();
		before.setId(1);
		before.setNome("nome #1");
		before.setDescrizione("descrizione #1");
		before.setUtenti(utenti);
		before.setPermessi(permessi);
		
		Object after = null;

		AuditUtil.log(before, after);
	}
}
