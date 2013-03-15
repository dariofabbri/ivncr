package it.ivncr.erp.service.recapitoemail;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.RecapitoEmail;
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

public class RecapitoEmailServiceImpl extends AbstractService implements RecapitoEmailService {

	@Override
	public QueryResult<RecapitoEmail> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public RecapitoEmail retrieve(Integer id) {

		RecapitoEmail recapitoEmail = (RecapitoEmail)session.get(RecapitoEmail.class, id);
		logger.debug("Recapito email found: " + recapitoEmail);

		return recapitoEmail;
	}

	@Override
	public RecapitoEmail create(
			Integer codiceAddetto,
			String email) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		RecapitoEmail entity = new RecapitoEmail();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setEmail(email);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Recapito email successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public RecapitoEmail update(
			Integer id,
			String email) {

		RecapitoEmail entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified recapito email: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setEmail(email);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		RecapitoEmail entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified recapito email: %d", id);
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
	public List<RecapitoEmail> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from RecapitoEmail rem " +
				"where rem.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<RecapitoEmail> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}