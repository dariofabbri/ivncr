package it.ivncr.erp.service.decretogpg;

import it.ivncr.erp.model.personale.DecretoGpg;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface DecretoGpgService extends EntityService<DecretoGpg> {

	DecretoGpg retrieve(Integer id);
	DecretoGpg retrieveDeep(Integer id);

	DecretoGpg create(
			Integer codiceAddetto,
			Integer codiceTipoRinnovo,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note);

	DecretoGpg update(
			Integer id,
			Integer codiceTipoRinnovo,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note);

	void delete(Integer id);

	List<DecretoGpg> listByAddetto(Integer codiceAddetto);
}