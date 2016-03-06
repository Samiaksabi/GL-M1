package datanucleus.dao.ress;

import javax.jdo.annotations.*;

@PersistenceCapable
public class User{
	@PrimaryKey
    public String name;
    private String password;
	public String mail;
    public UserStatus status;
    
    public User(){
    	
    }
    
    public User(String name, String password, String mail, UserStatus status){
    	this.name=name;
    	this.password=password;
    	this.mail=mail;
    	this.status=status;	
    }
    
    public String getPassword() {
		return password;
	}
    
    
    
    @Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", mail=" + mail + ", status=" + status + "]";
	}

	public boolean edit(User elt){
		if(this.name!=elt.name){
			return false;
		}
		this.password=elt.password;
		this.mail=elt.password;
		this.mail=elt.mail;
		this.status=elt.status;
		return true;
	}

}
