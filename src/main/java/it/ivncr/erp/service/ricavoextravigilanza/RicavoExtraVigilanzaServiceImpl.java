package it.ivncr.erp.service.ricavoextravigilanza;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.RicavoExtraVigilanza;
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

public class RicavoExtraVigilanzaServiceImpl extends AbstractService implements RicavoExtraVigilanzaService {

	@Override
	public QueryResult<RicavoExtraVigilanza> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}


	@Override
	public RicavoExtraVigilanza retrieve(Integer id) {

		RicavoExtraVigilanza ricavoExtraVigilanza = (RicavoExtraVigilanza)session.get(RicavoExtraVigilanza.class, id);
		logger.debug("Ricavo extra vigilanza found: " + ricavoExtraVigilanza);

		return ricavoExtraVigilanza;
	}


	@Override
	public RicavoExtraVigilanza retrieveDeep(Integer id) {

		String hql =
				"from RicavoExtraVigilanza rev " +
				"left join fetch rev.tipoServizio tse " +
				"left join fetch rev.specificaServizio sse " +
				"left join fetch rev.raggruppamentoFatturazione rfa " +
				"where rev.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		RicavoExtraVigilanza ricavoExtraVigilanza = (RicavoExtraVigilanza)query.uniqueResult();
		logger.debug("Ricavo extra vigilanza found: " + ricavoExtraVigilanza);

		return ricavoExtraVigilanza;
	}


	@Override
	public RicavoExtraVigilanza create(
			Integer codiceContratto,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceRaggruppamentoFatturazione,
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
		RaggruppamentoFatturazione raggruppamentoFatturazione = codiceRaggruppamentoFatturazione != null ? (RaggruppamentoFatturazione)session.get(RaggruppamentoFatturazione.class, codiceRaggruppamentoFatturazione) : null;

		// Create the new entity.
		//
		RicavoExtraVigilanza entity = new RicavoExtraVigilanza();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setAlias(alias);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setRaggruppamentoFatturazione(raggruppamentoFatturazione);
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
	public RicavoExtraVigilanza update(
			Integer id,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceRaggruppamentoFatturazione,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note) {

		RicavoExtraVigilanza entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ricavo extra vigilanza: %d", id);
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
		RaggruppamentoFatturazione raggruppamentoFatturazione = codiceRaggruppamentoFatturazione != null ? (RaggruppamentoFatturazione)session.get(RaggruppamentoFatturazione.class, codiceRaggruppamentoFatturazione) : null;

		// Set entity fields.
		//
		entity.setAlias(alias);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setRaggruppamentoFatturazione(raggruppamentoFatturazione);
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

		RicavoExtraVigilanza entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ricavo extra vigilanza: %d", id);
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
	public List<RicavoExtraVigilanza> listByContratto(Integer codiceContratto) {

		String hql =
				"from RicavoExtraVigilanza rev " +
				"left join fetch rev.tipoServizio tse " +
				"left join fetch rev.specificaServizio sse " +
				"left join fetch rev.raggruppamentoFatturazione rfa " +
				"where rev.contratto.id = :codiceContratto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<RicavoExtraVigilanza> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}