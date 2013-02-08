package it.ivncr.erp.service.contatto;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Contatto;
import it.ivncr.erp.model.commerciale.TipoContatto;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Map;

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

		return contatto;
	}
}