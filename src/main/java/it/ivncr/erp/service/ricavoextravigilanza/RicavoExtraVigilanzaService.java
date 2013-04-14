package it.ivncr.erp.service.ricavoextravigilanza;

import it.ivncr.erp.model.commerciale.contratto.RicavoExtraVigilanza;
import it.ivncr.erp.service.EntityService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RicavoExtraVigilanzaService extends EntityService<RicavoExtraVigilanza> {

	RicavoExtraVigilanza retrieve(Integer id);
	RicavoExtraVigilanza retrieveDeep(Integer id);

	RicavoExtraVigilanza create(
			Integer codiceContratto,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceRaggruppamentoFatturazione,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note);

	RicavoExtraVigilanza update(
			Integer id,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceRaggruppamentoFatturazione,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturaMinimoUnMese,
			Boolean fatturazioneAnticipata,
			Integer fatturaOgniMesi,
			BigDecimal canoneMensile,
			String note);

	void delete(Integer id);

	List<RicavoExtraVigilanza> listByContratto(Integer id);
}