package datanucleus.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import datanucleus.dao.ress.Airport;


public class AirportDAOImpl implements AirportDAO {
	
	private PersistenceManagerFactory pmf;

	public AirportDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	public Collection<Airport> getAll() {
		Collection<Airport> airport;
		Collection<Airport> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("SELECT FROM "+Airport.class.getName());

			airport = (List<Airport>) q.execute();
			detached = (List<Airport>) pm.detachCopyAll(airport);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	@SuppressWarnings("unchecked")
	public Airport getElement(String airportICAO) {
		Collection<Airport> airport;
		Collection<Airport> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Airport.class.getName()+" WHERE ICAO_code == \""+airportICAO+"\"");
			/*Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("identifier == id");
			 */
			
			airport = (List<Airport>) q.execute(/*id*/);
			detached = (List<Airport>) pm.detachCopyAll(airport);

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

	public void addElement(Airport elt) {
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

	@SuppressWarnings("unchecked")
	public void deleteElement(String airportICAO) {
		Collection<Airport> airport = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q=pm.newQuery("SELECT FROM "+Airport.class.getName()+" WHERE ICAO_code == \""+airportICAO+"\"");
			/*
			Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("username == user");
			 
			airport = (Airport) q.execute(id);
			*/
			airport = (List<Airport>) q.execute();
			pm.deletePersistentAll(airport);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;
	}

	@SuppressWarnings("unchecked")
	public void editElement(String airportICAO, Airport elt) {
		Collection<Airport> airport = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Airport.class.getName()+" WHERE ICAO_code == \""+airportICAO+"\"");
			
					
			airport = (List<Airport>) q.execute();
			if(!airport.isEmpty()){
				//for(Airport a:air)
				Airport air=airport.iterator().next();
				air.edit(elt);
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
