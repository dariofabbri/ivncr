package it.ivncr.erp.service.esattore;

import it.ivncr.erp.model.commerciale.contratto.Esattore;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface EsattoreService extends EntityService<Esattore> {

	List<Esattore> listDisponibiliPerContratto(Integer contrattoId);

	List<Esattore> listDisponibiliPerContratto(Integer contrattoId, Integer esattoreId);
}