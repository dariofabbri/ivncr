package it.ivncr.erp.service.cliente;

import it.ivncr.erp.model.commerciale.Cliente;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

public interface ClienteService extends EntityService<Cliente> {

	QueryResult<Cliente> list(
			String codice,
			String ragioneSociale,
			String partitaIva,
			String codiceFiscale,
			Integer offset,
			Integer limit);

	Cliente retrieve(Integer id);
	Cliente retrieveDeep(Integer id);
	Cliente retrieveByCodice(String codice);

	Cliente create(
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

}