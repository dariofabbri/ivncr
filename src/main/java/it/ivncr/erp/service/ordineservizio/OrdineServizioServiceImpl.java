package it.ivncr.erp.service.ordineservizio;

import it.ivncr.erp.model.commerciale.cliente.ObiettivoServizio;
import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.RaggruppamentoFatturazione;
import it.ivncr.erp.model.commerciale.contratto.SpecificaServizio;
import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.model.commerciale.contratto.TipoServizio;
import it.ivncr.erp.model.commerciale.ods.OdsApparecchiatura;
import it.ivncr.erp.model.commerciale.ods.OdsFrazionamento;
import it.ivncr.erp.model.commerciale.ods.OdsOrariCalendario;
import it.ivncr.erp.model.commerciale.ods.OdsOrariRicorrenti;
import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.model.commerciale.ods.TipoOrdineServizio;
import it.ivncr.erp.model.generale.Azienda;
import it.ivncr.erp.model.generale.Contatore;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class OrdineServizioServiceImpl extends AbstractService implements OrdineServizioService {

	@Override
	public QueryResult<OrdineServizio> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceAziendaCodiceContrattoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio q =
				new QueryByCodiceAziendaCodiceContrattoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio(session);

		Integer codiceAzienda = null;
		if(filters.get("codiceAzienda") != null)
			codiceAzienda = Integer.decode(filters.get("codiceAzienda"));

		String codiceContratto = filters.get("contratto.codice");
		String codice = filters.get("codice");
		String alias = filters.get("alias");
		String tipoServizio = filters.get("tipoServizio.descrizione");
		String specificaServizio = filters.get("specificaServizio.descrizione");
		String obiettivoServizio = filters.get("obiettivoServizio.descrizione");

		q.setCodiceAzienda(codiceAzienda);
		q.setCodiceContratto(codiceContratto);
		q.setCodice(codice);
		q.setAlias(alias);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<OrdineServizio> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<OrdineServizio> listFromContratto(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceContrattoOnerosoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio q =
				new QueryByCodiceContrattoOnerosoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		Boolean oneroso = null;
		if(filters.get("oneroso") != null)
			oneroso = Boolean.parseBoolean(filters.get("oneroso"));

		String codice = filters.get("codice");
		String alias = filters.get("alias");
		String tipoServizio = filters.get("tipoServizio.descrizione");
		String specificaServizio = filters.get("specificaServizio.descrizione");
		String obiettivoServizio = filters.get("obiettivoServizio.descrizione");

		q.setCodiceContratto(codiceContratto);
		q.setOneroso(oneroso);
		q.setCodice(codice);
		q.setAlias(alias);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setOffset(first);
		q.setLimit(pageSize);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<OrdineServizio> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<OrdineServizio> list(
			Integer codiceAzienda,
			String codiceContratto,
			String codice,
			String alias,
			String tipoServizio,
			String specificaServizio,
			String obiettivoServizio,
			Integer offset,
			Integer limit) {

		QueryByCodiceAziendaCodiceContrattoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio q =
				new QueryByCodiceAziendaCodiceContrattoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio(session);

		q.setCodiceAzienda(codiceAzienda);
		q.setCodiceContratto(codiceContratto);
		q.setCodice(codice);
		q.setAlias(alias);
		q.setTipoServizio(tipoServizio);
		q.setSpecificaServizio(specificaServizio);
		q.setObiettivoServizio(obiettivoServizio);
		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<OrdineServizio> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public OrdineServizio retrieve(Integer id) {

		OrdineServizio ods = (OrdineServizio)session.get(OrdineServizio.class, id);
		logger.debug("Ordine servizio found: " + ods);

		return ods;
	}

	@Override
	public OrdineServizio retrieveDeep(Integer id) {

		String hql =
				"from OrdineServizio ods " +
				"left join fetch ods.contratto con " +
				"left join fetch ods.tipoOrdineServizio tos " +
				"left join fetch ods.padre pad " +
				"left join fetch ods.nuovaAttivazione nat " +
				"left join fetch ods.tipoServizio tse " +
				"left join fetch ods.specificaServizio sse " +
				"left join fetch ods.obiettivoServizio ose " +
				"left join fetch ods.tariffa tar " +
				"left join fetch ods.canone can " +
				"left join fetch ods.raggruppamentoFatturazione raf " +
				"where ods.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		OrdineServizio ods = (OrdineServizio)query.uniqueResult();
		logger.debug("Ordine servizio found: " + ods);

		return ods;
	}

	@Override
	public OrdineServizio createNuovaAttivazione(
			Integer codiceContratto,
			String alias,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Integer codiceTipoServizio,
			Integer codiceSpecificaServizio,
			Integer codiceObiettivoServizio,
			Boolean oneroso,
			Boolean cessato) {

		Date now = new Date();

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		TipoOrdineServizio tipoOrdineServizio = (TipoOrdineServizio)session.get(TipoOrdineServizio.class, TipoOrdineServizio.NUOVA_ATTIVAZIONE);
		TipoServizio tipoServizio = (TipoServizio)session.get(TipoServizio.class, codiceTipoServizio);
		SpecificaServizio specificaServizio = (SpecificaServizio)session.get(SpecificaServizio.class, codiceSpecificaServizio);
		ObiettivoServizio obiettivoServizio = (ObiettivoServizio)session.get(ObiettivoServizio.class, codiceObiettivoServizio);

		// Create the new entity.
		//
		OrdineServizio ordineServizio = new OrdineServizio();

		// Retrieve next codice.
		//
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now);
		String codice = retrieveNextCodice(contratto.getCliente().getAzienda().getId(), gc.get(Calendar.YEAR));

		// Set entity fields.
		//
		ordineServizio.setContratto(contratto);
		ordineServizio.setTipoOrdineServizio(tipoOrdineServizio);
		ordineServizio.setCodice(codice);
		ordineServizio.setAlias(alias);
		ordineServizio.setDataDecorrenza(dataDecorrenza);
		ordineServizio.setDataTermine(dataTermine);
		ordineServizio.setDataFineValidita(dataFineValidita);
		ordineServizio.setOrarioFineValidita(orarioFineValidita);
		ordineServizio.setTipoServizio(tipoServizio);
		ordineServizio.setSpecificaServizio(specificaServizio);
		ordineServizio.setObiettivoServizio(obiettivoServizio);
		ordineServizio.setOneroso(oneroso);
		ordineServizio.setCessato(cessato);

		ordineServizio.setCreazione(now);
		ordineServizio.setUltimaModifica(now);

		// Persist the entity to the database.
		//
		session.save(ordineServizio);
		logger.debug("Ordine servizio successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, ordineServizio);

		return ordineServizio;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrdineServizio createVariazione(
			Integer codicePadre,
			Integer codiceTipoOrdineServizio,
			String alias,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Boolean cessato) {

		Date now = new Date();

		// Fetch padre.
		//
		OrdineServizio padre = retrieve(codicePadre);
		if(padre == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", codicePadre);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Check dates (when applicable).
		//
		if(codiceTipoOrdineServizio.equals(TipoOrdineServizio.VAR_OCCASIONALE)) {

			if(dataDecorrenza.before(padre.getDataDecorrenza())) {
				String message = "The specified dataDecorrenza cannot precede the same field of parent.";
				logger.info(message);
				throw new RuntimeException(message);
			}

			if(
					(dataTermine == null && padre.getDataTermine() != null) ||
					(dataTermine != null && padre.getDataTermine() != null && dataTermine.after(padre.getDataTermine()))) {
				String message = "The specified dataTermine cannot be after the same field of parent.";
				logger.info(message);
				throw new RuntimeException(message);
			}
		}

		// Fetch referred entities.
		//
		Contratto contratto = padre.getContratto();
		TipoOrdineServizio tipoOrdineServizio = (TipoOrdineServizio)session.get(TipoOrdineServizio.class, codiceTipoOrdineServizio);
		TipoServizio tipoServizio = padre.getTipoServizio();
		SpecificaServizio specificaServizio = padre.getSpecificaServizio();
		ObiettivoServizio obiettivoServizio = padre.getObiettivoServizio();
		OrdineServizio nuovaAttivazione = padre.getTipoOrdineServizio().getId().equals(TipoOrdineServizio.NUOVA_ATTIVAZIONE) ? padre : padre.getNuovaAttivazione();


		// Create the new entity.
		//
		OrdineServizio ordineServizio = new OrdineServizio();

		// Retrieve next codice.
		//
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now);
		String codice = retrieveNextCodice(contratto.getCliente().getAzienda().getId(), gc.get(Calendar.YEAR));

		// Set entity fields.
		//
		ordineServizio.setContratto(contratto);
		ordineServizio.setTipoOrdineServizio(tipoOrdineServizio);
		ordineServizio.setNuovaAttivazione(nuovaAttivazione);
		ordineServizio.setPadre(padre);
		ordineServizio.setCodice(codice);
		ordineServizio.setAlias(alias);
		ordineServizio.setDataDecorrenza(dataDecorrenza);
		ordineServizio.setDataTermine(dataTermine);
		ordineServizio.setDataFineValidita(dataFineValidita);
		ordineServizio.setOrarioFineValidita(orarioFineValidita);
		ordineServizio.setTipoServizio(tipoServizio);
		ordineServizio.setSpecificaServizio(specificaServizio);
		ordineServizio.setObiettivoServizio(obiettivoServizio);
		ordineServizio.setOneroso(padre.getOneroso());
		ordineServizio.setCessato(cessato);

		ordineServizio.setTariffa(padre.getTariffa());
		ordineServizio.setCanone(padre.getCanone());
		ordineServizio.setRaggruppamentoFatturazione(padre.getRaggruppamentoFatturazione());
		ordineServizio.setOsservazioniFattura(padre.getOsservazioniFattura());
		ordineServizio.setNote(padre.getNote());
		ordineServizio.setModalitaOperative(padre.getModalitaOperative());

		ordineServizio.setCreazione(now);
		ordineServizio.setUltimaModifica(now);

		// Persist the entity to the database.
		//
		session.save(ordineServizio);
		logger.debug("Ordine servizio successfully created.");

		// Process frazionamento.
		//
		String hql =
				"from OdsFrazionamento odf " +
				"where odf.ordineServizio.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", padre.getId());
		List<OdsFrazionamento> listFrazionamento = query.list();
		for(OdsFrazionamento src : listFrazionamento) {
			OdsFrazionamento dst = new OdsFrazionamento();
			dst.setOrdineServizio(ordineServizio);
			dst.setCliente(src.getCliente());
			dst.setQuota(src.getQuota());
			dst.setEsclusioneRitenutaGaranzia(src.getEsclusioneRitenutaGaranzia());
			session.save(dst);
		}

		// Process orari ricorrenti.
		//
		hql =
				"from OdsOrariRicorrenti oor " +
				"where oor.ordineServizio.id = :id ";
		query = session.createQuery(hql);
		query.setParameter("id", padre.getId());
		List<OdsOrariRicorrenti> listOrariRicorrenti = query.list();
		for(OdsOrariRicorrenti src : listOrariRicorrenti) {
			OdsOrariRicorrenti dst = new OdsOrariRicorrenti();
			dst.setOrdineServizio(ordineServizio);
			dst.setGiornoSettimana(src.getGiornoSettimana());
			dst.setEsclusoFestivo(src.getEsclusoFestivo());
			dst.setQuantita1(src.getQuantita1());
			dst.setOrarioInizio1(src.getOrarioInizio1());
			dst.setOrarioFine1(src.getOrarioFine1());
			dst.setQuantita2(src.getQuantita2());
			dst.setOrarioInizio2(src.getOrarioInizio2());
			dst.setOrarioFine2(src.getOrarioFine2());
			dst.setQuantita3(src.getQuantita3());
			dst.setOrarioInizio3(src.getOrarioInizio3());
			dst.setOrarioFine3(src.getOrarioFine3());
			session.save(dst);
		}

		// Process orari calendario.
		//
		hql =
				"from OdsOrariCalendario ooc " +
				"where ooc.ordineServizio.id = :id ";
		query = session.createQuery(hql);
		query.setParameter("id", padre.getId());
		List<OdsOrariCalendario> listOrariCalendario = query.list();
		for(OdsOrariCalendario src : listOrariCalendario) {
			OdsOrariCalendario dst = new OdsOrariCalendario();
			dst.setOrdineServizio(ordineServizio);
			dst.setDataServizio(src.getDataServizio());
			dst.setQuantita1(src.getQuantita1());
			dst.setOrarioInizio1(src.getOrarioInizio1());
			dst.setOrarioFine1(src.getOrarioFine1());
			dst.setQuantita2(src.getQuantita2());
			dst.setOrarioInizio2(src.getOrarioInizio2());
			dst.setOrarioFine2(src.getOrarioFine2());
			dst.setQuantita3(src.getQuantita3());
			dst.setOrarioInizio3(src.getOrarioInizio3());
			dst.setOrarioFine3(src.getOrarioFine3());
			session.save(dst);
		}

		// Process apparecchiatura.
		//
		hql =
				"from OdsApparecchiatura oap " +
				"left join fetch oap.apparecchiaturaTecnologica ate " +
				"where oap.ordineServizio.id = :id ";
		query = session.createQuery(hql);
		query.setParameter("id", padre.getId());
		List<OdsApparecchiatura> listApparecchiatura = query.list();
		for(OdsApparecchiatura src : listApparecchiatura) {
			OdsApparecchiatura dst = new OdsApparecchiatura();
			dst.setOrdineServizio(ordineServizio);
			dst.setApparecchiaturaTecnologica(src.getApparecchiaturaTecnologica());
			session.save(dst);
		}


		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, ordineServizio);

		return ordineServizio;
	}

	@Override
	public OrdineServizio updateTestata(
			Integer id,
			String alias,
			Date dataDecorrenza,
			Date dataTermine,
			Date dataFineValidita,
			Date orarioFineValidita,
			Boolean cessato) {

		OrdineServizio ordineServizio = retrieve(id);
		if(ordineServizio == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, ordineServizio);


		Date now = new Date();

		// Set entity fields.
		//
		ordineServizio.setAlias(alias);
		ordineServizio.setDataDecorrenza(dataDecorrenza);
		ordineServizio.setDataTermine(dataTermine);
		ordineServizio.setDataFineValidita(dataFineValidita);
		ordineServizio.setOrarioFineValidita(orarioFineValidita);
		ordineServizio.setCessato(cessato);

		ordineServizio.setUltimaModifica(now);

		session.update(ordineServizio);
		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, ordineServizio);

		return ordineServizio;
	}

	@Override
	public OrdineServizio updateFatturazione(
			Integer id,
			Boolean oneroso,
			Integer codiceTariffa,
			Integer codiceCanone,
			Integer codiceRaggruppamentoFatturazione,
			String osservazioniFattura,
			List<OdsFrazionamento> listOdsFrazionamento) {

		OrdineServizio ordineServizio = retrieve(id);
		if(ordineServizio == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, ordineServizio);

		// Fetch referred entities.
		//
		Tariffa tariffa = codiceTariffa != null ? (Tariffa)session.get(Tariffa.class, codiceTariffa) : null;
		Canone canone = codiceCanone != null ? (Canone)session.get(Canone.class, codiceCanone) : null;
		RaggruppamentoFatturazione raggruppamentoFatturazione = codiceRaggruppamentoFatturazione != null ? (RaggruppamentoFatturazione)session.get(RaggruppamentoFatturazione.class, codiceRaggruppamentoFatturazione) : null;


		Date now = new Date();

		// Set entity fields.
		//
		ordineServizio.setTariffa(tariffa);
		ordineServizio.setCanone(canone);
		ordineServizio.setOneroso(oneroso);
		ordineServizio.setRaggruppamentoFatturazione(raggruppamentoFatturazione);
		ordineServizio.setOsservazioniFattura(osservazioniFattura);
		ordineServizio.setUltimaModifica(now);
		session.update(ordineServizio);


		// Clean up current set of ods frazionamento.
		//
		String hql =
				"delete from OdsFrazionamento odf " +
				"where odf.ordineServizio.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();


		// Create the rows for passed frazionamento.
		//
		if(listOdsFrazionamento != null && listOdsFrazionamento.size() > 0) {

			BigDecimal totalPercentage = BigDecimal.ZERO;

			for(OdsFrazionamento odsFrazionamento : listOdsFrazionamento) {

				OdsFrazionamento o = new OdsFrazionamento();
				o.setOrdineServizio(ordineServizio);
				o.setCliente(odsFrazionamento.getCliente());
				o.setQuota(odsFrazionamento.getQuota());
				o.setEsclusioneRitenutaGaranzia(odsFrazionamento.getEsclusioneRitenutaGaranzia());
				session.save(o);

				totalPercentage = totalPercentage.add(odsFrazionamento.getQuota());
			}

			// Check total percentage.
			//
			if(totalPercentage.compareTo(new BigDecimal(100)) != 0) {
				String msg = String.format("Total percentage for frazionamento was: %s", totalPercentage);
				logger.error(msg);
				throw new RuntimeException(msg);
			}
		}

		logger.debug("Entity successfully updated.");

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, ordineServizio);

		return ordineServizio;
	}


	@Override
	public OrdineServizio setNote(Integer id, String note) {

		OrdineServizio ordineServizio = retrieve(id);
		if(ordineServizio == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, ordineServizio);

		// Update the record.
		//
		Date now = new Date();
		ordineServizio.setNote(note);
		ordineServizio.setUltimaModifica(now);
		session.update(ordineServizio);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, ordineServizio);

		return ordineServizio;
	}


	@Override
	public OrdineServizio setModalitaOperative(Integer id, String modalitaOperative) {

		OrdineServizio ordineServizio = retrieve(id);
		if(ordineServizio == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, ordineServizio);

		// Update the record.
		//
		Date now = new Date();
		ordineServizio.setModalitaOperative(modalitaOperative);
		ordineServizio.setUltimaModifica(now);
		session.update(ordineServizio);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, ordineServizio);

		return ordineServizio;
	}


	@Override
	public OrdineServizio setOsservazioniFattura(Integer id, String osservazioniFattura) {

		OrdineServizio ordineServizio = retrieve(id);
		if(ordineServizio == null) {
			String message = String.format("It has not been possible to retrieve specified entity: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, ordineServizio);

		// Update the record.
		//
		Date now = new Date();
		ordineServizio.setOsservazioniFattura(osservazioniFattura);
		ordineServizio.setUltimaModifica(now);
		session.update(ordineServizio);

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Destination, ordineServizio);

		return ordineServizio;
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
	public List<Tariffa> listAvailableTariffa(Integer codiceOrdineServizio) {

		String hql =
				"from Tariffa tar " +
				"where tar.contratto.id in " +
				"(select ods.contratto.id from OrdineServizio ods where ods.id = :codiceOrdineServizio) ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);

		List<Tariffa> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Canone> listAvailableCanone(Integer codiceOrdineServizio) {

		String hql =
				"from Canone can " +
				"where can.contratto.id in " +
				"(select ods.contratto.id from OrdineServizio ods where ods.id = :codiceOrdineServizio) ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceOrdineServizio", codiceOrdineServizio);

		List<Canone> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}


	private String getNextCodice(Integer codiceAzienda, Integer anno, boolean increment) {

		// Prepare key for specified year.
		//
		String key = String.format("CODICE_ODS_%04d", anno);

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

			String descrizione = String.format("Contatore per la generazione dei codici ordini di servizio per l'anno %04d", anno);

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

		return String.format("%04d/%06d", year, numeric);
	}
}
