package datanucleus.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import datanucleus.dao.ress.User;

public class UserDAOImpl implements UserDAO {
	
	private PersistenceManagerFactory pmf;

	public UserDAOImpl(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}

	public Collection<User> getAll() {
		Collection<User> user;
		Collection<User> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("SELECT FROM "+User.class.getName());

			user = (List<User>) q.execute();
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

	public User getElement(String name) {
		Collection<User> user;
		Collection<User> detached;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+User.class.getName()+" WHERE name == \""+name+"\"");
			/*Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("identifier == id");
			 */
			
			user = (List<User>) q.execute(/*id*/);
			detached = (List<User>) pm.detachCopyAll(user);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		if(detached.isEmpty()){
			return null;
		}
		return detached.iterator().next();
	}

	public void addElement(User elt) {
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

	public void deleteElement(String name) {
		Collection<User> user = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Query q=pm.newQuery("SELECT FROM "+User.class.getName()+" WHERE name == \""+name+"\"");
			/*
			Query q = pm.newQuery(Airport.class);
			q.declareParameters("int id");
			q.setFilter("username == user");
			 
			airport = (Airport) q.execute(id);
			*/
			user = (List<User>) q.execute();
			pm.deletePersistentAll(user);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return;
		
	}

	public void editElement(String name, User elt) {
		Collection<User> user = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q=pm.newQuery("SELECT FROM "+User.class.getName()+" WHERE name == \""+name+"\"");
			
					
			user = (List<User>) q.execute();
			if(!user.isEmpty()){
				User u=user.iterator().next();
				u.edit(elt);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
	}

	public boolean login(String name, String password) {
		User user=this.getElement(name);
		return user.getPassword()==password;
	}

}
