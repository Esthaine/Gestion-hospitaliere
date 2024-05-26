package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.FicheDao;
import com.gestion.hospitaliere.dao.impl.FicheDaoImpl;
import com.gestion.hospitaliere.entity.Fiche;
import com.gestion.hospitaliere.service.IFicheService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class FicheServiceImpl implements IFicheService {


    FicheDao ficheDao;
    Class<Fiche> ficheClass;

    public FicheServiceImpl() throws ClassNotFoundException {
        ficheClass = (Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche");
        ficheDao = new FicheDaoImpl(ficheClass);
    }

    @Override
    public void fichePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long patientId = Long.parseLong(request.getParameter("patientId"));
        Fiche fiche = ficheDao.findByPatient(patientId);
        if(fiche != null){
            request.setAttribute("fiche", fiche);
        }
        request.getRequestDispatcher("/hospital/fiche.jsp").forward(request, response);
    }

    @Override
    public void consultationPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long patientId = Long.parseLong(request.getParameter("patientId"));
        Fiche fiche = ficheDao.findByPatient(patientId);
        if(fiche != null){
            request.setAttribute("fiche", fiche);
        }
        request.getRequestDispatcher("/hospital/consultationOperation.jsp").forward(request, response);
    }
}
