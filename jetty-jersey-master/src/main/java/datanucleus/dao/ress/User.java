package datanucleus.dao.ress;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class User{
    public String username;
    public String password;
    public String mail;
    public UserStatus status;

}
