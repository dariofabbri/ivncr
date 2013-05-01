package it.ivncr.erp.service.contratto;

import it.ivncr.erp.model.commerciale.contratto.Contratto;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceClienteCodiceAliasRagioneSociale extends Query<Contratto> {

	private Integer codiceCliente;
	private String codice;
	private String alias;
	private String ragioneSociale;

	public QueryByCodiceClienteCodiceAliasRagioneSociale(Session session) {

		super(session);
	}

	public Integer getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(Integer codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	@Override
	protected boolean checkQueryArguments() {

		if(codiceCliente == null) {
			logger.error("Argument codiceCliente cannot be null.");
			return false;
		}
		return true;
	}

	@Override
	protected String getCountHql() {

		String hql =
				"select count(*) " +
				"from Contratto con " +
				"where con.cliente.id = :codiceCliente ";

		if(codice != null)
			hql += "and upper(con.codice) like :codice ";

		if(alias != null)
			hql += "and upper(con.alias) like :alias ";

		if(ragioneSociale != null)
			hql += "and upper(con.ragioneSociale) like :ragioneSociale ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Contratto con " +
				"where con.cliente.id = :codiceCliente ";

		if(codice != null)
			hql += "and upper(con.codice) like :codice ";

		if(alias != null)
			hql += "and upper(con.alias) like :alias ";

		if(ragioneSociale != null)
			hql += "and upper(con.ragioneSociale) like :ragioneSociale ";

		if(sortCriteria != null) {
			hql += "order by con." + sortCriteria + " ";

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

			if (param.equals("codiceCliente"))
				q.setParameter("codiceCliente", codiceCliente);

			else if (param.equals("codice"))
				q.setParameter("codice", "%" + codice.toUpperCase() + "%");

			else if (param.equals("alias"))
				q.setParameter("alias", "%" + alias.toUpperCase() + "%");

			else if (param.equals("ragioneSociale"))
				q.setParameter("ragioneSociale", "%" + ragioneSociale.toUpperCase() + "%");
		}
	}
}