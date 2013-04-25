package it.ivncr.erp.service.odsoraricalendario;

import it.ivncr.erp.model.commerciale.ods.OdsOrariCalendario;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface OdsOrariCalendarioService extends EntityService<OdsOrariCalendario> {

	OdsOrariCalendario retrieve(Integer id);
	OdsOrariCalendario retrieveDeep(Integer id);

	OdsOrariCalendario create(
			Integer codiceOrdineServizio,
			Date dataServizio,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3);

	OdsOrariCalendario update(
			Integer id,
			Date dataServizio,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3);

	void delete(Integer id);

	List<OdsOrariCalendario> listByOrdineServizio(Integer codiceOrdineServizio);
}