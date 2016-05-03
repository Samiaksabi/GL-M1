package datanucleus.dao.ress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import datanucleus.dao.DAOAccessor;

@PersistenceCapable
public class Flight{
	
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
    public String identifier;
	
    public String commercial_number;
    public String ATC_code;
    
    public String plane;

    @Persistent(defaultFetchGroup = "true")
//    @Join
    public Collection<String> crew_members=new ArrayList<String>();
    
    public String departure_airport;
    
    public String arrival_airport;
    
    public Date departure_time;
    public Date  arrival_time;

    public String ofp_url;
    public String weather_maps_url;
   
    @Persistent(defaultFetchGroup = "true")
    @Join
    public List<String> notam =new ArrayList<String>();
    
    public Flight(){
    	
    }
    
    public Flight(String commercialNumber,String departureAirport,String arrivalAirport, Date departureTime, Date arrivalTime){
    	this.commercial_number=commercialNumber;
    	this.departure_airport=departureAirport;
    	this.arrival_airport=arrivalAirport;
    	this.departure_time=departureTime;
    	this.arrival_time=arrivalTime;
    	this.identifier = "";
    }
    
    public void addCrew(String crew_username){
    	this.crew_members.add(crew_username);
    }
	
    public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}
    
	public String getCommercial_number() {
		return commercial_number;
	}

	public void setCommercial_number(String commercial_number) {
		this.commercial_number = commercial_number;
	}

	public String getATC_code() {
		return ATC_code;
	}

	public void setATC_code(String aTC_code) {
		ATC_code = aTC_code;
	}

	public Collection<String> getCrew_members() {
		return crew_members;
	}

	public void setCrew_members(Collection<String> crew_members) {
		this.crew_members = crew_members;
	}

	public String getDeparture_airport() {
		return departure_airport;
	}

	public void setDeparture_airport(String departure_airport) {
		this.departure_airport = departure_airport;
	}

	public String getArrival_airport() {
		return arrival_airport;
	}

	public void setArrival_airport(String arrival_airport) {
		this.arrival_airport = arrival_airport;
	}

	public Date getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}

	public Date getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getOfp_url() {
		return ofp_url;
	}

	public void setOfp_url(String ofp_url) {
		this.ofp_url = ofp_url;
	}

	public String getWeather_maps_url() {
		return weather_maps_url;
	}

	public void setWeather_maps_url(String weather_maps_url) {
		this.weather_maps_url = weather_maps_url;
	}

	public List<String> getNotam() {
		return notam;
	}

	public void setNotam(List<String> notam) {
		this.notam = notam;
	}

	@Override
	public String toString() {
		return "Flight [id=" + identifier + ", commercial_number=" + commercial_number + ", ATC_code=" + ATC_code + ", plane="
				+ plane + ", crew_members=" + crew_members + ", departure_airport=" + departure_airport
				+ ", arrival_airport=" + arrival_airport + ", departure_time=" + departure_time + ", arrival_time="
				+ arrival_time + ", ofp_url=" + ofp_url + ", weather_maps_url=" + weather_maps_url + ", notam=" + notam
				+ "]";
	}
	
	public boolean edit(Flight elt){
		if(!this.identifier.equals(elt.identifier)){
			return false;
		}
		this.ATC_code=elt.ATC_code;
		this.plane=elt.plane;
		this.departure_airport = elt.departure_airport;
		this.arrival_airport=elt.arrival_airport;
		this.departure_time=elt.departure_time;
		this.arrival_time=elt.arrival_time;
		this.ofp_url=elt.ofp_url;
		this.weather_maps_url=elt.weather_maps_url;
		return true;
	}
	
	/*
	public boolean isValid(){
		return !(DAOAccessor.getAirportDAO().getElement(departure_airport)==null
				|| DAOAccessor.getAirportDAO().getElement(arrival_airport) == null
				|| departure_time.getTime() > arrival_time.getTime()
				|| departure_airport.equals(arrival_airport));
	}
	*/
}
