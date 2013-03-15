package it.ivncr.erp.service.recapitoemail;

import it.ivncr.erp.model.personale.RecapitoEmail;
import it.ivncr.erp.service.EntityService;

import java.util.List;

public interface RecapitoEmailService extends EntityService<RecapitoEmail> {

	RecapitoEmail retrieve(Integer id);

	RecapitoEmail create(
			Integer codiceAddetto,
			String email);

	RecapitoEmail update(
			Integer id,
			String email);

	void delete(Integer id);

	List<RecapitoEmail> listByAddetto(Integer codiceAddetto);
}