package it.ivncr.erp.service.avanzamentocarriera;

import it.ivncr.erp.model.personale.AvanzamentoCarriera;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface AvanzamentoCarrieraService extends EntityService<AvanzamentoCarriera> {

	AvanzamentoCarriera retrieve(Integer id);
	AvanzamentoCarriera retrieveDeep(Integer id);

	AvanzamentoCarriera create(
			Integer codiceAddetto,
			Integer codiceQualifica,
			Integer codiceLivelloCcnl,
			Date validoDa,
			Date validoA);

	AvanzamentoCarriera update(
			Integer id,
			Integer codiceQualifica,
			Integer codiceLivelloCcnl,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<AvanzamentoCarriera> listByAddetto(Integer codiceAddetto);
}