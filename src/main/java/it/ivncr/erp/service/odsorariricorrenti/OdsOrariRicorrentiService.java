package it.ivncr.erp.service.odsorariricorrenti;

import it.ivncr.erp.model.commerciale.ods.OdsOrariRicorrenti;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface OdsOrariRicorrentiService extends EntityService<OdsOrariRicorrenti> {

	OdsOrariRicorrenti retrieve(Integer id);

	OdsOrariRicorrenti retrieveByOrdineServizioGiornoSettimana(
			Integer codiceOrdineServizio,
			Integer codiceGiornoSettimana);

	OdsOrariRicorrenti setGiorno(
			Integer codiceOrdineServizio,
			Integer codiceGiornoSettimana,
			Boolean esclusoFestivo,
			Integer quantita1,
			Date orarioInizio1,
			Date orarioFine1,
			Integer quantita2,
			Date orarioInizio2,
			Date orarioFine2,
			Integer quantita3,
			Date orarioInizio3,
			Date orarioFine3);

	List<OdsOrariRicorrenti> setGiorni(
			Integer codiceOrdineServizio,
			Integer[] codiciGiornoSettimana,
			Boolean esclusoFestivo,
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

	void delete(Iterable<Integer> ids);

	void deleteByOrdineServizio(Integer codiceOrdineServizio);

	List<OdsOrariRicorrenti> listByOrdineServizio(Integer codiceOrdineServizio);
}