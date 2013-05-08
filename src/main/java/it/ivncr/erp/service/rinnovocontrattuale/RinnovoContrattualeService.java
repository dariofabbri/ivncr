package it.ivncr.erp.service.rinnovocontrattuale;

import it.ivncr.erp.model.commerciale.contratto.RinnovoContrattuale;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface RinnovoContrattualeService extends EntityService<RinnovoContrattuale> {

	RinnovoContrattuale retrieve(Integer id);

	RinnovoContrattuale create(
			Integer codiceContratto,
			Date dataDecorrenzaPre,
			Date dataTerminePre,
			Date dataDecorrenzaPost,
			Date dataTerminePost,
			String note);

	RinnovoContrattuale update(
			Integer id,
			Date dataDecorrenzaPre,
			Date dataTerminePre,
			Date dataDecorrenzaPost,
			Date dataTerminePost,
			String note);

	void delete(Integer id);

	List<RinnovoContrattuale> listByContratto(Integer codiceContratto);
}