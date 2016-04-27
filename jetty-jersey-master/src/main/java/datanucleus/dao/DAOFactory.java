package datanucleus.dao;


import java.util.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import datanucleus.dao.ress.*;


public class DAOFactory {
	
	static final private PersistenceManagerFactory pmf=JDOHelper.getPersistenceManagerFactory("FlightPlanificationSystemDataBase");
	
	static final private AirportDAO airportDAO=new AirportDAOImpl(pmf);
	static final private PlaneDAO planeDAO=new PlaneDAOImpl(pmf);
	static final private UserDAO userDAO=new UserDAOImpl(pmf);
	static final private CrewDAO crewDAO=new CrewDAOImpl(pmf);
	static final private FlightDAO flightDAO=new FlightDAOImpl(pmf);
	
	public static AirportDAO getAirportDAO(){
		return DAOFactory.airportDAO;
	}

	public static PlaneDAO getPlaneDAO(){
		return DAOFactory.planeDAO;
	}
	
	public static UserDAO getUserDAO(){
		return DAOFactory.userDAO;
	}
	
	public static CrewDAO getCrewDAO(){
		return DAOFactory.crewDAO;
	}
	
	public static FlightDAO getFlightDAO(){
		return DAOFactory.flightDAO;
	}
	/*public static FlightDAO getFlightDAO(){
		return new FlightDAOFakeImpl();
	}*/
	
	public static void populate_db(){
		Airport a = new Airport("CDG");
		getAirportDAO().addElement(a);
		Airport a2 = new Airport("ORY");
		getAirportDAO().addElement(a2);
		Crew c = new Crew("AlbertLepilote","Albert","Pilote","password","albert@pilote.com",UserStatus.CREW, CrewStatus.PILOT);
		getCrewDAO().addElement(c);
		User u = new User("AlbertCCO","Albert","Machin","password","albert@cco.com",UserStatus.CCO);
		getUserDAO().addElement(u);
		Plane p = new Plane("TVZ-222","CDG");
		getPlaneDAO().addElement(p);
		Flight f = new Flight("CN-42","CDG","ORY", new Date(),new Date());
		getFlightDAO().addElement(f);
	}
	
}
