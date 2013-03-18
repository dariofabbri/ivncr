package it.ivncr.erp.service.armamento;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Armamento;
import it.ivncr.erp.model.personale.StatoArma;
import it.ivncr.erp.model.personale.TipoArma;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;
import it.ivncr.erp.util.AuditUtil;
import it.ivncr.erp.util.AuditUtil.Operation;
import it.ivncr.erp.util.AuditUtil.Snapshot;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class ArmamentoServiceImpl extends AbstractService implements ArmamentoService {

	@Override
	public QueryResult<Armamento> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public Armamento retrieve(Integer id) {

		Armamento armamento = (Armamento)session.get(Armamento.class, id);
		logger.debug("Armamento found: " + armamento);

		return armamento;
	}

	@Override
	public Armamento retrieveDeep(Integer id) {

		String hql =
				"from Armamento arm " +
				"left join fetch arm.tipoArma tar " +
				"left join fetch arm.statoArma sar " +
				"where arm.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Armamento armamento = (Armamento)query.uniqueResult();
		logger.debug("Armamento found: " + armamento);

		return armamento;
	}

	@Override
	public Armamento create(
			Integer codiceAddetto,
			Integer codiceTipoArma,
			String modelloArma,
			String calibroArma,
			String matricola,
			Integer codiceStatoArma,
			Date dataDenuncia,
			Date dataInizio,
			Date dataFine,
			String note) {

		// Fetch referred entities.
		//
		TipoArma tipoArma = (TipoArma)session.get(TipoArma.class, codiceTipoArma);
		StatoArma statoArma = (StatoArma)session.get(StatoArma.class, codiceStatoArma);
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

		// Create the new entity.
		//
		Armamento entity = new Armamento();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoArma(tipoArma);
		entity.setModelloArma(modelloArma);
		entity.setCalibroArma(calibroArma);
		entity.setMatricola(matricola);
		entity.setStatoArma(statoArma);
		entity.setDataDenuncia(dataDenuncia);
		entity.setDataInizio(dataInizio);
		entity.setDataFine(dataFine);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Armamento successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public Armamento update(
			Integer id,
			Integer codiceTipoArma,
			String modelloArma,
			String calibroArma,
			String matricola,
			Integer codiceStatoArma,
			Date dataDenuncia,
			Date dataInizio,
			Date dataFine,
			String note) {

		Armamento entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified armamento: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoArma tipoArma = (TipoArma)session.get(TipoArma.class, codiceTipoArma);
		StatoArma statoArma = (StatoArma)session.get(StatoArma.class, codiceStatoArma);

		// Set entity fields.
		//
		entity.setTipoArma(tipoArma);
		entity.setModelloArma(modelloArma);
		entity.setCalibroArma(calibroArma);
		entity.setMatricola(matricola);
		entity.setStatoArma(statoArma);
		entity.setDataDenuncia(dataDenuncia);
		entity.setDataInizio(dataInizio);
		entity.setDataFine(dataFine);
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

		Armamento entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified armamento: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		session.delete(entity);

		// Audit call for the delete operation.
		//
		AuditUtil.log(Operation.Delete, Snapshot.Source, entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Armamento> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from Armamento arm " +
				"left join fetch arm.tipoArma tar " +
				"left join fetch arm.statoArma sar " +
				"where arm.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<Armamento> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}