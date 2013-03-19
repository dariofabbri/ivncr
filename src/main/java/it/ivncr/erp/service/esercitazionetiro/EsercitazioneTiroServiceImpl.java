package it.ivncr.erp.service.esercitazionetiro;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.EsercitazioneTiro;
import it.ivncr.erp.model.personale.TipoEsercitazioneTiro;
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

public class EsercitazioneTiroServiceImpl extends AbstractService implements EsercitazioneTiroService {

	@Override
	public QueryResult<EsercitazioneTiro> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public EsercitazioneTiro retrieve(Integer id) {

		EsercitazioneTiro documento = (EsercitazioneTiro)session.get(EsercitazioneTiro.class, id);
		logger.debug("Esercitazione tiro found: " + documento);

		return documento;
	}

	@Override
	public EsercitazioneTiro retrieveDeep(Integer id) {

		String hql =
				"from EsercitazioneTiro est " +
				"left join fetch est.tipoEsercitazioneTiro tet " +
				"where est.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		EsercitazioneTiro esercitazione = (EsercitazioneTiro)query.uniqueResult();
		logger.debug("Esercitazione tiro found: " + esercitazione);

		return esercitazione;
	}

	@Override
	public EsercitazioneTiro create(
			Integer codiceAddetto,
			Date dataTiro,
			String poligono,
			Integer codiceTipoEsercitazione,
			BigDecimal importoRichiesto,
			BigDecimal importoRimborsato) {

		// Fetch referred entities.
		//
		TipoEsercitazioneTiro tipoEsercitazione = (TipoEsercitazioneTiro)session.get(TipoEsercitazioneTiro.class, codiceTipoEsercitazione);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		EsercitazioneTiro entity = new EsercitazioneTiro();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setDataTiro(dataTiro);
		entity.setPoligono(poligono);
		entity.setTipoEsercitazioneTiro(tipoEsercitazione);
		entity.setImportoRichiesto(importoRichiesto);
		entity.setImportoRimborsato(importoRimborsato);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Esercitazione tiro successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public EsercitazioneTiro update(
			Integer id,
			Date dataTiro,
			String poligono,
			Integer codiceTipoEsercitazione,
			BigDecimal importoRichiesto,
			BigDecimal importoRimborsato){

		EsercitazioneTiro entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified esercitazione tiro: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoEsercitazioneTiro tipoEsercitazione = (TipoEsercitazioneTiro)session.get(TipoEsercitazioneTiro.class, codiceTipoEsercitazione);

		// Set entity fields.
		//
		entity.setDataTiro(dataTiro);
		entity.setPoligono(poligono);
		entity.setTipoEsercitazioneTiro(tipoEsercitazione);
		entity.setImportoRichiesto(importoRichiesto);
		entity.setImportoRimborsato(importoRimborsato);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		EsercitazioneTiro entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified esercitazione tiro: %d", id);
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
	public List<EsercitazioneTiro> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from EsercitazioneTiro est " +
				"left join fetch est.tipoEsercitazioneTiro tet " +
				"where est.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<EsercitazioneTiro> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}