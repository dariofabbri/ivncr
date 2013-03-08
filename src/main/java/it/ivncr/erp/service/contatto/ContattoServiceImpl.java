package it.ivncr.erp.service.contatto;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Contatto;
import it.ivncr.erp.model.commerciale.TipoContatto;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class ContattoServiceImpl extends AbstractService implements ContattoService {

	@Override
	public QueryResult<Contatto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceClienteNomeTelefono1Telefono2CellulareFaxEmail q =
				new QueryByCodiceClienteNomeTelefono1Telefono2CellulareFaxEmail(session);

		Integer codiceCliente = null;
		if(filters.get("codiceCliente") != null)
			codiceCliente = Integer.decode(filters.get("codiceCliente"));

		String nome = filters.get("nome");
		String telefono1 = filters.get("telefono1");
		String telefono2 = filters.get("telefono2");
		String cellulare = filters.get("cellulare");
		String fax = filters.get("fax");
		String email = filters.get("email");

		q.setCodiceCliente(codiceCliente);
		q.setNome(nome);
		q.setTelefono1(telefono1);
		q.setTelefono2(telefono2);
		q.setCellulare(cellulare);
		q.setFax(fax);
		q.setEmail(email);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Contatto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Contatto> list(
			Integer codiceCliente,
			String nome,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email,
			Integer offset,
			Integer limit) {

		QueryByCodiceClienteNomeTelefono1Telefono2CellulareFaxEmail q =
				new QueryByCodiceClienteNomeTelefono1Telefono2CellulareFaxEmail(session);

		q.setCodiceCliente(codiceCliente);
		q.setNome(nome);
		q.setTelefono1(telefono1);
		q.setTelefono2(telefono2);
		q.setCellulare(cellulare);
		q.setFax(fax);
		q.setEmail(email);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Contatto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public Contatto retrieve(Integer id) {

		Contatto contatto = (Contatto)session.get(Contatto.class, id);
		logger.debug("Contatto found: " + contatto);

		return contatto;
	}

	@Override
	public Contatto retrieveDeep(Integer id) {

		String hql =
				"from Contatto con " +
				"left join fetch con.tipoContatto tco " +
				"where con.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Contatto contatto = (Contatto)query.uniqueResult();
		logger.debug("Contatto found: " + contatto);

		return contatto;
	}

	@Override
	public Contatto create(
			Integer codiceCliente,
			Integer codiceTipoContatto,
			String titolo,
			String nome,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email) {

		// Fetch referred entities.
		//
		TipoContatto tipoContatto = (TipoContatto)session.get(TipoContatto.class, codiceTipoContatto);
		Cliente cliente = (Cliente)session.get(Cliente.class, codiceCliente);

		// Create the new entity.
		//
		Contatto contatto = new Contatto();

		// Set entity fields.
		//
		contatto.setCliente(cliente);
		contatto.setTipoContatto(tipoContatto);
		contatto.setTitolo(titolo);
		contatto.setNome(nome);
		contatto.setTelefono1(telefono1);
		contatto.setTelefono2(telefono2);
		contatto.setCellulare(cellulare);
		contatto.setFax(fax);
		contatto.setEmail(email);

		// Persist the entity to the database.
		//
		session.save(contatto);
		logger.debug("Contatto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, contatto);

		return contatto;
	}


	@Override
	public Contatto update(
			Integer id,
			Integer codiceTipoContatto,
			String titolo,
			String nome,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email) {

		Contatto contatto = retrieve(id);
		if(contatto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, contatto);

		// Fetch referred entities.
		//
		TipoContatto tipoContatto = (TipoContatto)session.get(TipoContatto.class, codiceTipoContatto);

		// Set entity fields.
		//
		contatto.setTipoContatto(tipoContatto);
		contatto.setTitolo(titolo);
		contatto.setNome(nome);
		contatto.setTelefono1(telefono1);
		contatto.setTelefono2(telefono2);
		contatto.setCellulare(cellulare);
		contatto.setFax(fax);
		contatto.setEmail(email);

		session.update(contatto);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, contatto);

		return contatto;
	}


	@Override
	public void delete(Integer id) {

		Contatto contatto = retrieve(id);
		if(contatto == null) {
			String message = String.format("It has not been possible to retrieve specified contatto: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(contatto);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, contatto);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listDistinctTitolo() {

		String hql =
				"select distinct con.titolo from Contatto con ";
		Query query = session.createQuery(hql);
		List<String> result = query.list();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public void setDefault(Integer clienteId, Integer contattoId) {

		String hql =
				"update Contatto con " +
				"set con.preferito = false " +
				"where con.cliente.id = :clienteId ";
		Query query = session.createQuery(hql);
		query.setParameter("clienteId", clienteId);
		query.executeUpdate();
		logger.debug("Default selection cleared.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, String.format("Cleared default contatto for cliente %d.", clienteId));


		if(contattoId == null) {
			logger.debug("No default specified, not setting a new one.");
			return;
		}

		hql =
				"update Contatto con " +
				"set con.preferito = true " +
				"where con.cliente.id = :clienteId " +
				"and con.id = :contattoId ";
		query = session.createQuery(hql);
		query.setParameter("clienteId", clienteId);
		query.setParameter("contattoId", contattoId);
		query.executeUpdate();
		logger.debug("New default set.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, String.format("Set new default contatto %d for cliente %d.", contattoId, clienteId));
	}

	@Override
	public Contatto getDefault(Integer clienteId, boolean fallback) {

		String hql =
				"from Contatto con " +
				"where con.cliente.id = :clienteId " +
				"and con.preferito = true ";
		Query query = session.createQuery(hql);
		query.setParameter("clienteId", clienteId);
		Contatto contatto = (Contatto)query.uniqueResult();

		if(contatto == null && fallback) {
			logger.debug("Default contatto not found using flag, falling back to first of the list.");

			hql =
					"from Contatto con " +
					"where con.cliente.id = :clienteId " +
					"order by con.id ";
			query = session.createQuery(hql);
			query.setParameter("clienteId", clienteId);
			query.setMaxResults(1);
			contatto = (Contatto)query.uniqueResult();
		}

		logger.debug("Default contatto found: " + contatto);

		return contatto;
	}
}