package com.gestion.hospitaliere.service;

import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthentificationService implements IAuthentificationService{

    private final UserDao userRepository;
    public AuthentificationService() throws ClassNotFoundException {
        userRepository = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
    }

    @Override
    public void authentication(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Find

        userRepository.findAll();
    }
}
