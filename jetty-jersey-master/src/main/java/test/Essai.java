package test;

import java.util.Collection;
import java.util.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.junit.Test;

import datanucleus.dao.*;
import datanucleus.dao.ress.*;


public class Essai {
	
	@Test
	public void test(){
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Example");
		
		FlightDAO flightDAO=new FlightDAOImpl(pmf);
		
		PlaneDAO planeDAO=new PlaneDAOImpl(pmf);
		
		UserDAO userDAO=new UserDAOImpl(pmf);
		CrewDAO crewDAO=new CrewDAOImpl(pmf);
		
		
		AirportDAO airportDAO=new AirportDAOImpl(pmf);
		
		airportDAO.addElement(new Airport("Elie"));
		airportDAO.addElement(new Airport("Samia"));
		airportDAO.addElement(new Airport("Tantely"));
		
		
		System.out.println("OK");
		
		airportDAO.deleteElement("Samia");
		
		Airport a=airportDAO.getElement("Elie");
		System.out.println(a);
		planeDAO.addElement(new Plane("1","Elie"));
		
		System.out.println(pmf.getManagedClasses());
		/*
		PersistenceManagerFactory pmf2 = JDOHelper.getPersistenceManagerFactory("Example");
		PlaneDAO planeDAO=new PlaneDAOImpl(pmf2);
		planeDAO.addElement(new Plane(1,a));
		*/
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
		
		
		userDAO.addElement(new User("Alex","pass","test@eu.com",UserStatus.CREW));
		System.out.println(userDAO.getElement("Alex"));

		crewDAO.addElement(new Crew("Si","pass","test@eu.com",UserStatus.CREW,CrewStatus.PILOT));
		//crewDAO.deleteElement("Si");
		System.out.println(crewDAO.getAll());
		
		flightDAO.addElement(new Flight("AF057","Elie","Tantely",new Date(),new Date()));
		flightDAO.addElement(new Flight("AF056","Elie","Tantely",new Date(),new Date()));
		//flightDAO.deleteElement("AF057");
		//flightDAO.addNotam("AF057", "Super");;
		System.out.println(flightDAO.getAll());
		
	}
}
