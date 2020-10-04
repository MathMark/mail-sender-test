package com.pet.mailSender.dao.imp;

import com.pet.mailSender.dao.Dao;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import sun.rmi.transport.Transport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class AbstractDao <T extends Serializable> implements Dao<T> {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.pet.mailSender.model");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private Class<T> clazz;

    public final void setClazz(Class<T> clazzToSet){
        this.clazz = clazzToSet;
    }

    @Override
    public synchronized void add(T entity) {
        if(!entityManager.getTransaction().isActive()){
            try{
                entityManager.getTransaction().begin();
                entityManager.persist(entity);
                entityManager.getTransaction().commit();
            }catch (Exception e){
               // entityManager.getTransaction().rollback();
            }

        }

    }

    @Override
    public synchronized List<T> getAll() {
        List<T> result = new ArrayList<>();
        if(!entityManager.getTransaction().isActive()) {
            try{
                entityManager.getTransaction().begin();
                result = entityManager.createQuery("FROM " + clazz.getName(), clazz).getResultList();
                entityManager.getTransaction().commit();
            }catch (Exception e){
               // entityManager.getTransaction().rollback();
            }

        }
        return result;
    }

    @Override
    public synchronized T getById(int id) {
        T object = null;
        if(!entityManager.getTransaction().isActive()){
            try{
                entityManager.getTransaction().begin();
                object = entityManager.find(clazz, id);
                entityManager.getTransaction().commit();
            }catch (Exception e){
               // entityManager.getTransaction().rollback();
            }

        }
        return object;
    }

    @Override
    public synchronized T update(T entity) {
        T object = null;
        if(!entityManager.getTransaction().isActive()) {
            try{
                entityManager.getTransaction().begin();
                object = entityManager.merge(entity);
                entityManager.getTransaction().commit();
            }catch (Exception e){
               // entityManager.getTransaction().rollback();
            }

        }
        return object;
    }

    @Override
    public synchronized void delete(T entity) {
        if(!entityManager.getTransaction().isActive()) {
            try{
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
            }catch (Exception e){
               // entityManager.getTransaction().rollback();
            }

        }
    }

}
