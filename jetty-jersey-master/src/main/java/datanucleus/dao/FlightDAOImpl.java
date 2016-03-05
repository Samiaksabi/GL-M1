package datanucleus.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import datanucleus.dao.ress.Plane;
import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.Flight;


public class FlightDAOImpl implements FlightDAO {
	
	private PersistenceManagerFactory pmf;

	public FlightDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	public Collection<Flight> getAll() {
		Collection<Flight> flight = null;
		Collection<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("SELECT FROM "+Flight.class.getName());

			flight = (List<Flight>) q.execute();
			detached = (List<Flight>) pm.detachCopyAll(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	public Flight getElement(String commercialNumber) {
		Collection<Flight> flight = null;
		Collection<Flight> detached = new ArrayList<Flight>();
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Flight.class.getName()+" WHERE commercial_number == \""+commercialNumber+"\"");
			/*Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("identifier == id");
			 */
			
			flight = (List<Flight>) q.execute(/*id*/);
			detached = (List<Flight>) pm.detachCopyAll(flight);

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

	public void addElement(Flight elt) {
		
		AirportDAO airportDAO=new AirportDAOImpl(this.pmf);
		
		if(airportDAO.getElement(elt.departure_airport)==null){
			System.out.println("airportDAO");
			//TODO Should throw an Exception
			return;
		}
		
		PlaneDAO planeDAO=new PlaneDAOImpl(this.pmf);
		if(planeDAO.getElement(elt.plane)==null){
			System.out.println("planeDAO");
			//TODO Should throw an Exception
			return;
		}
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q=pm.newQuery("SELECT FROM "+Flight.class.getName()+" WHERE commercial_number == \""+elt.commercial_number+"\"");
			/*Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("identifier == id");
			 */
			
			
			boolean volExistant=false;
			
			List<Flight>flight = (List<Flight>) q.execute();
			for(Flight f:flight){
				if(f.departure_time.equals(elt.departure_time) && f.departure_airport.equals(elt.departure_airport)){
					volExistant=true;
					break;
				}
			}

			if(!volExistant){
				pm.makePersistent(elt);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public void deleteElement(String commercialNumber) {
		Collection<Crew> crew = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q=pm.newQuery("SELECT FROM "+Flight.class.getName()+" WHERE commercial_number == \""+commercialNumber+"\"");
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

	public void editElement(String commercialNumber, Flight elt) {
		Collection<Flight> flight = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+Flight.class.getName()+" WHERE commercial_number == \""+commercialNumber+"\"");
			
					
			flight = (List<Flight>) q.execute();
			if(!flight.isEmpty()){
				//for(Airport a:air)
				Flight f=flight.iterator().next();
				f.edit(elt);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

	public Flight getFlight(String crew_name, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Flight> getAll(String crew_name) {
		// TODO Auto-generated method stub
		return null;
	}

}
