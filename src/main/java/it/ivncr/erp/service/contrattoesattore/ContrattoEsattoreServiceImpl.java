package it.ivncr.erp.service.contrattoesattore;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.ContrattoEsattore;
import it.ivncr.erp.model.commerciale.contratto.Esattore;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class ContrattoEsattoreServiceImpl extends AbstractService implements ContrattoEsattoreService {

	@Override
	public QueryResult<ContrattoEsattore> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public ContrattoEsattore retrieve(Integer id) {

		ContrattoEsattore esattore = (ContrattoEsattore)session.get(ContrattoEsattore.class, id);
		logger.debug("Contratto esattore found: " + esattore);

		return esattore;
	}

	@Override
	public ContrattoEsattore retrieveDeep(Integer id) {

		String hql =
				"from ContrattoEsattore coe " +
				"left join fetch coe.contratto ctr " +
				"left join fetch coe.esattore esa " +
				"where coe.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ContrattoEsattore esattore = (ContrattoEsattore)query.uniqueResult();
		logger.debug("Contratto esattore found: " + esattore);

		return esattore;
	}

	@Override
	public ContrattoEsattore create(
			Integer codiceContratto,
			Integer codiceEsattore,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		Esattore esattore = (Esattore)session.get(Esattore.class, codiceEsattore);

		// Create the new entity.
		//
		ContrattoEsattore entity = new ContrattoEsattore();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setEsattore(esattore);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Contratto esattore successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public ContrattoEsattore update(
			Integer id,
			Integer codiceEsattore,
			Date validoDa,
			Date validoA) {

		ContrattoEsattore entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified contratto esattore: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		Esattore esattore = (Esattore)session.get(Esattore.class, codiceEsattore);

		// Set entity fields.
		//
		entity.setEsattore(esattore);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		ContrattoEsattore entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified contratto esattore: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ContrattoEsattore> listByContratto(Integer codiceContratto) {

		String hql =
				"from ContrattoEsattore coe " +
				"left join fetch coe.contratto ctr " +
				"left join fetch coe.esattore esa " +
				"where coe.contratto.id = :codiceContratto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<ContrattoEsattore> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}