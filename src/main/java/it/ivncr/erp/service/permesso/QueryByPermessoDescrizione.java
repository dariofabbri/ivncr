package it.ivncr.erp.service.permesso;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByPermessoDescrizione extends Query<Permesso> {

	private String permesso;
	private String descrizione;
	
	public QueryByPermessoDescrizione(Session session) {
		
		super(session);
	}

	public String getPermesso() {
		return permesso;
	}

	public void setPermesso(String permesso) {
		this.permesso = permesso;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	protected boolean checkQueryArguments() {

		return true;
	}

	@Override
	protected String getCountHql() {

		String hql = 
				"select count(*) " +
				"from Permesso per " +
				"where 1 = 1 ";
		
		if(permesso != null)
			hql += "and upper(per.permesso) like :permesso ";
		
		if(descrizione != null)
			hql += "and upper(per.descrizione) like :descrizione ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql = 
				"from Permesso per " +
				"where 1 = 1 ";
		
		if(permesso != null)
			hql += "and upper(per.permesso) like :permesso ";
		
		if(descrizione != null)
			hql += "and upper(per.descrizione) like :descrizione ";

		if(sortCriteria != null) {
			hql += "order by per." + sortCriteria + " ";
			
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

			if (param.equals("permesso"))
				q.setParameter("permesso", "%" + permesso.toUpperCase() + "%");

			else if (param.equals("descrizione"))
				q.setParameter("descrizione", "%" + descrizione.toUpperCase() + "%");
		}
	}
}