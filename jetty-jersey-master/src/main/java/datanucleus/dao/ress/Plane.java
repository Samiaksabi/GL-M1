package datanucleus.dao.ress;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Plane{
    @PrimaryKey
    //@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
    public String identifier;
    public String airport;

    public Plane(){
    	
    }
    
    /*
    public Plane(String airport_ICAO){
    	this.airport_ICAO=airport_ICAO;
    }
    */
    public Plane(String identifier, String airportICAO){
    	this.identifier=identifier;
    	this.airport=airportICAO;
    }

	@Override
	public String toString() {
		return "Plane [identifier=" + identifier + ", airport=" + airport + "]";
	}
	
	public boolean edit(Plane elt){
		if(this.identifier!=elt.identifier){
			return false;
		}
		this.airport=elt.airport;
		return true;
	}
    
}
