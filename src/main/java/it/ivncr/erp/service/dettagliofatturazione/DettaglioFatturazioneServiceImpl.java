package it.ivncr.erp.service.dettagliofatturazione;

import it.ivncr.erp.model.commerciale.cliente.Indirizzo;
import it.ivncr.erp.model.commerciale.contratto.CondizioniPagamento;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.DettaglioFatturazione;
import it.ivncr.erp.model.commerciale.contratto.LayoutStampa;
import it.ivncr.erp.model.commerciale.contratto.MetodoPagamento;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Date;
import java.util.Map;

import org.hibernate.Query;

public class DettaglioFatturazioneServiceImpl extends AbstractService implements DettaglioFatturazioneService {

	@Override
	public QueryResult<DettaglioFatturazione> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceContrattoCondizioniPagamentoMetodoPagamentoIndirizzo q =
				new QueryByCodiceContrattoCondizioniPagamentoMetodoPagamentoIndirizzo(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		String condizioniPagamento = filters.get("condizioniPagamento.descrizione");
		String metodoPagamento = filters.get("metodoPagamento.descrizione");
		String indirizzo = filters.get("indirizzo.indirizzo");

		q.setCodiceContratto(codiceContratto);
		q.setCondizioniPagamento(condizioniPagamento);
		q.setMetodoPagamento(metodoPagamento);
		q.setIndirizzo(indirizzo);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<DettaglioFatturazione> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<DettaglioFatturazione> list(
			Integer codiceContratto,
			String condizioniPagamento,
			String metodoPagamento,
			String indirizzo,
			Integer offset,
			Integer limit) {

		QueryByCodiceContrattoCondizioniPagamentoMetodoPagamentoIndirizzo q =
				new QueryByCodiceContrattoCondizioniPagamentoMetodoPagamentoIndirizzo(session);

		q.setCodiceContratto(codiceContratto);
		q.setCondizioniPagamento(condizioniPagamento);
		q.setMetodoPagamento(metodoPagamento);
		q.setIndirizzo(indirizzo);

		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<DettaglioFatturazione> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}


	@Override
	public DettaglioFatturazione retrieve(Integer id) {

		DettaglioFatturazione dettaglioFatturazione = (DettaglioFatturazione)session.get(DettaglioFatturazione.class, id);
		logger.debug("Dettaglio fatturazione found: " + dettaglioFatturazione);

		return dettaglioFatturazione;
	}


	@Override
	public DettaglioFatturazione retrieveDeep(Integer id) {

		String hql =
				"from DettaglioFatturazione dfa " +
				"left join fetch dfa.condizioniPagamento cpa " +
				"left join fetch dfa.metodoPagamento mpa " +
				"left join fetch dfa.indirizzo ind " +
				"left join fetch dfa.layoutStampa las " +
				"where dfa.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		DettaglioFatturazione dettaglioFatturazione = (DettaglioFatturazione)query.uniqueResult();
		logger.debug("Dettaglio fatturazione found: " + dettaglioFatturazione);

		return dettaglioFatturazione;
	}


	@Override
	public DettaglioFatturazione create(
			Integer codiceContratto,
			Integer codiceCondizioniPagamento,
			Integer codiceMetodoPagamento,
			Integer codiceIndirizzo,
			Integer codiceLayoutStampa,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		CondizioniPagamento condizioniPagamento = (CondizioniPagamento)session.get(CondizioniPagamento.class, codiceCondizioniPagamento);
		MetodoPagamento metodoPagamento = (MetodoPagamento)session.get(MetodoPagamento.class, codiceMetodoPagamento);
		Indirizzo indirizzo = (Indirizzo)session.get(Indirizzo.class, codiceIndirizzo);
		LayoutStampa layoutStampa = (LayoutStampa)session.get(LayoutStampa.class, codiceLayoutStampa);

		// Create the new entity.
		//
		DettaglioFatturazione entity = new DettaglioFatturazione();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setCondizioniPagamento(condizioniPagamento);
		entity.setMetodoPagamento(metodoPagamento);
		entity.setIndirizzo(indirizzo);
		entity.setLayoutStampa(layoutStampa);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Dettaglio fatturazione successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public DettaglioFatturazione update(
			Integer id,
			Integer codiceCondizioniPagamento,
			Integer codiceMetodoPagamento,
			Integer codiceIndirizzo,
			Integer codiceLayoutStampa,
			Date validoDa,
			Date validoA) {

		DettaglioFatturazione entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified dettaglio fatturazione: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		CondizioniPagamento condizioniPagamento = (CondizioniPagamento)session.get(CondizioniPagamento.class, codiceCondizioniPagamento);
		MetodoPagamento metodoPagamento = (MetodoPagamento)session.get(MetodoPagamento.class, codiceMetodoPagamento);
		Indirizzo indirizzo = (Indirizzo)session.get(Indirizzo.class, codiceIndirizzo);
		LayoutStampa layoutStampa = (LayoutStampa)session.get(LayoutStampa.class, codiceLayoutStampa);

		// Set entity fields.
		//
		entity.setCondizioniPagamento(condizioniPagamento);
		entity.setMetodoPagamento(metodoPagamento);
		entity.setIndirizzo(indirizzo);
		entity.setLayoutStampa(layoutStampa);
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

		DettaglioFatturazione entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified dettaglio fatturazione: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}
}