package it.ivncr.erp.service.posizionelavorativa;

import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Ccnl;
import it.ivncr.erp.model.personale.PosizioneLavorativa;
import it.ivncr.erp.model.personale.TipoContratto;
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

public class PosizioneLavorativaServiceImpl extends AbstractService implements PosizioneLavorativaService {

	@Override
	public QueryResult<PosizioneLavorativa> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public PosizioneLavorativa retrieve(Integer id) {

		PosizioneLavorativa posizioneLavorativa = (PosizioneLavorativa)session.get(PosizioneLavorativa.class, id);
		logger.debug("Posizione lavorativa found: " + posizioneLavorativa);

		return posizioneLavorativa;
	}

	@Override
	public PosizioneLavorativa retrieveDeep(Integer id) {

		String hql =
				"from PosizioneLavorativa pla " +
				"left join fetch pla.tipoContratto tco " +
				"left join fetch pla.ccnl ccn " +
				"left join fetch pla.azienda azi " +
				"where pla.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		PosizioneLavorativa posizioneLavorativa = (PosizioneLavorativa)query.uniqueResult();
		logger.debug("Posizione lavorativa found: " + posizioneLavorativa);

		return posizioneLavorativa;
	}

	@Override
	public PosizioneLavorativa create(
			Integer codiceAddetto,
			Integer codiceTipoContratto,
			Integer codiceCcnl,
			Integer codiceAzienda,
			Integer durataContratto,
			Date dataAssunzione,
			Integer durataProva,
			Date dataPrimoGiorno,
			Date dataFineProva,
			Date dataCessazione,
			Date dataFineContratto,
			String motivoDimissioni) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		TipoContratto tipoContratto = (TipoContratto)session.get(TipoContratto.class, codiceTipoContratto);
		Ccnl ccnl = (Ccnl)session.get(Ccnl.class, codiceCcnl);
		Azienda azienda = (Azienda)session.get(Azienda.class, codiceAzienda);

		// Create the new entity.
		//
		PosizioneLavorativa entity = new PosizioneLavorativa();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoContratto(tipoContratto);
		entity.setCcnl(ccnl);
		entity.setAzienda(azienda);
		entity.setDurataContratto(durataContratto);
		entity.setDataAssunzione(dataAssunzione);
		entity.setDurataProva(durataProva);
		entity.setDataPrimoGiorno(dataPrimoGiorno);
		entity.setDataFineProva(dataFineProva);
		entity.setDataCessazione(dataCessazione);
		entity.setDataFineContratto(dataFineContratto);
		entity.setMotivoDimissioni(motivoDimissioni);

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
	public PosizioneLavorativa update(
			Integer id,
			Integer codiceTipoContratto,
			Integer codiceCcnl,
			Integer codiceAzienda,
			Integer durataContratto,
			Date dataAssunzione,
			Integer durataProva,
			Date dataPrimoGiorno,
			Date dataFineProva,
			Date dataCessazione,
			Date dataFineContratto,
			String motivoDimissioni) {

		PosizioneLavorativa entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified posizione lavorativa: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoContratto tipoContratto = (TipoContratto)session.get(TipoContratto.class, codiceTipoContratto);
		Ccnl ccnl = (Ccnl)session.get(Ccnl.class, codiceCcnl);
		Azienda azienda = (Azienda)session.get(Azienda.class, codiceAzienda);

		// Set entity fields.
		//
		entity.setTipoContratto(tipoContratto);
		entity.setCcnl(ccnl);
		entity.setAzienda(azienda);
		entity.setDurataContratto(durataContratto);
		entity.setDataAssunzione(dataAssunzione);
		entity.setDurataProva(durataProva);
		entity.setDataPrimoGiorno(dataPrimoGiorno);
		entity.setDataFineProva(dataFineProva);
		entity.setDataCessazione(dataCessazione);
		entity.setDataFineContratto(dataFineContratto);
		entity.setMotivoDimissioni(motivoDimissioni);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		PosizioneLavorativa entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified posizione lavorativa: %d", id);
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
	public List<PosizioneLavorativa> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from PosizioneLavorativa pla " +
				"left join fetch pla.tipoContratto tco " +
				"left join fetch pla.ccnl ccn " +
				"left join fetch pla.azienda azi " +
				"where pla.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<PosizioneLavorativa> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}