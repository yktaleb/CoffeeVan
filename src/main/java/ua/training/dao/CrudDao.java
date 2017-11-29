package ua.training.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    T save(T entity);

    T update(T entity);

    T findOne(ID id);

    List<T> findAll();

//    default List<T> findAll(long size, long offset) {
//        //TODO implement in all dao
//        throw new UnsupportedOperationException();
//    }

    void delete(ID id);
}
