package datanucleus.dao;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import datanucleus.dao.fake.FlightDAOFakeImpl;

public class DAOFactory {
	
	/*static private PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("Example");
	
	public static AirportDAO getAirportDAO(){
		return new AirportDAOImpl(pmf);
	}

	public static PlaneDAO getPlaneDAO(){
		return new PlaneDAOImpl(pmf);
	}
	
	public static UserDAO getUserDAO(){
		return new UserDAOImpl(pmf);
	}
	
	public static CrewDAO getCrewDAO(){
		return new CrewDAOImpl(pmf);
	}*/
	
	public static FlightDAO getFlightDAO(){
		return new FlightDAOFakeImpl();
		//return new FlightDAOImpl(pmf);
	}
	
}
