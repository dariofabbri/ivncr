package it.ivncr.erp.service.canone;

import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.CanoneStorico;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class CanoneServiceImpl extends AbstractService implements CanoneService {

	@Override
	public QueryResult<Canone> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceContrattoAliasTipoServizioSpecificaServizio q =
				new QueryByCodiceContrattoAliasTipoServizioSpecificaServizio(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		String alias = filters.get("alias");
		String tipoServizio = filters.get("tipoServizio.descrizione");
		String specificaServizio = filters.get("specificaServizio.descrizione");

		q.setCodiceContratto(codiceContratto);
		q.setAlias(alias);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Canone> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Canone> list(
			Integer codiceContratto,
			String alias,
			String tipoServizio,
			String specificaServizio,
			Integer offset,
			Integer limit) {

		QueryByCodiceContrattoAliasTipoServizioSpecificaServizio q =
				new QueryByCodiceContrattoAliasTipoServizioSpecificaServizio(session);

		q.setCodiceContratto(codiceContratto);
		q.setAlias(alias);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);

		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Canone> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}


	@Override
	public Canone retrieve(Integer id) {

		Canone canone = (Canone)session.get(Canone.class, id);
		logger.debug("Canone found: " + canone);

		return canone;
	}


	@Override
	public Canone retrieveDeep(Integer id) {

		String hql =
				"from Canone can " +
				"left join fetch can.tipoServizio tse " +
				"left join fetch can.specificaServizio sse " +
				"where can.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Canone canone = (Canone)query.uniqueResult();
		logger.debug("Canone found: " + canone);

		return canone;
	}


	@Override
	public Canone create(
			Integer codiceContratto,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);

		// Create the new entity.
		//
		Canone entity = new Canone();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setAlias(alias);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataCessazione(dataCessazione);
		entity.setFatturaMinimoUnMese(fatturaMinimoUnMese);
		entity.setFatturazioneAnticipata(fatturazioneAnticipata);
		entity.setFatturaOgniMesi(fatturaOgniMesi);
		entity.setCanoneMensile(canoneMensile);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Canone successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Canone update(
			Integer id,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note) {

		// Save current timestamp.
		//
		Date now = new Date();

		// Retrieve specified entity.
		//
		Canone entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified canone: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Save entity in journal table.
		//
		CanoneStorico journal = new CanoneStorico();
		journal.setCanone(entity);
		journal.setModifica(now);
		journal.setAlias(entity.getAlias());
		journal.setTipoServizio(entity.getTipoServizio());
		journal.setSpecificaServizio(entity.getSpecificaServizio());
		journal.setDataInizioValidita(entity.getDataInizioValidita());
		journal.setDataCessazione(entity.getDataCessazione());
		journal.setFatturaMinimoUnMese(entity.getFatturaMinimoUnMese());
		journal.setFatturazioneAnticipata(entity.getFatturazioneAnticipata());
		journal.setFatturaOgniMesi(entity.getFatturaOgniMesi());
		journal.setCanoneMensile(entity.getCanoneMensile());
		journal.setNote(entity.getNote());
		session.save(journal);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);

		// Set entity fields.
		//
		entity.setAlias(alias);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataCessazione(dataCessazione);
		entity.setFatturaMinimoUnMese(fatturaMinimoUnMese);
		entity.setFatturazioneAnticipata(fatturazioneAnticipata);
		entity.setFatturaOgniMesi(fatturaOgniMesi);
		entity.setCanoneMensile(canoneMensile);
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

		Canone entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified canone: %d", id);
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
	public List<Canone> listByContratto(Integer codiceContratto) {

		String hql =
				"from Canone can " +
				"left join fetch can.tipoServizio tse " +
				"left join fetch can.specificaServizio sse " +
				"where can.contratto.id = :codiceContratto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<Canone> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}