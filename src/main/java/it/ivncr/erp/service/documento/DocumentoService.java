package it.ivncr.erp.service.documento;

import it.ivncr.erp.model.personale.Documento;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface DocumentoService extends EntityService<Documento> {

	Documento retrieve(Integer id);
	Documento retrieveDeep(Integer id);

	Documento create(
			Integer codiceAddetto,
			Integer codiceTipoDocumento,
			String numero,
			String rilasciatoDa,
			Date dataRilascio,
			Date dataScadenza);

	Documento update(
			Integer id,
			Integer codiceTipoDocumento,
			String numero,
			String rilasciatoDa,
			Date dataRilascio,
			Date dataScadenza);

	void delete(Integer id);

	List<Documento> listByAddetto(Integer codiceAddetto);
}