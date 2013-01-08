package it.ivncr.erp.service.utente;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.Service;
import it.ivncr.erp.service.SortDirection;

import java.util.List;
import java.util.Map;

public interface UtenteService extends Service {

	QueryResult<Utente> list(
			Integer id,
			String username,
			String nome,
			String cognome,
			String note,
			Boolean attivo,
			Integer offset,
			Integer limit);

	QueryResult<Utente> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters);

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
	
	List<Ruolo> listRuoli(Integer id);
	
	Ruolo addRuolo(Integer utenteId, Integer ruoloId);
	
	void deleteRuolo(Integer utenteId, Integer ruoloId);
}