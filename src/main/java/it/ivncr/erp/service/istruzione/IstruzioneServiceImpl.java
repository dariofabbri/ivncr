package it.ivncr.erp.service.istruzione;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Istruzione;
import it.ivncr.erp.model.personale.TitoloStudio;
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

public class IstruzioneServiceImpl extends AbstractService implements IstruzioneService {

	@Override
	public QueryResult<Istruzione> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Istruzione retrieve(Integer id) {

		Istruzione istruzione = (Istruzione)session.get(Istruzione.class, id);
		logger.debug("Istruzione found: " + istruzione);

		return istruzione;
	}

	@Override
	public Istruzione retrieveDeep(Integer id) {

		String hql =
				"from Istruzione ist " +
				"left join fetch ist.titoloStudio tst " +
				"where ist.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Istruzione istruzione = (Istruzione)query.uniqueResult();
		logger.debug("Istruzione found: " + istruzione);

		return istruzione;
	}

	@Override
	public Istruzione create(
			Integer codiceAddetto,
			Integer codiceTitoloStudio,
			Date dataConseguimento,
			String presso) {

		// Fetch referred entities.
		//
		TitoloStudio titoloStudio = (TitoloStudio)session.get(TitoloStudio.class, codiceTitoloStudio);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		Istruzione entity = new Istruzione();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTitoloStudio(titoloStudio);
		entity.setDataConseguimento(dataConseguimento);
		entity.setPresso(presso);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Istruzione successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Istruzione update(
			Integer id,
			Integer codiceTitoloStudio,
			Date dataConseguimento,
			String presso) {

		Istruzione entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified istruzione: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TitoloStudio titoloStudio = (TitoloStudio)session.get(TitoloStudio.class, codiceTitoloStudio);

		// Set entity fields.
		//
		entity.setTitoloStudio(titoloStudio);
		entity.setDataConseguimento(dataConseguimento);
		entity.setPresso(presso);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		Istruzione entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified istruzione: %d", id);
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
	public List<Istruzione> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from Istruzione ist " +
				"left join fetch ist.titoloStudio tst " +
				"where ist.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<Istruzione> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}