package dao;


import java.util.Collection;

import ress.Flight;

public interface FlightDAO extends DAO<Flight>{

	 Flight getFlight(int crew_id,int id);
	 Collection<Flight> getAll(int crew_id);
	 
}
