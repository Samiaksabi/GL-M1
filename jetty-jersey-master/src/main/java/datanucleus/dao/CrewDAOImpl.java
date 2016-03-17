package datanucleus.dao;

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
		Collection<Crew> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Crew.class);

			Collection<Crew> crew = (List<Crew>) q.execute();
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

	public Crew getElement(String userName) {
		Crew detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Crew.class);
			q.declareParameters("String name");
			q.setFilter("userName == name");
			q.setUnique(true);
			
			Crew crew = (Crew) q.execute(userName);
			detached = (Crew) pm.detachCopy(crew);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached;

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

	public void deleteElement(String userName) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
				
			Query q = pm.newQuery(Crew.class);
			q.declareParameters("String name");
			q.setFilter("userName == name");
			q.setUnique(true);
			
			Crew crew = (Crew) q.execute(userName);
			pm.deletePersistent(crew);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;
	}

	public void editElement(String userName, Crew elt) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Crew.class);
			q.declareParameters("String name");
			q.setFilter("userName == name");
			q.setUnique(true);
			
			Crew crew = (Crew) q.execute(userName);
			if(crew!=null){
				crew.edit(elt);
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
