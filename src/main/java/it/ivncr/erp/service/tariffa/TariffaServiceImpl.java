package it.ivncr.erp.service.tariffa;

import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.contratto.TipoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.model.commerciale.contratto.TipoTariffa;
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

public class TariffaServiceImpl extends AbstractService implements TariffaService {

	@Override
	public QueryResult<Tariffa> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoTariffaTipoFatturazione q =
				new QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoTariffaTipoFatturazione(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		String descrizione = filters.get("descrizione");
		String tipoServizio = filters.get("tipoServizio.descrizione");
		String specificaServizio = filters.get("specificaServizio.descrizione");
		String obiettivoServizio = filters.get("obiettivoServizio.alias");
		String tipoTariffa = filters.get("tipoTariffa.descrizione");
		String tipoFatturazione = filters.get("tipoFatturazione.descrizione");

		q.setCodiceContratto(codiceContratto);
		q.setDescrizione(descrizione);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setTipoTariffa(tipoTariffa);
		q.setTipoFatturazione(tipoFatturazione);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Tariffa> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Tariffa> list(
			Integer codiceContratto,
			String descrizione,
			String tipoServizio,
			String specificaServizio,
			String obiettivoServizio,
			String tipoTariffa,
			String tipoFatturazione,
			Integer offset,
			Integer limit) {

		QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoTariffaTipoFatturazione q =
				new QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoTariffaTipoFatturazione(session);

		q.setCodiceContratto(codiceContratto);
		q.setDescrizione(descrizione);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setTipoTariffa(tipoTariffa);
		q.setTipoFatturazione(tipoFatturazione);

		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Tariffa> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}


	@Override
	public Tariffa retrieve(Integer id) {

		Tariffa tariffa = (Tariffa)session.get(Tariffa.class, id);
		logger.debug("Tariffa found: " + tariffa);

		return tariffa;
	}


	@Override
	public Tariffa retrieveDeep(Integer id) {

		String hql =
				"from Tariffa tar " +
				"left join fetch tar.tipoServizio tse " +
				"left join fetch tar.specificaServizio sse " +
				"left join fetch tar.obiettivoServizio ose " +
				"left join fetch tar.tipoTariffa tta " +
				"left join fetch tar.tipoFatturazione tfa " +
				"where tar.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Tariffa tariffa = (Tariffa)query.uniqueResult();
		logger.debug("Tariffa found: " + tariffa);

		return tariffa;
	}


	@Override
	public Tariffa create(
			Integer codiceContratto,
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Integer codiceTipoTariffa,
			BigDecimal costo,
			Integer numero,
			Date dataInizioValidita,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);
		ObiettivoServizio obiettivoServizio = (ObiettivoServizio)session.get(ObiettivoServizio.class, codiceObiettivoServizio);
		TipoTariffa tipoTariffa = (TipoTariffa)session.get(TipoTariffa.class, codiceTipoTariffa);
		TipoFatturazione tipoFatturazione = (TipoFatturazione)session.get(TipoFatturazione.class, codiceTipoFatturazione);

		// Create the new entity.
		//
		Tariffa entity = new Tariffa();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setDescrizione(descrizione);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setObiettivoServizio(obiettivoServizio);
		entity.setTipoTariffa(tipoTariffa);
		entity.setCosto(costo);
		entity.setNumero(numero);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setTipoFatturazione(tipoFatturazione);
		entity.setDataCessazione(dataCessazione);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Tariffa successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Tariffa update(
			Integer id,
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Integer codiceTipoTariffa,
			BigDecimal costo,
			Integer numero,
			Date dataInizioValidita,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note) {

		Tariffa entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified tariffa: %d", id);
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
		TipoTariffa tipoTariffa = (TipoTariffa)session.get(TipoTariffa.class, codiceTipoTariffa);
		TipoFatturazione tipoFatturazione = (TipoFatturazione)session.get(TipoFatturazione.class, codiceTipoFatturazione);

		// Set entity fields.
		//
		entity.setDescrizione(descrizione);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setObiettivoServizio(obiettivoServizio);
		entity.setTipoTariffa(tipoTariffa);
		entity.setCosto(costo);
		entity.setNumero(numero);
		entity.setDataInizioValidita(dataInizioValidita);
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

		Tariffa entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified tariffa: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}
}