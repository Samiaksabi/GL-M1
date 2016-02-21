package dao.fake;

import java.util.Collection;

import ress.Flight;
import dao.FlightDAO;
import database.Database;
import database.DatabaseFactory;

public class FlightRessourceFake implements FlightDAO{

	private  static Database db = DatabaseFactory.getDatabase();
	
	public Collection<Flight> getAll(){
		return db.getFlights().getDb();
	}

	public Flight getElement(int id){
		return db.getFlights().get(id);
	}
	
	public void deleteElement(int id){
		db.getFlights().delete(id);
	}

    public Collection<Flight> getAll(int crew_id){
		return db.getFlights(crew_id);
	}
	
    public Flight getFlight(int crew_id,int id){
		return db.getFlight(crew_id,id);
	}

	public void addElement(Flight f){
		db.getFlights().add(f);
	}
	
	public void editElement(int id, Flight f){
		db.getFlights().edit(id, f);
	}
}
