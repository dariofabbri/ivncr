package it.ivncr.erp.service.addetto;

import it.ivncr.erp.model.personale.Addetto;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceAziendaMatricolaNomeCognomeCodiceFiscale extends Query<Addetto> {

	private Integer codiceAzienda;
	private String matricola;
	private String nome;
	private String cognome;
	private String codiceFiscale;

	public QueryByCodiceAziendaMatricolaNomeCognomeCodiceFiscale(Session session) {

		super(session);
	}

	public Integer getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(Integer codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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
				"from Addetto add " +
				"where add.azienda.id = :codiceAzienda ";

		if(matricola != null)
			hql += "and upper(add.matricola) like :matricola ";

		if(nome != null)
			hql += "and upper(add.nome) like :nome ";

		if(cognome != null)
			hql += "and upper(add.cognome) like :cognome ";

		if(codiceFiscale != null)
			hql += "and upper(add.codiceFiscale) like :codiceFiscale ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Addetto add " +
				"where add.azienda.id = :codiceAzienda ";

		if(matricola != null)
			hql += "and upper(add.matricola) like :matricola ";

		if(nome != null)
			hql += "and upper(add.nome) like :nome ";

		if(cognome != null)
			hql += "and upper(add.cognome) like :cognome ";

		if(codiceFiscale != null)
			hql += "and upper(add.codiceFiscale) like :codiceFiscale ";

		if(sortCriteria != null) {
			hql += "order by add." + sortCriteria + " ";

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

			else if (param.equals("matricola"))
				q.setParameter("matricola", "%" + matricola.toUpperCase() + "%");

			else if (param.equals("nome"))
				q.setParameter("nome", "%" + nome.toUpperCase() + "%");

			else if (param.equals("cognome"))
				q.setParameter("cognome", "%" + cognome.toUpperCase() + "%");

			else if (param.equals("codiceFiscale"))
				q.setParameter("codiceFiscale",  "%" + codiceFiscale.toUpperCase() + "%");
		}
	}
}