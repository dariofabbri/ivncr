package it.ivncr.erp.service.reparto;

import it.ivncr.erp.model.personale.Reparto;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class RepartoServiceImpl extends AbstractService implements RepartoService {

	@Override
	public QueryResult<Reparto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Reparto retrieve(Integer id) {

		Reparto reparto = (Reparto)session.get(Reparto.class, id);
		logger.debug("Reparto found: " + reparto);

		return reparto;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Reparto> listByAzienda(Integer codiceAzienda) {

		String hql =
				"from Reparto rep " +
				"where rep.azienda.id = :codiceAzienda ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAzienda", codiceAzienda);

		List<Reparto> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}