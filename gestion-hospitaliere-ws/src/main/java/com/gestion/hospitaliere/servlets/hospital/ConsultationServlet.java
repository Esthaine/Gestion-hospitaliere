package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.service.IRendezVousService;
import com.gestion.hospitaliere.service.impl.RendezVousServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/hopital/medecin/consultation"})
public class ConsultationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IRendezVousService rendezVousService = new RendezVousServiceImpl();
            rendezVousService.listerRendezVousPerDoctor(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
