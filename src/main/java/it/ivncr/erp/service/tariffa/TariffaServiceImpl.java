package it.ivncr.erp.service.tariffa;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
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

public class TariffaServiceImpl extends AbstractService implements TariffaService {

	@Override
	public QueryResult<Tariffa> list(
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

		QueryResult<Tariffa> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Tariffa> list(
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
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			BigDecimal costoOrario,
			BigDecimal costoOperazione,
			BigDecimal costoFissoUnaTantum,
			BigDecimal costoFissoATempo,
			Integer costoFissoMesi,
			Integer franchigieTotali,
			Integer franchigieATempo,
			Integer franchigieMesi,
			BigDecimal ritenutaGaranzia,
			Integer ritenutaGaranziaGiorni,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturazioneAnticipata,
			Boolean extraFatturatoAParte,
			Boolean fatturaSpezzata,
			Integer fatturaOgniMesi,
			Boolean fatturaMinimoUnMese,
			String note) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);

		// Create the new entity.
		//
		Tariffa entity = new Tariffa();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setAlias(alias);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setCostoOrario(costoOrario);
		entity.setCostoOperazione(costoOperazione);
		entity.setCostoFissoUnaTantum(costoFissoUnaTantum);
		entity.setCostoFissoATempo(costoFissoATempo);
		entity.setCostoFissoMesi(costoFissoMesi);
		entity.setFranchigieTotali(franchigieTotali);
		entity.setFranchigieATempo(franchigieATempo);
		entity.setFranchigieMesi(franchigieMesi);
		entity.setRitenutaGaranzia(ritenutaGaranzia);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataCessazione(dataCessazione);
		entity.setFatturazioneAnticipata(fatturazioneAnticipata);
		entity.setExtraFatturatoAParte(extraFatturatoAParte);
		entity.setFatturaSpezzata(fatturaSpezzata);
		entity.setFatturaOgniMesi(fatturaOgniMesi);
		entity.setFatturaMinimoUnMese(fatturaMinimoUnMese);
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
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			BigDecimal costoOrario,
			BigDecimal costoOperazione,
			BigDecimal costoFissoUnaTantum,
			BigDecimal costoFissoATempo,
			Integer costoFissoMesi,
			Integer franchigieTotali,
			Integer franchigieATempo,
			Integer franchigieMesi,
			BigDecimal ritenutaGaranzia,
			Integer ritenutaGaranziaGiorni,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturazioneAnticipata,
			Boolean extraFatturatoAParte,
			Boolean fatturaSpezzata,
			Integer fatturaOgniMesi,
			Boolean fatturaMinimoUnMese,
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

		// Set entity fields.
		//
		entity.setAlias(alias);
		entity.setTipoServizio(tipoServizio);
		entity.setSpecificaServizio(specificaServizio);
		entity.setCostoOrario(costoOrario);
		entity.setCostoOperazione(costoOperazione);
		entity.setCostoFissoUnaTantum(costoFissoUnaTantum);
		entity.setCostoFissoATempo(costoFissoATempo);
		entity.setCostoFissoMesi(costoFissoMesi);
		entity.setFranchigieTotali(franchigieTotali);
		entity.setFranchigieATempo(franchigieATempo);
		entity.setFranchigieMesi(franchigieMesi);
		entity.setRitenutaGaranzia(ritenutaGaranzia);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataCessazione(dataCessazione);
		entity.setFatturazioneAnticipata(fatturazioneAnticipata);
		entity.setExtraFatturatoAParte(extraFatturatoAParte);
		entity.setFatturaSpezzata(fatturaSpezzata);
		entity.setFatturaOgniMesi(fatturaOgniMesi);
		entity.setFatturaMinimoUnMese(fatturaMinimoUnMese);
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