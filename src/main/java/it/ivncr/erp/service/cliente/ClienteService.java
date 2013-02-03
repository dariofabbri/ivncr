package it.ivncr.erp.service.cliente;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.List;

public interface ClienteService extends EntityService<Cliente> {

	QueryResult<Cliente> list(
			Integer codiceAzienda,
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer offset,
			Integer limit);

	Cliente retrieve(Integer id);
	Cliente retrieveDeep(Integer id);
	Cliente retrieveByCodice(Integer codiceAzienda, String codice);

	Cliente create(
			Integer codiceAzienda,
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer codiceGruppoCliente,
			Integer codiceDivisa,
			Integer codiceTipoBusinessPartner,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email);

	Cliente update(
			Integer id,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer codiceGruppoCliente,
			Integer codiceDivisa,
			Integer codiceTipoBusinessPartner,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email);

	String retrieveNextCodiceAppend(Integer codiceAzienda);
	String[] retrieveNextCodice(Integer codiceAzienda);

	List<Cliente> listConflicts(
			Integer codiceAzienda,
			Integer id,
			String partitaIva,
			String codiceFiscale);
}