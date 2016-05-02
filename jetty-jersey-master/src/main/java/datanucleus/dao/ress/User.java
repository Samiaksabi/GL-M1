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
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", mail=" + mail + ", status=" + status + "]";
	}
	
	public boolean isCorrectPassword(String password) {
		return this.password.equals(password);
	}

	public boolean edit(User elt){
		if(!this.userName.equals(elt.userName)){
			return false;
		}
		this.firstName=elt.firstName;
		this.lastName=elt.lastName;
		this.password=elt.password;
		this.mail=elt.mail;
		this.status=elt.status;
		return true;
	}

}
