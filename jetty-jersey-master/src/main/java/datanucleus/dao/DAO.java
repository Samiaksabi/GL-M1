package datanucleus.dao;
import java.util.*;

public interface DAO<T>{

    Collection<T> getAll();
    T getElement(String name);
    void addElement(T elt);
    void deleteElement(String name);
    void editElement(String name, T elt);

}
