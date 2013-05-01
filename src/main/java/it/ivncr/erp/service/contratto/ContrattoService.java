package it.ivncr.erp.service.contratto;

import it.ivncr.erp.model.commerciale.cliente.Indirizzo;
import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ContrattoService extends EntityService<Contratto> {

	QueryResult<Contratto> list(
			Integer codiceAzienda,
			String codice,
			String alias,
			String codiceCliente,
			String ragioneSociale,
			Integer offset,
			Integer limit);

	QueryResult<Contratto> listFromCliente(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters);

	Contratto retrieve(Integer id);
	Contratto retrieveDeep(Integer id);

	Contratto create(
			Integer codiceCliente,
			String alias,
			String ragioneSociale,
			Date dataContratto,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataCessazione);

	Contratto update(
			Integer id,
			String alias,
			String ragioneSociale,
			Date dataContratto,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataCessazione,
			Boolean tacitoRinnovo,
			Integer giorniPeriodoRinnovo,
			Integer mesiPeriodoRinnovo,
			Integer anniPeriodoRinnovo,
			Integer giorniPreavvisoScadenza);

	Contratto setNote(Integer id, String note);

	String peekNextCodice(Integer codiceAzienda, Integer anno);
	String retrieveNextCodice(Integer codiceAzienda, Integer anno);

	List<Indirizzo> listAvailableIndirizzi(Integer id);

	List<ObiettivoServizio> listAvailableObiettiviServizio(Integer id);
}