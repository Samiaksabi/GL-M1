package datanucleus.dao;

import org.eclipse.jetty.server.session.JDBCSessionManager.Session;

import datanucleus.dao.ress.User;

public interface UserDAO extends DAO<User>{
    
    boolean login(String name, String password);
    
    Session logout();

}
