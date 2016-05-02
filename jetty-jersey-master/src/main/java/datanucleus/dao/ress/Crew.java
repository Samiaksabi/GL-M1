package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Crew extends User{
	
    public CrewStatus crewStatus;
	
	public Crew(){
		
	}
	
	public Crew(String userName, String firstName, String lastName, String password, String mail, UserStatus status, CrewStatus crewStatus){
		super(userName,firstName,lastName,password,mail,status);
		this.crewStatus=crewStatus;
	}

	public CrewStatus getCrewStatus() {
		return crewStatus;
	}

	public void setCrewStatus(CrewStatus crewStatus) {
		this.crewStatus = crewStatus;
	}

	@Override
	public String toString() {
		return "Crew [crewStatus=" + crewStatus + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", mail=" + mail + ", status=" + status + "]";
	}
	
	
	
}
