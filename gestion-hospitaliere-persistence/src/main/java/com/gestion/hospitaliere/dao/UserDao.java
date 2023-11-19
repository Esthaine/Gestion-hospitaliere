package com.gestion.hospitaliere.dao;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.entity.User;

import java.util.List;

public class UserDao implements JpaRepository<User>{

    private Persistence persistence;

    public UserDao(Persistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public User save(User user) {
        persistence.entityManager().getTransaction().begin();

        persistence.entityManager().getTransaction().commit();
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        return null;
    }

    @Override
    public List<User> saveAll(User... t) {
        return null;
    }

    @Override
    public List<User> deleteMany(Long... ids) {
        return null;
    }

    @Override
    public List<User> deleteMany(User... users) {
        return null;
    }
}
