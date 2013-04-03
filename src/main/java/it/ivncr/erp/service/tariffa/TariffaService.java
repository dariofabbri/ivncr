package it.ivncr.erp.service.tariffa;

import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.math.BigDecimal;
import java.util.Date;

public interface TariffaService extends EntityService<Tariffa> {

	QueryResult<Tariffa> list(
			Integer codiceContratto,
			String descrizione,
			String tipoServizio,
			String specificaServizio,
			String obiettivoServizio,
			String tipoTariffa,
			String tipoFatturazione,
			Integer offset,
			Integer limit);

	Tariffa retrieve(Integer id);
	Tariffa retrieveDeep(Integer id);

	Tariffa create(
			Integer codiceContratto,
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Integer codiceTipoTariffa,
			BigDecimal costo,
			Integer numero,
			Date dataInizioValidita,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note);

	Tariffa update(
			Integer id,
			String descrizione,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Integer codiceTipoTariffa,
			BigDecimal costo,
			Integer numero,
			Date dataInizioValidita,
			Integer codiceTipoFatturazione,
			Date dataCessazione,
			String note);

	void delete(Integer id);
}