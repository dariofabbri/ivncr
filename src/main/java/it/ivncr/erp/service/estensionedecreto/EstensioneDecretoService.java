package it.ivncr.erp.service.estensionedecreto;

import it.ivncr.erp.model.personale.EstensioneDecreto;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface EstensioneDecretoService extends EntityService<EstensioneDecreto> {

	EstensioneDecreto retrieve(Integer id);

	EstensioneDecreto create(
			Integer codiceAddetto,
			String provincia,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note);

	EstensioneDecreto update(
			Integer id,
			String provincia,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note);

	void delete(Integer id);

	List<EstensioneDecreto> listByAddetto(Integer codiceAddetto);
}