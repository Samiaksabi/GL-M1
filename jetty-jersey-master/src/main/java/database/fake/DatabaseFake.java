package database.fake;

import database.*;

import java.util.*;
import ress.*;

public class DatabaseFake implements Database{
	
	public static class DatabaseFakeUtils{
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
					c.status = CrewStatus.PILOT;
				else
					c.status = CrewStatus.STEWARD;
				db.crews.add(c);
			}
			for(int i = 0; i<NB_FLIGHTS; i++){
				Flight f = new Flight();
				f.ATC_code = rnd_code(10);
				f.commercial_number = rnd_code(10);
				f.crew_members = new ArrayList<Crew>();
				for(int j = 0;j<10;j++)
					f.crew_members.add(db.crews.get((int)(Math.random()*(NB_CREWS-1))));
				db.flights.add(f);
			}
		}
	}
	
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
	
	public Collection<Flight> getFlights(int crew_id){
		Crew c = crews.get(crew_id);
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
	
	public Flight getFlight(int crew_id, int id){
		Collection<Flight> fc = getFlights(crew_id);
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
	
	
}

