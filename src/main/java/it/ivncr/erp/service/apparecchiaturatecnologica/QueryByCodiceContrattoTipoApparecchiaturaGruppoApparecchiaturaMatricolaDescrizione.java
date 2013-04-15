package it.ivncr.erp.service.apparecchiaturatecnologica;

import it.ivncr.erp.model.commerciale.contratto.ApparecchiaturaTecnologica;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceContrattoTipoApparecchiaturaGruppoApparecchiaturaMatricolaDescrizione
	extends Query<ApparecchiaturaTecnologica> {

	private Integer codiceContratto;
	private String tipoApparecchiatura;
	private String gruppoApparecchiatura;
	private String matricola;
	private String descrizione;

	public QueryByCodiceContrattoTipoApparecchiaturaGruppoApparecchiaturaMatricolaDescrizione(Session session) {

		super(session);
	}

	public Integer getCodiceContratto() {
		return codiceContratto;
	}

	public void setCodiceContratto(Integer codiceContratto) {
		this.codiceContratto = codiceContratto;
	}

	public String getTipoApparecchiatura() {
		return tipoApparecchiatura;
	}

	public void setTipoApparecchiatura(String tipoApparecchiatura) {
		this.tipoApparecchiatura = tipoApparecchiatura;
	}

	public String getGruppoApparecchiatura() {
		return gruppoApparecchiatura;
	}

	public void setGruppoApparecchiatura(String gruppoApparecchiatura) {
		this.gruppoApparecchiatura = gruppoApparecchiatura;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
				"from ApparecchiaturaTecnologica ate " +
				"where ate.contratto.id = :codiceContratto ";

		if(tipoApparecchiatura != null)
			hql += "and upper(ate.tipoApparecchiaturaTecnologica.descrizione) like :tipoApparecchiatura ";

		if(gruppoApparecchiatura != null)
			hql += "and upper(ate.tipoApparecchiaturaTecnologica.gruppoApparecchiatura.descrizione) like :gruppoApparecchiatura ";

		if(matricola != null)
			hql += "and upper(ate.matricola) like :matricola ";

		if(descrizione != null)
			hql += "and upper(ate.descrizione) like :descrizione ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from ApparecchiaturaTecnologica ate " +
				"left join fetch ate.tipoApparecchiaturaTecnologica tat " +
				"left join fetch tat.gruppoApparecchiatura gap " +
				"where ate.contratto.id = :codiceContratto ";

		if(tipoApparecchiatura != null)
			hql += "and upper(ate.tipoApparecchiaturaTecnologica.descrizione) like :tipoApparecchiatura ";

		if(gruppoApparecchiatura != null)
			hql += "and upper(ate.tipoApparecchiaturaTecnologica.gruppoApparecchiatura.descrizione) like :gruppoApparecchiatura ";

		if(matricola != null)
			hql += "and upper(ate.matricola) like :matricola ";

		if(descrizione != null)
			hql += "and upper(ate.descrizione) like :descrizione ";

		if(sortCriteria != null) {

			hql += "order by ate." + sortCriteria + " ";

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

			else if (param.equals("tipoApparecchiatura"))
				q.setParameter("tipoApparecchiatura", "%" + tipoApparecchiatura.toUpperCase() + "%");

			else if (param.equals("gruppoApparecchiatura"))
				q.setParameter("gruppoApparecchiatura", "%" + gruppoApparecchiatura.toUpperCase() + "%");

			else if (param.equals("matricola"))
				q.setParameter("matricola", "%" + matricola.toUpperCase() + "%");

			else if (param.equals("descrizione"))
				q.setParameter("descrizione", "%" + descrizione.toUpperCase() + "%");
		}
	}
}