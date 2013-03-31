package it.ivncr.erp.service.contrattoesattore;

import it.ivncr.erp.model.commerciale.contratto.ContrattoEsattore;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface ContrattoEsattoreService extends EntityService<ContrattoEsattore> {

	ContrattoEsattore retrieve(Integer id);
	ContrattoEsattore retrieveDeep(Integer id);

	ContrattoEsattore create(
			Integer codiceContratto,
			Integer codiceEsattore,
			Date validoDa,
			Date validoA);

	ContrattoEsattore update(
			Integer id,
			Integer codiceEsattore,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<ContrattoEsattore> listByContratto(Integer codiceContratto);
}