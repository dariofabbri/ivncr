package it.ivncr.erp.service.indirizzoaddetto;

import it.ivncr.erp.model.personale.IndirizzoAddetto;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface IndirizzoAddettoService extends EntityService<IndirizzoAddetto> {

	IndirizzoAddetto retrieve(Integer id);
	IndirizzoAddetto retrieveDeep(Integer id);

	IndirizzoAddetto create(
			Integer codiceAddetto,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese,
			Date validoDa,
			Date validoA);

	IndirizzoAddetto update(
			Integer id,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<IndirizzoAddetto> listByAddetto(Integer codiceAddetto);
}