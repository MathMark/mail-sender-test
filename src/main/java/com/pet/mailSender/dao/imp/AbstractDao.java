package com.pet.mailSender.dao.imp;

import com.pet.mailSender.dao.Dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao <T extends Serializable> implements Dao<T> {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.pet.mailSender.model");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private Class<T> clazz;

    public final void setClazz(Class<T> clazzToSet){
        this.clazz = clazzToSet;
    }

    @Override
    public void add(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> getAll() {
        entityManager.getTransaction().begin();
        List<T> result = entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
        entityManager.getTransaction().commit();
        return result;
    }

    @Override
    public T getById(int id) {
        entityManager.getTransaction().begin();
        T object = entityManager.find(clazz, id);
        entityManager.getTransaction().commit();
        return object;
    }

    @Override
    public T update(T entity) {
        entityManager.getTransaction().begin();
        T object = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return object;
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

}
