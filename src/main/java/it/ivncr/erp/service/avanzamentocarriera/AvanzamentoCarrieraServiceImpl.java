package it.ivncr.erp.service.avanzamentocarriera;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.AvanzamentoCarriera;
import it.ivncr.erp.model.personale.LivelloCcnl;
import it.ivncr.erp.model.personale.Qualifica;
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

public class AvanzamentoCarrieraServiceImpl extends AbstractService implements AvanzamentoCarrieraService {

	@Override
	public QueryResult<AvanzamentoCarriera> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public AvanzamentoCarriera retrieve(Integer id) {

		AvanzamentoCarriera avanzamento = (AvanzamentoCarriera)session.get(AvanzamentoCarriera.class, id);
		logger.debug("Avanzamento carriera found: " + avanzamento);

		return avanzamento;
	}

	@Override
	public AvanzamentoCarriera retrieveDeep(Integer id) {

		String hql =
				"from AvanzamentoCarriera avc " +
				"left join fetch avc.qualifica qua " +
				"left join fetch avc.livelloCcnl lic " +
				"where avc.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		AvanzamentoCarriera avanzamento = (AvanzamentoCarriera)query.uniqueResult();
		logger.debug("Avanzamento carriera found: " + avanzamento);

		return avanzamento;
	}

	@Override
	public AvanzamentoCarriera create(
			Integer codiceAddetto,
			Integer codiceQualifica,
			Integer codiceLivelloCcnl,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		Qualifica qualifica = (Qualifica)session.get(Qualifica.class, codiceQualifica);
		LivelloCcnl livelloCcnl = (LivelloCcnl)session.get(LivelloCcnl.class, codiceLivelloCcnl);

		// Create the new entity.
		//
		AvanzamentoCarriera entity = new AvanzamentoCarriera();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setQualifica(qualifica);
		entity.setLivelloCcnl(livelloCcnl);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Posizione lavorativa successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public AvanzamentoCarriera update(
			Integer id,
			Integer codiceQualifica,
			Integer codiceLivelloCcnl,
			Date validoDa,
			Date validoA) {

		AvanzamentoCarriera entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified avanzamento carriera: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		Qualifica qualifica = (Qualifica)session.get(Qualifica.class, codiceQualifica);
		LivelloCcnl livelloCcnl = (LivelloCcnl)session.get(LivelloCcnl.class, codiceLivelloCcnl);

		// Set entity fields.
		//
		entity.setQualifica(qualifica);
		entity.setLivelloCcnl(livelloCcnl);
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

		AvanzamentoCarriera entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified avanzamento carriera: %d", id);
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
	public List<AvanzamentoCarriera> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from AvanzamentoCarriera avc " +
				"left join fetch avc.qualifica qua " +
				"left join fetch avc.livelloCcnl lic " +
				"where avc.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<AvanzamentoCarriera> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}