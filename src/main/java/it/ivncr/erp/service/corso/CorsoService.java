package it.ivncr.erp.service.corso;

import it.ivncr.erp.model.personale.Corso;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface CorsoService extends EntityService<Corso> {

	Corso retrieve(Integer id);

	Corso create(
			Integer codiceAddetto,
			String ente,
			String abilitazione,
			Integer oreCorso,
			String valutazione,
			Date dataConseguimento,
			Date dataInizio,
			Date dataFine,
			String note);

	Corso update(
			Integer id,
			String ente,
			String abilitazione,
			Integer oreCorso,
			String valutazione,
			Date dataConseguimento,
			Date dataInizio,
			Date dataFine,
			String note);

	void delete(Integer id);

	List<Corso> listByAddetto(Integer codiceAddetto);
}