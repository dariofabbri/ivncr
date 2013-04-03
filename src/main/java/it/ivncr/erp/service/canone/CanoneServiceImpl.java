package it.ivncr.erp.service.canone;

import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoFatturazione;
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

		QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoFatturazione q =
				new QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoFatturazione(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		String descrizione = filters.get("descrizione");
		String tipoServizio = filters.get("tipoServizio.descrizione");
		String specificaServizio = filters.get("specificaServizio.descrizione");
		String obiettivoServizio = filters.get("obiettivoServizio.alias");
		String tipoFatturazione = filters.get("tipoFatturazione.descrizione");

		q.setCodiceContratto(codiceContratto);
		q.setDescrizione(descrizione);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setTipoFatturazione(tipoFatturazione);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Canone> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Canone> list(
			Integer codiceContratto,
			String descrizione,
			String tipoServizio,
			String specificaServizio,
			String obiettivoServizio,
			String tipoFatturazione,
			Integer offset,
			Integer limit) {

		QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoFatturazione q =
				new QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoFatturazione(session);

		q.setCodiceContratto(codiceContratto);
		q.setDescrizione(descrizione);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setTipoFatturazione(tipoFatturazione);

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
				"left join fetch can.obiettivoServizio ose " +
				"left join fetch can.raggruppamentoFatturazione rfa " +
				"left join fetch can.tipoFatturazione tfa " +
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
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Date dataInizioValidita,
			Integer codiceRaggruppamentoFatturazione,
			BigDecimal importoMensile,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);
		ObiettivoServizio obiettivoServizio = (ObiettivoServizio)session.get(ObiettivoServizio.class, codiceObiettivoServizio);
		RaggruppamentoFatturazione raggruppamentoFatturazione = (RaggruppamentoFatturazione)session.get(RaggruppamentoFatturazione.class, codiceRaggruppamentoFatturazione);
		TipoFatturazione tipoFatturazione = (TipoFatturazione)session.get(TipoFatturazione.class, codiceTipoFatturazione);

		// Create the new entity.
		//
		Canone entity = new Canone();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setDescrizione(descrizione);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setObiettivoServizio(obiettivoServizio);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setRaggruppamentoFatturazione(raggruppamentoFatturazione);
		entity.setImportoMensile(importoMensile);
		entity.setTipoFatturazione(tipoFatturazione);
		entity.setDataCessazione(dataCessazione);
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
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Date dataInizioValidita,
			Integer codiceRaggruppamentoFatturazione,
			BigDecimal importoMensile,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note) {

		Canone entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified canone: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);
		ObiettivoServizio obiettivoServizio = (ObiettivoServizio)session.get(ObiettivoServizio.class, codiceObiettivoServizio);
		RaggruppamentoFatturazione raggruppamentoFatturazione = (RaggruppamentoFatturazione)session.get(RaggruppamentoFatturazione.class, codiceRaggruppamentoFatturazione);
		TipoFatturazione tipoFatturazione = (TipoFatturazione)session.get(TipoFatturazione.class, codiceTipoFatturazione);

		// Set entity fields.
		//
		entity.setDescrizione(descrizione);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setObiettivoServizio(obiettivoServizio);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setRaggruppamentoFatturazione(raggruppamentoFatturazione);
		entity.setImportoMensile(importoMensile);
		entity.setTipoFatturazione(tipoFatturazione);
		entity.setDataCessazione(dataCessazione);
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
}