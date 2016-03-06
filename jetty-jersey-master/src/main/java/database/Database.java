package database;

import java.util.Collection;

import datanucleus.dao.ress.Airport;
import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.Flight;
import datanucleus.dao.ress.Plane;
import datanucleus.dao.ress.User;

public interface Database{
	
	public Collection<Flight> getFlights(String crew);
	public Flight getFlight(String crew, int id);
	
	public DB<Flight> getFlights();

	public DB<User> getUsers();

	public DB<Airport> getAirports();

	public DB<Crew> getCrews();

	public DB<Plane> getPlanes();
	
	
}
