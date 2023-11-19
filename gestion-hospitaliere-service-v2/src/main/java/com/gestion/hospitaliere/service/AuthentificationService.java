package com.gestion.hospitaliere.service;

import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.dto.UserDto;
import com.gestion.hospitaliere.model.request.UserRequest;

public class AuthentificationService implements IAuthentificationService{
    private UserDao userDao;

    public AuthentificationService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authentification(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEyeColor(userRequest.getPassword());
        return userDao.save(user);
    }
}
