package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.service.IUserService;
import com.gestion.hospitaliere.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/hopital/profile"})
public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IUserService userService = new UserServiceImpl();
            userService.userProfile(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            IUserService userService = new UserServiceImpl();
            userService.userOrganisationProfileUpdate(req, resp);
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
