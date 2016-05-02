package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Plane{
	
    @PrimaryKey
    public String identifier;
    public String airport;

    public Plane(){
    	
    }
    
    public Plane(String identifier, String airportICAO){
    	this.identifier=identifier;
    	this.airport=airportICAO;
    }

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	@Override
	public String toString() {
		return "Plane [identifier=" + identifier + ", airport=" + airport + "]";
	}
	
	public boolean edit(Plane elt){
		if(!this.identifier.equals(elt.identifier)){
			return false;
		}
		this.airport=elt.airport;
		return true;
	}
    
}
