package datanucleus.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datanucleus.dao.ress.User;

public class UserDAOImpl implements UserDAO {
	
	private static Logger logger = LogManager.getLogger(UserDAOImpl.class);
	private PersistenceManagerFactory pmf;

	public UserDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	public Collection<User> getAll() {
		Collection<User> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery(User.class);

			Collection<User> user= (List<User>) q.execute();
			detached = (List<User>) pm.detachCopyAll(user);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;

	}

	public User getElement(String userName) {
		User detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(User.class);
			q.declareParameters("String s");
			q.setFilter("userName == s");
			q.setUnique(true);
			
			User user = (User) q.execute(userName);
			detached = (User) pm.detachCopy(user);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return detached;
	}

	public void addElement(User elt) {
		
		if(this.getElement(elt.userName)!=null){
			logger.error("Can't add User because this username already exist in the database.");
			return;
		}
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			pm.makePersistent(elt);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public void deleteElement(String userName) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
				
			Query q = pm.newQuery(User.class);
			q.declareParameters("String s");
			q.setFilter("userName == s");
			q.setUnique(true); 
			
			User user= (User) q.execute(userName);
			if(user==null){
				logger.warn("Can't delete User because this username doesn't exist in the database.");
			}
			pm.deletePersistent(user);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;
		
	}

	public void editElement(String userName, User elt) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q = pm.newQuery(User.class);
			q.declareParameters("String s");
			q.setFilter("userName == s");
			q.setUnique(true);
			
			User user = (User) q.execute(userName);
			
			if(user!=null){
				user.edit(elt);
			}
			else{
				logger.error("Can't edit User because this username doesn't exist in the database.");
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

	public boolean login(String user_name, String password) {
		User user=this.getElement(user_name);
		return user.isCorrectPassword(password);
	}

}
