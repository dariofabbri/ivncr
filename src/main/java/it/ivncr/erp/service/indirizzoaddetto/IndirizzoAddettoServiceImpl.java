package it.ivncr.erp.service.indirizzoaddetto;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.IndirizzoAddetto;
import it.ivncr.erp.model.personale.TipoIndirizzoAddetto;
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

public class IndirizzoAddettoServiceImpl extends AbstractService implements IndirizzoAddettoService {

	@Override
	public QueryResult<IndirizzoAddetto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public IndirizzoAddetto retrieve(Integer id) {

		IndirizzoAddetto indirizzo = (IndirizzoAddetto)session.get(IndirizzoAddetto.class, id);
		logger.debug("Indirizzo addetto found: " + indirizzo);

		return indirizzo;
	}

	@Override
	public IndirizzoAddetto retrieveDeep(Integer id) {

		String hql =
				"from IndirizzoAddetto iad " +
				"left join fetch iad.tipoIndirizzo tin " +
				"where iad.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		IndirizzoAddetto indirizzo = (IndirizzoAddetto)query.uniqueResult();
		logger.debug("Indirizzo addetto found: " + indirizzo);

		return indirizzo;
	}

	@Override
	public IndirizzoAddetto create(
			Integer codiceAddetto,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		TipoIndirizzoAddetto tipoIndirizzo = (TipoIndirizzoAddetto)session.get(TipoIndirizzoAddetto.class, codiceTipoIndirizzo);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		IndirizzoAddetto entity = new IndirizzoAddetto();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoIndirizzo(tipoIndirizzo);
		entity.setDestinatario1(destinatario1);
		entity.setDestinatario2(destinatario2);
		entity.setToponimo(toponimo);
		entity.setIndirizzo(indirizzo);
		entity.setCivico(civico);
		entity.setLocalita(localita);
		entity.setCap(cap);
		entity.setProvincia(provincia);
		entity.setPaese(paese);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Indirizzo addetto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public IndirizzoAddetto update(
			Integer id,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese,
			Date validoDa,
			Date validoA) {

		IndirizzoAddetto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified indirizzo addetto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoIndirizzoAddetto tipoIndirizzo = (TipoIndirizzoAddetto)session.get(TipoIndirizzoAddetto.class, codiceTipoIndirizzo);

		// Set entity fields.
		//
		entity.setTipoIndirizzo(tipoIndirizzo);
		entity.setDestinatario1(destinatario1);
		entity.setDestinatario2(destinatario2);
		entity.setToponimo(toponimo);
		entity.setIndirizzo(indirizzo);
		entity.setCivico(civico);
		entity.setLocalita(localita);
		entity.setCap(cap);
		entity.setProvincia(provincia);
		entity.setPaese(paese);
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

		IndirizzoAddetto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified indirizzo addetto: %d", id);
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
	public List<IndirizzoAddetto> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from IndirizzoAddetto iad " +
				"left join fetch iad.tipoIndirizzo tin " +
				"where iad.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<IndirizzoAddetto> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}