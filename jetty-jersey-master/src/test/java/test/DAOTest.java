package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import alertSystem.AlertSystem;
import datanucleus.dao.*;
import datanucleus.dao.ress.*;
import junit.framework.Assert;

public class DAOTest {
	
	private static Logger logger = LogManager.getLogger(DAOTest.class);
	
	/*
	@Test
	public void test(){
		
		FlightDAO flightDAO=DAOFactory.getFlightDAO();
		PlaneDAO planeDAO=DAOFactory.getPlaneDAO();
		UserDAO userDAO=DAOFactory.getUserDAO();
		CrewDAO crewDAO=DAOFactory.getCrewDAO();
		AirportDAO airportDAO=DAOFactory.getAirportDAO();
		
		airportDAO.addElement(new Airport("Elie"));
		airportDAO.addElement(new Airport("Samia"));
		airportDAO.addElement(new Airport("Tantely"));
		
		airportDAO.deleteElement("Samia");
		
		Airport a=airportDAO.getElement("Elie");
		System.out.println(a);
		planeDAO.addElement(new Plane("1","Elie"));
		
		//airportDAO.editElement(1, new Airport(1,"Stefan"));
		
		
		Collection<Airport> b=airportDAO.getAll();
		
		if(b.isEmpty()){
			System.out.println("Empty");
		}
		
		for(Airport air:b){
			System.out.println(air);
		}
		
		Collection<Plane> c=planeDAO.getAll();
		
		for(Plane p:c){
			System.out.println(p);
		}
		
		
		userDAO.addElement(new User("alex2","Alex","Ashokoumar","pass","test@eu.com",UserStatus.CREW));
		System.out.println(userDAO.getElement("alex2"));

		crewDAO.addElement(new Crew("Si","Si","Chen","pass","test@eu.com",UserStatus.CREW,CrewStatus.PILOT));
		//crewDAO.deleteElement("Si");
		System.out.println(crewDAO.getAll());
		
		flightDAO.addElement(new Flight("AF057","Elie","Tantely",new Date(),new Date()));
		flightDAO.addElement(new Flight("AF056","Elie","Tantely",new Date(),new Date()));
		//flightDAO.deleteElement("AF057");
		//flightDAO.addNotam("AF057", "Super");;
		System.out.println(flightDAO.getAll());
		
	}
	*/

	@Before
	public void beforeTest() {
		System.out.println("------------------------");
	}
	    
	@After
	public void afterTest() {
		System.out.println("------------------------");
	}

	@Test
	public void testAirport(){
		System.out.println("Test Airport :\n");
		
		AirportDAO airportDAO=DAOAccessor.getAirportDAO();
		
		airportDAO.addElement(new Airport("Elie"));
		airportDAO.addElement(new Airport("Samia"));
		airportDAO.addElement(new Airport("Tantely"));
		
		airportDAO.deleteElement("Samia");
		
		Airport a=airportDAO.getElement("Elie");
		System.out.println(a);
		

		Collection<Airport> b=airportDAO.getAll();
		
		if(b.isEmpty()){
			System.out.println("Empty");
		}
		
		for(Airport air:b){
			System.out.println(air);
		}
		assertEquals(2, b.size());
	}
/*
	@Test
	public void testAirport2(){
		System.out.println("Test Airport 2 :\n");
		
		AirportDAO airportDAO=DAOFactory.getAirportDAO();
		
		airportDAO.addElement(new Airport("Elie"));
		airportDAO.addElement(new Airport("Samia"));
		airportDAO.addElement(new Airport("Tantely"));
		
		airportDAO.deleteElement("Samia");
		
		Airport a=airportDAO.getElement("Elie");
		System.out.println(a);
		

		Collection<Airport> b=airportDAO.getAll();
		
		if(b.isEmpty()){
			System.out.println("Empty");
		}
		
		for(Airport air:b){
			System.out.println(air);
		}
		
	}
*/
	
	@Test
	public void testPlane(){
		System.out.println("Test Plane :\n");
		
		PlaneDAO planeDAO=DAOAccessor.getPlaneDAO();


		//planeDAO.addElement(new Plane("Elie"));
		planeDAO.addElement(new Plane("TBS-253","Elie"));
		
		Collection<Plane> c=planeDAO.getAll();
		
		for(Plane p:c){
			System.out.println(p);
		}
		assertEquals(1,c.size());
	}
	
	@Test
	public void testUserAndCrew(){
		System.out.println("Test User & Crew :\n");
	
		UserDAO userDAO=DAOAccessor.getUserDAO();
		CrewDAO crewDAO=DAOAccessor.getCrewDAO();
		
		userDAO.addElement(new User("alex2","Alex","Ashokoumar","pass","test@eu.com",UserStatus.CREW));
		System.out.println(userDAO.getElement("alex2"));
		
		crewDAO.addElement(new Crew("Si","Si","Chen","pass","test@eu.com",UserStatus.CREW,CrewStatus.PILOT));
		//crewDAO.deleteElement("Si");
		System.out.println(crewDAO.getAll());
		
		assertEquals(2,userDAO.getAll().size());
	}
	
	@Test
	public void testFlight() throws ParseException{
		System.out.println("\nTest Flight :\n");
		FlightDAO flightDAO=DAOAccessor.getFlightDAO();
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse("2007-03-06");
		Date date2=new SimpleDateFormat("yyyy-MM-dd").parse("2007-03-07");
		
		Flight f1=new Flight("AF057","","","Elie","Tantely",date1,new Date());
		Flight f2=new Flight("AF057","","","Elie","Tantely",date2,new Date());
		Flight f3=new Flight("AF057","","","Elie","Tantely",date1,new Date());
		Flight f4=new Flight("AF057","","","Tantely","Elie",date1,new Date());
		
		flightDAO.addElement(f1);
		flightDAO.addElement(f2);
		flightDAO.addElement(f3);
		flightDAO.addElement(f4);
		
		/*
		Plane p=DAOFactory.getPlaneDAO().getElement("TBS-253");
		System.out.println(p);
		f1.setPlane(p);
		*/
		/*
		f1.setPlane(new Plane("test","test"));
		flightDAO.editElement("1", f1);
		*/
		//flightDAO.deleteElement("AF057");
		//flightDAO.addNotam("AF057", "Super");
		
		flightDAO.addCrew("Si", "1");
		flightDAO.addCrew("Si", "1");
		flightDAO.addCrew("Si", "1");
		flightDAO.addCrew("Si", "1");
		
		flightDAO.addCrew("test", "3");

		Collection<Flight> flight=flightDAO.getAll("Si");

		assertEquals(1,flight.size());
		
		/*for(Flight f:flight){
			System.out.println(f);
		}*/
		
		/*
		flight=flightDAO.getAll(null);
		
		for(Flight f:flight){
			System.out.println(f);
		}
		*/
	}
	
}
