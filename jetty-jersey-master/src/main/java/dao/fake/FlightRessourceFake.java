package dao.fake;

import java.util.Collection;

import ress.Flight;
import server.FpsServer;

public abstract class FlightRessourceFake{

	public static Collection<Flight> getAll(){
		return FpsServer.db.flights.getDb();
	}

	public static Flight getElement(int id){
		return FpsServer.db.flights.get(id);
	}
	
	public static void deleteElement(int id){
		FpsServer.db.flights.delete(id);
	}

    public static Collection<Flight> getAll(int crew_id){
		return FpsServer.db.getFlights(crew_id);
	}
	
    public static Flight getFlight(int crew_id,int id){
		return FpsServer.db.getFlight(crew_id,id);
	}

	public static void addElement(Flight f){
		FpsServer.db.flights.add(f);
	}
	
	public static void editElement(int id, Flight f){
		FpsServer.db.flights.edit(id, f);
	}
}
