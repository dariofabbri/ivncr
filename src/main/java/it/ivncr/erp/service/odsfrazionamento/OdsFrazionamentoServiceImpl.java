package it.ivncr.erp.service.odsfrazionamento;

import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class OdsFrazionamentoServiceImpl extends AbstractService implements OdsFrazionamentoService {

	@Override
	public QueryResult<OdsFrazionamento> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public OdsFrazionamento retrieve(Integer id) {

		OdsFrazionamento odsFrazionamento = (OdsFrazionamento)session.get(OdsFrazionamento.class, id);
		logger.debug("Ods frazionamento found: " + odsFrazionamento);

		return odsFrazionamento;
	}

	@Override
	public OdsFrazionamento retrieveDeep(Integer id) {

		String hql =
				"from OdsFrazionamento odf " +
				"left join fetch odf.cliente cli " +
				"where odf.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		OdsFrazionamento odsFrazionamento = (OdsFrazionamento)query.uniqueResult();
		logger.debug("Ods frazionamento found: " + odsFrazionamento);

		return odsFrazionamento;
	}

	@Override
	public OdsFrazionamento create(
			Integer codiceOrdineServizio,
			Integer codiceCliente,
			BigDecimal quota,
			Boolean esclusioneRitenutaGaranzia) {

		// Fetch referred entities.
		//
		OrdineServizio ordineServizio = (OrdineServizio)session.get(OrdineServizio.class, codiceOrdineServizio);
		Cliente cliente = (Cliente)session.get(Cliente.class, codiceCliente);

		// Create the new entity.
		//
		OdsFrazionamento entity = new OdsFrazionamento();

		// Set entity fields.
		//
		entity.setOrdineServizio(ordineServizio);
		entity.setCliente(cliente);
		entity.setQuota(quota);
		entity.setEsclusioneRitenutaGaranzia(esclusioneRitenutaGaranzia);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Ods frazionamento successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public OdsFrazionamento update(
			Integer id,
			Integer codiceCliente,
			BigDecimal quota,
			Boolean esclusioneRitenutaGaranzia) {

		OdsFrazionamento entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods frazionamento: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Fetch referred entities.
		//
		Cliente cliente = (Cliente)session.get(Cliente.class, codiceCliente);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setCliente(cliente);
		entity.setQuota(quota);
		entity.setEsclusioneRitenutaGaranzia(esclusioneRitenutaGaranzia);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		OdsFrazionamento entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified ods frazionamento: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<OdsFrazionamento> listByOrdineServizio(Integer codiceOrdineServizio) {

		String hql =
				"from OdsFrazionamento odf " +
				"left join fetch odf.cliente cli " +
				"where odf.ordineServizio.id = :codiceOrdineServizio ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);

		List<OdsFrazionamento> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}