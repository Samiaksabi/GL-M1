package datanucleus.dao.ress;
import java.util.*;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Flight{

	//@PrimaryKey
    public String commercial_number;
    public String ATC_code;
    
    public String plane;
  /*
    @Persistent(table="Flight_Crew")
    @Join(column="Crew_name_OID")
    @Element(column="Crew_name_EID")
    */
    public Collection<String> crew_members=new ArrayList<String>();
    
    //@PrimaryKey
    public String departure_airport;
    
    public String arrival_airport;
    
    //@PrimaryKey
    public Date departure_time;
    public Date  arrival_time;

    public String ofp_url;
    public String weather_maps_url;
   // @Element
   public List<String> notam =new ArrayList<String>();
    
    public Flight(){
    	
    }
    
    public Flight(String commercialNumber,String departureAirport,String arrivalAirport, Date departureTime, Date arrivalTime){
    	this.commercial_number=commercialNumber;
    	this.departure_airport=departureAirport;
    	this.arrival_airport=arrivalAirport;
    	this.departure_time=departureTime;
    	this.arrival_time=arrivalTime;    	
    }
    
    @Override
	public String toString() {
		return "Flight [commercial_number=" + commercial_number + ", ATC_code=" + ATC_code + ", plane=" + plane
				+ ", crew_members=" + crew_members + ", departure_airport=" + departure_airport + ", arrival_airport="
				+ arrival_airport + ", departure_time=" + departure_time + ", arrival_time=" + arrival_time
				+ ", ofp_url=" + ofp_url + ", weather_maps_url=" + weather_maps_url + ", notam=" + notam + "]";
	}

	public boolean edit(Flight elt){
		if(this.commercial_number!=elt.commercial_number || this.departure_airport!=elt.departure_airport || this.departure_time!=elt.departure_time){
			return false;
		}
		//TODO
		return true;
	}
}
