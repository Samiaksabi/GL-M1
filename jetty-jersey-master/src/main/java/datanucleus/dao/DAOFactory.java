package datanucleus.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class DAOFactory {
	
	static PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("Example");;
	
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
	}
	
	public static FlightDAO getFlightDAO(){
		return new FlightDAOImpl(pmf);
	}
}
