package org.example.utils;

import java.util.List;

public interface CRUDDaoInterface<T> {
    int create(T t);
    List<T> getAll();
    T update(T t);
    boolean delete(T t);
}
