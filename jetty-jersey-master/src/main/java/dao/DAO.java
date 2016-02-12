package dao;
import java.util.*;

public interface DAO<T>{

    List<T> getList();
    List<T> getList(int id);
    T getElement(int id);
    int getId(T elt);
    void addElement(T elt);
    void deleteElement(T elt);
    void deleteElement(int id);
    void modifyElement(int id, T elt);

}
