package it.ivncr.erp.service.dettagliofatturazione;

import it.ivncr.erp.model.commerciale.contratto.DettaglioFatturazione;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryByCodiceContrattoCondizioniPagamentoMetodoPagamentoIndirizzo
	extends Query<DettaglioFatturazione> {

	private Integer codiceContratto;
	private String condizioniPagamento;
	private String metodoPagamento;
	private String indirizzo;

	public QueryByCodiceContrattoCondizioniPagamentoMetodoPagamentoIndirizzo(Session session) {

		super(session);
	}

	public Integer getCodiceContratto() {
		return codiceContratto;
	}

	public void setCodiceContratto(Integer codiceContratto) {
		this.codiceContratto = codiceContratto;
	}

	public String getCondizioniPagamento() {
		return condizioniPagamento;
	}

	public void setCondizioniPagamento(String condizioniPagamento) {
		this.condizioniPagamento = condizioniPagamento;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
				"from DettaglioFatturazione dfa " +
				"where dfa.contratto.id = :codiceContratto ";

		if(condizioniPagamento != null)
			hql += "and upper(dfa.condizioniPagamento.descrizione) like :condizioniPagamento ";

		if(metodoPagamento != null)
			hql += "and upper(dfa.metodoPagamento.descrizione) like :metodoPagamento ";

		if(indirizzo != null)
			hql += "and upper(dfa.indirizzo.indirizzo) like :indirizzo ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql =
				"from DettaglioFatturazione dfa " +
				"where dfa.contratto.id = :codiceContratto ";

		if(condizioniPagamento != null)
			hql += "and upper(dfa.condizioniPagamento.descrizione) like :condizioniPagamento ";

		if(metodoPagamento != null)
			hql += "and upper(dfa.metodoPagamento.descrizione) like :metodoPagamento ";

		if(indirizzo != null)
			hql += "and upper(dfa.indirizzo.indirizzo) like :indirizzo ";

		if(sortCriteria != null) {

			hql += "order by dfa." + sortCriteria + " ";

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

			else if (param.equals("condizioniPagamento"))
				q.setParameter("condizioniPagamento", "%" + condizioniPagamento.toUpperCase() + "%");

			else if (param.equals("metodoPagamento"))
				q.setParameter("metodoPagamento", "%" + metodoPagamento.toUpperCase() + "%");

			else if (param.equals("indirizzo"))
				q.setParameter("indirizzo", "%" + indirizzo.toUpperCase() + "%");
		}
	}
}