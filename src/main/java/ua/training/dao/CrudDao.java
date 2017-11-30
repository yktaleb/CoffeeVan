package ua.training.dao;

import ua.training.exception.UniqueException;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID> {

    T save(T entity) throws UniqueException;

    T update(T entity);

    Optional<T> findOne(ID id);

    List<T> findAll();

//    default List<T> findAll(long size, long offset) {
//        //TODO implement in all dao
//        throw new UnsupportedOperationException();
//    }

    void delete(ID id);
}
