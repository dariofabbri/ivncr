package it.ivncr.erp.service.addettoreparto;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.AddettoReparto;
import it.ivncr.erp.model.personale.Reparto;
import it.ivncr.erp.model.personale.RuoloAziendale;
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

public class AddettoRepartoServiceImpl extends AbstractService implements AddettoRepartoService {

	@Override
	public QueryResult<AddettoReparto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public AddettoReparto retrieve(Integer id) {

		AddettoReparto addettoReparto = (AddettoReparto)session.get(AddettoReparto.class, id);
		logger.debug("Addetto reparto found: " + addettoReparto);

		return addettoReparto;
	}

	@Override
	public AddettoReparto retrieveDeep(Integer id) {

		String hql =
				"from AddettoReparto adr " +
				"left join fetch adr.reparto rep " +
				"left join fetch adr.ruolo ruo " +
				"where adr.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		AddettoReparto addettoReparto = (AddettoReparto)query.uniqueResult();
		logger.debug("Addetto reparto found: " + addettoReparto);

		return addettoReparto;
	}

	@Override
	public AddettoReparto create(
			Integer codiceAddetto,
			Integer codiceReparto,
			Integer codiceRuolo,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		Reparto reparto = (Reparto)session.get(Reparto.class, codiceReparto);
		RuoloAziendale ruolo = (RuoloAziendale)session.get(RuoloAziendale.class, codiceRuolo);

		// Create the new entity.
		//
		AddettoReparto entity = new AddettoReparto();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setReparto(reparto);
		entity.setRuolo(ruolo);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Addetto reparto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public AddettoReparto update(
			Integer id,
			Integer codiceReparto,
			Integer codiceRuolo,
			Date validoDa,
			Date validoA) {

		AddettoReparto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified addetto reparto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		Reparto reparto = (Reparto)session.get(Reparto.class, codiceReparto);
		RuoloAziendale ruolo = (RuoloAziendale)session.get(RuoloAziendale.class, codiceRuolo);

		// Set entity fields.
		//
		entity.setReparto(reparto);
		entity.setRuolo(ruolo);
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

		AddettoReparto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified addetto reparto: %d", id);
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
	public List<AddettoReparto> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from AddettoReparto adr " +
				"left join fetch adr.reparto rep " +
				"left join fetch adr.ruolo ruo " +
				"where adr.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<AddettoReparto> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}