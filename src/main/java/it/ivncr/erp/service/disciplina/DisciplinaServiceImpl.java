package it.ivncr.erp.service.disciplina;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Disciplina;
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

public class DisciplinaServiceImpl extends AbstractService implements DisciplinaService {

	@Override
	public QueryResult<Disciplina> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Disciplina retrieve(Integer id) {

		Disciplina disciplina = (Disciplina)session.get(Disciplina.class, id);
		logger.debug("Disciplina found: " + disciplina);

		return disciplina;
	}

	@Override
	public Disciplina create(
			Integer codiceAddetto,
			String provvedimento,
			Date dataProvvedimento,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		Disciplina entity = new Disciplina();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setProvvedimento(provvedimento);
		entity.setDataProvvedimento(dataProvvedimento);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Sistema lavoro successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Disciplina update(
			Integer id,
			String provvedimento,
			Date dataProvvedimento,
			String note) {

		Disciplina entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified disciplina: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setProvvedimento(provvedimento);
		entity.setDataProvvedimento(dataProvvedimento);
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

		Disciplina entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified disciplina: %d", id);
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
	public List<Disciplina> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from Disciplina dis " +
				"where dis.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<Disciplina> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}