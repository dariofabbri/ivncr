package it.ivncr.erp.service.contrattogestore;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.ContrattoGestore;
import it.ivncr.erp.model.commerciale.contratto.GestoreContratto;
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

public class ContrattoGestoreServiceImpl extends AbstractService implements ContrattoGestoreService {

	@Override
	public QueryResult<ContrattoGestore> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public ContrattoGestore retrieve(Integer id) {

		ContrattoGestore gestore = (ContrattoGestore)session.get(ContrattoGestore.class, id);
		logger.debug("Contratto gestore found: " + gestore);

		return gestore;
	}

	@Override
	public ContrattoGestore retrieveDeep(Integer id) {

		String hql =
				"from ContrattoGestore cog " +
				"left join fetch cog.contratto ctr " +
				"left join fetch cog.gestore ges " +
				"where cog.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ContrattoGestore gestore = (ContrattoGestore)query.uniqueResult();
		logger.debug("Contratto gestore found: " + gestore);

		return gestore;
	}

	@Override
	public ContrattoGestore create(
			Integer codiceContratto,
			Integer codiceGestore,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		GestoreContratto gestore = (GestoreContratto)session.get(GestoreContratto.class, codiceGestore);

		// Create the new entity.
		//
		ContrattoGestore entity = new ContrattoGestore();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setGestore(gestore);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Contratto gestore successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public ContrattoGestore update(
			Integer id,
			Integer codiceGestore,
			Date validoDa,
			Date validoA) {

		ContrattoGestore entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified contratto gestore: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		GestoreContratto gestore = (GestoreContratto)session.get(GestoreContratto.class, codiceGestore);

		// Set entity fields.
		//
		entity.setGestore(gestore);
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

		ContrattoGestore entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified contratto gestore: %d", id);
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
	public List<ContrattoGestore> listByContratto(Integer codiceContratto) {

		String hql =
				"from ContrattoGestore cog " +
				"left join fetch cog.contratto ctr " +
				"left join fetch cog.gestore ges " +
				"where cog.contratto.id = :codiceContratto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<ContrattoGestore> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}