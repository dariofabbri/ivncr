package it.ivncr.erp.service.dettagliofatturazione;

import it.ivncr.erp.model.commerciale.contratto.DettaglioFatturazione;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.Date;

public interface DettaglioFatturazioneService extends EntityService<DettaglioFatturazione> {

	QueryResult<DettaglioFatturazione> list(
			Integer codiceContratto,
			String condizioniPagamento,
			String metodoPagamento,
			String indirizzo,
			Integer offset,
			Integer limit);

	DettaglioFatturazione retrieve(Integer id);
	DettaglioFatturazione retrieveDeep(Integer id);

	DettaglioFatturazione create(
			Integer codiceContratto,
			Integer codiceCondizioniPagamento,
			Integer codiceMetodoPagamento,
			Integer codiceIndirizzo,
			Integer codiceLayoutStampa,
			Date validoDa,
			Date validoA);

	DettaglioFatturazione update(
			Integer id,
			Integer codiceCondizioniPagamento,
			Integer codiceMetodoPagamento,
			Integer codiceIndirizzo,
			Integer codiceLayoutStampa,
			Date validoDa,
			Date validoA);

	void delete(Integer id);
}