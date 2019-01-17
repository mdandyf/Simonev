package simonev.mitrais.com.simonev.dao;

import java.util.List;

public interface BaseDao<T, ID> {
    void insert(T t);
    boolean update(T t);
    boolean delete(ID id);
    List<T> findAll();
    T findById(ID id);
}
