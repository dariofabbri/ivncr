package it.ivncr.erp.service.rinnovocontrattuale;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RinnovoContrattuale;
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

public class RinnovoContrattualeServiceImpl extends AbstractService implements RinnovoContrattualeService {

	@Override
	public QueryResult<RinnovoContrattuale> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public RinnovoContrattuale retrieve(Integer id) {

		RinnovoContrattuale rinnovo = (RinnovoContrattuale)session.get(RinnovoContrattuale.class, id);
		logger.debug("Rinnovo contrattuale found: " + rinnovo);

		return rinnovo;
	}

	@Override
	public RinnovoContrattuale create(
			Integer codiceContratto,
			Date dataDecorrenzaPre,
			Date dataTerminePre,
			Date dataDecorrenzaPost,
			Date dataTerminePost,
			String note) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);

		// Create the new entity.
		//
		RinnovoContrattuale entity = new RinnovoContrattuale();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setDataDecorrenzaPre(dataDecorrenzaPre);
		entity.setDataTerminePre(dataTerminePre);
		entity.setDataDecorrenzaPost(dataDecorrenzaPost);
		entity.setDataTerminePost(dataTerminePost);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Rinnovo contrattuale successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public RinnovoContrattuale update(
			Integer id,
			Date dataDecorrenzaPre,
			Date dataTerminePre,
			Date dataDecorrenzaPost,
			Date dataTerminePost,
			String note) {

		RinnovoContrattuale entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified rinnovo contrattuale: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setDataDecorrenzaPre(dataDecorrenzaPre);
		entity.setDataTerminePre(dataTerminePre);
		entity.setDataDecorrenzaPost(dataDecorrenzaPost);
		entity.setDataTerminePost(dataTerminePost);
		entity.setNote(note);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		RinnovoContrattuale entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified rinnovo contrattuale: %d", id);
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
	public List<RinnovoContrattuale> listByContratto(Integer codiceContratto) {

		String hql =
				"from RinnovoContrattuale ric " +
				"where ric.contratto.id = :codiceContratto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<RinnovoContrattuale> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}