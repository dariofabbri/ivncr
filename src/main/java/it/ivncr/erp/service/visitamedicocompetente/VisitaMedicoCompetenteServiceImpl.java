package it.ivncr.erp.service.visitamedicocompetente;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.VisitaMedicoCompetente;
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

public class VisitaMedicoCompetenteServiceImpl extends AbstractService implements VisitaMedicoCompetenteService {

	@Override
	public QueryResult<VisitaMedicoCompetente> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public VisitaMedicoCompetente retrieve(Integer id) {

		VisitaMedicoCompetente visita = (VisitaMedicoCompetente)session.get(VisitaMedicoCompetente.class, id);
		logger.debug("Visita medico competente found: " + visita);

		return visita;
	}

	@Override
	public VisitaMedicoCompetente create(
			Integer codiceAddetto,
			String medico,
			Date dataVisita,
			String esito,
			Date dataVisitaSuccessiva) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		VisitaMedicoCompetente entity = new VisitaMedicoCompetente();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setMedico(medico);
		entity.setDataVisita(dataVisita);
		entity.setEsito(esito);
		entity.setDataVisitaSuccessiva(dataVisitaSuccessiva);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Visita medico competente successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public VisitaMedicoCompetente update(
			Integer id,
			String medico,
			Date dataVisita,
			String esito,
			Date dataVisitaSuccessiva) {

		VisitaMedicoCompetente entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified visita medico competente: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setMedico(medico);
		entity.setDataVisita(dataVisita);
		entity.setEsito(esito);
		entity.setDataVisitaSuccessiva(dataVisitaSuccessiva);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		VisitaMedicoCompetente entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified visita medico competente: %d", id);
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
	public List<VisitaMedicoCompetente> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from VisitaMedicoCompetente vmc " +
				"where vmc.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<VisitaMedicoCompetente> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}