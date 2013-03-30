package it.ivncr.erp.service.gestorecontratto;

import it.ivncr.erp.model.commerciale.contratto.GestoreContratto;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface GestoreContrattoService extends EntityService<GestoreContratto> {

	List<GestoreContratto> listDisponibiliPerContratto(Integer contrattoId);

	List<GestoreContratto> listDisponibiliPerContratto(Integer contrattoId, Integer gestoreId);
}