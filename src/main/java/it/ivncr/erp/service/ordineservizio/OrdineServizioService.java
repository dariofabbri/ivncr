package it.ivncr.erp.service.ordineservizio;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.Date;

public interface OrdineServizioService extends EntityService<OrdineServizio> {

	QueryResult<OrdineServizio> list(
			Integer codiceAzienda,
			String codiceContratto,
			String codice,
			String alias,
			String tipoServizio,
			String specificaServizio,
			String obiettivoServizio,
			Integer offset,
			Integer limit);

	OrdineServizio retrieve(Integer id);
	OrdineServizio retrieveDeep(Integer id);

	OrdineServizio create(
			Integer codiceContratto,
			String alias,
			Integer codiceTipoOrdineServizio,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Integer codiceTariffa,
			Integer codiceCanone,
			Boolean oneroso,
			Integer codiceRaggruppamentoFatturazione,
			Boolean cessato);

	OrdineServizio update(
			Integer id,
			String alias,
			Integer codiceTipoOrdineServizio,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Integer codiceTariffa,
			Integer codiceCanone,
			Boolean oneroso,
			Integer codiceRaggruppamentoFatturazione,
			Boolean cessato);

	OrdineServizio setNote(Integer id, String note);

	OrdineServizio setModalitaOperative(Integer id, String modalitaOperative);

	OrdineServizio setOsservazioniFattura(Integer id, String osservazioniFattura);

	String peekNextCodice(Integer codiceAzienda, Integer anno);
	String retrieveNextCodice(Integer codiceAzienda, Integer anno);
}