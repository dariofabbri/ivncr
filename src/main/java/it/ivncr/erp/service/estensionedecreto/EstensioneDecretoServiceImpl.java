package it.ivncr.erp.service.estensionedecreto;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.EstensioneDecreto;
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

public class EstensioneDecretoServiceImpl extends AbstractService implements EstensioneDecretoService {

	@Override
	public QueryResult<EstensioneDecreto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public EstensioneDecreto retrieve(Integer id) {

		EstensioneDecreto estensione = (EstensioneDecreto)session.get(EstensioneDecreto.class, id);
		logger.debug("Estensione decreto found: " + estensione);

		return estensione;
	}

	@Override
	public EstensioneDecreto create(
			Integer codiceAddetto,
			String provincia,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		EstensioneDecreto entity = new EstensioneDecreto();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setProvincia(provincia);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataFineValidita(dataFineValidita);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Estensione decreto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public EstensioneDecreto update(
			Integer id,
			String provincia,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note) {

		EstensioneDecreto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified estensone decreto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setProvincia(provincia);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataFineValidita(dataFineValidita);
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

		EstensioneDecreto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified estensone decreto: %d", id);
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
	public List<EstensioneDecreto> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from EstensioneDecreto esd " +
				"where esd.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<EstensioneDecreto> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}