package it.ivncr.erp.service.addettoreparto;

import it.ivncr.erp.model.personale.AddettoReparto;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface AddettoRepartoService extends EntityService<AddettoReparto> {

	AddettoReparto retrieve(Integer id);
	AddettoReparto retrieveDeep(Integer id);

	AddettoReparto create(
			Integer codiceAddetto,
			Integer codiceReparto,
			Integer codiceRuolo,
			Date validoDa,
			Date validoA);

	AddettoReparto update(
			Integer id,
			Integer codiceReparto,
			Integer codiceRuolo,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<AddettoReparto> listByAddetto(Integer codiceAddetto);
}