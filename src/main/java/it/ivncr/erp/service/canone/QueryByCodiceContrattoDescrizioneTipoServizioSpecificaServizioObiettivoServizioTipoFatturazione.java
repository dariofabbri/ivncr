package it.ivncr.erp.service.canone;

import it.ivncr.erp.model.commerciale.contratto.Canone;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoFatturazione
	extends Query<Canone> {

	private Integer codiceContratto;
	private String descrizione;
	private String tipoServizio;
	private String specificaServizio;
	private String obiettivoServizio;
	private String tipoFatturazione;

	public QueryByCodiceContrattoDescrizioneTipoServizioSpecificaServizioObiettivoServizioTipoFatturazione(Session session) {

		super(session);
	}

	public Integer getCodiceContratto() {
		return codiceContratto;
	}

	public void setCodiceContratto(Integer codiceContratto) {
		this.codiceContratto = codiceContratto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public String getTipoFatturazione() {
		return tipoFatturazione;
	}

	public void setTipoFatturazione(String tipoFatturazione) {
		this.tipoFatturazione = tipoFatturazione;
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
				"from Canone can " +
				"where can.contratto.id = :codiceContratto ";

		if(descrizione != null)
			hql += "and upper(can.descrizione) like :descrizione ";

		if(tipoServizio != null)
			hql += "and upper(can.tipoServizio.descrizione) like :tipoServizio ";

		if(specificaServizio != null)
			hql += "and upper(can.specificaServizio.descrizione) like :specificaServizio ";

		if(obiettivoServizio != null)
			hql += "and upper(can.obiettivoServizio.alias) like :obiettivoServizio ";

		if(tipoFatturazione != null)
			hql += "and upper(can.tipoFatturazione.descrizione) like :tipoFatturazione ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Canone can " +
				"where can.contratto.id = :codiceContratto ";

		if(descrizione != null)
			hql += "and upper(can.descrizione) like :descrizione ";

		if(tipoServizio != null)
			hql += "and upper(can.tipoServizio.descrizione) like :tipoServizio ";

		if(specificaServizio != null)
			hql += "and upper(can.specificaServizio.descrizione) like :specificaServizio ";

		if(obiettivoServizio != null)
			hql += "and upper(can.obiettivoServizio.alias) like :obiettivoServizio ";

		if(tipoFatturazione != null)
			hql += "and upper(can.tipoFatturazione.descrizione) like :tipoFatturazione ";

		if(sortCriteria != null) {

			hql += "order by can." + sortCriteria + " ";

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

			else if (param.equals("descrizione"))
				q.setParameter("descrizione", "%" + descrizione.toUpperCase() + "%");

			else if (param.equals("tipoServizio"))
				q.setParameter("tipoServizio", "%" + tipoServizio.toUpperCase() + "%");

			else if (param.equals("specificaServizio"))
				q.setParameter("specificaServizio", "%" + specificaServizio.toUpperCase() + "%");

			else if (param.equals("obiettivoServizio"))
				q.setParameter("obiettivoServizio", "%" + obiettivoServizio.toUpperCase() + "%");

			else if (param.equals("tipoFatturazione"))
				q.setParameter("tipoFatturazione", "%" + tipoFatturazione.toUpperCase() + "%");
		}
	}
}