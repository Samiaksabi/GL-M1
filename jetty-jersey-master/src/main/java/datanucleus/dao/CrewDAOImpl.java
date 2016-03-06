package datanucleus.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.User;

public class CrewDAOImpl implements CrewDAO{

	private PersistenceManagerFactory pmf;

	public CrewDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Crew> getAll() {
		Collection<Crew> crew;
		Collection<Crew> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("SELECT FROM "+Crew.class.getName());

			crew = (List<Crew>) q.execute();
			detached = (List<Crew>) pm.detachCopyAll(crew);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	public Crew getElement(String name) {
		Collection<Crew> crew;
		Collection<Crew> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Crew.class.getName()+" WHERE name == \""+name+"\"");
			/*Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("identifier == id");
			 */
			
			crew = (List<Crew>) q.execute(/*id*/);
			detached = (List<Crew>) pm.detachCopyAll(crew);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		if(detached.isEmpty()){
			return null;
		}
		return detached.iterator().next();

	}

	public void addElement(Crew elt) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(elt);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public void deleteElement(String name) {
		Collection<Crew> crew = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q=pm.newQuery("SELECT FROM "+Crew.class.getName()+" WHERE name == \""+name+"\"");
			/*
			Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("username == user");
			 
			airport = (Airport) q.execute(id);
			*/
			crew = (List<Crew>) q.execute();
			pm.deletePersistentAll(crew);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;

		
	}

	public void editElement(String name, Crew elt) {
		Collection<Crew> crew = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Crew.class.getName()+" WHERE name == \""+name+"\"");
			
					
			crew = (List<Crew>) q.execute();
			if(!crew.isEmpty()){
				User u=crew.iterator().next();
				u.edit(elt);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}
}
