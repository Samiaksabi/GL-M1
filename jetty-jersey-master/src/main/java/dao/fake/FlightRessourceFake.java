package dao.fake;

import java.util.Collection;

import ress.Flight;
import dao.FlightDAO;
import server.FpsServer;

public class FlightRessourceFake implements FlightDAO{

	public Collection<Flight> getAll(){
		return FpsServer.db.flights.getDb();
	}

	public Flight getElement(int id){
		return FpsServer.db.flights.get(id);
	}
	
	public void deleteElement(int id){
		FpsServer.db.flights.delete(id);
	}

    public Collection<Flight> getAll(int crew_id){
		return FpsServer.db.getFlights(crew_id);
	}
	
    public Flight getFlight(int crew_id,int id){
		return FpsServer.db.getFlight(crew_id,id);
	}

	public void addElement(Flight f){
		FpsServer.db.flights.add(f);
	}
	
	public void editElement(int id, Flight f){
		FpsServer.db.flights.edit(id, f);
	}
}
