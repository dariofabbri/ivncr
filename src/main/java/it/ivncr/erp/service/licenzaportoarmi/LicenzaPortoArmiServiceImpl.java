package it.ivncr.erp.service.licenzaportoarmi;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.LibrettoPortoArmi;
import it.ivncr.erp.model.personale.LicenzaPortoArmi;
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

public class LicenzaPortoArmiServiceImpl extends AbstractService implements LicenzaPortoArmiService {

	@Override
	public QueryResult<LicenzaPortoArmi> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public LicenzaPortoArmi retrieve(Integer id) {

		LicenzaPortoArmi licenza = (LicenzaPortoArmi)session.get(LicenzaPortoArmi.class, id);
		logger.debug("Licenza porto armi found: " + licenza);

		return licenza;
	}

	@Override
	public LicenzaPortoArmi retrieveDeep(Integer id) {

		String hql =
				"from LicenzaPortoArmi lpa " +
				"left join fetch lpa.librettoPortoArmi lib " +
				"where lpa.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LicenzaPortoArmi licenza = (LicenzaPortoArmi)query.uniqueResult();
		logger.debug("Licenza porto armi found: " + licenza);

		return licenza;
	}

	@Override
	public LicenzaPortoArmi create(
			Integer codiceAddetto,
			Integer codiceLibretto,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		LibrettoPortoArmi libretto = (LibrettoPortoArmi)session.get(LibrettoPortoArmi.class, codiceLibretto);

		// Create the new entity.
		//
		LicenzaPortoArmi entity = new LicenzaPortoArmi();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setLibrettoPortoArmi(libretto);
		entity.setNumero(numero);
		entity.setDataRilascio(dataRilascio);
		entity.setDataScadenza(dataScadenza);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Licenza porto armi successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public LicenzaPortoArmi update(
			Integer id,
			Integer codiceLibretto,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note) {

		LicenzaPortoArmi entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified licenza porto armi: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		LibrettoPortoArmi libretto = (LibrettoPortoArmi)session.get(LibrettoPortoArmi.class, codiceLibretto);

		// Set entity fields.
		//
		entity.setLibrettoPortoArmi(libretto);
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

		LicenzaPortoArmi entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified licenza porto armi: %d", id);
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
	public List<LicenzaPortoArmi> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from LicenzaPortoArmi lpa " +
				"left join fetch lpa.librettoPortoArmi lib " +
				"where lpa.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<LicenzaPortoArmi> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}