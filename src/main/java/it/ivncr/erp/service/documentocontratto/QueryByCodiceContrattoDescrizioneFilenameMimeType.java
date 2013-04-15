package it.ivncr.erp.service.documentocontratto;

import it.ivncr.erp.model.commerciale.contratto.DocumentoContratto;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceContrattoDescrizioneFilenameMimeType
	extends Query<DocumentoContratto> {

	private Integer codiceContratto;
	private String descrizione;
	private String filename;
	private String mimeType;

	public QueryByCodiceContrattoDescrizioneFilenameMimeType(Session session) {

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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
				"from DocumentoContratto dco " +
				"where dco.contratto.id = :codiceContratto ";

		if(descrizione != null)
			hql += "and upper(dco.descrizione) like :descrizione ";

		if(filename != null)
			hql += "and upper(dco.filename) like :filename ";

		if(mimeType != null)
			hql += "and upper(dco.mimeType) like :mimeType ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from DocumentoContratto dco " +
				"where dco.contratto.id = :codiceContratto ";

		if(descrizione != null)
			hql += "and upper(dco.descrizione) like :descrizione ";

		if(filename != null)
			hql += "and upper(dco.filename) like :filename ";

		if(mimeType != null)
			hql += "and upper(dco.mimeType) like :mimeType ";

		if(sortCriteria != null) {

			hql += "order by dco." + sortCriteria + " ";

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

			else if (param.equals("filename"))
				q.setParameter("filename", "%" + filename.toUpperCase() + "%");

			else if (param.equals("mimeType"))
				q.setParameter("mimeType", "%" + mimeType.toUpperCase() + "%");
		}
	}
}