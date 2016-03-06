package database.fake;

import java.util.*;
import database.DB;
public class DBFake<T> implements DB<T>{
	
	private Hashtable<Integer,T> db;
	private int key;
	
	public DBFake(){
		db = new Hashtable<Integer,T>();
		key = 0;
	}
	
	public Collection<T> getDb(){
		return db.values();
	}
	
	public T get(int i){
		return db.get(i);
	}
	
	public void add(T elt){
		db.put(key++,elt);
	}
	
	public void delete(int id){
		db.remove(id);
	}
	
	public void edit(int id, T elt){
		db.remove(id);
		db.put(id, elt);
	}
}
