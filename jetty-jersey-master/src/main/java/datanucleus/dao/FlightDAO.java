package datanucleus.dao;


import java.io.File;
import java.io.IOException;
import java.util.Collection;

import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.Flight;

public interface FlightDAO extends DAO<Flight>{

	 Flight getFlight(String crew_userName,String id);
	 Collection<Flight> getAll(String userName);
	 void addCrew(String crew_name, String id);
	 //void importExcelFile(File excelFile);
	 
}
