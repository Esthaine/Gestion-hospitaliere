package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.entity.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JpaRepositoryImpl<T extends AbstractEntity> implements JpaRepository<T> {
    private Class<T> clazz;

    public JpaRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }
    @Override
    @Transactional
    public T save(T t) {
        try{
            try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
                EntityManager entityManager = em.createEntityManager();
                if (t != null){
                    entityManager.getTransaction().begin();
                    if (t.getId() == null){
                        entityManager.persist(t);
                    }else{
                        entityManager.merge(t);
                    }
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    return t;
                }
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("Select t From " + clazz.getSimpleName() + " t order by t.id desc");
            entityManager.getTransaction().commit();
            List<T> ts = query.getResultList();
            entityManager.close();
            return ts;
        }
    }

    @Override
    public T findById(Long id) {
        try{
            try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
                EntityManager entityManager = em.createEntityManager();
                entityManager.getTransaction().begin();
                T t = entityManager.find(clazz, id);
                entityManager.getTransaction().commit();
                entityManager.close();
                if (t != null)
                    return t;
            }
        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public T deleteById(Long id) {
        T t = findById(id);
        if (t != null){
            try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
                EntityManager entityManager = em.createEntityManager();
                entityManager.getTransaction().begin();
                entityManager.remove(t);
                entityManager.getTransaction().commit();
                entityManager.close();
                return t;
            }
        }
        return null;
    }

    @Override
    public List<T> saveAll(T... t) {
        List<T> ts  = new ArrayList<>();
        try(EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")){
            EntityManager entityManager = em.createEntityManager();
            Arrays.stream(t).forEach(data -> {
                entityManager.getTransaction().begin();
                entityManager.persist(data);
                entityManager.getTransaction().commit();
                ts.add(data);
            });
        }
        return ts;
    }

    @Override
    public List<T> deleteMany(Long... ids) {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        List<T> ts = new ArrayList<>();
        try (EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")){
            EntityManager entityManager = em.createEntityManager();
            Stream.of(ids).forEach(id -> {
                T t = findById(id);
                if (t != null) {
                    entityManager.getTransaction().begin();
                    Query deleteQuery = entityManager.createQuery("Delete From " + clazz.getSimpleName() + " t where t.id = :id");
                    deleteQuery.setParameter("id", id)
                            .executeUpdate();
                    int deletedUser = deleteQuery.getFirstResult();
                    if (deletedUser > 0) {
                        ts.add(t);
                    }
                    entityManager.getTransaction().commit();

                }
            });
            entityManager.close();
        }
        return ts;
    }

    @Override
    public List<T> deleteMany(T... ts) {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        List<T> list = new ArrayList<>();
        try(EntityManagerFactory em = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            Stream.of(ts).forEach(tId -> {
                T t = findById(tId.getId());
                if (t != null) {
                    entityManager.getTransaction().begin();
                    Query deleteQuery = entityManager.createQuery("Delete t From " + clazz.getSimpleName() + " t where t.id = :id");
                    deleteQuery.setParameter("id", t.getId())
                            .executeUpdate();
                    int deletedUser = deleteQuery.getFirstResult();
                    if (deletedUser > 0) {
                        list.add(t);
                    }
                    entityManager.getTransaction().commit();
                }
            });
        }
        return list;
    }
}
