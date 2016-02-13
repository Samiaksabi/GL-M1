package main;

import server.FpsServer;
import ress.*;
import java.util.*;
public class Main {
	
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

	
	
	public static void populate_db(){
		
		gen_names();
		
		for(int i = 0; i<NB_CREWS;i++){
			Crew c = new Crew();
			c.name = rnd_name();
			if(Math.random() < 0.5)
				c.status = CrewStatus.PILOT;
			else
				c.status = CrewStatus.STEWARD;
			FpsServer.db.crews.add(c);
		}
		
		for(int i = 0; i<NB_FLIGHTS; i++){
			Flight f = new Flight();
			f.ATC_code = rnd_code(10);
			f.commercial_number = rnd_code(10);
			f.crew_members = new ArrayList<Crew>();
			for(int j = 0;j<10;j++)
				f.crew_members.add(FpsServer.db.crews.get((int)(Math.random()*(NB_CREWS-1))));
			FpsServer.db.flights.add(f);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		FpsServer.init();
		populate_db();
		FpsServer.start();
	}

}
