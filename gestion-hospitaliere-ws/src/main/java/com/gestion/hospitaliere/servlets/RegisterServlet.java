package com.gestion.hospitaliere.servlets;

import com.gestion.hospitaliere.service.IUserService;
import com.gestion.hospitaliere.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/enregistrement"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            IUserService userService = new UserServiceImpl();
            userService.patientRegistration(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
