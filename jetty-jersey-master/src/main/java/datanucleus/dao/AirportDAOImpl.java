package datanucleus.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datanucleus.dao.ress.Airport;
import test.DAOTest;


public class AirportDAOImpl implements AirportDAO {
	
	private static Logger logger = LogManager.getLogger(AirportDAOImpl.class);
	private PersistenceManagerFactory pmf;

	public AirportDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	@SuppressWarnings("unchecked")
	public Collection<Airport> getAll() {
		Collection<Airport> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Airport.class);

			Collection<Airport> airport= (List<Airport>) q.execute();
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
		Airport detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Airport.class);
			q.declareParameters("String airportICAO");
			q.setFilter("ICAO_code == airportICAO");
			q.setUnique(true);
			
			Airport airport=(Airport) q.execute(airportICAO);
			detached = (Airport) pm.detachCopy(airport);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached;
	}
	
	public void addElement(Airport elt) {
		
		if(this.getElement(elt.ICAO_code)!=null){
			logger.error("Airport can't be added because already exist in the database.");
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

	@SuppressWarnings("unchecked")
	public void deleteElement(String airportICAO) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Airport.class);
			
			q.declareParameters("String airportICAO");
			q.setFilter("ICAO_code == airportICAO");
			q.setUnique(true);
			
			Airport airport = (Airport) q.execute(airportICAO);
			if(airport==null){
				logger.warn("Airport can't be deleted because doesn't exist in the database.");
			}
			pm.deletePersistent(airport);

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
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Airport.class);
			
			q.declareParameters("String airportICAO");
			q.setFilter("ICAO_code == airportICAO");
			q.setUnique(true);
			
			Airport airport = (Airport) q.execute(airportICAO);
			
			if(airport!=null){
				airport.edit(elt);
			}
			else{
				logger.error("Airport can't be edited because doesn't exist in the database.");
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
