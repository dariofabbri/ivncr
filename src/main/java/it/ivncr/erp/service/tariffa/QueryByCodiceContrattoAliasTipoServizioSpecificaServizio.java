package it.ivncr.erp.service.tariffa;

import it.ivncr.erp.model.commerciale.contratto.Tariffa;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceContrattoAliasTipoServizioSpecificaServizio
	extends Query<Tariffa> {

	private Integer codiceContratto;
	private String alias;
	private String tipoServizio;
	private String specificaServizio;

	public QueryByCodiceContrattoAliasTipoServizioSpecificaServizio(Session session) {

		super(session);
	}

	public Integer getCodiceContratto() {
		return codiceContratto;
	}

	public void setCodiceContratto(Integer codiceContratto) {
		this.codiceContratto = codiceContratto;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getSpecificaServizio() {
		return specificaServizio;
	}

	public void setSpecificaServizio(String specificaServizio) {
		this.specificaServizio = specificaServizio;
	}

	@Override
	protected boolean checkQueryArguments() {

		if(codiceContratto == null) {
			logger.error("Argument codiceContratto cannot be null.");
			return false;
		}
		return true;
	}

	@Override
	protected String getCountHql() {

		String hql =
				"select count(*) " +
				"from Tariffa tar " +
				"where tar.contratto.id = :codiceContratto ";

		if(alias != null)
			hql += "and upper(tar.alias) like :alias ";

		if(tipoServizio != null)
			hql += "and upper(tar.tipoServizio.descrizione) like :tipoServizio ";

		if(specificaServizio != null)
			hql += "and upper(tar.specificaServizio.descrizione) like :specificaServizio ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Tariffa tar " +
				"where tar.contratto.id = :codiceContratto ";

		if(alias != null)
			hql += "and upper(tar.alias) like :alias ";

		if(tipoServizio != null)
			hql += "and upper(tar.tipoServizio.descrizione) like :tipoServizio ";

		if(specificaServizio != null)
			hql += "and upper(tar.specificaServizio.descrizione) like :specificaServizio ";

		if(sortCriteria != null) {

			hql += "order by tar." + sortCriteria + " ";

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

			if (param.equals("codiceContratto"))
				q.setParameter("codiceContratto", codiceContratto);

			else if (param.equals("alias"))
				q.setParameter("alias", "%" + alias.toUpperCase() + "%");

			else if (param.equals("tipoServizio"))
				q.setParameter("tipoServizio", "%" + tipoServizio.toUpperCase() + "%");

			else if (param.equals("specificaServizio"))
				q.setParameter("specificaServizio", "%" + specificaServizio.toUpperCase() + "%");
		}
	}
}