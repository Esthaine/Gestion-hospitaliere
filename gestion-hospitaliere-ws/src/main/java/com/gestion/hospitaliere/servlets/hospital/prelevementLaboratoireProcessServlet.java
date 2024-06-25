package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.service.IFicheService;
import com.gestion.hospitaliere.service.impl.FicheServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/hopital/labo/prelevement/process"})
public class prelevementLaboratoireProcessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IFicheService ficheService = new FicheServiceImpl();
            ficheService.getPrelevementLaboratoire(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IFicheService ficheService = new FicheServiceImpl();
            ficheService.processPrelevementLaboratoire(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
