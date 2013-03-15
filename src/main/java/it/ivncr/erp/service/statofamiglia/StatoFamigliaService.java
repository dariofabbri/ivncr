package it.ivncr.erp.service.statofamiglia;

import it.ivncr.erp.model.personale.StatoFamiglia;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface StatoFamigliaService extends EntityService<StatoFamiglia> {

	StatoFamiglia retrieve(Integer id);
	StatoFamiglia retrieveDeep(Integer id);

	StatoFamiglia create(
			Integer codiceAddetto,
			Integer codiceGradoParentela,
			String nome,
			Date dataNascita,
			Date validoDa,
			Date validoA);

	StatoFamiglia update(
			Integer id,
			Integer codiceGradoParentela,
			String nome,
			Date dataNascita,
			Date validoDa,
			Date validoA);

	void delete(Integer id);

	List<StatoFamiglia> listByAddetto(Integer codiceAddetto);
}