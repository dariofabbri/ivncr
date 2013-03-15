package it.ivncr.erp.service.documento;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Documento;
import it.ivncr.erp.model.personale.TipoDocumento;
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

public class DocumentoServiceImpl extends AbstractService implements DocumentoService {

	@Override
	public QueryResult<Documento> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Documento retrieve(Integer id) {

		Documento documento = (Documento)session.get(Documento.class, id);
		logger.debug("Documento found: " + documento);

		return documento;
	}

	@Override
	public Documento retrieveDeep(Integer id) {

		String hql =
				"from Documento doc " +
				"left join fetch doc.tipoDocumento tdo " +
				"where doc.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Documento documento = (Documento)query.uniqueResult();
		logger.debug("Documento found: " + documento);

		return documento;
	}

	@Override
	public Documento create(
			Integer codiceAddetto,
			Integer codiceTipoDocumento,
			String numero,
			String rilasciatoDa,
			Date dataRilascio,
			Date dataScadenza) {

		// Fetch referred entities.
		//
		TipoDocumento tipoDocumento = (TipoDocumento)session.get(TipoDocumento.class, codiceTipoDocumento);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		Documento entity = new Documento();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoDocumento(tipoDocumento);
		entity.setNumero(numero);
		entity.setRilasciatoDa(rilasciatoDa);
		entity.setDataRilascio(dataRilascio);
		entity.setDataScadenza(dataScadenza);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Documento successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Documento update(
			Integer id,
			Integer codiceTipoDocumento,
			String numero,
			String rilasciatoDa,
			Date dataRilascio,
			Date dataScadenza) {

		Documento entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified documento: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoDocumento tipoDocumento = (TipoDocumento)session.get(TipoDocumento.class, codiceTipoDocumento);

		// Set entity fields.
		//
		entity.setTipoDocumento(tipoDocumento);
		entity.setNumero(numero);
		entity.setRilasciatoDa(rilasciatoDa);
		entity.setDataRilascio(dataRilascio);
		entity.setDataScadenza(dataScadenza);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		Documento entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified documento: %d", id);
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
	public List<Documento> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from Documento doc " +
				"left join fetch doc.tipoDocumento tdo " +
				"where doc.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<Documento> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}