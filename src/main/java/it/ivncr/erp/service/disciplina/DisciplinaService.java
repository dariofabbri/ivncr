package it.ivncr.erp.service.disciplina;

import it.ivncr.erp.model.personale.Disciplina;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface DisciplinaService extends EntityService<Disciplina> {

	Disciplina retrieve(Integer id);

	Disciplina create(
			Integer codiceAddetto,
			String provvedimento,
			Date dataProvvedimento,
			String note);

	Disciplina update(
			Integer id,
			String provvedimento,
			Date dataProvvedimento,
			String note);

	void delete(Integer id);

	List<Disciplina> listByAddetto(Integer codiceAddetto);
}