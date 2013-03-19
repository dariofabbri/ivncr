package it.ivncr.erp.service.corso;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Corso;
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

public class CorsoServiceImpl extends AbstractService implements CorsoService {

	@Override
	public QueryResult<Corso> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Corso retrieve(Integer id) {

		Corso corso = (Corso)session.get(Corso.class, id);
		logger.debug("Corso found: " + corso);

		return corso;
	}

	@Override
	public Corso create(
			Integer codiceAddetto,
			String ente,
			String abilitazione,
			Integer oreCorso,
			String valutazione,
			Date dataConseguimento,
			Date dataInizio,
			Date dataFine,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		Corso entity = new Corso();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setEnte(ente);
		entity.setAbilitazione(abilitazione);
		entity.setOreCorso(oreCorso);
		entity.setValutazione(valutazione);
		entity.setDataConseguimento(dataConseguimento);
		entity.setDataInizio(dataInizio);
		entity.setDataFine(dataFine);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Corso successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Corso update(
			Integer id,
			String ente,
			String abilitazione,
			Integer oreCorso,
			String valutazione,
			Date dataConseguimento,
			Date dataInizio,
			Date dataFine,
			String note) {

		Corso entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified corso: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setEnte(ente);
		entity.setAbilitazione(abilitazione);
		entity.setOreCorso(oreCorso);
		entity.setValutazione(valutazione);
		entity.setDataConseguimento(dataConseguimento);
		entity.setDataInizio(dataInizio);
		entity.setDataFine(dataFine);
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

		Corso entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified corso: %d", id);
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
	public List<Corso> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from Corso cor " +
				"where cor.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<Corso> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}