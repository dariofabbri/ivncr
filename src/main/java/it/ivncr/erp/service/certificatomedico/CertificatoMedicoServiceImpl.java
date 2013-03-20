package it.ivncr.erp.service.certificatomedico;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.CertificatoMedico;
import it.ivncr.erp.model.personale.TipoCertificatoMedico;
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

public class CertificatoMedicoServiceImpl extends AbstractService implements CertificatoMedicoService {

	@Override
	public QueryResult<CertificatoMedico> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public CertificatoMedico retrieve(Integer id) {

		CertificatoMedico certificato = (CertificatoMedico)session.get(CertificatoMedico.class, id);
		logger.debug("Certificato medico found: " + certificato);

		return certificato;
	}

	@Override
	public CertificatoMedico retrieveDeep(Integer id) {

		String hql =
				"from CertificatoMedico cem " +
				"left join fetch cem.tipoCertificatoMedico tcm " +
				"where cem.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		CertificatoMedico certificato = (CertificatoMedico)query.uniqueResult();
		logger.debug("Certificato medico found: " + certificato);

		return certificato;
	}

	@Override
	public CertificatoMedico create(
			Integer codiceAddetto,
			Integer codiceTipoCertificato,
			Date dataCertificato,
			Date dataRicezione,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note) {

		// Fetch referred entities.
		//
		Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);
		TipoCertificatoMedico tipoCertificatoMedico = (TipoCertificatoMedico)session.get(TipoCertificatoMedico.class, codiceTipoCertificato);

		// Create the new entity.
		//
		CertificatoMedico entity = new CertificatoMedico();

		// Set entity fields.
		//
		entity.setAddetto(addetto);
		entity.setTipoCertificato(tipoCertificatoMedico);
		entity.setDataCertificato(dataCertificato);
		entity.setDataRicezione(dataRicezione);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataFineValidita(dataFineValidita);
		entity.setNote(note);

		// Persist the entity to the database.
		//
		session.save(entity);
		logger.debug("Certificato medico successfully created.");

		// Audit call for the create operation.
		//
		AuditUtil.log(Operation.Create, Snapshot.Destination, entity);

		return entity;
	}


	@Override
	public CertificatoMedico update(
			Integer id,
			Integer codiceTipoCertificato,
			Date dataCertificato,
			Date dataRicezione,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note) {

		CertificatoMedico entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified certificato medico: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		// Audit call for the update operation.
		//
		AuditUtil.log(Operation.Update, Snapshot.Source, entity);

		// Fetch referred entities.
		//
		TipoCertificatoMedico tipoCertificatoMedico = (TipoCertificatoMedico)session.get(TipoCertificatoMedico.class, codiceTipoCertificato);

		// Set entity fields.
		//
		entity.setTipoCertificato(tipoCertificatoMedico);
		entity.setDataCertificato(dataCertificato);
		entity.setDataRicezione(dataRicezione);
		entity.setDataInizioValidita(dataInizioValidita);
		entity.setDataFineValidita(dataFineValidita);
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

		CertificatoMedico entity = retrieve(id);
		if(entity == null) {
			String message = String.format("It has not been possible to retrieve specified certificato medico: %d", id);
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
	public List<CertificatoMedico> listByAddetto(Integer codiceAddetto) {

		String hql =
				"from CertificatoMedico cem " +
				"left join fetch cem.tipoCertificatoMedico tcm " +
				"where cem.addetto.id = :codiceAddetto ";
		Query query = session.createQuery(hql);
		query.setParameter("codiceAddetto", codiceAddetto);

		List<CertificatoMedico> result = query.list();
		logger.debug("Query result: " + result);

		return result;
	}
}