package it.ivncr.erp.service.utente;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.List;

public interface UtenteService extends EntityService<Utente> {

	QueryResult<Utente> list(
			Integer id,
			String username,
			String nome,
			String cognome,
			String note,
			Boolean attivo,
			Integer offset,
			Integer limit);

	Utente retrieve(Integer id);
	Utente retrieveByUsername(String username);

	void delete(Integer id);

	Utente create(
			String username, 
			String password, 
			String nome, 
			String cognome,
			String note);

	Utente update(
			Integer id, 
			String username, 
			String nome, 
			String cognome, 
			String note);

	Utente changePassword(
			Integer id, 
			String password);
	
	Utente updateLastLogonTimestamp(Integer id);

	List<Ruolo> listRuoli(Integer id);
	
	Ruolo addRuolo(Integer utenteId, Integer ruoloId);
	
	void deleteRuolo(Integer utenteId, Integer ruoloId);
		
	void setRuoli(Integer utenteId, Integer[] ruoliId);
}