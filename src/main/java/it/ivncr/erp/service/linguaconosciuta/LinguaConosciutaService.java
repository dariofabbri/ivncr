package it.ivncr.erp.service.linguaconosciuta;

import it.ivncr.erp.model.personale.LinguaConosciuta;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface LinguaConosciutaService extends EntityService<LinguaConosciuta> {

	LinguaConosciuta retrieve(Integer id);
	LinguaConosciuta retrieveDeep(Integer id);

	LinguaConosciuta create(
			Integer codiceAddetto,
			Integer codiceLingua,
			Integer codiceLivelloLingua,
			String note);

	LinguaConosciuta update(
			Integer id,
			Integer codiceLingua,
			Integer codiceLivelloLingua,
			String note);

	void delete(Integer id);

	List<LinguaConosciuta> listByAddetto(Integer codiceAddetto);
}