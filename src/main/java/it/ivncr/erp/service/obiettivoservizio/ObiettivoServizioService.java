package it.ivncr.erp.service.obiettivoservizio;

import it.ivncr.erp.model.commerciale.ObiettivoServizio;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

public interface ObiettivoServizioService extends EntityService<ObiettivoServizio> {

	QueryResult<ObiettivoServizio> list(
			Integer codiceCliente,
			String alias,
			String indirizzo,
			String edificio,
			String scala,
			String piano,
			String interno,
			String localita,
			String cap,
			String provincia,
			String paese,
			Integer offset,
			Integer limit);

	ObiettivoServizio retrieve(Integer id);

	ObiettivoServizio create(
			Integer codiceCliente,
			String alias,
			String toponimo,
			String indirizzo,
			String civico,
			String edificio,
			String scala,
			String piano,
			String interno,
			String localita,
			String cap,
			String provincia,
			String paese,
			String note);

	ObiettivoServizio update(
			Integer id,
			String alias,
			String toponimo,
			String indirizzo,
			String civico,
			String edificio,
			String scala,
			String piano,
			String interno,
			String localita,
			String cap,
			String provincia,
			String paese,
			String note);

	void delete(Integer id);
}