package database;

import java.util.Collection;

public interface DB<T> {
	
	public Collection<T> getDb();
	
	public T get(String i);
	
	public T get(int i);
	
	public void add(String id, T elt);
	
	public void delete(String id);
	
	public void edit(String id, T elt);
}
