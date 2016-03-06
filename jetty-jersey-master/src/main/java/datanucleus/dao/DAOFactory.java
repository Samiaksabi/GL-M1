package datanucleus.dao;


import java.util.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import datanucleus.dao.ress.*;


public class DAOFactory {
	
	static private PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("Example");
	
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
	
	public static void populate_db(){
		Airport a = new Airport("CDG");
		getAirportDAO().addElement(a);
		Crew c = new Crew("Albert Lepilote","password","albert@pilote.com",UserStatus.CREW, CrewStatus.PILOT);
		getCrewDAO().addElement(c);
		Plane p = new Plane("TVZ-222","CDG");
		getPlaneDAO().addElement(p);
		Date d = new Date();
		Flight f = new Flight("CN-42","CDG","CDG", new Date(),new Date());
		getFlightDAO().addElement(f);
	}
	
}
