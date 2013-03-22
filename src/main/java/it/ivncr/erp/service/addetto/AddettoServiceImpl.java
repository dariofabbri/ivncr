package it.ivncr.erp.service.addetto;

import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.StatoCivile;
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

public class AddettoServiceImpl extends AbstractService implements AddettoService {

	@Override
	public QueryResult<Addetto> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceAziendaMatricolaNomeCognomeCodiceFiscale q =
				new QueryByCodiceAziendaMatricolaNomeCognomeCodiceFiscale(session);

		Integer codiceAzienda = null;
		if(filters.get("codiceAzienda") != null)
			codiceAzienda = Integer.decode(filters.get("codiceAzienda"));

		String matricola = filters.get("matricola");
		String nome = filters.get("nome");
		String cognome = filters.get("cognome");
		String codiceFiscale = filters.get("codiceFiscale");

		q.setCodiceAzienda(codiceAzienda);
		q.setMatricola(matricola);
		q.setNome(nome);
		q.setCognome(cognome);
		q.setCodiceFiscale(codiceFiscale);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<Addetto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<Addetto> list(
			Integer codiceAzienda,
			String matricola,
			String nome,
			String cognome,
			String codiceFiscale,
			Integer offset,
			Integer limit) {

		QueryByCodiceAziendaMatricolaNomeCognomeCodiceFiscale q =
				new QueryByCodiceAziendaMatricolaNomeCognomeCodiceFiscale(session);

		q.setCodiceAzienda(codiceAzienda);
		q.setMatricola(matricola);
		q.setNome(nome);
		q.setCognome(cognome);
		q.setCodiceFiscale(codiceFiscale);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<Addetto> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public Addetto retrieve(Integer id) {

		Addetto addetto = (Addetto)session.get(Addetto.class, id);
		logger.debug("Addetto found: " + addetto);

		return addetto;
	}

	@Override
	public Addetto retrieveDeep(Integer id) {

		String hql =
				"from Addetto add " +
				"left join fetch add.statoCivile stc " +
				"where add.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Addetto addetto = (Addetto)query.uniqueResult();
		logger.debug("Addetto found: " + addetto);

		return addetto;
	}

	@Override
	public Addetto retrieveByMatricola(Integer codiceAzienda, String matricola) {

		String hql =
				"from Addetto add " +
				"where add.azienda.id = :codiceAzienda " +
				"and add.matricola = :matricola ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAzienda", codiceAzienda);
		query.setParameter("matricola", matricola);
		Addetto addetto = (Addetto)query.uniqueResult();
		logger.debug("Addetto found: " + addetto);

		return addetto;
	}

	@Override
	public Addetto create(
			Integer codiceAzienda,
			String matricola,
			String nome,
			String cognome,
			Date dataNascita,
			String luogoNascita,
			String codiceFiscale,
			String sesso,
			String note,
			Boolean fittizio,
			Date dataGiuramento,
			Integer codiceStatoCivile) {

		Date now = new Date();

		// Fetch referred entities.
		//
		Azienda azienda = (Azienda)session.get(Azienda.class, codiceAzienda);
		StatoCivile statoCivile = (StatoCivile)session.get(StatoCivile.class, codiceStatoCivile);

		// Create the new entity.
		//
		Addetto addetto = new Addetto();

		// Set entity fields.
		//
		addetto.setAzienda(azienda);
		addetto.setMatricola(matricola);
		addetto.setNome(nome);
		addetto.setCognome(cognome);
		addetto.setDataNascita(dataNascita);
		addetto.setLuogoNascita(luogoNascita);
		addetto.setCodiceFiscale(codiceFiscale);
		addetto.setSesso(sesso);
		addetto.setNote(note);
		addetto.setAttivo(true);
		addetto.setFittizio(fittizio);
		addetto.setDataGiuramento(dataGiuramento);
		addetto.setStatoCivile(statoCivile);

		addetto.setCreazione(now);
		addetto.setUltimaModifica(now);

		// Persist the entity to the database.
		//
		session.save(addetto);
		logger.debug("Addetto successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, addetto);

		return addetto;
	}

	@Override
	public Addetto update(
			Integer id,
			String nome,
			String cognome,
			Date dataNascita,
			String luogoNascita,
			String codiceFiscale,
			String sesso,
			String note,
			Boolean fittizio,
			Date dataGiuramento,
			Integer codiceStatoCivile) {

		Addetto addetto = retrieve(id);
		if(addetto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, addetto);


		Date now = new Date();

		// Fetch referred entities.
		//
		StatoCivile statoCivile = (StatoCivile)session.get(StatoCivile.class, codiceStatoCivile);

		// Set entity fields.
		//
		addetto.setNome(nome);
		addetto.setCognome(cognome);
		addetto.setDataNascita(dataNascita);
		addetto.setLuogoNascita(luogoNascita);
		addetto.setCodiceFiscale(codiceFiscale);
		addetto.setSesso(sesso);
		addetto.setNote(note);
		addetto.setFittizio(fittizio);
		addetto.setDataGiuramento(dataGiuramento);
		addetto.setStatoCivile(statoCivile);

		addetto.setUltimaModifica(now);

		session.update(addetto);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, addetto);

		return addetto;
	}


	@Override
	public String retrieveNextMatricola(Integer codiceAzienda) {

		String hql =
				"select max(add.matricola) " +
				"from Addetto add " +
				"where add.azienda.id = :codiceAzienda ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAzienda", codiceAzienda);
		String lastCodice = (String)query.uniqueResult();

		Integer max = null;
		if(lastCodice == null) {
			max = 1;
		} else {
			max = Integer.parseInt(lastCodice) + 1;
		}

		String matricola = String.format("%05d", max);
		logger.debug("Next matricola: " + matricola);
		return matricola;
	}


	@Override
	public void setFoto(Integer id, byte[] foto) {

		Addetto addetto = retrieve(id);
		if(addetto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, addetto);

		addetto.setFoto(foto);
		session.update(addetto);
		logger.debug(String.format("Picture successfully uploaded for specified addetto: %s", id));

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, addetto);
	}


	@Override
	public Addetto setNote(Integer id, String note) {

		Addetto addetto = retrieve(id);
		if(addetto == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, addetto);

		// Update the record.
		//
		Date now = new Date();
		addetto.setNote(note);
		addetto.setUltimaModifica(now);
		session.update(addetto);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, addetto);

		return addetto;
	}
}
