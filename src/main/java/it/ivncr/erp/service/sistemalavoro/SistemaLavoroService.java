package it.ivncr.erp.service.sistemalavoro;

import it.ivncr.erp.model.personale.SistemaLavoro;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface SistemaLavoroService extends EntityService<SistemaLavoro> {

	SistemaLavoro retrieve(Integer id);
	SistemaLavoro retrieveDeep(Integer id);

	SistemaLavoro create(
			Integer codiceAddetto,
			Integer codiceTipoSistemaLavoro,
			Date validoDa,
			Date validoA);

	SistemaLavoro update(
			Integer id,
			Integer codiceTipoSistemaLavoro,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<SistemaLavoro> listByAddetto(Integer codiceAddetto);
}