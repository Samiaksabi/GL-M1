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
		Collection<Plane> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Plane.class);

			Collection<Plane> plane = (List<Plane>) q.execute();
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
		Plane detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Plane.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Plane plane = (Plane) q.execute(id);
			detached = (Plane) pm.detachCopy(plane);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached;
	}

	public void addElement(Plane elt) {
		
		AirportDAO airportDAO=DAOAccessor.getAirportDAO();
		if(airportDAO.getElement(elt.airport)==null){
			
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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Plane.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Plane plane = (Plane) q.execute(id);
			
			pm.deletePersistent(plane);

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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Plane.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Plane plane = (Plane) q.execute(id);
			
			if(plane!=null){
				plane.edit(elt);
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
