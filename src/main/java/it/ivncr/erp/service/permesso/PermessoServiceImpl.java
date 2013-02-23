package it.ivncr.erp.service.permesso;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

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
	public Permesso create(
			String permesso,
			String descrizione) {

		Permesso entity = new Permesso();
		entity.setPermesso(permesso);
		entity.setDescrizione(descrizione);

		session.save(entity);

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}

	@Override
	public Permesso update(
			Integer id,
			String permesso,
			String descrizione) {

		Permesso entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified permesso: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		entity.setPermesso(permesso);
		entity.setDescrizione(descrizione);

		session.update(entity);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}

	@Override
	public void delete(Integer id) {

		Permesso entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified permesso: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}
}