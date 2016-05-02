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

	public String getICAO_code() {
		return ICAO_code;
	}

	public void setICAO_code(String iCAO_code) {
		ICAO_code = iCAO_code;
	}
	
	@Override
	public String toString() {
		return "Airport [ICAO_code=" + ICAO_code + "]";
	}
	
	public void edit(Airport airport){
		this.ICAO_code=airport.ICAO_code;
	}
	
}
