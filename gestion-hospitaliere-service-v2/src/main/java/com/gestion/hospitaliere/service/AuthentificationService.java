package com.gestion.hospitaliere.service;

import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.dto.UserDto;
import com.gestion.hospitaliere.model.request.UserRequest;

public class AuthentificationService implements IAuthentificationService{
    private UserDaoImpl userDao;

    public AuthentificationService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authentification(UserRequest userRequest) {
        userDao.findAll().stream().forEach(System.out::println);
        System.out.println("----");
        return null;
    }
}
