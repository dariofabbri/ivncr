package it.ivncr.erp.service.ruolo;

import it.ivncr.erp.model.accesso.Permesso;
import it.ivncr.erp.model.accesso.Ruolo;
import it.ivncr.erp.service.AbstractService;
import it.ivncr.erp.service.AlreadyPresentException;
import it.ivncr.erp.service.NotFoundException;
import it.ivncr.erp.service.QueryResult;
import it.ivncr.erp.service.SortDirection;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public class RuoloServiceImpl extends AbstractService implements RuoloService {

	@Override
	public QueryResult<Ruolo> list(
			String nome,
			String descrizione,
			Integer offset,
			Integer limit) {

		QueryByNomeDescrizione q = new QueryByNomeDescrizione(session);

		q.setNome(nome);
		q.setDescrizione(descrizione);
		q.setOffset(offset);
		q.setLimit(limit);
		
		return q.query();
	}

	@Override
	public QueryResult<Ruolo> list(
			int first, 
			int pageSize,
			String sortCriteria, 
			SortDirection sortDirection,
			Map<String, String> filters) {

		QueryByNomeDescrizione q = new QueryByNomeDescrizione(session);

		String nome = filters.get("nome");
		String descrizione = filters.get("descrizione");
		
		q.setNome(nome);
		q.setDescrizione(descrizione);
		
		q.setOffset(first);
		q.setLimit(pageSize);
		
		q.setSortCriteria(sortCriteria);
		q.setSortDirection(sortDirection);
		
		QueryResult<Ruolo> result = q.query();
		logger.debug("Query returned: " + result);
		
		return result;
	}
	
	@Override
	public Ruolo retrieve(Integer id) {

		Ruolo ruolo = (Ruolo)session.get(Ruolo.class, id);
		logger.debug("Ruolo found: " + ruolo);
		
		return ruolo;
	}

	@Override
	public Ruolo retrieveByNome(String nome) {

		String hql = 
				"from Ruolo ruo " +
				"where ruo.nome = :nome ";
		Query query = session.createQuery(hql);
		query.setParameter("nome", nome);
		Ruolo ruolo = (Ruolo)query.uniqueResult();
		logger.debug("Ruolo found: " + ruolo);
		
		return ruolo;
	}

	@Override
	public void delete(Integer id) {
		
		Ruolo ruolo = retrieve(id);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		session.delete(ruolo);
	}

	@Override
	public Ruolo create(
			String nome, 
			String descrizione) {
		
		Ruolo ruolo = new Ruolo();
		
		ruolo.setNome(nome);
		ruolo.setDescrizione(descrizione);
		
		session.save(ruolo);
		
		return ruolo;
	}

	@Override
	public Ruolo update(
			Integer id, 
			String nome,
			String descrizione) {
		
		Ruolo ruolo = retrieve(id);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		ruolo.setNome(nome);
		ruolo.setDescrizione(descrizione);
		
		session.update(ruolo);
		
		return ruolo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permesso> retrievePermessi(Integer id) {

		Ruolo ruolo = (Ruolo)session.get(Ruolo.class, id);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		String hql = 
				"select distinct per from Permesso per " +
				"inner join per.ruoli rol " +
				"where rol.id = :id " +
				"order by per.permesso ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Permesso> list = (List<Permesso>)query.list();
		logger.debug("Permessi found: " + list);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permesso> retrievePermessiNotAssociated(Integer id) {

		Ruolo ruolo = (Ruolo)session.get(Ruolo.class, id);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", id);
			logger.info(message);
			throw new NotFoundException(message);
		}

		String hql = 
				"from Permesso per " +
				"where per.id not in " +
				"(select per2.id from Ruolo ruo " +
				"inner join ruo.permessi per2 " +
				"where ruo.id = :id) " +
				"order by per.permesso ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Permesso> list = (List<Permesso>)query.list();
		logger.debug("Permessi found: " + list);
		
		return list;
	}
	
	@Override
	public Permesso addPermesso(Integer ruoloId, Integer permessoId) {

		Ruolo ruolo = retrieve(ruoloId);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified role: %d", ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		Permesso permesso = (Permesso)session.get(Permesso.class, permessoId);
		if(permesso == null) {
			String message = String.format("It has not been possible to retrieve specified permesso: %d", permessoId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		if(ruolo.getPermessi().contains(permesso)) {
			String message = String.format("Specified permesso %d already associated to specified ruolo %d.", permessoId, ruoloId);
			logger.info(message);
			throw new AlreadyPresentException(message);
		}
		
		ruolo.getPermessi().add(permesso);
		session.update(ruolo);
		
		return permesso;
	}
	
	@Override
	public void addPermessi(Integer ruoloId, Integer[] permessiId) {

		Ruolo ruolo = retrieve(ruoloId);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified role: %d", ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		for(Integer permessoId : permessiId) {
			
			Permesso permesso = (Permesso)session.get(Permesso.class, permessoId);
			if(permesso == null) {
				String message = String.format("It has not been possible to retrieve specified permesso: %d", permessoId);
				logger.info(message);
				throw new NotFoundException(message);
			}
			
			if(ruolo.getPermessi().contains(permesso)) {
				String message = String.format("Specified permesso %d already associated to specified ruolo %d.", permessoId, ruoloId);
				logger.info(message);
				throw new AlreadyPresentException(message);
			}

			ruolo.getPermessi().add(permesso);
		}
		
		session.update(ruolo);
	}
	
	@Override
	public void deletePermesso(Integer ruoloId, Integer permessoId) {

		Ruolo ruolo = retrieve(ruoloId);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		Permesso found = null;
		for(Permesso permesso : ruolo.getPermessi()) {
			
			if(permesso.getId().equals(permessoId)) {
				found = permesso;
				break;
			}
		}
		
		if(found == null) {
			String message = String.format("It has not been possible to find permesso %d associated to ruolo %d.", permessoId, ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		ruolo.getPermessi().remove(found);
		session.update(ruolo);
		session.flush();
	}
	
	/*
	@Override
	public void deletePermessi(Integer ruoloId, Integer[] permessiId) {

		Ruolo ruolo = retrieve(ruoloId);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		String hql = 
				"delete from Ruolo.permessi per " +
				"where per.id in (:permessiId) ";
		Query query = session.createQuery(hql);
		query.setParameterList("permessiId", permessiId);
		query.executeUpdate();
	}
	*/

	@Override
	public void deletePermessi(Integer ruoloId, Integer[] permessiId) {

		Ruolo ruolo = retrieve(ruoloId);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		for(Integer permessoId : permessiId) {
			
			Permesso found = null;
			for(Permesso permesso : ruolo.getPermessi()) {
				
				if(permesso.getId().equals(permessoId)) {
					found = permesso;
					break;
				}
			}
			
			if(found == null) {
				String message = String.format("It has not been possible to find permesso %d associated to ruolo %d.", permessoId, ruoloId);
				logger.info(message);
				throw new NotFoundException(message);
			}
			
			ruolo.getPermessi().remove(found);	
		}
		
		session.update(ruolo);
		session.flush();
	}

	@Override
	public void setPermessi(Integer ruoloId, Integer[] permessiId) {

		Ruolo ruolo = retrieve(ruoloId);
		if(ruolo == null) {
			String message = String.format("It has not been possible to retrieve specified ruolo: %d", ruoloId);
			logger.info(message);
			throw new NotFoundException(message);
		}
		
		// Remove all permissions.
		//
		ruolo.getPermessi().clear();
		
		// Iterate on passed array, retrieve selected permissions
		// and add them back to the current role.
		//
		for(Integer permessoId : permessiId) {
			
			Permesso permesso = (Permesso)session.get(Permesso.class, permessoId);
			if(permesso == null) {
				String message = String.format("It has not been possible to retrieve specified permesso: %d", permessoId);
				logger.info(message);
				throw new NotFoundException(message);
			}

			ruolo.getPermessi().add(permesso);
		}
		
		session.update(ruolo);
		session.flush();
	}
}