package it.ivncr.erp.service.visitamedicocompetente;

import it.ivncr.erp.model.personale.VisitaMedicoCompetente;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface VisitaMedicoCompetenteService extends EntityService<VisitaMedicoCompetente> {

	VisitaMedicoCompetente retrieve(Integer id);

	VisitaMedicoCompetente create(
			Integer codiceAddetto,
			String medico,
			Date dataVisita,
			String esito,
			Date dataVisitaSuccessiva);

	VisitaMedicoCompetente update(
			Integer id,
			String medico,
			Date dataVisita,
			String esito,
			Date dataVisitaSuccessiva);

	void delete(Integer id);

	List<VisitaMedicoCompetente> listByAddetto(Integer codiceAddetto);
}