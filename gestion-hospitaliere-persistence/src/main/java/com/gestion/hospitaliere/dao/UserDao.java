package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.User;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UserDao implements JpaRepository<User>{

    private final Persistence persistence;

    public UserDao(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public User save(User user) {
        try{

            if (user != null){
                persistence.entityManager().getTransaction().begin();
                persistence.entityManager().persist(user);
                persistence.entityManager().getTransaction().commit();
                return user;
            }
        }catch (Exception e){
            System.out.printf("Message : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        Query query = persistence.entityManager().createQuery("Select u From User u");
        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        try{
            User user = persistence.entityManager().find(User.class, id);
            if (user != null)
                return user;

        }catch (Exception e){
            System.out.printf("Error " + e.getMessage());
        }
        return null;
    }

    @Override
    public User deleteById(Long id) {
        try{

        }catch (Exception e){
            System.out.printf("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> saveAll(User... users) {
        List<User> users1  = new ArrayList<>();
        try{
            persistence.entityManager().getTransaction().begin();
            Arrays.stream(users).forEach(user -> {
                persistence.entityManager().persist(user);
                users1.add(user);
            });
            persistence.entityManager().getTransaction().commit();

        }catch (Exception e){
            System.out.println();
        }
        return users1;
    }

    @Override
    public List<User> deleteMany(Long... ids) {
        List<User> users = new ArrayList<>();
        try{
            Stream.of(ids).forEach(id ->{
                User user = findById(id);
                if (user != null){
                    persistence.entityManager().getTransaction().begin();
                    Query deleteQuery = persistence.entityManager().createQuery("Delete u From User where u.id");
                    deleteQuery.setParameter("id", id);
                    int deletedUser = deleteQuery.getFirstResult();
                    if (deletedUser > 0){
                        users.add(user);
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
    public List<User> deleteMany(User... users) {
        try{
        }catch (Exception e){
           System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
