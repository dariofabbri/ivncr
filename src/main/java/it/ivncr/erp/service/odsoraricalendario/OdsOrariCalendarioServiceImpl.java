package it.ivncr.erp.service.odsoraricalendario;

import it.ivncr.erp.model.commerciale.contratto.ApparecchiaturaTecnologica;
import it.ivncr.erp.model.commerciale.ods.OdsApparecchiatura;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
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

public class OdsOrariCalendarioServiceImpl extends AbstractService implements OdsOrariCalendarioService {

	@Override
	public QueryResult<OdsApparecchiatura> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public OdsApparecchiatura retrieve(Integer id) {

		OdsApparecchiatura odsApparecchiatura = (OdsApparecchiatura)session.get(OdsApparecchiatura.class, id);
		logger.debug("Ods apparecchiatura found: " + odsApparecchiatura);

		return odsApparecchiatura;
	}

	@Override
	public OdsApparecchiatura retrieveDeep(Integer id) {

		String hql =
				"from OdsApparecchiatura oda " +
				"left join fetch oda.apparecchiaturaTecnologica ate " +
				"left join fetch ate.tipoApparecchiaturaTecnologica tat " +
				"left join fetch tat.gruppoApparecchiatura gra " +
				"where oda.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		OdsApparecchiatura odsApparecchiatura = (OdsApparecchiatura)query.uniqueResult();
		logger.debug("Ods apparecchiatura found: " + odsApparecchiatura);

		return odsApparecchiatura;
	}

	@Override
	public OdsApparecchiatura create(
			Integer codiceOrdineServizio,
			Integer codiceApparecchiaturaTecnologica) {

		// Fetch referred entities.
		//
		OrdineServizio ordineServizio = (OrdineServizio)session.get(OrdineServizio.class, codiceOrdineServizio);
		ApparecchiaturaTecnologica apparecchiaturaTecnologica = (ApparecchiaturaTecnologica)session.get(ApparecchiaturaTecnologica.class, codiceApparecchiaturaTecnologica);

		// Create the new entity.
		//
		OdsApparecchiatura entity = new OdsApparecchiatura();

		// Set entity fields.
		//
		entity.setOrdineServizio(ordineServizio);
		entity.setApparecchiaturaTecnologica(apparecchiaturaTecnologica);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Ods apparecchiatura successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public OdsApparecchiatura update(
			Integer id,
			Integer codiceApparecchiaturaTecnologica) {

		OdsApparecchiatura entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods apparecchiatura: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		ApparecchiaturaTecnologica apparecchiaturaTecnologica = (ApparecchiaturaTecnologica)session.get(ApparecchiaturaTecnologica.class, codiceApparecchiaturaTecnologica);

		// Set entity fields.
		//
		entity.setApparecchiaturaTecnologica(apparecchiaturaTecnologica);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		OdsApparecchiatura entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods apparecchiatura: %d", id);
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
	public List<OdsApparecchiatura> listByOrdineServizio(Integer codiceOrdineServizio) {

		String hql =
				"from OdsApparecchiatura oda " +
				"left join fetch oda.apparecchiaturaTecnologica ate " +
				"left join fetch ate.tipoApparecchiaturaTecnologica tat " +
				"left join fetch tat.gruppoApparecchiatura gra " +
				"where oda.ordineServizio.id = :codiceOrdineServizio ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);

		List<OdsApparecchiatura> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}