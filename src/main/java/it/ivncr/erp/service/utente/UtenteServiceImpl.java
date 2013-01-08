package it.ivncr.erp.service.utente;

import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Query;

public class UtenteServiceImpl extends AbstractService implements UtenteService {

	private static final int HASH_ITERATIONS = 10000;
	
	@Override
	public QueryResult<Utente> list(
			Integer matricola,
			String username,
			String nome,
			String cognome,
			String tipoAccount,
			Integer offset,
			Integer limit) {

		QueryUtenteByMatricolaUsernameNomeCognomeTipoAccount q = new QueryUtenteByMatricolaUsernameNomeCognomeTipoAccount(session);

		q.setMatricola(matricola);
		q.setUsername(username);
		q.setNome(nome);
		q.setCognome(cognome);
		q.setTipoAccount(tipoAccount);
		q.setOffset(offset);
		q.setLimit(limit);
		
		QueryResult<Utente> result = q.query();
		logger.debug("Query returned: " + result);
		
		return result;
	}

	@Override
	public QueryResult<Utente> list(
			int first, 
			int pageSize,
			String sortCriteria, 
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryUtenteByMatricolaUsernameNomeCognomeTipoAccount q = new QueryUtenteByMatricolaUsernameNomeCognomeTipoAccount(session);

		Integer matricola = null;
		if(filters.get("matricola") != null)
			matricola = Integer.decode(filters.get("matricola"));
		
		String username = filters.get("username");
		String nome = filters.get("nome");
		String cognome = filters.get("cognome");
		String tipoAccount = filters.get("tipoAccount");
		
		q.setMatricola(matricola);
		q.setUsername(username);
		q.setNome(nome);
		q.setCognome(cognome);
		q.setTipoAccount(tipoAccount);
		
		q.setOffset(first);
		q.setLimit(pageSize);
		
		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);
		
		QueryResult<Utente> result = q.query();
		logger.debug("Query returned: " + result);
		
		return result;
	}
	
	@Override
	public Utente retrieveByMatricola(Integer matricola) {

		Utente utente = (Utente)session.get(Utente.class, matricola);
		logger.debug("Utente found: " + utente);
		
		return utente;
	}

	@Override
	public Utente retrieveByUsername(String username) {

		String hql = 
				"from Utente ute " +
				"where ute.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		Utente utente = (Utente)query.uniqueResult();
		logger.debug("Utente found: " + utente);
		
		return utente;
	}

	@Override
	public void deleteByMatricola(Integer matricola) {
		
		Utente utente = retrieveByMatricola(matricola);
		if(utente == null) {
			String message = String.format("It has not been possible to retrieve specified user: %d", matricola);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		session.delete(utente);
		logger.debug("Utente successfully deleted.");
	}

	@Override
	public Utente create(
			Integer matricola,
			String username,
			String password,
			String nome, 
			String cognome, 
			String tipoAccount) {
		
		Utente utente = new Utente();
		
		utente.setMatricola(matricola);
		utente.setUsername(username);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setTipoAccount(tipoAccount);
		
		String salt = generateSalt();
		String digest = generateDigest(password, salt, HASH_ITERATIONS);

		utente.setDigest(digest);
		utente.setSalt(salt);
		utente.setIterations(HASH_ITERATIONS);
		
		session.save(utente);
		logger.debug("Utente successfully created.");
		
		return utente;
	}

	@Override
	public Utente update(
			Integer matricola, 
			String username,
			String nome, 
			String cognome, 
			String tipoAccount) {

		Utente utente = retrieveByMatricola(matricola);
		if(utente == null) {
			String message = String.format("It has not been possible to retrieve specified user: %d", matricola);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		utente.setUsername(username);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setTipoAccount(tipoAccount);
		
		session.update(utente);
		logger.debug("Utente successfully updated.");
		
		return utente;
	}
	
	@Override
	public Utente changePassword(Integer matricola, String password) {

		Utente utente = retrieveByMatricola(matricola);
		if(utente == null) {
			String message = String.format("It has not been possible to retrieve specified user: %d", matricola);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		String salt = generateSalt();
		String digest = generateDigest(password, salt, HASH_ITERATIONS);

		utente.setDigest(digest);
		utente.setSalt(salt);
		utente.setIterations(HASH_ITERATIONS);
		
		session.update(utente);
		logger.debug("Password successfully changed.");
		
		return utente;
	}
	
	
	private String generateSalt() {
		
		SecureRandomNumberGenerator srng = new SecureRandomNumberGenerator();
		String salt = srng.nextBytes(64).toHex();

		return salt;
	}
	
	
	private String generateDigest(String password, String salt, int iterations) {
		
		String hashed = new SimpleHash("SHA-512", password, salt, iterations).toHex();
		return hashed;		
	}
}