package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.FicheDaoImpl;
import com.gestion.hospitaliere.dao.impl.MedicamentDaoImpl;
import com.gestion.hospitaliere.entity.Fiche;
import com.gestion.hospitaliere.entity.Medicament;
import com.gestion.hospitaliere.service.IFicheService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public class FicheServiceImpl implements IFicheService {


    FicheDao ficheDao;
    Class<Fiche> ficheClass;
    Class<Medicament> medicamentClass;

    AntecedentMedicalDao antecedentMedicalDao;
    ResultatsExamensDao resultatsExamensDao;
    MedicamentDao medicamentDao;
    ExamenDao examenDao;


    public FicheServiceImpl() throws ClassNotFoundException {
        ficheClass = (Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche");
        medicamentClass = (Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament");

        ficheDao = new FicheDaoImpl(ficheClass);
        medicamentDao = new MedicamentDaoImpl(medicamentClass);
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
        List<Medicament> medicaments = medicamentDao.findAll();
        if(fiche != null){
            request.setAttribute("fiche", fiche);
            request.setAttribute("medicaments", medicaments);
        }
        request.getRequestDispatcher("/hospital/consultationOperation.jsp").forward(request, response);
    }

    @Override
    public void performPatientConsulation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] dateDebut = req.getParameterValues("date_debuts");
        String[] dateFin = req.getParameterValues("date_fins");
        String[] type = req.getParameterValues("type");
        String[] descriptions = req.getParameterValues("descriptions");

        String[] questions = req.getParameterValues("questions");
        String[] appreciationsQuestions = req.getParameterValues("appreciation_questions");

        String[] reponses = req.getParameterValues("reponses");
        String[] appreciationReponses = req.getParameterValues("appreciation_reponses");

        String[] medicaments = req.getParameterValues("medicaments");

        //Long patientId = Long.parseLong(req.getParameter("patientId"));


//        Fiche fiche = ficheDao.findByPatient(patientId);
//
//        if(fiche == null){
//            req.setAttribute("error", "Le patient n'a pas encore une fiche.");
//            req.getRequestDispatcher("/hospital/consultationOperation.jsp").forward(req, resp);
//        }

    }
}
