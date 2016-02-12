import java.util.*;

public interface FlightDAO extends DAO<Flight>{
    
    List<Flight> search(String commercial_number);

}
