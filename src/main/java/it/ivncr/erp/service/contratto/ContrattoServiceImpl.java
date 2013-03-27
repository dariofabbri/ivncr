package it.ivncr.erp.service.contratto;

import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Date;
import java.util.Map;

import org.hibernate.Query;

public class ContrattoServiceImpl extends AbstractService implements ContrattoService {

	@Override
	public QueryResult<Contratto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceAziendaCodiceAliasCodiceClienteRagioneSociale q =
				new QueryByCodiceAziendaCodiceAliasCodiceClienteRagioneSociale(session);

		Integer codiceAzienda = null;
		if(filters.get("codiceAzienda") != null)
			codiceAzienda = Integer.decode(filters.get("codiceAzienda"));

		String codice = filters.get("codice");
		String alias = filters.get("alias");
		String codiceCliente = filters.get("codiceCliente");
		String ragioneSociale = filters.get("ragioneSociale");

		q.setCodiceAzienda(codiceAzienda);
		q.setCodice(codice);
		q.setAlias(alias);
		q.setCodiceCliente(codiceCliente);
		q.setRagioneSociale(ragioneSociale);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Contratto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Contratto> list(
			Integer codiceAzienda,
			String codice,
			String alias,
			String codiceCliente,
			String ragioneSociale,
			Integer offset,
			Integer limit) {

		QueryByCodiceAziendaCodiceAliasCodiceClienteRagioneSociale q =
				new QueryByCodiceAziendaCodiceAliasCodiceClienteRagioneSociale(session);

		q.setCodiceAzienda(codiceAzienda);
		q.setCodice(codice);
		q.setAlias(alias);
		q.setCodiceCliente(codiceCliente);
		q.setRagioneSociale(ragioneSociale);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Contratto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public Contratto retrieve(Integer id) {

		Contratto contratto = (Contratto)session.get(Contratto.class, id);
		logger.debug("Contratto found: " + contratto);

		return contratto;
	}

	@Override
	public Contratto retrieveDeep(Integer id) {

		String hql =
				"from Contratto con " +
				"left join fetch con.cliente cli " +
				"where cli.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Contratto contratto = (Contratto)query.uniqueResult();
		logger.debug("Contratto found: " + contratto);

		return contratto;
	}

	@Override
	public Contratto create(
			Integer codiceCliente,
			String codice,
			String alias,
			String ragioneSociale,
			Date dataContratto,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataCessazione) {

		Date now = new Date();

		// Fetch referred entities.
		//
		Cliente cliente = (Cliente)session.get(Cliente.class, codiceCliente);

		// Create the new entity.
		//
		Contratto contratto = new Contratto();

		// Set entity fields.
		//
		contratto.setCliente(cliente);
		contratto.setCodice(codice);
		contratto.setAlias(alias);
		contratto.setRagioneSociale(ragioneSociale);
		contratto.setDataContratto(dataContratto);
		contratto.setDataDecorrenza(dataDecorrenza);
		contratto.setDataTermine(dataTermine);
		contratto.setDataCessazione(dataCessazione);

		contratto.setCreazione(now);
		contratto.setUltimaModifica(now);

		// Persist the entity to the database.
		//
		session.save(contratto);
		logger.debug("Contratto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, contratto);

		return contratto;
	}

	@Override
	public Contratto update(
			Integer id,
			String alias,
			String ragioneSociale,
			Date dataContratto,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataCessazione,
			Boolean tacitoRinnovo,
			Integer giorniPeriodoRinnovo,
			Integer mesiPeriodoRinnovo,
			Integer anniPeriodoRinnovo,
			Integer giorniPreavvisoScadenza) {

		Contratto contratto = retrieve(id);
		if(contratto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, contratto);


		Date now = new Date();

		// Set entity fields.
		//
		contratto.setAlias(alias);
		contratto.setRagioneSociale(ragioneSociale);
		contratto.setDataContratto(dataContratto);
		contratto.setDataDecorrenza(dataDecorrenza);
		contratto.setDataTermine(dataTermine);
		contratto.setDataCessazione(dataCessazione);
		contratto.setTacitoRinnovo(tacitoRinnovo);
		contratto.setGiorniPeriodoRinnovo(giorniPeriodoRinnovo);
		contratto.setMesiPeriodoRinnovo(mesiPeriodoRinnovo);
		contratto.setAnniPeriodoRinnovo(anniPeriodoRinnovo);
		contratto.setGiorniPreavvisoScadenza(giorniPreavvisoScadenza);

		contratto.setUltimaModifica(now);

		session.update(contratto);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, contratto);

		return contratto;
	}


	@Override
	public Contratto setNote(Integer id, String note) {

		Contratto contratto = retrieve(id);
		if(contratto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, contratto);

		// Update the record.
		//
		Date now = new Date();
		contratto.setNote(note);
		contratto.setUltimaModifica(now);
		session.update(contratto);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, contratto);

		return contratto;
	}
}
