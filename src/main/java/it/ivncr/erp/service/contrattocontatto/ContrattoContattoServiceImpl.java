package it.ivncr.erp.service.contrattocontatto;

import it.ivncr.erp.model.commerciale.cliente.Contatto;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.ContrattoContatto;
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

public class ContrattoContattoServiceImpl extends AbstractService implements ContrattoContattoService {

	@Override
	public QueryResult<ContrattoContatto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public ContrattoContatto retrieve(Integer id) {

		ContrattoContatto contatto = (ContrattoContatto)session.get(ContrattoContatto.class, id);
		logger.debug("Contratto contatto found: " + contatto);

		return contatto;
	}

	@Override
	public ContrattoContatto retrieveDeep(Integer id) {

		String hql =
				"from ContrattoContatto coc " +
				"left join fetch coc.contratto ctr " +
				"left join fetch coc.contatto cnt " +
				"where coc.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ContrattoContatto contatto = (ContrattoContatto)query.uniqueResult();
		logger.debug("Contratto contatto found: " + contatto);

		return contatto;
	}

	@Override
	public ContrattoContatto create(
			Integer codiceContratto,
			Integer codiceContatto,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		Contatto contatto = (Contatto)session.get(Contatto.class, codiceContatto);

		// Create the new entity.
		//
		ContrattoContatto entity = new ContrattoContatto();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setContatto(contatto);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Contratto contatto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public ContrattoContatto update(
			Integer id,
			Integer codiceContatto,
			Date validoDa,
			Date validoA) {

		ContrattoContatto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified contratto contatto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		Contatto contatto = (Contatto)session.get(Contatto.class, codiceContatto);

		// Set entity fields.
		//
		entity.setContatto(contatto);
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

		ContrattoContatto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified contratto contatto: %d", id);
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
	public List<ContrattoContatto> listByContratto(Integer codiceContratto) {

		String hql =
				"from ContrattoContatto coc " +
				"left join fetch coc.contratto ctr " +
				"left join fetch coc.contatto cnt " +
				"where coc.contratto.id = :codiceContratto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<ContrattoContatto> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}