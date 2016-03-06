package database.fake;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import database.DB;
public class DBFake<T> implements DB<T>{
	
	private Hashtable<String,T> db;
	
	public DBFake(){
		db = new Hashtable<String,T>();
	}
	
	public Collection<T> getDb(){
		return db.values();
	}
	
	public T get(String i){
		return db.get(i);
	}
	
	public T get(int i){
		Iterator<T> it = getDb().iterator();
		for(int j = 0; j<i;j++)
			it.next();
		return(it.next());
	}
	
	public void add(String id, T elt){
		db.put(id,elt);
	}
	
	public void delete(String id){
		db.remove(id);
	}
	
	public void edit(String id, T elt){
		db.remove(id);
		db.put(id, elt);
	}
}
