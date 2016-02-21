package database;

import java.util.Collection;

import ress.Airport;
import ress.Crew;
import ress.Flight;
import ress.Plane;
import ress.User;

public interface Database{
	
	public Collection<Flight> getFlights(int crew_id);
	public Flight getFlight(int crew_id, int id);
	
	public DB<Flight> getFlights();

	public DB<User> getUsers();

	public DB<Airport> getAirports();

	public DB<Crew> getCrews();

	public DB<Plane> getPlanes();
	
	
}
