package it.ivncr.erp.service.certificatomedico;

import it.ivncr.erp.model.personale.CertificatoMedico;
import it.ivncr.erp.service.EntityService;

import java.util.Date;
import java.util.List;

public interface CertificatoMedicoService extends EntityService<CertificatoMedico> {

	CertificatoMedico retrieve(Integer id);
	CertificatoMedico retrieveDeep(Integer id);

	CertificatoMedico create(
			Integer codiceAddetto,
			Integer codiceTipoCertificato,
			Date dataCertificato,
			Date dataRicezione,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note);

	CertificatoMedico update(
			Integer id,
			Integer codiceTipoCertificato,
			Date dataCertificato,
			Date dataRicezione,
			Date dataInizioValidita,
			Date dataFineValidita,
			String note);

	void delete(Integer id);

	List<CertificatoMedico> listByAddetto(Integer codiceAddetto);
}