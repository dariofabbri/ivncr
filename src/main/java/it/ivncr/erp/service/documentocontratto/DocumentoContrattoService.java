package it.ivncr.erp.service.documentocontratto;

import it.ivncr.erp.model.commerciale.contratto.DocumentoContratto;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

public interface DocumentoContrattoService extends EntityService<DocumentoContratto> {

	QueryResult<DocumentoContratto> list(
			Integer codiceContratto,
			String descrizione,
			String filename,
			String mimeType,
			Integer offset,
			Integer limit);

	DocumentoContratto retrieve(Integer id);

	DocumentoContratto create(
			Integer codiceContratto,
			String descrizione,
			String filename,
			String mimeType,
			byte[] documento,
			String note);

	DocumentoContratto update(
			Integer id,
			String descrizione,
			String filename,
			String mimeType,
			String note);

	void delete(Integer id);
}