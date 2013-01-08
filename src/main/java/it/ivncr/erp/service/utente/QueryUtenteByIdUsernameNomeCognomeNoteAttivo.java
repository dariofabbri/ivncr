package it.ivncr.erp.service.utente;

import it.ivncr.erp.model.accesso.Utente;
import it.ivncr.erp.service.Query;

import org.hibernate.Session;

public class QueryUtenteByIdUsernameNomeCognomeNoteAttivo extends Query<Utente> {

	private Integer id;
	private String username;
	private String nome;
	private String cognome;
	private String note;
	private Boolean attivo;
	
	public QueryUtenteByIdUsernameNomeCognomeNoteAttivo(Session session) {
		
		super(session);
		
		sortCriteria = "id";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	@Override
	protected boolean checkQueryArguments() {

		return true;
	}

	@Override
	protected String getCountHql() {

		String hql = 
				"select count(*) " +
				"from Utente ute " +
				"where 1 = 1 ";
		
		if(id != null)
			hql += "and ute.id = :id ";
		
		if(username != null)
			hql += "and upper(ute.username) like :username ";
		
		if(nome != null)
			hql += "and upper(ute.nome) like :nome ";
		
		if(cognome != null)
			hql += "and upper(ute.cognome) like :cognome ";
		
		if(note != null)
			hql += "and upper(ute.note) like :note ";
		
		if(attivo != null)
			hql += "and ute.attivo = :attivo ";

		return hql;
	}

	@Override
	protected String getQueryHql() {

		String hql = 
				"from Utente ute " +
				"where 1 = 1 ";
		
		if(id != null)
			hql += "and ute.id = :id ";
		
		if(username != null)
			hql += "and upper(ute.username) like :username ";
		
		if(nome != null)
			hql += "and upper(ute.nome) like :nome ";
		
		if(cognome != null)
			hql += "and upper(ute.cognome) like :cognome ";
		
		if(note != null)
			hql += "and upper(ute.note) like :note ";
		
		if(attivo != null)
			hql += "and ute.attivo = :attivo ";

		if(sortCriteria != null) {
			hql += "order by ute." + sortCriteria + " ";
			
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

			if (param.equals("id"))
				q.setParameter("id", id);
			
			else if (param.equals("username"))
				q.setParameter("username", "%" + username.toUpperCase() + "%");

			else if (param.equals("nome"))
				q.setParameter("nome", "%" + nome.toUpperCase() + "%");
			
			else if (param.equals("cognome"))
				q.setParameter("cognome", "%" + cognome.toUpperCase() + "%");
			
			else if (param.equals("note"))
				q.setParameter("note",  "%" + note.toUpperCase() + "%");
		
			else if (param.equals("attivo"))
				q.setParameter("attivo", attivo);
		}
	}
}