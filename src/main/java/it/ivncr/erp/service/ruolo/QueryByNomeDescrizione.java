package it.ivncr.erp.service.ruolo;

import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByNomeDescrizione extends Query<Ruolo> {

	private String nome;
	private String descrizione;
	
	public QueryByNomeDescrizione(Session session) {
		
		super(session);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
				"from Role ruo " +
				"where 1 = 1 ";
		
		if(nome != null)
			hql += "and upper(ruo.nome) like :nome ";
		
		if(descrizione != null)
			hql += "and upper(ruo.descrizione) like :descrizione ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql = 
				"from Role ruo " +
				"where 1 = 1 ";
				
		if(nome != null)
			hql += "and upper(ruo.nome) like :nome ";
		
		if(descrizione != null)
			hql += "and upper(ruo.descrizione) like :descrizione ";

		if(sortCriteria != null) {
			hql += "order by ruo." + sortCriteria + " ";
			
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

			if (param.equals("nome"))
				q.setParameter("nome", "%" + nome.toUpperCase() + "%");
			
			else if (param.equals("descrizione"))
				q.setParameter("descrizione",  "%" + descrizione.toUpperCase() + "%");
		}
	}
}