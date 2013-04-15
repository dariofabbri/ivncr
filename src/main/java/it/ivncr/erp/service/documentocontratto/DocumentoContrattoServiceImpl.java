package it.ivncr.erp.service.documentocontratto;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.DocumentoContratto;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Date;
import java.util.Map;

public class DocumentoContrattoServiceImpl extends AbstractService implements DocumentoContrattoService {

	@Override
	public QueryResult<DocumentoContratto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceContrattoDescrizioneFilenameMimeType q =
				new QueryByCodiceContrattoDescrizioneFilenameMimeType(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		String descrizione = filters.get("descrizione");
		String filename = filters.get("filename");
		String mimeType = filters.get("mimeType");

		q.setCodiceContratto(codiceContratto);
		q.setDescrizione(descrizione);
		q.setFilename(filename);
		q.setMimeType(mimeType);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<DocumentoContratto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<DocumentoContratto> list(
			Integer codiceContratto,
			String descrizione,
			String filename,
			String mimeType,
			Integer offset,
			Integer limit) {

		QueryByCodiceContrattoDescrizioneFilenameMimeType q =
				new QueryByCodiceContrattoDescrizioneFilenameMimeType(session);

		q.setCodiceContratto(codiceContratto);
		q.setDescrizione(descrizione);
		q.setFilename(filename);
		q.setMimeType(mimeType);

		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<DocumentoContratto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}


	@Override
	public DocumentoContratto retrieve(Integer id) {

		DocumentoContratto documento = (DocumentoContratto)session.get(DocumentoContratto.class, id);
		logger.debug("Documento contratto found: " + documento);

		return documento;
	}


	@Override
	public DocumentoContratto create(
			Integer codiceContratto,
			String descrizione,
			String filename,
			String mimeType,
			byte[] documento,
			String note) {

		Date now = new Date();

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);

		// Create the new entity.
		//
		DocumentoContratto entity = new DocumentoContratto();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setDescrizione(descrizione);
		entity.setFilename(filename);
		entity.setMimeType(mimeType);
		entity.setCaricamento(now);
		entity.setDocumento(documento);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Documento contratto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public DocumentoContratto update(
			Integer id,
			String descrizione,
			String filename,
			String mimeType,
			String note) {

		DocumentoContratto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified documento contratto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setDescrizione(descrizione);
		entity.setFilename(filename);
		entity.setMimeType(mimeType);
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

		DocumentoContratto entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified documento contratto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}
}