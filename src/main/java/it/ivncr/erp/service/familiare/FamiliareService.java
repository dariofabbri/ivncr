package it.ivncr.erp.service.familiare;

import it.ivncr.erp.model.personale.Familiare;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface FamiliareService extends EntityService<Familiare> {

	Familiare retrieve(Integer id);
	Familiare retrieveDeep(Integer id);

	Familiare create(
			Integer codiceAddetto,
			Integer codiceTipoFamiliare,
			String nome,
			String cognome,
			String luogoNascita,
			Date dataNascita,
			String note);

	Familiare update(
			Integer id,
			Integer codiceTipoFamiliare,
			String nome,
			String cognome,
			String luogoNascita,
			Date dataNascita,
			String note);

	void delete(Integer id);

	List<Familiare> listByAddetto(Integer codiceAddetto);
}