package it.ivncr.erp.service.indirizzo;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.Indirizzo;
import it.ivncr.erp.model.commerciale.TipoIndirizzo;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Map;

import org.hibernate.Query;

public class IndirizzoServiceImpl extends AbstractService implements IndirizzoService {

	@Override
	public QueryResult<Indirizzo> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceClienteDestinatarioIndirizzoLocalitaCapProvinciaPaese q =
				new QueryByCodiceClienteDestinatarioIndirizzoLocalitaCapProvinciaPaese(session);

		Integer codiceCliente = null;
		if(filters.get("codiceCliente") != null)
			codiceCliente = Integer.decode(filters.get("codiceCliente"));

		String destinatario = filters.get("destinatario");
		String indirizzo = filters.get("indirizzo");
		String localita = filters.get("localita");
		String cap = filters.get("cap");
		String provincia = filters.get("provincia");
		String paese = filters.get("paese");

		q.setCodiceCliente(codiceCliente);
		q.setDestinatario(destinatario);
		q.setIndirizzo(indirizzo);
		q.setLocalita(localita);
		q.setCap(cap);
		q.setProvincia(provincia);
		q.setPaese(paese);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Indirizzo> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Indirizzo> list(
			Integer codiceCliente,
			String destinatario1,
			String destinatario2,
			String indirizzo,
			String localita,
			String cap,
			String provincia,
			String paese,
			Integer offset,
			Integer limit) {

		QueryByCodiceClienteDestinatario1Destinatario2IndirizzoLocalitaCapProvinciaPaese q =
				new QueryByCodiceClienteDestinatario1Destinatario2IndirizzoLocalitaCapProvinciaPaese(session);

		q.setCodiceCliente(codiceCliente);
		q.setDestinatario1(destinatario1);
		q.setDestinatario2(destinatario2);
		q.setIndirizzo(indirizzo);
		q.setLocalita(localita);
		q.setCap(cap);
		q.setProvincia(provincia);
		q.setPaese(paese);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Indirizzo> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Indirizzo> list(
			Integer codiceCliente,
			String destinatario,
			String indirizzo,
			String localita,
			String cap,
			String provincia,
			String paese,
			Integer offset,
			Integer limit) {

		QueryByCodiceClienteDestinatarioIndirizzoLocalitaCapProvinciaPaese q =
				new QueryByCodiceClienteDestinatarioIndirizzoLocalitaCapProvinciaPaese(session);

		q.setCodiceCliente(codiceCliente);
		q.setDestinatario(destinatario);
		q.setIndirizzo(indirizzo);
		q.setLocalita(localita);
		q.setCap(cap);
		q.setProvincia(provincia);
		q.setPaese(paese);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Indirizzo> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public Indirizzo retrieve(Integer id) {

		Indirizzo indirizzo = (Indirizzo)session.get(Indirizzo.class, id);
		logger.debug("Indirizzo found: " + indirizzo);

		return indirizzo;
	}

	@Override
	public Indirizzo retrieveDeep(Integer id) {

		String hql =
				"from Indirizzo ind " +
				"left join fetch ind.tipoIndirizzo tin " +
				"where ind.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Indirizzo indirizzo = (Indirizzo)query.uniqueResult();
		logger.debug("Indirizzo found: " + indirizzo);

		return indirizzo;
	}

	@Override
	public Indirizzo create(
			Integer codiceCliente,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese) {

		// Fetch referred entities.
		//
		TipoIndirizzo tipoIndirizzo = (TipoIndirizzo)session.get(TipoIndirizzo.class, codiceTipoIndirizzo);
		Cliente cliente = (Cliente)session.get(Cliente.class, codiceCliente);

		// Create the new entity.
		//
		Indirizzo entity = new Indirizzo();

		// Set entity fields.
		//
		entity.setCliente(cliente);
		entity.setTipoIndirizzo(tipoIndirizzo);
		entity.setDestinatario1(destinatario1);
		entity.setDestinatario2(destinatario2);
		entity.setToponimo(toponimo);
		entity.setIndirizzo(indirizzo);
		entity.setCivico(civico);
		entity.setLocalita(localita);
		entity.setCap(cap);
		entity.setProvincia(provincia);
		entity.setPaese(paese);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Indirizzo successfully created.");

		return entity;
	}


	@Override
	public Indirizzo update(
			Integer id,
			Integer codiceTipoIndirizzo,
			String destinatario1,
			String destinatario2,
			String toponimo,
			String indirizzo,
			String civico,
			String localita,
			String cap,
			String provincia,
			String paese) {

		Indirizzo entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified indirizzo: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Fetch referred entities.
		//
		TipoIndirizzo tipoIndirizzo = (TipoIndirizzo)session.get(TipoIndirizzo.class, codiceTipoIndirizzo);

		// Set entity fields.
		//
		entity.setTipoIndirizzo(tipoIndirizzo);
		entity.setDestinatario1(destinatario1);
		entity.setDestinatario2(destinatario2);
		entity.setToponimo(toponimo);
		entity.setIndirizzo(indirizzo);
		entity.setCivico(civico);
		entity.setLocalita(localita);
		entity.setCap(cap);
		entity.setProvincia(provincia);
		entity.setPaese(paese);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		return entity;
	}


	@Override
	public void delete(Integer id) {

		Indirizzo indirizzo = retrieve(id);
		if(indirizzo == null) {
			String message = String.format("It has not been possible to retrieve specified indirizzo: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(indirizzo);
	}
}