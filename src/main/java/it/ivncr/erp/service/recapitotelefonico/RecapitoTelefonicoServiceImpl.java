package it.ivncr.erp.service.recapitotelefonico;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.RecapitoTelefonico;
import it.ivncr.erp.model.personale.TipoRecapitoTelefonico;
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

public class RecapitoTelefonicoServiceImpl extends AbstractService implements RecapitoTelefonicoService {

	@Override
	public QueryResult<RecapitoTelefonico> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public RecapitoTelefonico retrieve(Integer id) {

		RecapitoTelefonico recapitoTelefonico = (RecapitoTelefonico)session.get(RecapitoTelefonico.class, id);
		logger.debug("Recapito telefonico found: " + recapitoTelefonico);

		return recapitoTelefonico;
	}

	@Override
	public RecapitoTelefonico retrieveDeep(Integer id) {

		String hql =
				"from RecapitoTelefonico rte " +
				"left join fetch rte.tipoRecapitoTelefonico trt " +
				"where rte.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		RecapitoTelefonico recapitoTelefonico = (RecapitoTelefonico)query.uniqueResult();
		logger.debug("Recapito telefonico found: " + recapitoTelefonico);

		return recapitoTelefonico;
	}

	@Override
	public RecapitoTelefonico create(
			Integer codiceAddetto,
			Integer codiceTipoRecapitoTelefonico,
			String recapito) {

		// Fetch referred entities.
		//
		TipoRecapitoTelefonico tipoRecapitoTelefonico = (TipoRecapitoTelefonico)session.get(TipoRecapitoTelefonico.class, codiceTipoRecapitoTelefonico);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		RecapitoTelefonico entity = new RecapitoTelefonico();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoRecapitoTelefonico(tipoRecapitoTelefonico);
		entity.setRecapito(recapito);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Recapito telefonico successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public RecapitoTelefonico update(
			Integer id,
			Integer codiceTipoRecapitoTelefonico,
			String recapito) {

		RecapitoTelefonico entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified recapito telefonico: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoRecapitoTelefonico tipoRecapitoTelefonico = (TipoRecapitoTelefonico)session.get(TipoRecapitoTelefonico.class, codiceTipoRecapitoTelefonico);

		// Set entity fields.
		//
		entity.setTipoRecapitoTelefonico(tipoRecapitoTelefonico);
		entity.setRecapito(recapito);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		RecapitoTelefonico entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified recapito telefonico: %d", id);
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
	public List<RecapitoTelefonico> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from RecapitoTelefonico rte " +
				"left join fetch rte.tipoRecapitoTelefonico trt " +
				"where rte.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<RecapitoTelefonico> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}