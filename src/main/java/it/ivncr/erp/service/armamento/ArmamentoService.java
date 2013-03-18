package it.ivncr.erp.service.armamento;

import it.ivncr.erp.model.personale.Armamento;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface ArmamentoService extends EntityService<Armamento> {

	Armamento retrieve(Integer id);
	Armamento retrieveDeep(Integer id);

	Armamento create(
			Integer codiceAddetto,
			Integer codiceTipoArma,
			String modelloArma,
			String calibroArma,
			String matricola,
			Integer codiceStatoArma,
			Date dataDenuncia,
			Date dataInizio,
			Date dataFine,
			String note);

	Armamento update(
			Integer id,
			Integer codiceTipoArma,
			String modelloArma,
			String calibroArma,
			String matricola,
			Integer codiceStatoArma,
			Date dataDenuncia,
			Date dataInizio,
			Date dataFine,
			String note);

	void delete(Integer id);

	List<Armamento> listByAddetto(Integer codiceAddetto);
}