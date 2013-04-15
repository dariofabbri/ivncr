package it.ivncr.erp.service.apparecchiaturatecnologica;

import it.ivncr.erp.model.commerciale.contratto.ApparecchiaturaTecnologica;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.math.BigDecimal;
import java.util.Date;

public interface ApparecchiaturaTecnologicaService extends EntityService<ApparecchiaturaTecnologica> {

	QueryResult<ApparecchiaturaTecnologica> list(
			Integer codiceContratto,
			String tipoApparecchiatura,
			String gruppoApparecchiatura,
			String matricola,
			String descrizione,
			Integer offset,
			Integer limit);

	ApparecchiaturaTecnologica retrieve(Integer id);
	ApparecchiaturaTecnologica retrieveDeep(Integer id);

	ApparecchiaturaTecnologica create(
			Integer codiceContratto,
			Integer codiceTipoApparecchiaturaTecnologica,
			String descrizione,
			String matricola,
			Boolean comodatoUso,
			Date dataInstallazione,
			Date dataFatturazione,
			Date dataRitiro,
			BigDecimal costoUnaTantum,
			String note);

	ApparecchiaturaTecnologica update(
			Integer id,
			Integer codiceTipoApparecchiaturaTecnologica,
			String descrizione,
			String matricola,
			Boolean comodatoUso,
			Date dataInstallazione,
			Date dataFatturazione,
			Date dataRitiro,
			BigDecimal costoUnaTantum,
			String note);

	void delete(Integer id);
}