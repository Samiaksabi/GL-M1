package datanucleus.dao;
import java.util.*;

public interface DAO<T>{

    Collection<T> getAll();
    T getElement(int id);
    void addElement(T elt);
    void deleteElement(int id);
    void editElement(int id, T elt);

}
