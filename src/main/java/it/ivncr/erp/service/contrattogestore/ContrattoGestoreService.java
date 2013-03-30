package it.ivncr.erp.service.contrattogestore;

import it.ivncr.erp.model.commerciale.contratto.ContrattoGestore;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface ContrattoGestoreService extends EntityService<ContrattoGestore> {

	ContrattoGestore retrieve(Integer id);
	ContrattoGestore retrieveDeep(Integer id);

	ContrattoGestore create(
			Integer codiceContratto,
			Integer codiceGestore,
			Date validoDa,
			Date validoA);

	ContrattoGestore update(
			Integer id,
			Integer codiceGestore,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<ContrattoGestore> listByContratto(Integer codiceContratto);
}