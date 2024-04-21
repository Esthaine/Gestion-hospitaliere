package com.gestion.hospitaliere.servlets.hospital;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hopital/dashboard")
public class DashboardServlet extends HttpServlet {

    //Docteurs
    //RDV
    //
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/hospital/dashboard.jsp").forward(req, resp);
    }
}
