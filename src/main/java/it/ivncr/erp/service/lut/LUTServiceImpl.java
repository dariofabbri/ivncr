package it.ivncr.erp.service.lut;

import it.ivncr.erp.service.AbstractService;

import java.util.List;

import org.hibernate.Query;

public class LUTServiceImpl extends AbstractService implements LUTService {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T retrieveItem(String lut, Object id) {

		String hql =
				"from " + lut + " lut " +
				"where lut.id = :id";

		Query query = session.createQuery(hql);
		query.setParameter("id", id);

		T item = (T)query.uniqueResult();
		logger.debug("LUT item found: " + item);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T retrieveItemByDescrizione(String lut, String descrizione) {

		String hql =
				"from " + lut + " lut " +
				"where lut.descrizione = :descrizione";

		Query query = session.createQuery(hql);
		query.setParameter("descrizione", descrizione);

		T item = (T)query.uniqueResult();
		logger.debug("LUT item found: " + item);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T retrieveItemByDescrizione(
			String lut,
			String column,
			String descrizione) {

		String hql =
				"from " + lut + " lut " +
				"where lut." + column + " = :descrizione";

		Query query = session.createQuery(hql);
		query.setParameter("descrizione", descrizione);

		T item = (T)query.uniqueResult();
		logger.debug("LUT item found: " + item);
		return item;
	}

	@Override
	public <T> List<T> listItems(String lut) {

		return listItems(lut, null, null, null);
	}

	@Override
	public <T> List<T> listItems(String lut, String orderColumn) {

		return listItems(lut, orderColumn, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> listItems(
			String lut,
			String orderColumn,
			String filterColumn,
			Object filterValue) {

		String hql =
				"from " + lut + " lut ";

		if(filterColumn != null) {
			hql += "where lut." + filterColumn + " = :filterValue ";
		}
		if(orderColumn != null) {
			hql += "order by lut." + orderColumn;
		}

		Query query = session.createQuery(hql);
		if(filterColumn != null) {
			query.setParameter("filterValue", filterValue);
		}

		List<T> list = query.list();
		logger.debug("Items found: " + list);

		return list;
	}
}