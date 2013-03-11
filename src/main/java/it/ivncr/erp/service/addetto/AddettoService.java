package it.ivncr.erp.service.addetto;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.Date;

public interface AddettoService extends EntityService<Addetto> {

	QueryResult<Addetto> list(
			Integer codiceAzienda,
			String matricola,
			String nome,
			String cognome,
			String codiceFiscale,
			Integer offset,
			Integer limit);

	Addetto retrieve(Integer id);
	Addetto retrieveDeep(Integer id);
	Addetto retrieveByMatricola(Integer codiceAzienda, String matricola);

	Addetto create(
			Integer codiceAzienda,
			String matricola,
			String nome,
			String cognome,
			Date dataNascita,
			String luogoNascita,
			String codiceFiscale,
			String sesso,
			String note,
			Boolean fittizio,
			Date dataGiuramento,
			Integer codiceStatoCivile);

	Addetto update(
			Integer id,
			String nome,
			String cognome,
			Date dataNascita,
			String luogoNascita,
			String codiceFiscale,
			String sesso,
			String note,
			Boolean fittizio,
			Date dataGiuramento,
			Integer codiceStatoCivile);

	String retrieveNextMatricola(Integer codiceAzienda);

	void setFoto(Integer id, byte[] foto);
}