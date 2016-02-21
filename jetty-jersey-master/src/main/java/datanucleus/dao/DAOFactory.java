package datanucleus.dao;

import dao.FlightDAO;
import dao.fake.FlightRessourceFake;

public class DAOFactory {
	
	public static FlightDAO getFlightDAO(){
		return new FlightRessourceFake();
	}
}
