package com.gestion.hospitaliere.servlets;

import com.gestion.hospitaliere.config.Persistence;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.model.request.UserRequest;
import com.gestion.hospitaliere.service.AuthentificationService;
import com.gestion.hospitaliere.service.IAuthentificationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/connexion"})
public class AuthentificationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAuthentificationService service = new AuthentificationService(new UserDao(new Persistence()));

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserRequest request = new UserRequest();
        request.setUsername(username);
        request.setPassword(password);
        service.authentification(request);
        req.getRequestDispatcher("/dashboard");
    }
}
