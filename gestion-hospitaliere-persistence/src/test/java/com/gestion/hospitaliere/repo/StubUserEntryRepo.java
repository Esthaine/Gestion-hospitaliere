package com.gestion.hospitaliere.repo;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.entity.User;

import java.util.Date;

public class StubUserEntryRepo extends JpaRepositoryImpl<User> implements UserDao{
    public StubUserEntryRepo(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public User register() {
        User user = new User();
        user.setEmail("user@gmail.com");
        return null;
    }

    @Override
    public User authentication() {
        return null;
    }
}
