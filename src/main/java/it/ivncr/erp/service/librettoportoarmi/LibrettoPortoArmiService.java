package it.ivncr.erp.service.librettoportoarmi;

import it.ivncr.erp.model.personale.LibrettoPortoArmi;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface LibrettoPortoArmiService extends EntityService<LibrettoPortoArmi> {

	LibrettoPortoArmi retrieve(Integer id);

	LibrettoPortoArmi create(
			Integer codiceAddetto,
			String numero,
			Boolean fucile,
			Date dataRilascio,
			Date dataScadenza,
			String note);

	LibrettoPortoArmi update(
			Integer id,
			String numero,
			Boolean fucile,
			Date dataRilascio,
			Date dataScadenza,
			String note);

	void delete(Integer id);

	List<LibrettoPortoArmi> listByAddetto(Integer codiceAddetto);
}