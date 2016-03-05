package datanucleus.dao.ress;
import java.util.*;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Flight{

    public String commercial_number;
    public String ATC_code;
    
    public Plane plane;
    public List<Crew> crew_members;

    public Airport departure_airport;
    public Airport arrival_airport;
    public String departure_time;
    public String  arrival_time;

    public String ofp_url;
    public String weather_maps_url;
    public List<String> notam;

}
