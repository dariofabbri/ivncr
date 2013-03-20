package it.ivncr.erp.service.datisanitari;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.DatiSanitari;
import it.ivncr.erp.model.personale.GruppoSanguigno;
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

public class DatiSanitariServiceImpl extends AbstractService implements DatiSanitariService {

	@Override
	public QueryResult<DatiSanitari> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public DatiSanitari retrieve(Integer id) {

		DatiSanitari datiSanitari = (DatiSanitari)session.get(DatiSanitari.class, id);
		logger.debug("Dati sanitari found: " + datiSanitari);

		return datiSanitari;
	}

	@Override
	public DatiSanitari retrieveDeep(Integer id) {

		String hql =
				"from DatiSanitari das " +
				"left join fetch das.gruppoSanguigno gsa " +
				"where das.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		DatiSanitari datiSanitari = (DatiSanitari)query.uniqueResult();
		logger.debug("Dati sanitari found: " + datiSanitari);

		return datiSanitari;
	}

	@Override
	public DatiSanitari create(
			Integer codiceAddetto,
			String medicoBase,
			Integer codiceGruppoSanguigno,
			String asl,
			String indirizzoAsl,
			String comune,
			String provincia,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		GruppoSanguigno gruppoSanguigno = (GruppoSanguigno)session.get(GruppoSanguigno.class, codiceGruppoSanguigno);

		// Create the new entity.
		//
		DatiSanitari entity = new DatiSanitari();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setMedicoBase(medicoBase);
		entity.setGruppoSanguigno(gruppoSanguigno);
		entity.setAsl(asl);
		entity.setIndirizzoAsl(indirizzoAsl);
		entity.setComune(comune);
		entity.setProvincia(provincia);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Dati sanitari successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public DatiSanitari update(
			Integer id,
			String medicoBase,
			Integer codiceGruppoSanguigno,
			String asl,
			String indirizzoAsl,
			String comune,
			String provincia,
			Date validoDa,
			Date validoA) {

		DatiSanitari entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified dati sanitari: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		GruppoSanguigno gruppoSanguigno = (GruppoSanguigno)session.get(GruppoSanguigno.class, codiceGruppoSanguigno);

		// Set entity fields.
		//
		entity.setMedicoBase(medicoBase);
		entity.setGruppoSanguigno(gruppoSanguigno);
		entity.setAsl(asl);
		entity.setIndirizzoAsl(indirizzoAsl);
		entity.setComune(comune);
		entity.setProvincia(provincia);
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

		DatiSanitari entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified dati sanitari: %d", id);
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
	public List<DatiSanitari> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from DatiSanitari das " +
				"left join fetch das.gruppoSanguigno gsa " +
				"where das.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<DatiSanitari> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> listDistinctComune() {

		String hql =
				"select distinct das.comune from DatiSanitari das ";
		Query query = session.createQuery(hql);
		List<String> result = query.list();
		logger.debug("Query returned: " + result);

		return result;
	}
}