package it.ivncr.erp.service.indirizzo;

import it.ivncr.erp.model.commerciale.Indirizzo;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

public interface IndirizzoService extends EntityService<Indirizzo> {

	QueryResult<Indirizzo> list(
			Integer codiceCliente,
			String destinatario1,
			String destinatario2,
			String indirizzo,
			String localita,
			String cap,
			String provincia,
			String paese,
			Integer offset,
			Integer limit);

	Indirizzo retrieve(Integer id);
	Indirizzo retrieveDeep(Integer id);

	Indirizzo create(
			Integer codiceCliente,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese);

	Indirizzo update(
			Integer id,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese);

	void delete(Integer id);
}