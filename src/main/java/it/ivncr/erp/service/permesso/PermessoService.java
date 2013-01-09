package it.ivncr.erp.service.permesso;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

public interface PermessoService extends EntityService<Permesso> {

	QueryResult<Permesso> list(
			String permesso,
			String descrizione,
			Integer offset,
			Integer limit);

	Permesso retrieve(Integer id);

	void delete(Integer id);

	Permesso create(String permesso, String descrizione);

	Permesso update(Integer id, String permesso, String descrizione);
}