package it.ivncr.erp.service.odsfrazionamento;

import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.service.EntityService;

import java.math.BigDecimal;
import java.util.List;

public interface OdsFrazionamentoService extends EntityService<OdsFrazionamento> {

	OdsFrazionamento retrieve(Integer id);

	OdsFrazionamento retrieveDeep(Integer id);

	OdsFrazionamento create(
			Integer codiceOrdineServizio,
			Integer codiceCliente,
			BigDecimal quota,
			Boolean esclusioneRitenutaGaranzia);

	OdsFrazionamento update(
			Integer id,
			Integer codiceCliente,
			BigDecimal quota,
			Boolean esclusioneRitenutaGaranzia);

	void delete(Integer id);

	List<OdsFrazionamento> listByOrdineServizio(Integer codiceOrdineServizio);
}