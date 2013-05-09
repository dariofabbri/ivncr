package it.ivncr.erp.service.contratto;

import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.model.commerciale.cliente.Indirizzo;
import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RinnovoContrattuale;
import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.generale.Contatore;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
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
	public QueryResult<Contratto> listFromCliente(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceClienteCodiceAliasRagioneSociale q =
				new QueryByCodiceClienteCodiceAliasRagioneSociale(session);

		Integer codiceCliente = null;
		if(filters.get("codiceCliente") != null)
			codiceCliente = Integer.decode(filters.get("codiceCliente"));

		String codice = filters.get("codice");
		String alias = filters.get("alias");
		String ragioneSociale = filters.get("ragioneSociale");

		q.setCodiceCliente(codiceCliente);
		q.setCodice(codice);
		q.setAlias(alias);
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
				"where con.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Contratto contratto = (Contratto)query.uniqueResult();
		logger.debug("Contratto found: " + contratto);

		return contratto;
	}

	@Override
	public Contratto create(
			Integer codiceCliente,
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

		// Retrieve next codice.
		//
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now);
		String codice = retrieveNextCodice(cliente.getAzienda().getId(), gc.get(Calendar.YEAR));

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


	@Override
	public String peekNextCodice(Integer codiceAzienda, Integer anno) {

		return getNextCodice(codiceAzienda, anno, false);

	}


	@Override
	public String retrieveNextCodice(Integer codiceAzienda, Integer anno) {

		return getNextCodice(codiceAzienda, anno, true);

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Indirizzo> listAvailableIndirizzi(Integer codiceContratto) {

		String hql =
				"from Indirizzo ind " +
				"where ind.cliente.id in " +
				"(select con.cliente.id from Contratto con where con.id = :codiceContratto) ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<Indirizzo> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ObiettivoServizio> listAvailableObiettiviServizio(Integer codiceContratto) {

		String hql =
				"from ObiettivoServizio ose " +
				"where ose.cliente.id in " +
				"(select con.cliente.id from Contratto con where con.id = :codiceContratto) ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceContratto", codiceContratto);

		List<ObiettivoServizio> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	@Override
	public Contratto applicaRinnovo(Integer id, String note) {

		Contratto contratto = retrieve(id);
		if(contratto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Check if enough info are present for the operation.
		//
		if(
				contratto.getGiorniPeriodoRinnovo() == null &&
				contratto.getMesiPeriodoRinnovo() == null &&
				contratto.getAnniPeriodoRinnovo() == null) {

			String message = "At least one among giorniPeriodoRinnovo, mesiPeriodoRinnovo and anniPeriodoRinnovo fields must have a value.";
			logger.info(message);
			throw new RuntimeException(message);
		}
		if(contratto.getDataTermine() == null) {

			String message = "The field dataTermine must be present, in order to be able to automatically apply specified conditions.";
			logger.info(message);
			throw new RuntimeException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, contratto);

		// Save current timestamp snapshot.
		//
		Date now = new Date();

		// Calculate new set of dates.
		//
		Date dataDecorrenza = DateUtils.addDays(contratto.getDataTermine(), 1);
		Date dataTermine = dataDecorrenza;
		if(contratto.getGiorniPeriodoRinnovo() != null) {
			dataTermine = DateUtils.addDays(dataTermine, contratto.getGiorniPeriodoRinnovo());
		}
		if(contratto.getMesiPeriodoRinnovo() != null) {
			dataTermine = DateUtils.addMonths(dataTermine, contratto.getMesiPeriodoRinnovo());
		}
		if(contratto.getAnniPeriodoRinnovo() != null) {
			dataTermine = DateUtils.addYears(dataTermine, contratto.getAnniPeriodoRinnovo());
		}

		// Create a journal entry.
		//
		RinnovoContrattuale rinnovoContrattuale = new RinnovoContrattuale();
		rinnovoContrattuale.setContratto(contratto);
		rinnovoContrattuale.setDataDecorrenzaPre(contratto.getDataDecorrenza());
		rinnovoContrattuale.setDataTerminePre(contratto.getDataTermine());
		rinnovoContrattuale.setDataDecorrenzaPost(dataDecorrenza);
		rinnovoContrattuale.setDataTerminePost(dataTermine);
		rinnovoContrattuale.setNote(note);
		session.save(rinnovoContrattuale);

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, rinnovoContrattuale);


		// Set entity fields.
		//
		contratto.setDataDecorrenza(dataDecorrenza);
		contratto.setDataTermine(dataTermine);
		contratto.setUltimaModifica(now);
		session.update(contratto);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, contratto);

		logger.debug("Entity successfully updated.");

		return contratto;
	}


	private String getNextCodice(Integer codiceAzienda, Integer anno, boolean increment) {

		// Prepare key for specified year.
		//
		String key = String.format("CODICE_CONTRATTO_%04d", anno);

		// Retrieve next available number from counters table.
		//
		String hql =
				"from Contatore con " +
				"where con.codice = :codice " +
				"and con.azienda.id = :codiceAzienda ";
		Query query = session.createQuery(hql);
		query.setParameter("codice", key);
		query.setParameter("codiceAzienda", codiceAzienda);
		Contatore contatore = (Contatore)query.uniqueResult();

		// If no row has been found, just create a new one
		// and initialize the counter to 1.
		//
		if(contatore == null) {

			String descrizione = String.format("Contatore per la generazione dei codici contratto per l'anno %04d", anno);

			// Fetch azienda using specified code.
			//
			Azienda azienda = (Azienda)session.get(Azienda.class, codiceAzienda);

			contatore = new Contatore();
			contatore.setAzienda(azienda);
			contatore.setCodice(key);
			contatore.setDescrizione(descrizione);
			contatore.setContatore(1);
			session.save(contatore);
		}

		// Format found number and generate the required code.
		//
		String codice = makeCodiceFromNumericAndYear(contatore.getContatore(), anno);

		// If requested, increment the counter.
		//
		if(increment) {

			contatore.incrementContatore();
			session.update(contatore);
		}

		return codice;
	}


	private String makeCodiceFromNumericAndYear(Integer numeric, Integer year) {

		return String.format("%d/%04d", numeric, year);
	}
}
