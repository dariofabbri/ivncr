package it.ivncr.erp.service.canone;

import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CanoneService extends EntityService<Canone> {

	QueryResult<Canone> list(
			Integer codiceContratto,
			String alias,
			String tipoServizio,
			String specificaServizio,
			Integer offset,
			Integer limit);

	Canone retrieve(Integer id);
	Canone retrieveDeep(Integer id);

	Canone create(
			Integer codiceContratto,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note);

	Canone update(
			Integer id,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note);

	void delete(Integer id);

	List<Canone> listByContratto(Integer id);
}