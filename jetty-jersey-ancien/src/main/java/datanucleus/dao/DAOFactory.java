package datanucleus.dao;

import datanucleus.dao.fake.FlightDAOFakeImpl;

public class DAOFactory {
	
	private static FlightDAOFakeImpl flightDAO = new FlightDAOFakeImpl();
	
	public static FlightDAO getFlightDAO(){
		return flightDAO;
	}
}
