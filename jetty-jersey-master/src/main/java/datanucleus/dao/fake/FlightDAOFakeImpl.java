package datanucleus.dao.fake;

import java.util.Collection;

import database.Database;
import database.DatabaseFactory;
import datanucleus.dao.FlightDAO;
import datanucleus.dao.ress.Flight;

public class FlightDAOFakeImpl implements FlightDAO{

	private static Database db = DatabaseFactory.getDatabase();
		
	public Collection<Flight> getAll(){
		return db.getFlights().getDb();
	}

	public Flight getElement(String id){
		return db.getFlights().get(id);
	}
	
	public void deleteElement(String id){
		db.getFlights().delete(id);
	}

    public Collection<Flight> getAll(String crew){
		return db.getFlights(crew);
	}
	
    public Flight getFlight(String crew,int id){
		return db.getFlight(crew,id);
	}

	public void addElement(Flight f){
		db.getFlights().add(f.commercial_number,f);
	}
	
	public void editElement(String id, Flight f){
		db.getFlights().edit(id, f);
	}
}
