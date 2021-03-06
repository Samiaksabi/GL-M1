package datanucleus.dao.fake;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
	
    public Flight getFlight(String crew,String id){
		return db.getFlight(crew,id);
	}

	public void addElement(Flight f){
		db.getFlights().add(f.commercial_number,f);
	}
	
	public void editElement(String id, Flight f){
		db.getFlights().edit(id, f);
	}

	public void addCrew(String crew_name, String id) {
		// TODO Auto-generated method stub
		
	}

	public void importExcelFile(InputStream stream) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	public void importOfpFile(InputStream stream, String id) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	public void importWeatherMap(InputStream stream, String id) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	public void importLeafletFile(InputStream stream) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}

	public void importNotamFile(InputStream stream, String id) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}
}
