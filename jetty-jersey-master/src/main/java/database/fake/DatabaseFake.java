package database.fake;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import database.DB;
import database.Database;
import datanucleus.dao.ress.Airport;
import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.Flight;
import datanucleus.dao.ress.Plane;
import datanucleus.dao.ress.User;

public class DatabaseFake implements Database{
	
	/*public static class DatabaseFakeUtils{
		final static int NB_FLIGHTS = 10;
		final static int NB_CREWS = 20;
		public static List<String> names = new ArrayList<String>();
		
		public static String rnd_code(int n){
			String s = "";
			for(int i = 0; i<n; i++){
				if(Math.random()<0.5)
					s+=(char)(Math.random()*25+65);
				else
					s+=(int)(Math.random()*10);
				
			}
			return s;
		}
	
		public static void gen_names(){
			names.add("Julienne");
			names.add("Naouel");
			names.add("Stephane");
			names.add("Samia");
			names.add("Essia");
			names.add("Tantely");
			names.add("Alex");
			names.add("Chen");
			names.add("Elie");
		}
	
		public static String rnd_name(){
			return (names.get((int)(Math.random()*(names.size()-1))) + " " + names.get((int)(Math.random()*(names.size()-1))));
		}

	
	
		public static void populate_db(DatabaseFake db){
			gen_names();
			for(int i = 0; i<NB_CREWS;i++){
				Crew c = new Crew();
				c.name = rnd_name();
				if(Math.random() < 0.5)
					c.crewStatus = CrewStatus.PILOT;
				else
					c.crewStatus = CrewStatus.STEWARD;
				db.crews.add(c.name,c);
			}
			for(int i = 0; i<NB_FLIGHTS; i++){
				Flight f = new Flight();
				f.ATC_code = rnd_code(10);
				f.commercial_number = rnd_code(10);
				f.crew_members = new ArrayList<String>();
				for(int j = 0;j<10;j++)
					f.crew_members.add(db.crews.get((int)(Math.random()*(NB_CREWS-1))).name);
				db.flights.add(f.commercial_number,f);
			}
		}
	}*/
	
	private DBFake<Flight> flights;
	private DBFake<User> users;
	private DBFake<Airport> airports;
	private DBFake<Crew> crews;
	private DBFake<Plane> planes;
	
	public DatabaseFake(){
		flights  = new DBFake<Flight>();
		users    = new DBFake<User>();
		airports = new DBFake<Airport>();
		crews    = new DBFake<Crew>();
		planes   = new DBFake<Plane>();
	}
	
	public Collection<Flight> getFlights(String crew){
		Crew c = crews.get(crew);
		Iterator<Flight> it = flights.getDb().iterator();
		Flight f;
		List<Flight> res = new ArrayList<Flight>();
		while(it.hasNext()){
			f = it.next();
			if(f.crew_members.contains(c))
				res.add(f);
		}
		return res;
	}
	
	public Flight getFlight(String crew, int id){
		Collection<Flight> fc = getFlights(crew);
		Iterator<Flight> it = fc.iterator();
		for(int i = 0; i<id;i++)
			it.next();
		return(it.next());
	}

	public DB<Flight> getFlights() {
		return flights;
	}

	public DB<User> getUsers() {
		return users;
	}

	public DB<Airport> getAirports() {
		return airports;
	}

	public DB<Crew> getCrews() {
		return crews;
	}

	public DB<Plane> getPlanes() {
		return planes;
	}

	public Flight getFlight(String crew, String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

