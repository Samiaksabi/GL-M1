package dao;
import java.util.*;

import ress.Flight;

public interface FlightDAO extends DAO<Flight>{
    
    List<Flight> search(String commercial_number);

}
