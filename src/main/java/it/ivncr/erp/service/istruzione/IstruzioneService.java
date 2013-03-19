package it.ivncr.erp.service.istruzione;

import it.ivncr.erp.model.personale.Istruzione;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface IstruzioneService extends EntityService<Istruzione> {

	Istruzione retrieve(Integer id);
	Istruzione retrieveDeep(Integer id);

	Istruzione create(
			Integer codiceAddetto,
			Integer codiceTitoloStudio,
			Date dataConseguimento,
			String presso);

	Istruzione update(
			Integer id,
			Integer codiceTitoloStudio,
			Date dataConseguimento,
			String presso);

	void delete(Integer id);

	List<Istruzione> listByAddetto(Integer codiceAddetto);
}