package it.ivncr.erp.service.posizionelavorativa;

import it.ivncr.erp.model.personale.PosizioneLavorativa;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface PosizioneLavorativaService extends EntityService<PosizioneLavorativa> {

	PosizioneLavorativa retrieve(Integer id);
	PosizioneLavorativa retrieveDeep(Integer id);

	PosizioneLavorativa create(
			Integer codiceAddetto,
			Integer codiceTipoContratto,
			Integer codiceCcnl,
			Integer codiceAzienda,
			Integer durataContratto,
			Date dataAssunzione,
			Integer durataProva,
			Date dataPrimoGiorno,
			Date dataFineProva,
			Date dataCessazione,
			Date dataFineContratto,
			String motivoDimissioni);

	PosizioneLavorativa update(
			Integer id,
			Integer codiceTipoContratto,
			Integer codiceCcnl,
			Integer codiceAzienda,
			Integer durataContratto,
			Date dataAssunzione,
			Integer durataProva,
			Date dataPrimoGiorno,
			Date dataFineProva,
			Date dataCessazione,
			Date dataFineContratto,
			String motivoDimissioni);

	void delete(Integer id);

	List<PosizioneLavorativa> listByAddetto(Integer codiceAddetto);
}