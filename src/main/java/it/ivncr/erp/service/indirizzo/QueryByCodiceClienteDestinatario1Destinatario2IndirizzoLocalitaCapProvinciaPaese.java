package it.ivncr.erp.service.indirizzo;

import it.ivncr.erp.model.commerciale.Indirizzo;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceClienteDestinatario1Destinatario2IndirizzoLocalitaCapProvinciaPaese
	extends Query<Indirizzo> {

	private Integer codiceCliente;
	private String destinatario1;
	private String destinatario2;
	private String indirizzo;
	private String localita;
	private String cap;
	private String provincia;
	private String paese;

	public QueryByCodiceClienteDestinatario1Destinatario2IndirizzoLocalitaCapProvinciaPaese(Session session) {

		super(session);
	}

	public Integer getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(Integer codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getDestinatario1() {
		return destinatario1;
	}

	public void setDestinatario1(String destinatario1) {
		this.destinatario1 = destinatario1;
	}

	public String getDestinatario2() {
		return destinatario2;
	}

	public void setDestinatario2(String destinatario2) {
		this.destinatario2 = destinatario2;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
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
				"from Indirizzo ind " +
				"where ind.cliente.id = :codiceCliente ";

		if(destinatario1 != null)
			hql += "and upper(ind.destinatario1) like :destinatario1 ";

		if(destinatario2 != null)
			hql += "and upper(ind.destinatario2) like :destinatario2 ";

		if(indirizzo != null)
			hql += "and upper(ind.indirizzo) like :indirizzo ";

		if(localita != null)
			hql += "and upper(ind.localita) like :localita ";

		if(cap != null)
			hql += "and upper(ind.cap) like :cap ";

		if(provincia != null)
			hql += "and upper(ind.provincia) like :provincia ";

		if(paese != null)
			hql += "and upper(ind.paese) like :paese ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from Indirizzo ind " +
				"where ind.cliente.id = :codiceCliente ";

		if(destinatario1 != null)
			hql += "and upper(ind.destinatario1) like :destinatario1 ";

		if(destinatario2 != null)
			hql += "and upper(ind.destinatario2) like :destinatario2 ";

		if(indirizzo != null)
			hql += "and upper(ind.indirizzo) like :indirizzo ";

		if(localita != null)
			hql += "and upper(ind.localita) like :localita ";

		if(cap != null)
			hql += "and upper(ind.cap) like :cap ";

		if(provincia != null)
			hql += "and upper(ind.provincia) like :provincia ";

		if(paese != null)
			hql += "and upper(ind.paese) like :paese ";

		if(sortCriteria != null) {
			hql += "order by ind." + sortCriteria + " ";

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

			else if (param.equals("destinatario1"))
				q.setParameter("destinatario1", "%" + destinatario1.toUpperCase() + "%");

			else if (param.equals("destinatario2"))
				q.setParameter("destinatario2", "%" + destinatario2.toUpperCase() + "%");

			else if (param.equals("indirizzo"))
				q.setParameter("indirizzo", "%" + indirizzo.toUpperCase() + "%");

			else if (param.equals("localita"))
				q.setParameter("localita", "%" + localita.toUpperCase() + "%");

			else if (param.equals("cap"))
				q.setParameter("cap", "%" + cap.toUpperCase() + "%");

			else if (param.equals("provincia"))
				q.setParameter("provincia",  "%" + provincia.toUpperCase() + "%");

			else if (param.equals("paese"))
				q.setParameter("paese",  "%" + paese.toUpperCase() + "%");
		}
	}
}