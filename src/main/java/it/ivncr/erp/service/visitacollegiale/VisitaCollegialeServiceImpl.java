package it.ivncr.erp.service.visitacollegiale;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.VisitaCollegiale;
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

public class VisitaCollegialeServiceImpl extends AbstractService implements VisitaCollegialeService {

	@Override
	public QueryResult<VisitaCollegiale> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public VisitaCollegiale retrieve(Integer id) {

		VisitaCollegiale visita = (VisitaCollegiale)session.get(VisitaCollegiale.class, id);
		logger.debug("Visita collegiale found: " + visita);

		return visita;
	}

	@Override
	public VisitaCollegiale create(
			Integer codiceAddetto,
			Date dataRichiesta,
			String motivazione,
			Date dataEsito,
			String esito) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		VisitaCollegiale entity = new VisitaCollegiale();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setDataRichiesta(dataRichiesta);
		entity.setMotivazione(motivazione);
		entity.setDataEsito(dataEsito);
		entity.setEsito(esito);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Visita collegiale successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public VisitaCollegiale update(
			Integer id,
			Date dataRichiesta,
			String motivazione,
			Date dataEsito,
			String esito) {

		VisitaCollegiale entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified visita collegiale: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setDataRichiesta(dataRichiesta);
		entity.setMotivazione(motivazione);
		entity.setDataEsito(dataEsito);
		entity.setEsito(esito);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		VisitaCollegiale entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified visita collegiale: %d", id);
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
	public List<VisitaCollegiale> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from VisitaCollegiale vic " +
				"where vic.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<VisitaCollegiale> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}