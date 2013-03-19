package it.ivncr.erp.service.posizionemilitare;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.model.personale.Grado;
import it.ivncr.erp.model.personale.PosizioneMilitare;
import it.ivncr.erp.model.personale.TipoPosizioneMilitare;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.Date;
import java.util.Map;

import org.hibernate.Query;

public class PosizioneMilitareServiceImpl extends AbstractService implements PosizioneMilitareService {

	@Override
	public QueryResult<PosizioneMilitare> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters) {

		throw new UnsupportedOperationException("Not implemented.");
	}

	@Override
	public PosizioneMilitare retrieve(Integer id) {

		PosizioneMilitare posizione = (PosizioneMilitare)session.get(PosizioneMilitare.class, id);
		logger.debug("Posizione militare found: " + posizione);

		return posizione;
	}

	@Override
	public PosizioneMilitare retrieveDeep(Integer id) {

		String hql =
				"from PosizioneMilitare pom " +
				"left join fetch pom.tipoPosizioneMilitare tpm " +
				"left join fetch pom.grado gra " +
				"where pom.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		PosizioneMilitare posizione = (PosizioneMilitare)query.uniqueResult();
		logger.debug("Posizione militare found: " + posizione);

		return posizione;
	}


	@Override
	public PosizioneMilitare retrieveByAddettoId(Integer addettoId) {

		String hql =
				"from PosizioneMilitare pom " +
				"left join fetch pom.tipoPosizioneMilitare tpm " +
				"left join fetch pom.grado gra " +
				"where pom.addetto.id = :addettoId ";
		Query query = session.createQuery(hql);
		query.setParameter("addettoId", addettoId);
		PosizioneMilitare posizione = (PosizioneMilitare)query.uniqueResult();
		logger.debug("Posizione militare found: " + posizione);

		return posizione;
	}


	@Override
	public PosizioneMilitare setForAddetto(
			Integer codiceAddetto,
			Integer codiceTipoPosizione,
			String presso,
			Integer codiceGrado,
			Date dataCongedo,
			String note) {

		// Retrieve, if present, current record for specified addetto.
		//
		PosizioneMilitare entity = retrieveByAddettoId(codiceAddetto);

		// If not found, create an empty record.
		//
		if(entity == null) {

			// Fetch addetto entity.
			//
			Addetto addetto = (Addetto)session.get(Addetto.class, codiceAddetto);

			// Create new record.
			//
			entity = new PosizioneMilitare();
			entity.setAddetto(addetto);
		}


		// Fetch referred entities.
		//
		TipoPosizioneMilitare tipoPosizioneMilitare = (TipoPosizioneMilitare)session.get(TipoPosizioneMilitare.class, codiceTipoPosizione);
		Grado grado = (Grado)session.get(Grado.class, codiceGrado);

		// Set fields.
		//
		entity.setTipoPosizioneMilitare(tipoPosizioneMilitare);
		entity.setPresso(presso);
		entity.setGrado(grado);
		entity.setDataCongedo(dataCongedo);
		entity.setNote(note);

		// Persist the record to the database.
		//
		session.saveOrUpdate(entity);

		// Return entity.
		//
		return entity;
	}
}