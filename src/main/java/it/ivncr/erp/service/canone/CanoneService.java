package it.ivncr.erp.service.canone;

import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.math.BigDecimal;
import java.util.Date;

public interface CanoneService extends EntityService<Canone> {

	QueryResult<Canone> list(
			Integer codiceContratto,
			String descrizione,
			String tipoServizio,
			String specificaServizio,
			String obiettivoServizio,
			String tipoFatturazione,
			Integer offset,
			Integer limit);

	Canone retrieve(Integer id);
	Canone retrieveDeep(Integer id);

	Canone create(
			Integer codiceContratto,
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Date dataInizioValidita,
			Integer codiceRaggruppamentoFatturazione,
			BigDecimal importoMensile,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note);

	Canone update(
			Integer id,
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Date dataInizioValidita,
			Integer codiceRaggruppamentoFatturazione,
			BigDecimal importoMensile,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note);

	void delete(Integer id);
}