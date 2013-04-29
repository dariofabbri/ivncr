package it.ivncr.erp.service.odsorariricorrenti;

import it.ivncr.erp.model.commerciale.ods.OdsOrariRicorrenti;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.generale.GiornoSettimana;
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

import org.hibernate.Query;

public class OdsOrariRicorrentiServiceImpl extends AbstractService implements OdsOrariRicorrentiService {

	@Override
	public QueryResult<OdsOrariRicorrenti> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public OdsOrariRicorrenti retrieve(Integer id) {

		OdsOrariRicorrenti odsOrariRicorrenti = (OdsOrariRicorrenti)session.get(OdsOrariRicorrenti.class, id);
		logger.debug("Ods orari ricorrenti found: " + odsOrariRicorrenti);

		return odsOrariRicorrenti;
	}


	@Override
	public OdsOrariRicorrenti retrieveByOrdineServizioGiornoSettimana(
			Integer codiceOrdineServizio,
			Integer codiceGiornoSettimana) {

		String hql =
				"from OdsOrariRicorrenti oor " +
				"where oor.ordineServizio.id = :codiceOrdineServizio " +
				"and oor.giornoSettimana.id = :codiceGiornoSettimana ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);
		query.setParameter("codiceGiornoSettimana", codiceGiornoSettimana);
		OdsOrariRicorrenti orario = (OdsOrariRicorrenti)query.uniqueResult();
		logger.debug("Ods orari ricorrenti found: " + orario);

		return orario;
	}

	@Override
	public OdsOrariRicorrenti setGiorno(
			Integer codiceOrdineServizio,
			Integer codiceGiornoSettimana,
			Boolean esclusoFestivo,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3) {

		// Check if a row is already present.
		//
		OdsOrariRicorrenti entity = retrieveByOrdineServizioGiornoSettimana(codiceOrdineServizio, codiceGiornoSettimana);
		if(entity == null) {

			entity = new OdsOrariRicorrenti();

			// Fetch referred entities.
			//
			OrdineServizio ordineServizio = (OrdineServizio)session.get(OrdineServizio.class, codiceOrdineServizio);
			GiornoSettimana giornoSettimana = (GiornoSettimana)session.get(GiornoSettimana.class, codiceGiornoSettimana);

			// Set entity fields.
			//
			entity.setOrdineServizio(ordineServizio);
			entity.setGiornoSettimana(giornoSettimana);
			entity.setEsclusoFestivo(esclusoFestivo);
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
			logger.debug("Ods orari ricorrenti successfully created.");

			// Audit call for the create operation.
			//
			AuditUtil.log(Operation.Create, Snapshot.Destination, entity);
		}

		// Already present, just update the row.
		//
		else {

			// Audit call for the update operation.
			//
			AuditUtil.log(Operation.Update, Snapshot.Source, entity);

			// Set entity fields.
			//
			entity.setEsclusoFestivo(esclusoFestivo);
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
			session.update(entity);
			logger.debug("Ods orari ricorrenti successfully updated.");

			// Audit call for the update operation.
			//
			AuditUtil.log(Operation.Update, Snapshot.Destination, entity);
		}

		return entity;
	}


	@Override
	public List<OdsOrariRicorrenti> setGiorni(
			Integer codiceOrdineServizio,
			Integer[] codiciGiornoSettimana,
			Boolean esclusoFestivo,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3) {

		List<OdsOrariRicorrenti> result = new ArrayList<OdsOrariRicorrenti>();

		for(Integer codiceGiornoSettimana : codiciGiornoSettimana) {

			OdsOrariRicorrenti entity = setGiorno(
					codiceOrdineServizio,
					codiceGiornoSettimana,
					esclusoFestivo,
					quantita1,
					orarioInizio1,
					orarioFine1,
					quantita2,
					orarioInizio2,
					orarioFine2,
					quantita3,
					orarioInizio3,
					orarioFine3);
			result.add(entity);
		}

		return result;
	}


	@Override
	public void delete(Integer id) {

		OdsOrariRicorrenti entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods orari ricorrenti: %d", id);
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


	@SuppressWarnings("unchecked")
	@Override
	public List<OdsOrariRicorrenti> listByOrdineServizio(Integer codiceOrdineServizio) {

		String hql =
				"from OdsOrariRicorrenti oor " +
				"where oor.ordineServizio.id = :codiceOrdineServizio ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);

		List<OdsOrariRicorrenti> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	@Override
	public void deleteByOrdineServizio(Integer codiceOrdineServizio) {

		String hql =
				"delete from OdsOrariRicorrenti oor " +
				"where oor.ordineServizio.id = :codiceOrdineServizio ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);
		int rows = query.executeUpdate();
		logger.debug(String.format("Deleted %d rows.", rows));
	}
}