package it.ivncr.erp.service.infosindacali;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.CaricaSindacale;
import it.ivncr.erp.model.personale.InfoSindacali;
import it.ivncr.erp.model.personale.SiglaSindacale;
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

public class InfoSindacaliServiceImpl extends AbstractService implements InfoSindacaliService {

	@Override
	public QueryResult<InfoSindacali> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public InfoSindacali retrieve(Integer id) {

		InfoSindacali infoSindacali = (InfoSindacali)session.get(InfoSindacali.class, id);
		logger.debug("Informazioni sindacali found: " + infoSindacali);

		return infoSindacali;
	}

	@Override
	public InfoSindacali retrieveDeep(Integer id) {

		String hql =
				"from InfoSindacali ins " +
				"left join fetch ins.siglaSindacale sis " +
				"left join fetch ins.caricaSindacale cas " +
				"where ins.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		InfoSindacali infoSindacali = (InfoSindacali)query.uniqueResult();
		logger.debug("Informazioni sindacali found: " + infoSindacali);

		return infoSindacali;
	}

	@Override
	public InfoSindacali create(
			Integer codiceAddetto,
			Integer codiceSiglaSindacale,
			Integer codiceCaricaSindacale,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		SiglaSindacale siglaSindacale = (SiglaSindacale)session.get(SiglaSindacale.class, codiceSiglaSindacale);
		CaricaSindacale caricaSindacale = (CaricaSindacale)session.get(CaricaSindacale.class, codiceCaricaSindacale);

		// Create the new entity.
		//
		InfoSindacali entity = new InfoSindacali();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setSiglaSindacale(siglaSindacale);
		entity.setCaricaSindacale(caricaSindacale);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Posizione lavorativa successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public InfoSindacali update(
			Integer id,
			Integer codiceSiglaSindacale,
			Integer codiceCaricaSindacale,
			Date validoDa,
			Date validoA) {

		InfoSindacali entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified informazionji sindacali: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		SiglaSindacale siglaSindacale = (SiglaSindacale)session.get(SiglaSindacale.class, codiceSiglaSindacale);
		CaricaSindacale caricaSindacale = (CaricaSindacale)session.get(CaricaSindacale.class, codiceCaricaSindacale);

		// Set entity fields.
		//
		entity.setSiglaSindacale(siglaSindacale);
		entity.setCaricaSindacale(caricaSindacale);
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

		InfoSindacali entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified informazionji sindacali: %d", id);
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
	public List<InfoSindacali> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from InfoSindacali ins " +
				"left join fetch ins.siglaSindacale sis " +
				"left join fetch ins.caricaSindacale cas " +
				"where ins.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<InfoSindacali> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}