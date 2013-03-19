package it.ivncr.erp.service.esercitazionetiro;

import it.ivncr.erp.model.personale.EsercitazioneTiro;
import it.ivncr.erp.service.EntityService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EsercitazioneTiroService extends EntityService<EsercitazioneTiro> {

	EsercitazioneTiro retrieve(Integer id);
	EsercitazioneTiro retrieveDeep(Integer id);

	EsercitazioneTiro create(
			Integer codiceAddetto,
			Date dataTiro,
			String poligono,
			Integer codiceTipoEsercitazione,
			BigDecimal importoRichiesto,
			BigDecimal importoRimborsato);

	EsercitazioneTiro update(
			Integer id,
			Date dataTiro,
			String poligono,
			Integer codiceTipoEsercitazione,
			BigDecimal importoRichiesto,
			BigDecimal importoRimborsato);

	void delete(Integer id);

	List<EsercitazioneTiro> listByAddetto(Integer codiceAddetto);
}