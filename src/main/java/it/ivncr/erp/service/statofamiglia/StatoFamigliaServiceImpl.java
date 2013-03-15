package it.ivncr.erp.service.statofamiglia;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.GradoParentela;
import it.ivncr.erp.model.personale.StatoFamiglia;
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

public class StatoFamigliaServiceImpl extends AbstractService implements StatoFamigliaService {

	@Override
	public QueryResult<StatoFamiglia> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public StatoFamiglia retrieve(Integer id) {

		StatoFamiglia statoFamiglia = (StatoFamiglia)session.get(StatoFamiglia.class, id);
		logger.debug("Stato famiglia found: " + statoFamiglia);

		return statoFamiglia;
	}

	@Override
	public StatoFamiglia retrieveDeep(Integer id) {

		String hql =
				"from StatoFamiglia sfa " +
				"left join fetch sfa.gradoParentela gpa " +
				"where sfa.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		StatoFamiglia statoFamiglia = (StatoFamiglia)query.uniqueResult();
		logger.debug("Stato famiglia found: " + statoFamiglia);

		return statoFamiglia;
	}

	@Override
	public StatoFamiglia create(
			Integer codiceAddetto,
			Integer codiceGradoParentela,
			String nome,
			Date dataNascita,
			Date validoDa,
			Date validoA) {

		// Fetch referred entities.
		//
		GradoParentela gradoParentela = (GradoParentela)session.get(GradoParentela.class, codiceGradoParentela);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		StatoFamiglia entity = new StatoFamiglia();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setGradoParentela(gradoParentela);
		entity.setNome(nome);
		entity.setDataNascita(dataNascita);
		entity.setValidoDa(validoDa);
		entity.setValidoA(validoA);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Stato famiglia successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public StatoFamiglia update(
			Integer id,
			Integer codiceGradoParentela,
			String nome,
			Date dataNascita,
			Date validoDa,
			Date validoA) {

		StatoFamiglia entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified stato famiglia: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		GradoParentela gradoParentela = (GradoParentela)session.get(GradoParentela.class, codiceGradoParentela);

		// Set entity fields.
		//
		entity.setGradoParentela(gradoParentela);
		entity.setNome(nome);
		entity.setDataNascita(dataNascita);
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

		StatoFamiglia entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified stato famiglia: %d", id);
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
	public List<StatoFamiglia> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from StatoFamiglia sfa " +
				"left join fetch sfa.gradoParentela gpa " +
				"where sfa.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<StatoFamiglia> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}