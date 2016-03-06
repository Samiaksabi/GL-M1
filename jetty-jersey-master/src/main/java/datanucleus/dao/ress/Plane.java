package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Plane{
    @PrimaryKey
    public String identifier;
    public String airport_ICAO;

    public Plane(){
    	
    }
    
    public Plane(String identifier, String airport_ICAO){
    	this.identifier=identifier;
    	this.airport_ICAO=airport_ICAO;
    }

	@Override
	public String toString() {
		return "Plane [identifier=" + identifier + ", airport=" + airport_ICAO + "]";
	}
	
	public boolean edit(Plane elt){
		if(this.identifier!=elt.identifier){
			return false;
		}
		this.airport_ICAO=elt.airport_ICAO;
		return true;
	}
    
}
