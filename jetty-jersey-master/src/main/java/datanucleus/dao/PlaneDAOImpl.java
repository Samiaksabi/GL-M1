package datanucleus.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import datanucleus.dao.ress.Plane;

public class PlaneDAOImpl implements PlaneDAO {
	
	private PersistenceManagerFactory pmf;

	public PlaneDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	public Collection<Plane> getAll() {
		Collection<Plane> plane;
		Collection<Plane> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("SELECT FROM "+Plane.class.getName());

			plane = (List<Plane>) q.execute();
			detached = (List<Plane>) pm.detachCopyAll(plane);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	public Plane getElement(String id) {
		Collection<Plane> plane;
		Collection<Plane> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Plane.class.getName()+" WHERE identifier == \""+id+"\"");
			/*Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("identifier == id");
			 */
			
			plane = (List<Plane>) q.execute(/*id*/);
			detached = (List<Plane>) pm.detachCopyAll(plane);

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

	public void addElement(Plane elt) {
		
		AirportDAO airportDAO=new AirportDAOImpl(this.pmf);
		if(airportDAO.getElement(elt.airport_ICAO)==null){
			
			//TODO
			/* Should throw an Exception here*/
			
			return;
		}
		
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

	public void deleteElement(String id) {
		Collection<Plane> plane = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q=pm.newQuery("SELECT FROM "+Plane.class.getName()+" WHERE identifier == \""+id+"\"");
			/*
			Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("username == user");
			 
			airport = (Airport) q.execute(id);
			*/
			plane = (List<Plane>) q.execute();
			pm.deletePersistentAll(plane);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;
	}

	public void editElement(String id, Plane elt) {
		Collection<Plane> plane = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Plane.class.getName()+" WHERE identifier == \""+id+"\"");
			
					
			plane = (List<Plane>) q.execute();
			if(!plane.isEmpty()){
				Plane p=plane.iterator().next();
				p.edit(elt);
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
