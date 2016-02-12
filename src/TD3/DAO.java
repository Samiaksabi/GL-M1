import java.util.*;
public interface DAO<T>{
    List<T> getList();
    List<T> getList (int id);
    void addElement(T elt);
}
