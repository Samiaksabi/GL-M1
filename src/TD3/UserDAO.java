
public interface UserDAO extends DAO<User>{
    
    boolean login(String username, String password);

}
