package it.ivncr.erp.service.cliente;

import it.ivncr.erp.model.commerciale.cliente.Cliente;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceAziendaCodiceRagioneSocialePartitaIvaCodiceFiscale extends Query<Cliente> {

	private Integer codiceAzienda;
	private String codice;
	private String ragioneSociale;
	private String partitaIva;
	private String codiceFiscale;

	public QueryByCodiceAziendaCodiceRagioneSocialePartitaIvaCodiceFiscale(Session session) {

		super(session);
	}

	public Integer getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(Integer codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	@Override
	protected boolean checkQueryArguments() {

		if(codiceAzienda == null) {
			logger.error("Argument codiceAzienda cannot be null.");
			return false;
		}
		return true;
	}

	@Override
	protected String getCountHql() {

		String hql =
				"select count(*) " +
				"from Cliente cli " +
				"where cli.azienda.id = :codiceAzienda ";

		if(codice != null)
			hql += "and upper(cli.codice) like :codice ";

		if(ragioneSociale != null)
			hql += "and upper(cli.ragioneSociale) like :ragioneSociale ";

		if(partitaIva != null)
			hql += "and upper(cli.partitaIva) like :partitaIva ";

		if(codiceFiscale != null)
			hql += "and upper(cli.codiceFiscale) like :codiceFiscale ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Cliente cli " +
				"where cli.azienda.id = :codiceAzienda ";

		if(codice != null)
			hql += "and upper(cli.codice) like :codice ";

		if(ragioneSociale != null)
			hql += "and upper(cli.ragioneSociale) like :ragioneSociale ";

		if(partitaIva != null)
			hql += "and upper(cli.partitaIva) like :partitaIva ";

		if(codiceFiscale != null)
			hql += "and upper(cli.codiceFiscale) like :codiceFiscale ";

		if(sortCriteria != null) {
			hql += "order by cli." + sortCriteria + " ";

			if(sortDirection != null)
				hql += sortDirection.toHql();
		}

		return hql;
	}

	@Override
	protected void setQueryArguments(org.hibernate.Query q) {

		String[] named_params = q.getNamedParameters();
		for (int i = 0; i < named_params.length; ++i) {
			String param = named_params[i];

			if (param.equals("codiceAzienda"))
				q.setParameter("codiceAzienda", codiceAzienda);

			else if (param.equals("codice"))
				q.setParameter("codice", "%" + codice.toUpperCase() + "%");

			else if (param.equals("ragioneSociale"))
				q.setParameter("ragioneSociale", "%" + ragioneSociale.toUpperCase() + "%");

			else if (param.equals("partitaIva"))
				q.setParameter("partitaIva", "%" + partitaIva.toUpperCase() + "%");

			else if (param.equals("codiceFiscale"))
				q.setParameter("codiceFiscale",  "%" + codiceFiscale.toUpperCase() + "%");
		}
	}
}