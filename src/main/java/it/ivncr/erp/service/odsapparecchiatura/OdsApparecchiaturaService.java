package it.ivncr.erp.service.odsapparecchiatura;

import it.ivncr.erp.model.commerciale.ods.OdsApparecchiatura;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface OdsApparecchiaturaService extends EntityService<OdsApparecchiatura> {

	OdsApparecchiatura retrieve(Integer id);
	OdsApparecchiatura retrieveDeep(Integer id);

	OdsApparecchiatura create(
			Integer codiceOrdineServizio,
			Integer codiceApparecchiaturaTecnologica);

	OdsApparecchiatura update(
			Integer id,
			Integer codiceApparecchiaturaTecnologica);

	void delete(Integer id);

	List<OdsApparecchiatura> listByOrdineServizio(Integer codiceOrdineServizio);
}