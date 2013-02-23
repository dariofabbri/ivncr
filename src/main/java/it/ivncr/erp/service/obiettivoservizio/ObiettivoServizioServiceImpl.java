package it.ivncr.erp.service.obiettivoservizio;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.model.commerciale.ObiettivoServizio;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Map;

public class ObiettivoServizioServiceImpl extends AbstractService implements ObiettivoServizioService {

	@Override
	public QueryResult<ObiettivoServizio> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceClienteAliasIndirizzoEdificioScalaPianoInternoLocalitaCapProvinciaPaese q =
				new QueryByCodiceClienteAliasIndirizzoEdificioScalaPianoInternoLocalitaCapProvinciaPaese(session);

		Integer codiceCliente = null;
		if(filters.get("codiceCliente") != null)
			codiceCliente = Integer.decode(filters.get("codiceCliente"));

		String alias = filters.get("alias");
		String indirizzo = filters.get("indirizzo");
		String edificio = filters.get("edificio");
		String scala = filters.get("scala");
		String piano = filters.get("piano");
		String interno = filters.get("interno");
		String localita = filters.get("localita");
		String cap = filters.get("cap");
		String provincia = filters.get("provincia");
		String paese = filters.get("paese");

		q.setCodiceCliente(codiceCliente);
		q.setAlias(alias);
		q.setIndirizzo(indirizzo);
		q.setEdificio(edificio);
		q.setScala(scala);
		q.setPiano(piano);
		q.setInterno(interno);
		q.setLocalita(localita);
		q.setCap(cap);
		q.setProvincia(provincia);
		q.setPaese(paese);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<ObiettivoServizio> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<ObiettivoServizio> list(
			Integer codiceCliente,
			String alias,
			String indirizzo,
			String edificio,
			String scala,
			String piano,
			String interno,
			String localita,
			String cap,
			String provincia,
			String paese,
			Integer offset,
			Integer limit) {

		QueryByCodiceClienteAliasIndirizzoEdificioScalaPianoInternoLocalitaCapProvinciaPaese q =
				new QueryByCodiceClienteAliasIndirizzoEdificioScalaPianoInternoLocalitaCapProvinciaPaese(session);

		q.setCodiceCliente(codiceCliente);
		q.setAlias(alias);
		q.setIndirizzo(indirizzo);
		q.setEdificio(edificio);
		q.setScala(scala);
		q.setPiano(piano);
		q.setInterno(interno);
		q.setLocalita(localita);
		q.setCap(cap);
		q.setProvincia(provincia);
		q.setPaese(paese);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<ObiettivoServizio> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public ObiettivoServizio retrieve(Integer id) {

		ObiettivoServizio obiettivoServizio = (ObiettivoServizio)session.get(ObiettivoServizio.class, id);
		logger.debug("ObiettivoServizio found: " + obiettivoServizio);

		return obiettivoServizio;
	}

	@Override
	public ObiettivoServizio create(
			Integer codiceCliente,
			String alias,
			String toponimo,
			String indirizzo,
			String civico,
			String edificio,
			String scala,
			String piano,
			String interno,
			String localita,
			String cap,
			String provincia,
			String paese,
			String note) {

		// Fetch referred entities.
		//
		Cliente cliente = (Cliente)session.get(Cliente.class, codiceCliente);

		// Create the new entity.
		//
		ObiettivoServizio entity = new ObiettivoServizio();

		// Set entity fields.
		//
		entity.setCliente(cliente);
		entity.setAlias(alias);
		entity.setToponimo(toponimo);
		entity.setIndirizzo(indirizzo);
		entity.setCivico(civico);
		entity.setEdificio(edificio);
		entity.setScala(scala);
		entity.setPiano(piano);
		entity.setInterno(interno);
		entity.setLocalita(localita);
		entity.setCap(cap);
		entity.setProvincia(provincia);
		entity.setPaese(paese);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("ObiettivoServizio successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public ObiettivoServizio update(
			Integer id,
			String alias,
			String toponimo,
			String indirizzo,
			String civico,
			String edificio,
			String scala,
			String piano,
			String interno,
			String localita,
			String cap,
			String provincia,
			String paese,
			String note) {

		ObiettivoServizio entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified obiettivo servizio: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Set entity fields.
		//
		entity.setAlias(alias);
		entity.setToponimo(toponimo);
		entity.setIndirizzo(indirizzo);
		entity.setCivico(civico);
		entity.setEdificio(edificio);
		entity.setScala(scala);
		entity.setPiano(piano);
		entity.setInterno(interno);
		entity.setLocalita(localita);
		entity.setCap(cap);
		entity.setProvincia(provincia);
		entity.setPaese(paese);
		entity.setNote(note);

		session.update(entity);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public void delete(Integer id) {

		ObiettivoServizio entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified obiettivo servizio: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}
}