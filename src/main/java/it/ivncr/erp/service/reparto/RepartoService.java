package it.ivncr.erp.service.reparto;

import it.ivncr.erp.model.personale.Reparto;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface RepartoService extends EntityService<Reparto> {

	Reparto retrieve(Integer id);

	List<Reparto> listByAzienda(Integer codiceAzienda);
}