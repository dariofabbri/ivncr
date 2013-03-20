package it.ivncr.erp.service.datisanitari;

import it.ivncr.erp.model.personale.DatiSanitari;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface DatiSanitariService extends EntityService<DatiSanitari> {

	DatiSanitari retrieve(Integer id);
	DatiSanitari retrieveDeep(Integer id);

	DatiSanitari create(
			Integer codiceAddetto,
			String medicoBase,
			Integer codiceGruppoSanguigno,
			String asl,
			String indirizzoAsl,
			String comune,
			String provincia,
			Date validoDa,
			Date validoA);

	DatiSanitari update(
			Integer id,
			String medicoBase,
			Integer codiceGruppoSanguigno,
			String asl,
			String indirizzoAsl,
			String comune,
			String provincia,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<DatiSanitari> listByAddetto(Integer codiceAddetto);


	List<String> listDistinctComune();
}