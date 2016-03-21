package datanucleus.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import datanucleus.dao.ress.Flight;


public class FlightDAOImpl implements FlightDAO {
	
	private PersistenceManagerFactory pmf;
	
	public FlightDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	public Collection<Flight> getAll() {
		Collection<Flight> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);

			Collection<Flight> flight = (List<Flight>) q.execute();
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

	public Flight getElement(String id) {
		Flight detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);
			detached = (Flight) pm.detachCopy(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached;

	}

	public void addElement(Flight elt) {
		
		//AirportDAO airportDAO=new AirportDAOImpl(this.pmf);
		
		if(!elt.isValid()){
			//TODO Should throw an Exception
			return;
		}
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			
			q.declareImports("import java.util.Date");
			q.declareParameters("String commercialNumber,Date departureTime, String departureAirport");
			q.setFilter("departure_airport == departureAirport && commercial_number == commercialNumber && departure_time == departureTime");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(elt.commercial_number,elt.departure_time,elt.departure_airport);
			
			if(flight==null){
				pm.makePersistent(elt);
			}
			else{
				//TODO Should throw an Exception
			}
			
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
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);
			
			pm.deletePersistent(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;

		
	}

	public void editElement(String id, Flight elt) {
		if(!elt.isValid())
			return;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);
	
			if(flight!=null){
				flight.edit(elt);
				/*
				deleteElement(elt.commercial_number);
				addElement(elt);
				*/
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

	public Flight getFlight(String crew_name, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Flight> getAll(String crew_name) {
		
		Collection<Flight> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String crewName");
			q.setFilter("crew_members.contains(crewName)");
			
			Collection<Flight> flight = (List<Flight>) q.execute(crew_name);
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
		
		return detached;
	}
	
}
