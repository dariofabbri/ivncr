package it.ivncr.erp.service.decretogpg;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.DecretoGpg;
import it.ivncr.erp.model.personale.TipoRinnovoDecretoGpg;
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

public class DecretoGpgServiceImpl extends AbstractService implements DecretoGpgService {

	@Override
	public QueryResult<DecretoGpg> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public DecretoGpg retrieve(Integer id) {

		DecretoGpg decreto = (DecretoGpg)session.get(DecretoGpg.class, id);
		logger.debug("Decreto gpg found: " + decreto);

		return decreto;
	}

	@Override
	public DecretoGpg retrieveDeep(Integer id) {

		String hql =
				"from DecretoGpg dgp " +
				"left join fetch dgp.tipoRinnovo tri " +
				"where dgp.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		DecretoGpg decreto = (DecretoGpg)query.uniqueResult();
		logger.debug("Decreto gpg found: " + decreto);

		return decreto;
	}

	@Override
	public DecretoGpg create(
			Integer codiceAddetto,
			Integer codiceTipoRinnovo,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		TipoRinnovoDecretoGpg tipoRinnovo = (TipoRinnovoDecretoGpg)session.get(TipoRinnovoDecretoGpg.class, codiceTipoRinnovo);

		// Create the new entity.
		//
		DecretoGpg entity = new DecretoGpg();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoRinnovo(tipoRinnovo);
		entity.setNumero(numero);
		entity.setDataRilascio(dataRilascio);
		entity.setDataScadenza(dataScadenza);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Decreto gpg successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public DecretoGpg update(
			Integer id,
			Integer codiceTipoRinnovo,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note) {

		DecretoGpg entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified decreto gpg: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoRinnovoDecretoGpg tipoRinnovo = (TipoRinnovoDecretoGpg)session.get(TipoRinnovoDecretoGpg.class, codiceTipoRinnovo);

		// Set entity fields.
		//
		entity.setTipoRinnovo(tipoRinnovo);
		entity.setNumero(numero);
		entity.setDataRilascio(dataRilascio);
		entity.setDataScadenza(dataScadenza);
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

		DecretoGpg entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified decreto gpg: %d", id);
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
	public List<DecretoGpg> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from DecretoGpg dgp " +
				"left join fetch dgp.tipoRinnovo tri " +
				"where dgp.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<DecretoGpg> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}