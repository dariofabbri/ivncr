package it.ivncr.erp.service.infosindacali;

import it.ivncr.erp.model.personale.InfoSindacali;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface InfoSindacaliService extends EntityService<InfoSindacali> {

	InfoSindacali retrieve(Integer id);
	InfoSindacali retrieveDeep(Integer id);

	InfoSindacali create(
			Integer codiceAddetto,
			Integer codiceSiglaSindacale,
			Integer codiceCaricaSindacale,
			Date validoDa,
			Date validoA);

	InfoSindacali update(
			Integer id,
			Integer codiceSiglaSindacale,
			Integer codiceCaricaSindacale,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<InfoSindacali> listByAddetto(Integer codiceAddetto);
}