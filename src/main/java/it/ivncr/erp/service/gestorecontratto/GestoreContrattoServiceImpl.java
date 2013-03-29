package it.ivncr.erp.service.gestorecontratto;

import it.ivncr.erp.model.commerciale.contratto.GestoreContratto;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class GestoreContrattoServiceImpl extends AbstractService implements GestoreContrattoService {

	@Override
	public QueryResult<GestoreContratto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GestoreContratto> listDisponibiliPerContratto(Integer contrattoId) {

		String hql =
				"from GestoreContratto gco " +
				"left join gco.aziende azi " +
				"where azi.azienda.id = " +
				"(select azi.id from Contratto cnt " +
				"left join cnt.cliente cli " +
				"left join cli.azienda azi " +
				"where cnt.id = :contrattoId) " +
				"and azi.attivo = true " +
				"and gco.id not in " +
				"(select cog.gestore.id from ContrattoGestore cog " +
				"where cog.contratto.id = :contrattoId) ";
		Query query = session.createQuery(hql);
		query.setParameter("contrattoId", contrattoId);
		List<GestoreContratto> result = query.list();
		logger.debug("Query returned: " + result);

		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<GestoreContratto> listDisponibiliPerContratto(Integer contrattoId, Integer gestoreId) {


		String hql =
				"from GestoreContratto gco " +
				"left join gco.aziende azi " +
				"where azi.azienda.id = " +
				"(select azi.id from Contratto cnt " +
				"left join cnt.cliente cli " +
				"left join cli.azienda azi " +
				"where cnt.id = :contrattoId) " +
				"and azi.attivo = true " +
				"and gco.id not in " +
				"(select cog.gestore.id from ContrattoGestore cog " +
				"where cog.attivo = true and cog.contratto.id = :contrattoId " +
				"and cog.gestore.id <> :gestoreId) ";
		Query query = session.createQuery(hql);
		query.setParameter("contrattoId", contrattoId);
		query.setParameter("gestoreId", gestoreId);
		List<GestoreContratto> result = query.list();
		logger.debug("Query returned: " + result);

		return result;
	}
}