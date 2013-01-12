package it.ivncr.erp.service.ruolo;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.List;

public interface RuoloService extends EntityService<Ruolo> {

	QueryResult<Ruolo> list(
			String nome,
			String descrizione,
			Integer offset,
			Integer limit);

	Ruolo retrieve(Integer id);
	Ruolo retrieveByNome(String nome);

	void delete(Integer id);

	Ruolo create(String rolename, String description);

	Ruolo update(Integer id, String rolename, String description);

	List<Permesso> retrievePermessi(Integer id);
	
	List<Permesso> retrievePermessiNotAssociated(Integer id);
	
	Permesso addPermesso(Integer ruoloId, Integer permessoId);
	
	void addPermessi(Integer ruoloId, Integer[] permessiId);
	
	void deletePermesso(Integer ruoloId, Integer permessoId);

	void deletePermessi(Integer ruoloId, Integer[] permessiId);
}