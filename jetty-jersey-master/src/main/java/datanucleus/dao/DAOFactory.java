package datanucleus.dao;

import dao.FlightDAO;
import dao.fake.FlightRessourceFake;

public class DAOFactory {
	
	private static FlightRessourceFake flightRessource = new FlightRessourceFake();
	
	public static FlightDAO getFlightDAO(){
		return flightRessource;
	}
}
