package it.ivncr.erp.service.familiare;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Familiare;
import it.ivncr.erp.model.personale.TipoFamiliare;
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

public class FamiliareServiceImpl extends AbstractService implements FamiliareService {

	@Override
	public QueryResult<Familiare> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Familiare retrieve(Integer id) {

		Familiare familiare = (Familiare)session.get(Familiare.class, id);
		logger.debug("Familiare found: " + familiare);

		return familiare;
	}

	@Override
	public Familiare retrieveDeep(Integer id) {

		String hql =
				"from Familiare fam " +
				"left join fetch fam.tipoFamiliare tfa " +
				"where fam.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Familiare familiare = (Familiare)query.uniqueResult();
		logger.debug("Familiare found: " + familiare);

		return familiare;
	}

	@Override
	public Familiare create(
			Integer codiceAddetto,
			Integer codiceTipoFamiliare,
			String nome,
			String cognome,
			String luogoNascita,
			Date dataNascita,
			String note) {

		// Fetch referred entities.
		//
		TipoFamiliare tipoFamiliare = (TipoFamiliare)session.get(TipoFamiliare.class, codiceTipoFamiliare);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		Familiare entity = new Familiare();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoFamiliare(tipoFamiliare);
		entity.setNome(nome);
		entity.setCognome(cognome);
		entity.setLuogoNascita(luogoNascita);
		entity.setDataNascita(dataNascita);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Familiare successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Familiare update(
			Integer id,
			Integer codiceTipoFamiliare,
			String nome,
			String cognome,
			String luogoNascita,
			Date dataNascita,
			String note) {

		Familiare entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified familiare: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoFamiliare tipoFamiliare = (TipoFamiliare)session.get(TipoFamiliare.class, codiceTipoFamiliare);

		// Set entity fields.
		//
		entity.setTipoFamiliare(tipoFamiliare);
		entity.setNome(nome);
		entity.setCognome(cognome);
		entity.setLuogoNascita(luogoNascita);
		entity.setDataNascita(dataNascita);
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

		Familiare entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified familiare: %d", id);
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
	public List<Familiare> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from Familiare fam " +
				"left join fetch fam.tipoFamiliare tfa " +
				"where fam.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<Familiare> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}