package it.ivncr.erp.service.odsoraricalendario;

import it.ivncr.erp.model.commerciale.ods.OdsOrariCalendario;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;

public class OdsOrariCalendarioServiceImpl extends AbstractService implements OdsOrariCalendarioService {

	private static final int MAX_DAYS_IN_PERIOD = 10000;

	@Override
	public QueryResult<OdsOrariCalendario> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public OdsOrariCalendario retrieve(Integer id) {

		OdsOrariCalendario odsOrariCalendario = (OdsOrariCalendario)session.get(OdsOrariCalendario.class, id);
		logger.debug("Ods orari calendario found: " + odsOrariCalendario);

		return odsOrariCalendario;
	}

	@Override
	public OdsOrariCalendario create(
			Integer codiceOrdineServizio,
			Date dataServizio,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3) {

		// Fetch referred entities.
		//
		OrdineServizio ordineServizio = (OrdineServizio)session.get(OrdineServizio.class, codiceOrdineServizio);

		// Create the new entity.
		//
		OdsOrariCalendario entity = new OdsOrariCalendario();

		// Set entity fields.
		//
		entity.setOrdineServizio(ordineServizio);
		entity.setDataServizio(dataServizio);
		entity.setQuantita1(quantita1);
		entity.setOrarioInizio1(orarioInizio1);
		entity.setOrarioFine1(orarioFine1);
		entity.setQuantita2(quantita2);
		entity.setOrarioInizio2(orarioInizio2);
		entity.setOrarioFine2(orarioFine2);
		entity.setQuantita3(quantita3);
		entity.setOrarioInizio3(orarioInizio3);
		entity.setOrarioFine3(orarioFine3);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Ods orari calendario successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public List<OdsOrariCalendario> createPeriodo(
			Integer codiceOrdineServizio,
			Date dataInizioPeriodo,
			Date dataFinePeriodo,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3) {

		// Check arguments (inverted start and end period).
		//
		if(dataInizioPeriodo.after(dataFinePeriodo)) {
			String msg = String.format("Argument dataInizioPeriodo [%s] is after dataFinePeriodo [%s].", dataInizioPeriodo, dataFinePeriodo);
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		// Check if we are asking to generate a huge amount of rows.
		//
		long interval = dataFinePeriodo.getTime() - dataInizioPeriodo.getTime();
		if(interval > 86400000L * MAX_DAYS_IN_PERIOD) {
			String msg = String.format("The interval between dataInizioPeriodo [%s] and dataFinePeriodo [%s] represents more than %d days.",
					dataInizioPeriodo,
					dataFinePeriodo,
					MAX_DAYS_IN_PERIOD);
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		// Fetch referred entities.
		//
		OrdineServizio ordineServizio = (OrdineServizio)session.get(OrdineServizio.class, codiceOrdineServizio);

		// Prepare return object.
		//
		List<OdsOrariCalendario> list = new ArrayList<OdsOrariCalendario>();

		// Start iteration on dates of period.
		//
		Date dataServizio = dataInizioPeriodo;
		while(true) {

			if(dataServizio.after(dataFinePeriodo)) {
				break;
			}

			// Create the new entity.
			//
			OdsOrariCalendario entity = new OdsOrariCalendario();

			// Set entity fields.
			//
			entity.setOrdineServizio(ordineServizio);
			entity.setDataServizio(dataServizio);
			entity.setQuantita1(quantita1);
			entity.setOrarioInizio1(orarioInizio1);
			entity.setOrarioFine1(orarioFine1);
			entity.setQuantita2(quantita2);
			entity.setOrarioInizio2(orarioInizio2);
			entity.setOrarioFine2(orarioFine2);
			entity.setQuantita3(quantita3);
			entity.setOrarioInizio3(orarioInizio3);
			entity.setOrarioFine3(orarioFine3);

			// Persist the entity to the database.
			//
			session.save(entity);
			list.add(entity);

			// Audit call for the create operation.
			//
			AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

			// Move to next date.
			//
			dataServizio = DateUtils.addDays(dataServizio, 1);
		}
		logger.debug("List of ods orari calendario successfully created.");

		return list;
	}


	@Override
	public OdsOrariCalendario update(
			Integer id,
			Date dataServizio,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3) {

		OdsOrariCalendario entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods orari calendario: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setDataServizio(dataServizio);
		entity.setQuantita1(quantita1);
		entity.setOrarioInizio1(orarioInizio1);
		entity.setOrarioFine1(orarioFine1);
		entity.setQuantita2(quantita2);
		entity.setOrarioInizio2(orarioInizio2);
		entity.setOrarioFine2(orarioFine2);
		entity.setQuantita3(quantita3);
		entity.setOrarioInizio3(orarioInizio3);
		entity.setOrarioFine3(orarioFine3);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		OdsOrariCalendario entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods orari calendario: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}


	@Override
	public void delete(Iterable<Integer> ids) {

		for(Integer id : ids) {
			delete(id);
		}
	}


	@Override
	public int deletePeriodo(
		Integer codiceOrdineServizio,
		Date dataInizioPeriodo,
		Date dataFinePeriodo) {

		// Check arguments (inverted start and end period).
		//
		if(dataInizioPeriodo.after(dataFinePeriodo)) {
			String msg = String.format("Argument dataInizioPeriodo [%s] is after dataFinePeriodo [%s].", dataInizioPeriodo, dataFinePeriodo);
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}

		// Delete the range using a DML statement.
		//
		String hql =
				"delete OdsOrariCalendario ooc " +
				"where ooc.ordineServizio.id = :codiceOrdineServizio " +
				"and ooc.dataServizio >= :dataInizioPeriodo " +
				"and ooc.dataServizio <= :dataFinePeriodo ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);
		query.setParameter("dataInizioPeriodo", dataInizioPeriodo);
		query.setParameter("dataFinePeriodo", dataFinePeriodo);
		int deletedRows = query.executeUpdate();
		logger.debug(String.format("Deleted %d rows.", deletedRows));

		return deletedRows;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OdsOrariCalendario> listByOrdineServizio(Integer codiceOrdineServizio) {

		String hql =
				"from OdsOrariCalendario ooc " +
				"where ooc.ordineServizio.id = :codiceOrdineServizio ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);

		List<OdsOrariCalendario> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	@Override
	public boolean isGiornoPresent(
			Integer codiceOrdineServizio,
			Date dataServizio) {

		String hql =
				"select count(*) " +
				"from OdsOrariCalendario ooc " +
				"where ooc.ordineServizio.id = :codiceOrdineServizio " +
				"and ooc.dataServizio = :dataServizio ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);
		query.setParameter("dataServizio", dataServizio);

		Long count = (Long) query.uniqueResult();
		return count > 0;
	}


	@Override
	public boolean isPeriodoPresent(
			Integer codiceOrdineServizio,
			Date dataInizioPeriodo,
			Date dataFinePeriodo) {

		String hql =
				"select count(*) " +
				"from OdsOrariCalendario ooc " +
				"where ooc.ordineServizio.id = :codiceOrdineServizio " +
				"and ooc.dataServizio >= :dataInizioPeriodo " +
				"and ooc.dataServizio <= :dataFinePeriodo ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);
		query.setParameter("dataInizioPeriodo", dataInizioPeriodo);
		query.setParameter("dataFinePeriodo", dataFinePeriodo);

		Long count = (Long) query.uniqueResult();
		return count > 0;
	}
}