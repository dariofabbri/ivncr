package it.ivncr.erp.service.permesso;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Map;

public class PermessoServiceImpl extends AbstractService implements PermessoService {

	@Override
	public QueryResult<Permesso> list(
			String permesso,
			String descrizione,
			Integer offset,
			Integer limit) {

		QueryByPermessoDescrizione q = new QueryByPermessoDescrizione(session);

		q.setPermesso(permesso);
		q.setDescrizione(descrizione);
		q.setOffset(offset);
		q.setLimit(limit);
		
		return q.query();
	}

	@Override
	public QueryResult<Permesso> list(
			int first, 
			int pageSize,
			String sortCriteria, 
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByPermessoDescrizione q = new QueryByPermessoDescrizione(session);

		String permesso = filters.get("permesso");
		String descrizione = filters.get("descrizione");

		q.setPermesso(permesso);
		q.setDescrizione(descrizione);
		
		q.setOffset(first);
		q.setLimit(pageSize);
		
		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);
		
		QueryResult<Permesso> result = q.query();
		logger.debug("Query returned: " + result);
		
		return result;
	}

	@Override
	public Permesso retrieve(Integer id) {

		Permesso permesso = (Permesso)session.get(Permesso.class, id);
		logger.debug("Permesso found: " + permesso);
		
		return permesso;
	}

	@Override
	public void delete(Integer id) {
		
		Permesso permesso = retrieve(id);
		if(permesso == null) {
			String message = String.format("It has not been possible to retrieve specified permesso: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		session.delete(permesso);
	}

	@Override
	public Permesso create(
			String permesso,
			String descrizione) {
		
		Permesso p = new Permesso();
		p.setPermesso(permesso);
		p.setDescrizione(descrizione);
		
		session.save(p);
		
		return p;
	}

	@Override
	public Permesso update(
			Integer id, 
			String permesso,
			String descrizione) {
		
		Permesso p = retrieve(id);
		if(p == null) {
			String message = String.format("It has not been possible to retrieve specified permesso: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		p.setPermesso(permesso);
		p.setDescrizione(descrizione);
		
		session.update(p);
		
		return p;
	}
}