package it.ivncr.erp.service.ordineservizio;

import it.ivncr.erp.model.commerciale.ods.OrdineServizio;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceAziendaCodiceContrattoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio extends Query<OrdineServizio> {

	private Integer codiceAzienda;
	private String codiceContratto;
	private String codice;
	private String alias;
	private String tipoServizio;
	private String specificaServizio;
	private String obiettivoServizio;

	public QueryByCodiceAziendaCodiceContrattoCodiceAliasTipoServizioSpecificaServizioObiettivoServizio(Session session) {

		super(session);
	}

	public Integer getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(Integer codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getCodiceContratto() {
		return codiceContratto;
	}

	public void setCodiceContratto(String codiceContratto) {
		this.codiceContratto = codiceContratto;
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

	public String getObiettivoServizio() {
		return obiettivoServizio;
	}

	public void setObiettivoServizio(String obiettivoServizio) {
		this.obiettivoServizio = obiettivoServizio;
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
				"from OrdineServizio ods " +
				"where ods.contratto.cliente.azienda.id = :codiceAzienda ";

		if(codiceContratto != null)
			hql += "and upper(ods.contratto.codice) like :codiceContratto ";

		if(codice != null)
			hql += "and upper(ods.codice) like :codice ";

		if(alias != null)
			hql += "and upper(ods.alias) like :alias ";

		if(tipoServizio != null)
			hql += "and upper(ods.tipoServizio.descrizione) like :tipoServizio ";

		if(specificaServizio != null)
			hql += "and upper(ods.specificaServizio.descrizione) like :specificaServizio ";

		if(obiettivoServizio != null)
			hql += "and upper(ods.obiettivoServizio.alias) like :obiettivoServizio ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from OrdineServizio ods " +
				"where ods.contratto.cliente.azienda.id = :codiceAzienda ";

		if(codiceContratto != null)
			hql += "and upper(ods.contratto.codice) like :codiceContratto ";

		if(codice != null)
			hql += "and upper(ods.codice) like :codice ";

		if(alias != null)
			hql += "and upper(ods.alias) like :alias ";

		if(tipoServizio != null)
			hql += "and upper(ods.tipoServizio.descrizione) like :tipoServizio ";

		if(specificaServizio != null)
			hql += "and upper(ods.specificaServizio.descrizione) like :specificaServizio ";

		if(obiettivoServizio != null)
			hql += "and upper(ods.obiettivoServizio.alias) like :obiettivoServizio ";

		if(sortCriteria != null) {
			hql += "order by ods." + sortCriteria + " ";

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

			else if (param.equals("codiceContratto"))
				q.setParameter("codiceContratto", "%" + codiceContratto.toUpperCase() + "%");

			else if (param.equals("codice"))
				q.setParameter("codice", "%" + codice.toUpperCase() + "%");

			else if (param.equals("alias"))
				q.setParameter("alias", "%" + alias.toUpperCase() + "%");

			else if (param.equals("tipoServizio"))
				q.setParameter("tipoServizio", "%" + tipoServizio.toUpperCase() + "%");

			else if (param.equals("specificaServizio"))
				q.setParameter("specificaServizio", "%" + specificaServizio.toUpperCase() + "%");

			else if (param.equals("obiettivoServizio"))
				q.setParameter("obiettivoServizio", "%" + obiettivoServizio.toUpperCase() + "%");
		}
	}
}