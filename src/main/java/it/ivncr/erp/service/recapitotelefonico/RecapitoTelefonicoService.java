package it.ivncr.erp.service.recapitotelefonico;

import it.ivncr.erp.model.personale.RecapitoTelefonico;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface RecapitoTelefonicoService extends EntityService<RecapitoTelefonico> {

	RecapitoTelefonico retrieve(Integer id);
	RecapitoTelefonico retrieveDeep(Integer id);

	RecapitoTelefonico create(
			Integer codiceAddetto,
			Integer codiceTipoRecapitoTelefonico,
			String recapito);

	RecapitoTelefonico update(
			Integer id,
			Integer codiceTipoRecapitoTelefonico,
			String recapito);

	void delete(Integer id);

	List<RecapitoTelefonico> listByAddetto(Integer codiceAddetto);
}