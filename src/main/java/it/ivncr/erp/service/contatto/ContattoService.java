package it.ivncr.erp.service.contatto;

import it.ivncr.erp.model.commerciale.Contatto;
import it.ivncr.erp.service.EntityService;
import it.ivncr.erp.service.QueryResult;

import java.util.List;

public interface ContattoService extends EntityService<Contatto> {

	QueryResult<Contatto> list(
			Integer codiceCliente,
			String nome,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email,
			Integer offset,
			Integer limit);

	Contatto retrieve(Integer id);
	Contatto retrieveDeep(Integer id);

	Contatto create(
			Integer codiceCliente,
			Integer codiceTipoContatto,
			String titolo,
			String nome,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email);

	Contatto update(
			Integer id,
			Integer codiceTipoContatto,
			String titolo,
			String nome,
			String telefono1,
			String telefono2,
			String cellulare,
			String fax,
			String email);

	void delete(Integer id);

	List<String> listDistinctTitolo();

	void setDefault(Integer clienteId, Integer contattoId);

	Contatto getDefault(Integer clienteId, boolean fallback);
}