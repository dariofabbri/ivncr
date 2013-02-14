package it.ivncr.erp.service.obiettivoservizio;

import it.ivncr.erp.model.commerciale.ObiettivoServizio;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceClienteAliasIndirizzoEdificioScalaPianoInternoLocalitaCapProvinciaPaese
	extends Query<ObiettivoServizio> {

	private Integer codiceCliente;
	private String alias;
	private String indirizzo;
	private String edificio;
	private String scala;
	private String piano;
	private String interno;
	private String localita;
	private String cap;
	private String provincia;
	private String paese;

	public QueryByCodiceClienteAliasIndirizzoEdificioScalaPianoInternoLocalitaCapProvinciaPaese(Session session) {

		super(session);
	}

	public Integer getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(Integer codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getPiano() {
		return piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
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
				"from ObiettivoServizio obs " +
				"where obs.cliente.id = :codiceCliente ";

		if(alias != null)
			hql += "and upper(obs.alias) like :alias ";

		if(edificio != null)
			hql += "and upper(obs.edificio) like :edificio ";

		if(scala != null)
			hql += "and upper(obs.scala) like :scala ";

		if(piano != null)
			hql += "and upper(obs.piano) like :piano ";

		if(interno != null)
			hql += "and upper(obs.interno) like :interno ";

		if(indirizzo != null)
			hql += "and upper(obs.indirizzo) like :indirizzo ";

		if(localita != null)
			hql += "and upper(obs.localita) like :localita ";

		if(cap != null)
			hql += "and upper(obs.cap) like :cap ";

		if(provincia != null)
			hql += "and upper(obs.provincia) like :provincia ";

		if(paese != null)
			hql += "and upper(obs.paese) like :paese ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from ObiettivoServizio obs " +
				"where obs.cliente.id = :codiceCliente ";

		if(alias != null)
			hql += "and upper(obs.alias) like :alias ";

		if(edificio != null)
			hql += "and upper(obs.edificio) like :edificio ";

		if(scala != null)
			hql += "and upper(obs.scala) like :scala ";

		if(piano != null)
			hql += "and upper(obs.piano) like :piano ";

		if(interno != null)
			hql += "and upper(obs.interno) like :interno ";

		if(indirizzo != null)
			hql += "and upper(obs.indirizzo) like :indirizzo ";

		if(localita != null)
			hql += "and upper(obs.localita) like :localita ";

		if(cap != null)
			hql += "and upper(obs.cap) like :cap ";

		if(provincia != null)
			hql += "and upper(obs.provincia) like :provincia ";

		if(paese != null)
			hql += "and upper(obs.paese) like :paese ";

		if(sortCriteria != null) {
			hql += "order by obs." + sortCriteria + " ";

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

			else if (param.equals("alias"))
				q.setParameter("alias", "%" + alias.toUpperCase() + "%");

			else if (param.equals("indirizzo"))
				q.setParameter("indirizzo", "%" + indirizzo.toUpperCase() + "%");

			else if (param.equals("edificio"))
				q.setParameter("edificio", "%" + edificio.toUpperCase() + "%");

			else if (param.equals("piano"))
				q.setParameter("piano", "%" + piano.toUpperCase() + "%");

			else if (param.equals("scala"))
				q.setParameter("scala", "%" + scala.toUpperCase() + "%");

			else if (param.equals("interno"))
				q.setParameter("interno", "%" + interno.toUpperCase() + "%");

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