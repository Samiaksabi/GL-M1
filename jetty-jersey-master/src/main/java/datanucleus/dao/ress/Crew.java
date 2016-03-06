package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Crew extends User{
	
    public CrewStatus crewStatus;
	
	public Crew(){
		
	}
	
	public Crew(String name, String password, String mail, UserStatus status, CrewStatus crewStatus){
		super(name,password,mail,status);
		this.crewStatus=crewStatus;
	}
}
