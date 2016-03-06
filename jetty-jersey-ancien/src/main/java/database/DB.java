package database;

import java.util.Collection;

public interface DB<T> {
	
	public Collection<T> getDb();
	
	public T get(int i);
	
	public void add(T elt);
	
	public void delete(int id);
	
	public void edit(int id, T elt);
}
