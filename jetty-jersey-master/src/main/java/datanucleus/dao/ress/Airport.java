package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Airport{
	@PrimaryKey
    public String ICAO_code;

	public Airport(){
		
	}
	
	public Airport(String IACO_code){
		this.ICAO_code=IACO_code;
	}

	@Override
	public String toString() {
		return "Airport [ICAO_code=" + ICAO_code + "]";
	}

	public boolean edit(Airport elt){
		this.ICAO_code=elt.ICAO_code;
		return true;
	}
	
}
