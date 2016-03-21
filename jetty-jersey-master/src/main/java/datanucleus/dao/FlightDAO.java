package datanucleus.dao;


import java.util.Collection;

import datanucleus.dao.ress.Flight;

public interface FlightDAO extends DAO<Flight>{

	 Flight getFlight(String crew_userName,String id);
	 Collection<Flight> getAll(String userName);
	 
}
