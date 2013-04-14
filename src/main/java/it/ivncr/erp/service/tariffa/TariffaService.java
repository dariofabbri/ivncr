package it.ivncr.erp.service.tariffa;

import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TariffaService extends EntityService<Tariffa> {

	QueryResult<Tariffa> list(
			Integer codiceContratto,
			String alias,
			String tipoServizio,
			String specificaServizio,
			Integer offset,
			Integer limit);

	Tariffa retrieve(Integer id);
	Tariffa retrieveDeep(Integer id);

	Tariffa create(
			Integer codiceContratto,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			BigDecimal costoOrario,
			BigDecimal costoOperazione,
			BigDecimal costoFissoUnaTantum,
			BigDecimal costoFissoATempo,
			Integer costoFissoMesi,
			Integer franchigieTotali,
			Integer franchigieATempo,
			Integer franchigieMesi,
			BigDecimal ritenutaGaranzia,
			Integer ritenutaGaranziaGiorni,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturazioneAnticipata,
			Boolean extraFatturatoAParte,
			Boolean fatturaSpezzata,
			Integer fatturaOgniMesi,
			Boolean fatturaMinimoUnMese,
			String note);

	Tariffa update(
			Integer id,
			String alias,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			BigDecimal costoOrario,
			BigDecimal costoOperazione,
			BigDecimal costoFissoUnaTantum,
			BigDecimal costoFissoATempo,
			Integer costoFissoMesi,
			Integer franchigieTotali,
			Integer franchigieATempo,
			Integer franchigieMesi,
			BigDecimal ritenutaGaranzia,
			Integer ritenutaGaranziaGiorni,
			Date dataInizioValidita,
			Date dataCessazione,
			Boolean fatturazioneAnticipata,
			Boolean extraFatturatoAParte,
			Boolean fatturaSpezzata,
			Integer fatturaOgniMesi,
			Boolean fatturaMinimoUnMese,
			String note);

	void delete(Integer id);

	List<Tariffa> listByContratto(Integer id);
}