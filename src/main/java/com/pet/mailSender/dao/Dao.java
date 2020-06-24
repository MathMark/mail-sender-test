package com.pet.mailSender.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Serializable> {
    void add(T t);
    List<T> getAll();
    T getById(int id);
    T update(T t);
    void delete(T t);
}
