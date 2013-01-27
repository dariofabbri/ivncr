package it.ivncr.erp.service.cliente;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.model.commerciale.GruppoCliente;
import it.ivncr.erp.model.commerciale.TipoBusinessPartner;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Date;
import java.util.Map;

import org.hibernate.Query;

public class ClienteServiceImpl extends AbstractService implements ClienteService {

	@Override
	public QueryResult<Cliente> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceRagioneSocialePartitaIvaCodiceFiscale q = new QueryByCodiceRagioneSocialePartitaIvaCodiceFiscale(session);

		String codice = filters.get("codice");
		String ragioneSociale = filters.get("ragioneSociale");
		String partitaIva = filters.get("partitaIva");
		String codiceFiscale = filters.get("codiceFiscale");

		q.setCodice(codice);
		q.setRagioneSociale(ragioneSociale);
		q.setPartitaIva(partitaIva);
		q.setCodiceFiscale(codiceFiscale);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Cliente> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Cliente> list(
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer offset,
			Integer limit) {

		QueryByCodiceRagioneSocialePartitaIvaCodiceFiscale q = new QueryByCodiceRagioneSocialePartitaIvaCodiceFiscale(session);

		q.setCodice(codice);
		q.setRagioneSociale(ragioneSociale);
		q.setPartitaIva(partitaIva);
		q.setCodiceFiscale(codiceFiscale);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Cliente> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public Cliente retrieve(Integer id) {

		Cliente cliente = (Cliente)session.get(Cliente.class, id);
		logger.debug("Cliente found: " + cliente);

		return cliente;
	}

	@Override
	public Cliente retrieveDeep(Integer id) {

		String hql =
				"from Cliente cli " +
				"left join fetch cli.gruppoCliente gcl " +
				"left join fetch cli.divisa div " +
				"left join fetch cli.tipoBusinessPartner tbp " +
				"where cli.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Cliente cliente = (Cliente)query.uniqueResult();
		logger.debug("Cliente found: " + cliente);

		return cliente;
	}

	@Override
	public Cliente retrieveByCodice(String codice) {

		String hql =
				"from Cliente cli " +
				"where cli.codice = :codice ";
		Query query = session.createQuery(hql);
		query.setParameter("codice", codice);
		Cliente cliente = (Cliente)query.uniqueResult();
		logger.debug("Cliente found: " + cliente);

		return cliente;
	}

	@Override
	public Cliente create(
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer codiceGruppoCliente,
			Integer codiceDivisa,
			Integer codiceTipoBusinessPartner,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email) {

		Date now = new Date();

		// Fetch referred entities.
		//
		GruppoCliente gruppoCliente = (GruppoCliente)session.get(GruppoCliente.class, codiceGruppoCliente);
		Divisa divisa = (Divisa)session.get(Divisa.class, codiceDivisa);
		TipoBusinessPartner tipoBusinessPartner = (TipoBusinessPartner)session.get(TipoBusinessPartner.class, codiceTipoBusinessPartner);

		// Create the new entity.
		//
		Cliente cliente = new Cliente();

		// Set entity fields.
		//
		cliente.setCodice(codice);
		cliente.setRagioneSociale(ragioneSociale);
		cliente.setPartitaIva(partitaIva);
		cliente.setCodiceFiscale(codiceFiscale);
		cliente.setGruppoCliente(gruppoCliente);
		cliente.setDivisa(divisa);
		cliente.setTipoBusinessPartner(tipoBusinessPartner);
		cliente.setTelefono1(telefono1);
		cliente.setTelefono2(telefono2);
		cliente.setCellulare(cellulare);
		cliente.setFax(fax);
		cliente.setEmail(email);

		cliente.setCreazione(now);
		cliente.setUltimaModifica(now);
		cliente.setAttivo(true);
		cliente.setAttivoDal(now);
		cliente.setBloccato(false);

		// Persist the entity to the database.
		//
		session.save(cliente);
		logger.debug("Cliente successfully created.");

		return cliente;
	}

	@Override
	public Cliente update(
			Integer id,
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer codiceGruppoCliente,
			Integer codiceDivisa,
			Integer codiceTipoBusinessPartner,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email) {

		Cliente cliente = retrieve(id);
		if(cliente == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		Date now = new Date();

		// Fetch referred entities.
		//
		GruppoCliente gruppoCliente = (GruppoCliente)session.get(GruppoCliente.class, codiceGruppoCliente);
		Divisa divisa = (Divisa)session.get(Divisa.class, codiceDivisa);
		TipoBusinessPartner tipoBusinessPartner = (TipoBusinessPartner)session.get(TipoBusinessPartner.class, codiceTipoBusinessPartner);

		// Set entity fields.
		//
		cliente.setCodice(codice);
		cliente.setRagioneSociale(ragioneSociale);
		cliente.setPartitaIva(partitaIva);
		cliente.setCodiceFiscale(codiceFiscale);
		cliente.setGruppoCliente(gruppoCliente);
		cliente.setDivisa(divisa);
		cliente.setTipoBusinessPartner(tipoBusinessPartner);
		cliente.setTelefono1(telefono1);
		cliente.setTelefono2(telefono2);
		cliente.setCellulare(cellulare);
		cliente.setFax(fax);
		cliente.setEmail(email);

		cliente.setUltimaModifica(now);

		session.update(cliente);
		logger.debug("Entity successfully updated.");

		return cliente;
	}
}
