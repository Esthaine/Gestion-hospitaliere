package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.service.IRendezVousService;
import com.gestion.hospitaliere.service.impl.RendezVousServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/hopital/patient/premierSoin"})
public class PatientPremierSoinsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            IRendezVousService rendezVousService = new RendezVousServiceImpl();
            rendezVousService.voirRendezVous(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try{
            IRendezVousService rendezVousService = new RendezVousServiceImpl();
            if (action != null && action.equals("createFile")){
                rendezVousService.creerFichePatient(req, resp);
                return;
            }
            rendezVousService.prelevementPatient(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
