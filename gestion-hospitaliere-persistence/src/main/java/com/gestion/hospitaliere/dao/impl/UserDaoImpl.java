package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.entity.User;

public class UserDaoImpl extends JpaRepositoryImpl<User> implements UserDao{

    public UserDaoImpl(Class<User> clazz) {
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
