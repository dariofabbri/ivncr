package it.ivncr.erp.service.cliente;

import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.cliente.Divisa;
import it.ivncr.erp.model.commerciale.cliente.GruppoCliente;
import it.ivncr.erp.model.commerciale.cliente.TipoBusinessPartner;
import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.generale.Contatore;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.ServiceException;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;

public class ClienteServiceImpl extends AbstractService implements ClienteService {

	@Override
	public QueryResult<Cliente> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceAziendaCodiceRagioneSocialePartitaIvaCodiceFiscale q =
				new QueryByCodiceAziendaCodiceRagioneSocialePartitaIvaCodiceFiscale(session);

		Integer codiceAzienda = null;
		if(filters.get("codiceAzienda") != null)
			codiceAzienda = Integer.decode(filters.get("codiceAzienda"));

		String codice = filters.get("codice");
		String ragioneSociale = filters.get("ragioneSociale");
		String partitaIva = filters.get("partitaIva");
		String codiceFiscale = filters.get("codiceFiscale");

		q.setCodiceAzienda(codiceAzienda);
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
			Integer codiceAzienda,
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer offset,
			Integer limit) {

		QueryByCodiceAziendaCodiceRagioneSocialePartitaIvaCodiceFiscale q =
				new QueryByCodiceAziendaCodiceRagioneSocialePartitaIvaCodiceFiscale(session);

		q.setCodiceAzienda(codiceAzienda);
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
	public Cliente retrieveByCodice(Integer codiceAzienda, String codice) {

		String hql =
				"from Cliente cli " +
				"where cli.azienda.id = :codiceAzienda " +
				"and cli.codice = :codice ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAzienda", codiceAzienda);
		query.setParameter("codice", codice);
		Cliente cliente = (Cliente)query.uniqueResult();
		logger.debug("Cliente found: " + cliente);

		return cliente;
	}

	@Override
	public Cliente create(
			Integer codiceAzienda,
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
		Azienda azienda = (Azienda)session.get(Azienda.class, codiceAzienda);
		GruppoCliente gruppoCliente = (GruppoCliente)session.get(GruppoCliente.class, codiceGruppoCliente);
		Divisa divisa = (Divisa)session.get(Divisa.class, codiceDivisa);
		TipoBusinessPartner tipoBusinessPartner = (TipoBusinessPartner)session.get(TipoBusinessPartner.class, codiceTipoBusinessPartner);

		// Create the new entity.
		//
		Cliente cliente = new Cliente();

		// Set entity fields.
		//
		cliente.setAzienda(azienda);
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

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, cliente);

		return cliente;
	}

	@Override
	public Cliente update(
			Integer id,
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

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, cliente);


		Date now = new Date();

		// Fetch referred entities.
		//
		GruppoCliente gruppoCliente = (GruppoCliente)session.get(GruppoCliente.class, codiceGruppoCliente);
		Divisa divisa = (Divisa)session.get(Divisa.class, codiceDivisa);
		TipoBusinessPartner tipoBusinessPartner = (TipoBusinessPartner)session.get(TipoBusinessPartner.class, codiceTipoBusinessPartner);

		// Set entity fields.
		//
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

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, cliente);

		return cliente;
	}

	@Override
	public String retrieveNextCodiceAppend(Integer codiceAzienda) {

		String hql =
				"select max(cli.codice) " +
				"from Cliente cli " +
				"where cli.azienda.id = :codiceAzienda ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAzienda", codiceAzienda);
		String lastCodice = (String)query.uniqueResult();

		Integer max = null;
		if(lastCodice == null) {
			max = 1;
		} else {
			max = extractNumericPartFromCodice(lastCodice) + 1;
		}

		return makeCodiceFromNumeric(max);
	}

	@Override
	public String[] retrieveNextCodice(Integer codiceAzienda) {

		String lastCodice = retrieveNextCodiceAppend(codiceAzienda);

		// Retrieve pointer from counters table.
		//
		String hql =
				"from Contatore con " +
				"where con.codice = :codice " +
				"and con.azienda.id = :codiceAzienda ";
		Query query = session.createQuery(hql);
		query.setParameter("codice", "CODICE_CLIENTE");
		query.setParameter("codiceAzienda", codiceAzienda);
		Contatore contatore = (Contatore)query.uniqueResult();

		// If no row has been found, just create a new one
		// and initialize the pointer to 1.
		//
		if(contatore == null) {

			// Fetch azienda using specified code.
			//
			Azienda azienda = (Azienda)session.get(Azienda.class, codiceAzienda);

			contatore = new Contatore();
			contatore.setAzienda(azienda);
			contatore.setCodice("CODICE_CLIENTE");
			contatore.setDescrizione("Puntatore al punto di partenza per la ricerca del primo codice libero per la tabella dei clienti.");
			contatore.setContatore(1);
			session.save(contatore);
		}

		// Find first free code starting from pointer.
		//
		String codice = null;
		while(true) {
			codice = makeCodiceFromNumeric(contatore.getContatore());
			if(retrieveByCodice(codiceAzienda, codice) == null)
				break;

			contatore.incrementContatore();
		}

		// Update pointer in counter table.
		//
		session.update(contatore);


		// Prepare result object.
		//
		String[] result = new String[2];
		result[0] = lastCodice;
		result[1] = codice;

		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listConflicts(
			Integer codiceAzienda,
			Integer id,
			String partitaIva,
			String codiceFiscale) {

		String hql =
				"from Cliente cli " +
				"where cli.azienda.id = :codiceAzienda ";

		// If an id is specified, the search must be performed
		// excluding the current record.
		//
		if(id != null) {
			hql += "and cli.id <> :id ";
		}

		if(partitaIva != null && codiceFiscale != null) {
			hql += "and cli.partitaIva = :partitaIva and cli.codiceFiscale = :codiceFiscale ";
		} else if(partitaIva != null) {
			hql += "and cli.partitaIva = :partitaIva and cli.codiceFiscale is null ";
		} else if(codiceFiscale != null) {
			hql += "and cli.codiceFiscale = :codiceFiscale and cli.partitaIva is null ";
		} else {
			String msg = "Unexpected arguments: at least one between partitaIva and codiceFiscale must be not null.";
			logger.error(msg);
			throw new RuntimeException(msg);
		}

		Query query = session.createQuery(hql);
		query.setParameter("codiceAzienda", codiceAzienda);
		if(id != null) {
			query.setParameter("id", id);
		}
		if(partitaIva != null && codiceFiscale != null) {
			query.setParameter("partitaIva", partitaIva);
			query.setParameter("codiceFiscale", codiceFiscale);
		} else if(partitaIva != null) {
			query.setParameter("partitaIva", partitaIva);
		} else if(codiceFiscale != null) {
			query.setParameter("codiceFiscale", codiceFiscale);
		}

		List<Cliente> result = query.list();
		return result;
	}


	@Override
	public Cliente deactivate(Integer id, String note) {

		Cliente cliente = retrieve(id);
		if(cliente == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, cliente);

		// Check if the record has already been deactivated.
		//
		if(cliente.getBloccato()) {
			String message = String.format("The record for cliente entity with id %d is already deactivated.", id);
			logger.info(message);
			throw new ServiceException(message);
		}

		// Update the record.
		//
		Date now = new Date();
		cliente.setBloccato(true);
		cliente.setBloccatoDal(now);
		cliente.setBloccatoAl(null);
		cliente.setBloccatoNote(note);
		cliente.setAttivo(false);
		cliente.setAttivoAl(now);
		cliente.setUltimaModifica(now);
		session.update(cliente);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, cliente);

		return cliente;
	}


	@Override
	public Cliente activate(Integer id, String note) {

		Cliente cliente = retrieve(id);
		if(cliente == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, cliente);

		// Check if the record has already been activated.
		//
		if(cliente.getAttivo()) {
			String message = String.format("The record for cliente entity with id %d is already activated.", id);
			logger.info(message);
			throw new ServiceException(message);
		}

		// Update the record.
		//
		Date now = new Date();
		cliente.setAttivo(true);
		cliente.setAttivoDal(now);
		cliente.setAttivoAl(null);
		cliente.setAttivoNote(note);
		cliente.setBloccato(false);
		cliente.setBloccatoAl(now);
		cliente.setUltimaModifica(now);
		session.update(cliente);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, cliente);

		return cliente;
	}


	@Override
	public Cliente setNote(Integer id, String note) {

		Cliente cliente = retrieve(id);
		if(cliente == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, cliente);

		// Update the record.
		//
		Date now = new Date();
		cliente.setNote(note);
		cliente.setUltimaModifica(now);
		session.update(cliente);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, cliente);

		return cliente;
	}


	private Integer extractNumericPartFromCodice(String lastCodice) {

		Pattern pattern = Pattern.compile("^C(\\d{6})$");
		Matcher m = pattern.matcher(lastCodice);
		if(!m.matches()) {
			String msg = String.format("Unexpected codice [%s] found while looking for last one.", lastCodice);
			logger.error(msg);
			throw new RuntimeException(msg);
		}

		return Integer.parseInt(m.group(1));
	}

	private String makeCodiceFromNumeric(Integer numeric) {

		return String.format("C%06d", numeric);
	}
}
