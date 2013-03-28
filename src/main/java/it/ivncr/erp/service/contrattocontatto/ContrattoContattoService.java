package it.ivncr.erp.service.contrattocontatto;

import it.ivncr.erp.model.commerciale.contratto.ContrattoContatto;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface ContrattoContattoService extends EntityService<ContrattoContatto> {

	ContrattoContatto retrieve(Integer id);
	ContrattoContatto retrieveDeep(Integer id);

	ContrattoContatto create(
			Integer codiceContratto,
			Integer codiceContatto,
			Date validoDa,
			Date validoA);

	ContrattoContatto update(
			Integer id,
			Integer codiceContatto,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<ContrattoContatto> listByContratto(Integer codiceContratto);
}