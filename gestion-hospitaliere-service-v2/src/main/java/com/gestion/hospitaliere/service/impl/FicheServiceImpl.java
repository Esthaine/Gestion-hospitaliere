package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.service.IFicheService;
import com.gestion.hospitaliere.utils.AppConst;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FicheServiceImpl implements IFicheService {


    FicheDao ficheDao;
    Class<Fiche> ficheClass;
    Class<Medicament> medicamentClass;
    Class<Examen> examenClass;
    Class<Question> questionClass;
    Class<AntecedentMedical> antecedentMedicalClass;
    Class<ResultatsExamens> resultatsExamensClass;
    Class<Rendezvous> rendezvousClass;

    AntecedentMedicalDao antecedentMedicalDao;
    ResultatsExamensDao resultatsExamensDao;
    MedicamentDao medicamentDao;
    ExamenDao examenDao;
    QuestionDao questionDao;
    RendezVousDao rendezVousDao;


    public FicheServiceImpl() throws ClassNotFoundException {
        ficheClass = (Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche");
        medicamentClass = (Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament");
        examenClass = (Class<Examen>) Class.forName("com.gestion.hospitaliere.entity.Examen");
        antecedentMedicalClass = (Class<AntecedentMedical>) Class.forName("com.gestion.hospitaliere.entity.AntecedentMedical");
        questionClass  =  (Class<Question>) Class.forName("com.gestion.hospitaliere.entity.Question");
        resultatsExamensClass = (Class<ResultatsExamens>) Class.forName("com.gestion.hospitaliere.entity.ResultatsExamens");
        rendezvousClass = (Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous");


        ficheDao = new FicheDaoImpl(ficheClass);
        medicamentDao = new MedicamentDaoImpl(medicamentClass);
        examenDao = new ExamenDaoImpl(examenClass);
        antecedentMedicalDao = new AntecdentMedicalDaoImpl(antecedentMedicalClass);
        questionDao = new QuestionDaoImpl(questionClass);
        resultatsExamensDao = new ResultatExamenDaoImpl(resultatsExamensClass);
        rendezVousDao = new RendezVousDaoImpl(rendezvousClass);
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

        Long patientId = Long.parseLong(req.getParameter("patientId"));
        Long rendezVousId = Long.parseLong(req.getParameter("rendezVousId"));
        String[] dateDebut = req.getParameterValues("date_debuts");
        String[] dateFin = req.getParameterValues("date_fins");
        String[] type = req.getParameterValues("type");
        String[] descriptions = req.getParameterValues("descriptions");

        String[] questions = req.getParameterValues("questions");
        String[] appreciationsQuestions = req.getParameterValues("appreciation");

        String[] reponses = req.getParameterValues("reponses");
        String[] appreciationReponses = req.getParameterValues("appreciation_reponses");

        String[] medicaments = req.getParameterValues("medicaments");

        String sendTo = req.getParameter("envoyer_vers");


        Fiche fiche = ficheDao.findByPatient(patientId);

        if(fiche == null){
            req.setAttribute("error", "Le patient n'a pas encore une fiche.");
            req.getRequestDispatcher("/hospital/consultationOperation.jsp").forward(req, resp);
        }

        List<AntecedentMedical> antecedentMedicals = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        List<Medicament> medicamentList = new ArrayList<>();

        //Antecedent

        if (
                (dateDebut != null && dateDebut.length > 0)
                && (dateFin != null && dateFin.length > 0)
                && (type != null && type.length > 0 )
                && (descriptions != null && descriptions.length > 0)
        ){
            for (int i = 0; i < dateDebut.length ; i++ ) {
                AntecedentMedical antecedentMedical = new AntecedentMedical();
                antecedentMedical.setDateDebut(null);
                antecedentMedical.setDateFin(null);
                antecedentMedical.setDescription(descriptions[i]);
                antecedentMedical.setType(type[i]);
                antecedentMedical.setFiche(fiche);
                antecedentMedicals.add(antecedentMedical);
            }
        }else{
            req.setAttribute("error", "Le patient n'a pas encore une fiche.");
            req.getRequestDispatcher("").forward(req, resp);
        }


        //ResultExamen
        ResultatsExamens resultatsExamens = new ResultatsExamens();
        resultatsExamens.setFiche(fiche);



        //Medicament
        if (medicaments.length > 0){
            for (int k = 0; k < medicaments.length ; k++ ) {
                Medicament medicament = medicamentDao.findById(Long.parseLong(medicaments[k]));
                medicament.setFiche(fiche);
                medicamentDao.save(medicament);
            }
        }else{
            req.setAttribute("error", "Le patient n'a pas encore une fiche.");
            req.getRequestDispatcher("").forward(req, resp);
        }


        //enreegistrer plus retour a la liste
        //medicamentDao.saveAll(medicamentList.toArray(new Medicament[0]));

        antecedentMedicalDao.saveAll(antecedentMedicals.toArray(new AntecedentMedical[0]));


        resultatsExamens = resultatsExamensDao.save(resultatsExamens);
        //Examen
        if (questions.length > 0) {
            for (int j = 0; j < questions.length; j++) {
                Question question = new Question();
                question.setTitre(questions[j]);
                question.setResultatsExamens(resultatsExamens);
                question.setAppreciation(appreciationsQuestions[j]);
                questionList.add(question);
            }
        }else {
            req.setAttribute("error", "Le patient n'a pas encore une fiche.");
            req.getRequestDispatcher("").forward(req, resp);
        }



        Rendezvous rendezvous = rendezVousDao.findById(rendezVousId);
        if ( rendezvous != null ){
            if ( sendTo != null ){
                if ( sendTo.equals(AppConst.PHARMACIE ) ){
                    rendezvous.setStatus(RendezVousStatus.PHARMACIE);
                }
                if ( sendTo.equals(AppConst.LABORATOIRE) ){
                    rendezvous.setStatus(RendezVousStatus.LABORATOIRE);
                }
            }else{
                rendezvous.setStatus(RendezVousStatus.TRAITER);
            }
        }

        rendezVousDao.save(rendezvous);
        questionDao.saveAll(questionList.toArray(new Question[0]));

        UserDto user = (UserDto) req.getSession().getAttribute("authenticated");

        if (user == null && user.getUserId() == null) {
            req.getRequestDispatcher("/hospital/index.jsp").forward(req, resp);
        }

        List<Rendezvous> rendezvousList = rendezVousDao.findByDoctor(user.getUserId(), RendezVousStatus.EN_COURS);
        req.setAttribute("rendezvousList", rendezvousList);
        req.getRequestDispatcher("/hospital/consultations.jsp").forward(req, resp);

    }

    @Override
    public void getPrescriptionPharmacie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/hospital/prendrePrescriptionPharmacieProcess.jsp").forward(req, resp);
    }

    @Override
    public void processPrescriptionPharmacie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] medicament = req.getParameterValues("medicament");
        String rendezVousId = req.getParameter("rendezVousId");
        String patientId = req.getParameter("patientId");
        String ficheId   = req.getParameter("ficheId");

        Fiche fiche = ficheDao.findById(Long.parseLong(ficheId));


        Rendezvous rendezvous = rendezVousDao.findById(Long.parseLong(rendezVousId));

        if (medicament != null && medicament.length >0) {
            for (int i = 0; i < medicament.length ; i++) {
                Medicament med = medicamentDao.findById(Long.parseLong(medicament[i]));
                med.setFiche(fiche);
                med.setFourni(Medicament.FOURNI.OUI);
                medicamentDao.save(med);
            }
        }else{
            req.setAttribute("error", "Veillez selectionner le medicament a fournir au patient");
            req.getRequestDispatcher("/hospital/prendrePrescriptionPharmacieProcess.jsp").forward(req, resp);
        }
        rendezvous.setStatus(RendezVousStatus.EN_COURS);
        rendezVousDao.save(rendezvous);
        req.getRequestDispatcher("/hospital/prendrePrescriptionPharmacie.jsp").forward(req, resp);
    }

    @Override
    public void getPrelevementLaboratoire(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/hospital/prelevementLaboratoireProcess.jsp").forward(req, resp);
    }

    @Override
    public void processPrelevementLaboratoire(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] reponses = req.getParameterValues("reponses");
        String[] appreciations = req.getParameterValues("appreciation_reponses");
        String result = req.getParameter("result");
        String rendezVousId = req.getParameter("rendezVousId");

        List<Examen> examenList = new ArrayList<>();


        ResultatsExamens resultatsExamens = resultatsExamensDao.findById(Long.parseLong(result));

        if (reponses == null && appreciations == null){
            req.setAttribute("error", "Veuillez choisir une reponse");
            req.getRequestDispatcher("/hospital/prelevementLaboratoireProcess.jsp").forward(req, resp);
        }

        if (reponses.length > 0 && appreciations.length > 0){
            for (int i = 0; i < reponses.length ; i++) {
                Examen examen = new Examen();
                examen.setResultat(reponses[i]);
                examen.setResultatExamens(resultatsExamens);
                examen.setCommentaires(appreciations[i]);
                examenList.add(examen);
            }
        }
        examenDao.saveAll(examenList.toArray(new Examen[0]));
        Rendezvous rendezvous = rendezVousDao.findById(Long.parseLong(rendezVousId));
        if (rendezvous != null){
            rendezvous.setStatus(RendezVousStatus.EN_COURS);
        }
        rendezVousDao.save(rendezvous);
        List<Rendezvous> rendezvousList = rendezVousDao.findByStatus(RendezVousStatus.LABORATOIRE);
        req.setAttribute("rendezvousList", rendezvousList);
        req.getRequestDispatcher("/hospital/prelevementLaboratoire.jsp").forward(req, resp);

    }
}
