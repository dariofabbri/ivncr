package it.ivncr.erp.service.visitacollegiale;

import it.ivncr.erp.model.personale.VisitaCollegiale;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface VisitaCollegialeService extends EntityService<VisitaCollegiale> {

	VisitaCollegiale retrieve(Integer id);

	VisitaCollegiale create(
			Integer codiceAddetto,
			Date dataRichiesta,
			String motivazione,
			Date dataEsito,
			String esito);

	VisitaCollegiale update(
			Integer id,
			Date dataRichiesta,
			String motivazione,
			Date dataEsito,
			String esito);

	void delete(Integer id);

	List<VisitaCollegiale> listByAddetto(Integer codiceAddetto);
}