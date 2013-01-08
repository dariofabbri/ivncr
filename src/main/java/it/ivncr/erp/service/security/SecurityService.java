package it.ivncr.erp.service.security;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.Service;

import java.util.List;

public interface SecurityService extends Service {

	Utente getByUsername(String username);

	List<Ruolo> getRoles(String username);

	List<Permesso> getPermissions(String username);
}