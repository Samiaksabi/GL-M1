package database;

import java.util.Collection;

import datanucleus.dao.ress.Airport;
import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.Flight;
import datanucleus.dao.ress.Plane;
import datanucleus.dao.ress.User;

public interface Database{
	
	public Collection<Flight> getFlights(int crew_id);
	public Flight getFlight(int crew_id, int id);
	
	public DB<Flight> getFlights();

	public DB<User> getUsers();

	public DB<Airport> getAirports();

	public DB<Crew> getCrews();

	public DB<Plane> getPlanes();
	
	
}
