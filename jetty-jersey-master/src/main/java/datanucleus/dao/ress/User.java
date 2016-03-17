package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User{
	@PrimaryKey
    public String userName;
	public String firstName;
	public String lastName;
    private String password;
	public String mail;
    public UserStatus status;
    
    public User(){
    	
    }
    
    public User(String userName, String firstName, String lastName, String password, String mail, UserStatus status){
    	this.userName=userName;
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.password=password;
    	this.mail=mail;
    	this.status=status;	
    }
    
    public String getPassword() {
		return password;
	}
    
    

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", mail=" + mail + ", status=" + status + "]";
	}

	public boolean edit(User elt){
		if(this.userName!=elt.userName){
			return false;
		}
		this.password=elt.password;
		this.mail=elt.password;
		this.mail=elt.mail;
		this.status=elt.status;
		return true;
	}

}
