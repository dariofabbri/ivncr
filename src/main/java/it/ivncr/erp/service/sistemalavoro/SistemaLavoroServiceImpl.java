package it.ivncr.erp.service.sistemalavoro;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.SistemaLavoro;
import it.ivncr.erp.model.personale.TipoSistemaLavoro;
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

public class SistemaLavoroServiceImpl extends AbstractService implements SistemaLavoroService {

	@Override
	public QueryResult<SistemaLavoro> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public SistemaLavoro retrieve(Integer id) {

		SistemaLavoro sistemaLavoro = (SistemaLavoro)session.get(SistemaLavoro.class, id);
		logger.debug("Sistema lavoro found: " + sistemaLavoro);

		return sistemaLavoro;
	}

	@Override
	public SistemaLavoro retrieveDeep(Integer id) {

		String hql =
				"from SistemaLavoro sil " +
				"left join fetch sil.tipoSistemaLavoro tsl " +
				"where sil.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		SistemaLavoro sistemaLavoro = (SistemaLavoro)query.uniqueResult();
		logger.debug("Sistema lavoro found: " + sistemaLavoro);

		return sistemaLavoro;
	}

	@Override
	public SistemaLavoro create(
			Integer codiceAddetto,
			Integer codiceTipoSistemaLavoro,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		TipoSistemaLavoro tipoSistemaLavoro = (TipoSistemaLavoro)session.get(TipoSistemaLavoro.class, codiceTipoSistemaLavoro);

		// Create the new entity.
		//
		SistemaLavoro entity = new SistemaLavoro();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoSistemaLavoro(tipoSistemaLavoro);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

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
	public SistemaLavoro update(
			Integer id,
			Integer codiceTipoSistemaLavoro,
			Date validoDa,
			Date validoA) {

		SistemaLavoro entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified sistema lavoro: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoSistemaLavoro tipoSistemaLavoro = (TipoSistemaLavoro)session.get(TipoSistemaLavoro.class, codiceTipoSistemaLavoro);

		// Set entity fields.
		//
		entity.setTipoSistemaLavoro(tipoSistemaLavoro);
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

		SistemaLavoro entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified sistema lavoro: %d", id);
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
	public List<SistemaLavoro> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from SistemaLavoro sil " +
				"left join fetch sil.tipoSistemaLavoro tsl " +
				"where sil.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<SistemaLavoro> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}