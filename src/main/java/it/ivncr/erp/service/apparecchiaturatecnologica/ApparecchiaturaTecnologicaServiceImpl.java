package it.ivncr.erp.service.apparecchiaturatecnologica;

import it.ivncr.erp.model.commerciale.contratto.ApparecchiaturaTecnologica;
import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.model.commerciale.contratto.TipoApparecchiaturaTecnologica;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.hibernate.Query;

public class ApparecchiaturaTecnologicaServiceImpl extends AbstractService implements ApparecchiaturaTecnologicaService {

	@Override
	public QueryResult<ApparecchiaturaTecnologica> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByCodiceContrattoTipoApparecchiaturaGruppoApparecchiaturaMatricolaDescrizione q =
				new QueryByCodiceContrattoTipoApparecchiaturaGruppoApparecchiaturaMatricolaDescrizione(session);

		Integer codiceContratto = null;
		if(filters.get("codiceContratto") != null)
			codiceContratto = Integer.decode(filters.get("codiceContratto"));

		String tipoApparecchiatura = filters.get("tipoApparecchiaturaTecnologica.descrizione");
		String gruppoApparecchiatura = filters.get("tipoApparecchiaturaTecnologica.gruppoApparecchiatura.descrizione");
		String matricola = filters.get("matricola");
		String descrizione = filters.get("descrizione");

		q.setCodiceContratto(codiceContratto);
		q.setTipoApparecchiatura(tipoApparecchiatura);
		q.setGruppoApparecchiatura(gruppoApparecchiatura);
		q.setMatricola(matricola);
		q.setDescrizione(descrizione);

		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);

		QueryResult<ApparecchiaturaTecnologica> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}

	@Override
	public QueryResult<ApparecchiaturaTecnologica> list(
			Integer codiceContratto,
			String tipoApparecchiatura,
			String gruppoApparecchiatura,
			String matricola,
			String descrizione,
			Integer offset,
			Integer limit) {

		QueryByCodiceContrattoTipoApparecchiaturaGruppoApparecchiaturaMatricolaDescrizione q =
				new QueryByCodiceContrattoTipoApparecchiaturaGruppoApparecchiaturaMatricolaDescrizione(session);

		q.setCodiceContratto(codiceContratto);
		q.setTipoApparecchiatura(tipoApparecchiatura);
		q.setGruppoApparecchiatura(gruppoApparecchiatura);
		q.setMatricola(matricola);
		q.setDescrizione(descrizione);

		q.setOffset(offset);
		q.setLimit(limit);

		QueryResult<ApparecchiaturaTecnologica> result = q.query();
		logger.debug("Query returned: " + result);

		return result;
	}


	@Override
	public ApparecchiaturaTecnologica retrieve(Integer id) {

		ApparecchiaturaTecnologica apparecchiaturaTecnologica = (ApparecchiaturaTecnologica)session.get(ApparecchiaturaTecnologica.class, id);
		logger.debug("Apparecchiatura tecnologica found: " + apparecchiaturaTecnologica);

		return apparecchiaturaTecnologica;
	}


	@Override
	public ApparecchiaturaTecnologica retrieveDeep(Integer id) {

		String hql =
				"from ApparecchiaturaTecnologica ate " +
				"left join fetch ate.tipoApparecchiaturaTecnologica tat " +
				"left join fetch tat.gruppoApparecchiatura gap " +
				"where ate.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ApparecchiaturaTecnologica apparecchiaturaTecnologica = (ApparecchiaturaTecnologica)query.uniqueResult();
		logger.debug("Apparecchiatura tecnologica found: " + apparecchiaturaTecnologica);

		return apparecchiaturaTecnologica;
	}


	@Override
	public ApparecchiaturaTecnologica create(
			Integer codiceContratto,
			Integer codiceTipoApparecchiaturaTecnologica,
			String descrizione,
			String matricola,
			Boolean comodatoUso,
			Date dataInstallazione,
			Date dataFatturazione,
			Date dataRitiro,
			BigDecimal costoUnaTantum,
			String note) {

		// Fetch referred entities.
		//
		Contratto contratto = (Contratto)session.get(Contratto.class, codiceContratto);
		TipoApparecchiaturaTecnologica tipoApparecchiaturaTecnologica = (TipoApparecchiaturaTecnologica)session.get(TipoApparecchiaturaTecnologica.class, codiceTipoApparecchiaturaTecnologica);

		// Create the new entity.
		//
		ApparecchiaturaTecnologica entity = new ApparecchiaturaTecnologica();

		// Set entity fields.
		//
		entity.setContratto(contratto);
		entity.setTipoApparecchiaturaTecnologica(tipoApparecchiaturaTecnologica);
		entity.setDescrizione(descrizione);
		entity.setMatricola(matricola);
		entity.setComodatoUso(comodatoUso);
		entity.setDataInstallazione(dataInstallazione);
		entity.setDataFatturazione(dataFatturazione);
		entity.setDataRitiro(dataRitiro);
		entity.setCostoUnaTantum(costoUnaTantum);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Apparecchiatura tecnologica successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public ApparecchiaturaTecnologica update(
			Integer id,
			Integer codiceTipoApparecchiaturaTecnologica,
			String descrizione,
			String matricola,
			Boolean comodatoUso,
			Date dataInstallazione,
			Date dataFatturazione,
			Date dataRitiro,
			BigDecimal costoUnaTantum,
			String note) {

		ApparecchiaturaTecnologica entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified apparecchiatura tecnologica: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoApparecchiaturaTecnologica tipoApparecchiaturaTecnologica = (TipoApparecchiaturaTecnologica)session.get(TipoApparecchiaturaTecnologica.class, codiceTipoApparecchiaturaTecnologica);

		// Set entity fields.
		//
		entity.setTipoApparecchiaturaTecnologica(tipoApparecchiaturaTecnologica);
		entity.setDescrizione(descrizione);
		entity.setMatricola(matricola);
		entity.setComodatoUso(comodatoUso);
		entity.setDataInstallazione(dataInstallazione);
		entity.setDataFatturazione(dataFatturazione);
		entity.setDataRitiro(dataRitiro);
		entity.setCostoUnaTantum(costoUnaTantum);
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

		ApparecchiaturaTecnologica entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified apparecchiatura tecnologica: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}
}