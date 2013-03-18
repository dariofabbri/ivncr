package it.ivncr.erp.service.licenzaportoarmi;

import it.ivncr.erp.model.personale.LicenzaPortoArmi;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface LicenzaPortoArmiService extends EntityService<LicenzaPortoArmi> {

	LicenzaPortoArmi retrieve(Integer id);
	LicenzaPortoArmi retrieveDeep(Integer id);

	LicenzaPortoArmi create(
			Integer codiceAddetto,
			Integer codiceLibretto,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note);

	LicenzaPortoArmi update(
			Integer id,
			Integer codiceLibretto,
			String numero,
			Date dataRilascio,
			Date dataScadenza,
			String note);

	void delete(Integer id);

	List<LicenzaPortoArmi> listByAddetto(Integer codiceAddetto);
}