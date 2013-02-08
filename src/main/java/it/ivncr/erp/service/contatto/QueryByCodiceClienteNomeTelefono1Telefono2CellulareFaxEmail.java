package it.ivncr.erp.service.contatto;

import it.ivncr.erp.model.commerciale.Contatto;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceClienteNomeTelefono1Telefono2CellulareFaxEmail
	extends Query<Contatto> {

	private Integer codiceCliente;
	private String nome;
	private String telefono1;
	private String telefono2;
	private String cellulare;
	private String fax;
	private String email;

	public QueryByCodiceClienteNomeTelefono1Telefono2CellulareFaxEmail(Session session) {

		super(session);
	}

	public Integer getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(Integer codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
				"from Contatto con " +
				"where con.cliente.id = :codiceCliente ";

		if(nome != null)
			hql += "and upper(con.nome) like :nome ";

		if(telefono1 != null)
			hql += "and upper(con.telefono1) like :telefono1 ";

		if(telefono2 != null)
			hql += "and upper(con.telefono2) like :telefono2 ";

		if(cellulare != null)
			hql += "and upper(con.cellulare) like :cellulare ";

		if(fax != null)
			hql += "and upper(con.fax) like :fax ";

		if(email != null)
			hql += "and upper(con.email) like :email ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Contatto con " +
				"where con.cliente.id = :codiceCliente ";

		if(nome != null)
			hql += "and upper(con.nome) like :nome ";

		if(telefono1 != null)
			hql += "and upper(con.telefono1) like :telefono1 ";

		if(telefono2 != null)
			hql += "and upper(con.telefono2) like :telefono2 ";

		if(cellulare != null)
			hql += "and upper(con.cellulare) like :cellulare ";

		if(fax != null)
			hql += "and upper(con.fax) like :fax ";

		if(email != null)
			hql += "and upper(con.email) like :email ";

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

			else if (param.equals("nome"))
				q.setParameter("nome", "%" + nome.toUpperCase() + "%");

			else if (param.equals("telefono1"))
				q.setParameter("telefono1", "%" + telefono1.toUpperCase() + "%");

			else if (param.equals("telefono2"))
				q.setParameter("telefono2", "%" + telefono2.toUpperCase() + "%");

			else if (param.equals("cellulare"))
				q.setParameter("cellulare", "%" + cellulare.toUpperCase() + "%");

			else if (param.equals("fax"))
				q.setParameter("fax", "%" + fax.toUpperCase() + "%");

			else if (param.equals("email"))
				q.setParameter("email",  "%" + email.toUpperCase() + "%");
		}
	}
}