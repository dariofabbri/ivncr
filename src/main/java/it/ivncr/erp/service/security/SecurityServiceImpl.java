package it.ivncr.erp.service.security;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.AbstractService;

import java.util.List;

import org.hibernate.Query;

public class SecurityServiceImpl extends AbstractService implements SecurityService {

	public Utente getByUsername(String username) {

		String hql = 
				"from Utente ute " +
				"where ute.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		Utente utente = (Utente)query.uniqueResult();
		logger.debug("Utente found: " + utente);
		
		return utente;
	}

	
	@SuppressWarnings("unchecked")
	public List<Ruolo> getRoles(String username) {

		String hql =
				"select distinct ruo from Ruolo ruo " +
				"left join ruo.utenti ute " +
				"where ute.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Ruolo> roles = query.list();
		logger.debug("Roles found: " + roles);
		
		return roles;
	}

	
	@SuppressWarnings("unchecked")
	public List<Permesso> getPermissions(String username) {

		String hql = 
				"select distinct per from Permesso per " +
				"left join per.ruoli ruo " +
				"left join ruo.utenti ute " +
				"where ute.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Permesso> permissions = query.list();
		logger.debug("Permissions found: " + permissions);
		
		return permissions;
	}
}
