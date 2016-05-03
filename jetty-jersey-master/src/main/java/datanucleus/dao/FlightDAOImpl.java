package datanucleus.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import datanucleus.dao.ress.Flight;


public class FlightDAOImpl implements FlightDAO {
	
	private static Logger logger = LogManager.getLogger(FlightDAOImpl.class);
	private PersistenceManagerFactory pmf;
	
	public FlightDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	public Collection<Flight> getAll() {
		Collection<Flight> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(Flight.class);

			Collection<Flight> flight = (List<Flight>) q.execute();
			detached = (List<Flight>) pm.detachCopyAll(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	public Flight getElement(String id) {
		Flight detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);
			detached = (Flight) pm.detachCopy(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return detached;

	}

	public void addElement(Flight elt) {
		
		if(!isValid(elt)){
			return;
		}
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			
			q.declareImports("import java.util.Date");
			q.declareParameters("String commercialNumber,Date departureTime, String departureAirport");
			q.setFilter("departure_airport == departureAirport && commercial_number == commercialNumber && departure_time == departureTime");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(elt.commercial_number,elt.departure_time,elt.departure_airport);
			
			if(flight==null){
				pm.makePersistent(elt);
			}
			else{
				logger.error("This flight already exist in the database.");
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public void deleteElement(String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);
			if(flight==null){
				logger.warn("Flight can't be deleted because doesn't exist in the database.");
			}
			pm.deletePersistent(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;

		
	}

	public void editElement(String id, Flight elt) {
		if(!isValid(elt))
			return;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);
			if(flight!=null){
				flight.edit(elt);
			}
			else{
				logger.error("Flight can't be edited because doesn't exist in the database.");
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}
	
	private static boolean isValid(Flight flight){
		
		if(DAOAccessor.getAirportDAO().getElement(flight.departure_airport)==null){
			logger.error("The departure airport is not in the database.");
			return false;
		}
		if(DAOAccessor.getAirportDAO().getElement(flight.arrival_airport) == null){
			logger.error("The arrival airport is not in the database.");
			return false;
		}
		if(flight.departure_time.getTime() > flight.arrival_time.getTime()){
			logger.error("Flight departure date > Flight arrival date.");
			return false;
		}
		if(flight.departure_airport.equals(flight.arrival_airport)){
			logger.error("The departure and arrival airport are the same.");
			return false;
		}
		return true;
	}

	public Flight getFlight(String crew_name, String id) {
		Collection<Flight> flights = getAll(crew_name);
		Iterator<Flight> it = flights.iterator();
		while(it.hasNext()){
			Flight f = it.next();
			if(f.identifier.equals(id))
				return f;
		}
		return null;
	}
	
	public void addCrew(String crew_name, String id){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String id");
			q.setFilter("identifier == id");
			q.setUnique(true);
			
			Flight flight = (Flight) q.execute(id);

			if(flight==null){
				logger.error("Can't add crew because the flight doesn't exist in the database.");
			}
			else{
				flight.addCrew(crew_name);
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

	public Collection<Flight> getAll(String crew_name) {
		
		/*Collection<Flight> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(Flight.class);
			q.declareParameters("String crewName");
			q.setFilter("crew_members.contains(crewName)");
			
			Collection<Flight> flight = (List<Flight>) q.execute(crew_name);
			detached = (List<Flight>) pm.detachCopyAll(flight);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		if(detached.isEmpty()){
			return null;
		}
		
		return detached;*/
		Collection<Flight> flights = getAll();
		Collection<Flight> res = new ArrayList<Flight>();
		Iterator<Flight> it = flights.iterator();
		while(it.hasNext()){
			Flight f = it.next();
			if(f.hasCrew(crew_name))
				res.add(f);
		}
		return res;
	}

	public void importExcelFile(InputStream stream) throws FileNotFoundException, IOException{
		HSSFWorkbook wb = new HSSFWorkbook(stream);
		
		HSSFSheet sheet = wb.getSheetAt(0);
		
		for (Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
			HSSFRow row=null;
			try{
				row = (HSSFRow) rowIt.next();
				Flight flight=this.createFlightFromRow(row);
				this.addElement(flight);
			}catch(NullPointerException e){
				logger.error("NullPointerException on line "+row.getRowNum());
			}catch(IllegalStateException e){
				logger.error("IllegalStateException on line "+row.getRowNum());
			}
		}
		wb.close();
	}
	
	private Flight createFlightFromRow(HSSFRow row) throws NullPointerException, IllegalStateException{
		
		String commercialNumber = row.getCell(0).getStringCellValue();
		String ATC_code = row.getCell(1).getStringCellValue();
		String plane = row.getCell(2).getStringCellValue();
		Date departureDate = row.getCell(3).getDateCellValue();
		Date arrivalDate = row.getCell(4).getDateCellValue();
		String departureAirport = row.getCell(5).getStringCellValue();
		String arrivalAirport = row.getCell(6).getStringCellValue();
		
		return new Flight(commercialNumber,ATC_code,plane,departureAirport,arrivalAirport,departureDate, arrivalDate);
	}

	public void importOfpFile(InputStream stream, String id) throws FileNotFoundException, IOException {
		try
		{
			String url = "src/main/webapp/data/" + id + "_ofp.txt";
			File f = new File(url);

		    PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));
		    int i = 0;
		    while(true){
		    	i = stream.read();
		    	if(i!=-1)
		    		pw.print((char)i);
		    	else
		    		break;
		    }
		    pw.close();
		}
		catch (IOException exception)
		{
		    logger.error("Error while uploading ofp : " + exception.getMessage());
		}
	}

	public void importWeatherMap(InputStream stream, String id) throws FileNotFoundException, IOException {
		try
		{
			String url = "src/main/webapp/data/" + id + "_weather_map.jpg";
			File f = new File(url);
			OutputStream os = new FileOutputStream(f);
			byte[] b = new byte[2048];
			int length;
			while ((length = stream.read(b)) != -1) {
				os.write(b, 0, length);
			}
		}
		catch (IOException exception)
		{
		    logger.error("Error while uploading weather map : " + exception.getMessage());
		}
	}
	
}
