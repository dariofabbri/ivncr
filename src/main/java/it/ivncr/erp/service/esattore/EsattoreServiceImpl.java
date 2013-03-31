package it.ivncr.erp.service.esattore;

import it.ivncr.erp.model.commerciale.contratto.Esattore;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class EsattoreServiceImpl extends AbstractService implements EsattoreService {

	@Override
	public QueryResult<Esattore> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Esattore> listDisponibiliPerContratto(Integer contrattoId) {

		String hql =
				"select distinct esa " +
				"from Esattore esa " +
				"left join esa.aziende azi " +
				"where azi.azienda.id = " +
				"(select azi.id from Contratto cnt " +
				"left join cnt.cliente cli " +
				"left join cli.azienda azi " +
				"where cnt.id = :contrattoId) " +
				"and azi.attivo = true " +
				"and esa.id not in " +
				"(select coe.esattore.id from ContrattoEsattore coe " +
				"where coe.contratto.id = :contrattoId) ";
		Query query = session.createQuery(hql);
		query.setParameter("contrattoId", contrattoId);
		List<Esattore> result = query.list();
		logger.debug("Query returned: " + result);

		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Esattore> listDisponibiliPerContratto(Integer contrattoId, Integer esattoreId) {


		String hql =
				"select distinct esa " +
				"from Esattore esa " +
				"left join esa.aziende azi " +
				"where azi.azienda.id = " +
				"(select azi.id from Contratto cnt " +
				"left join cnt.cliente cli " +
				"left join cli.azienda azi " +
				"where cnt.id = :contrattoId) " +
				"and azi.attivo = true " +
				"and esa.id not in " +
				"(select coe.esattore.id from ContrattoEsattore coe " +
				"where coe.contratto.id = :contrattoId " +
				"and coe.esattore.id <> :esattoreId) ";
		Query query = session.createQuery(hql);
		query.setParameter("contrattoId", contrattoId);
		query.setParameter("esattoreId", esattoreId);
		List<Esattore> result = query.list();
		logger.debug("Query returned: " + result);

		return result;
	}
}