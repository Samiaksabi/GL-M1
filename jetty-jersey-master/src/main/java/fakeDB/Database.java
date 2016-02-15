package fakeDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

import ress.*;
import server.FpsServer;

public class Database {
	
	public DB<Flight> flights;
	public DB<User> users;
	public DB<Airport> airports;
	public DB<Crew> crews;
	public DB<Plane> planes;
	
	public Database(){
		flights  = new DB<Flight>();
		users    = new DB<User>();
		airports = new DB<Airport>();
		crews    = new DB<Crew>();
		planes   = new DB<Plane>();
	}
	
	public Collection<Flight> getFlights(int crew_id){
		Crew c = FpsServer.db.crews.get(crew_id);
		Iterator<Flight> it = FpsServer.db.flights.getDb().iterator();
		Flight f;
		List<Flight> res = new ArrayList<Flight>();
		while(it.hasNext()){
			f = it.next();
			if(f.crew_members.contains(c))
				res.add(f);
		}
		return res;
	}
	
	public Flight getFlight(int crew_id, int id){
		Collection<Flight> fc = getFlights(crew_id);
		Iterator<Flight> it = fc.iterator();
		for(int i = 0; i<id;i++)
			it.next();
		return(it.next());
	}
	
}
