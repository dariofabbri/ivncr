package it.ivncr.erp.service;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.ruolo.RuoloService;

import java.util.List;

import org.junit.Test;

public class RuoloServiceTest extends BaseServiceTest {
	
	@Test
	public void testCreate() {
		
		RuoloService rs = ServiceFactory.createService("Ruolo");
		
		Ruolo ruolo = rs.create("testrole", "Ruolo di test");
		
		List<Permesso> permessi = rs.retrievePermessiNotAssociated(ruolo.getId());

		Integer[] permessiId = new Integer[permessi.size()];
		for(int i = 0; i < permessi.size(); ++i) {
			permessiId[i] = permessi.get(i).getId();
		}
		
		rs.addPermessi(ruolo.getId(), permessiId);
		
		rs.deletePermesso(ruolo.getId(), permessiId[0]);
		
		rs.deletePermessi(ruolo.getId(), new Integer[] {permessiId[1], permessiId[2], permessiId[3]});
		
		rs.addPermesso(ruolo.getId(), permessiId[0]);
		
		rs.addPermessi(ruolo.getId(), new Integer[] {permessiId[1], permessiId[2], permessiId[3]});
		
		rs.delete(ruolo.getId());
	}
}
