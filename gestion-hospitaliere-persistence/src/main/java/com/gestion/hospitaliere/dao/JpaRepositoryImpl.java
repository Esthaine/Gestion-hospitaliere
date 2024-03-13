package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.AbstractEntity;
import com.gestion.hospitaliere.entity.User;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JpaRepositoryImpl<T extends AbstractEntity> implements JpaRepository<T> {

    private final Persistence persistence;
    private Class<T> clazz;

    public JpaRepositoryImpl(Persistence persistence, Class<T> clazz) {
        this.persistence = persistence;
        this.clazz = clazz;
    }
    @Override
    public T save(T t) {
        try{
            if (t != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(t);
                persistence.entityManager().getTransaction().commit();
                return t;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        Query query = persistence.entityManager().createQuery("Select t From "+clazz.getClass() + " t");
        return query.getResultList();
    }

    @Override
    public T findById(Long id) {
        try{
            T t = persistence.entityManager().find(clazz, id);
            if (t != null)
                return t;
        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public T deleteById(Long id) {
        return null;
    }

    @Override
    public List<T> saveAll(T... t) {
        List<T> ts  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(t).forEach(user -> {
                persistence.entityManager().persist(user);
                ts.add(user);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return ts;
    }

    @Override
    public List<T> deleteMany(Long... ids) {
        List<T> users = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                T t = findById(id);
                if (t != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete t From " + clazz + " where t.id");
                    deleteQuery.setParameter("id", id);
                    int deletedUser = deleteQuery.getFirstResult();
                    if (deletedUser > 0){
                        users.add(t);
                    }
                    persistence.entityManager().getTransaction().commit();
                }
            });
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<T> deleteMany(T... ts) {
        return null;
    }
}
