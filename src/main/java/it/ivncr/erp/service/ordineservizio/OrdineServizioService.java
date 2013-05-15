package it.ivncr.erp.service.ordineservizio;

import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

	QueryResult<OrdineServizio> listFromContratto(
			int first,
			int pageSize,
			String sortField,
			SortDirection fromSortOrder,
			Map<String, String> filters);

	OrdineServizio retrieve(Integer id);
	OrdineServizio retrieveDeep(Integer id);

	OrdineServizio createNuovaAttivazione(
			Integer codiceContratto,
			String alias,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Boolean oneroso,
			Boolean cessato);

	OrdineServizio createVariazione(
			Integer codicePadre,
			Integer codiceTipoOrdineServizio,
			String alias,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Boolean cessato);

	OrdineServizio updateTestata(
			Integer id,
			String alias,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Boolean cessato);

	OrdineServizio updateFatturazione(
			Integer id,
			Boolean oneroso,
			Integer codiceTariffa,
			Integer codiceCanone,
			Integer codiceRaggruppamentoFatturazione,
			String osservazioniFattura,
			List<OdsFrazionamento> listOdsFrazionamento);

	OrdineServizio setNote(Integer id, String note);

	OrdineServizio setModalitaOperative(Integer id, String modalitaOperative);

	OrdineServizio setOsservazioniFattura(Integer id, String osservazioniFattura);

	String peekNextCodice(Integer codiceAzienda, Integer anno);
	String retrieveNextCodice(Integer codiceAzienda, Integer anno);

	List<Tariffa> listAvailableTariffa(Integer id);
	List<Canone> listAvailableCanone(Integer id);
}