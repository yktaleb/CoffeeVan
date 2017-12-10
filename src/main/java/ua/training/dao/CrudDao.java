package ua.training.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID> {

    T save(T entity);

    T update(T entity);

    T findOne(ID id);

    List<T> findAll();

    void delete(ID id);

    List<T> findAll(long limit, long offset);
}
