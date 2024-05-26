package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.service.IFicheService;
import com.gestion.hospitaliere.service.impl.FicheServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/hopital/patient/consultation/operation"})
public class ConsultationOperationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IFicheService ficheService = new FicheServiceImpl();
            ficheService.consultationPatient(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
