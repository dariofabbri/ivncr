package it.ivncr.erp.service.linguaconosciuta;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Lingua;
import it.ivncr.erp.model.personale.LinguaConosciuta;
import it.ivncr.erp.model.personale.LivelloLingua;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class LinguaConosciutaServiceImpl extends AbstractService implements LinguaConosciutaService {

	@Override
	public QueryResult<LinguaConosciuta> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public LinguaConosciuta retrieve(Integer id) {

		LinguaConosciuta lingua = (LinguaConosciuta)session.get(LinguaConosciuta.class, id);
		logger.debug("Lingua conosciuta found: " + lingua);

		return lingua;
	}

	@Override
	public LinguaConosciuta retrieveDeep(Integer id) {

		String hql =
				"from LinguaConosciuta lic " +
				"left join fetch lic.lingua lin " +
				"left join fetch lic.livelloLingua lli " +
				"where lic.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LinguaConosciuta lingua = (LinguaConosciuta)query.uniqueResult();
		logger.debug("Lingua conosciuta found: " + lingua);

		return lingua;
	}

	@Override
	public LinguaConosciuta create(
			Integer codiceAddetto,
			Integer codiceLingua,
			Integer codiceLivelloLingua,
			String note) {

		// Fetch referred entities.
		//
		Lingua lingua = (Lingua)session.get(Lingua.class, codiceLingua);
		LivelloLingua livelloLingua = (LivelloLingua)session.get(LivelloLingua.class, codiceLivelloLingua);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		LinguaConosciuta entity = new LinguaConosciuta();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setLingua(lingua);
		entity.setLivelloLingua(livelloLingua);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Lingua conosciuta successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public LinguaConosciuta update(
			Integer id,
			Integer codiceLingua,
			Integer codiceLivelloLingua,
			String note) {

		LinguaConosciuta entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified lingua conosciuta: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		Lingua lingua = (Lingua)session.get(Lingua.class, codiceLingua);
		LivelloLingua livelloLingua = (LivelloLingua)session.get(LivelloLingua.class, codiceLivelloLingua);

		// Set entity fields.
		//
		entity.setLingua(lingua);
		entity.setLivelloLingua(livelloLingua);
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

		LinguaConosciuta entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified lingua conosciuta: %d", id);
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
	public List<LinguaConosciuta> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from LinguaConosciuta lic " +
				"left join fetch lic.lingua lin " +
				"left join fetch lic.livelloLingua lli " +
				"where lic.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<LinguaConosciuta> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}