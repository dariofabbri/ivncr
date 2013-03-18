package it.ivncr.erp.service.librettoportoarmi;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.LibrettoPortoArmi;
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

public class LibrettoPortoArmiServiceImpl extends AbstractService implements LibrettoPortoArmiService {

	@Override
	public QueryResult<LibrettoPortoArmi> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public LibrettoPortoArmi retrieve(Integer id) {

		LibrettoPortoArmi libretto = (LibrettoPortoArmi)session.get(LibrettoPortoArmi.class, id);
		logger.debug("Libretto porto armi found: " + libretto);

		return libretto;
	}

	@Override
	public LibrettoPortoArmi create(
			Integer codiceAddetto,
			String numero,
			Boolean fucile,
			Date dataRilascio,
			Date dataScadenza,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		LibrettoPortoArmi entity = new LibrettoPortoArmi();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setNumero(numero);
		entity.setFucile(fucile);
		entity.setDataRilascio(dataRilascio);
		entity.setDataScadenza(dataScadenza);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Libretto porto armi successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public LibrettoPortoArmi update(
			Integer id,
			String numero,
			Boolean fucile,
			Date dataRilascio,
			Date dataScadenza,
			String note) {

		LibrettoPortoArmi entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified libretto porto armi: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setNumero(numero);
		entity.setFucile(fucile);
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

		LibrettoPortoArmi entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified libretto porto armi: %d", id);
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
	public List<LibrettoPortoArmi> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from LibrettoPortoArmi lpa " +
				"where lpa.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<LibrettoPortoArmi> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}