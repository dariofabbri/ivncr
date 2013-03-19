package it.ivncr.erp.service.posizionemilitare;

import it.ivncr.erp.model.personale.PosizioneMilitare;
import it.ivncr.erp.service.EntityService;

import java.util.Date;

public interface PosizioneMilitareService extends EntityService<PosizioneMilitare> {

	PosizioneMilitare retrieve(Integer id);
	PosizioneMilitare retrieveDeep(Integer id);

	PosizioneMilitare retrieveByAddettoId(Integer addettoId);

	PosizioneMilitare setForAddetto(
			Integer addettoId,
			Integer codiceTipoPosizione,
			String presso,
			Integer codiceGrado,
			Date dataCongedo,
			String note);
}