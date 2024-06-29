package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.service.IRendezVousService;
import com.gestion.hospitaliere.utils.AppConst;
import com.gestion.hospitaliere.utils.AppUtils;
import com.gestion.hospitaliere.utils.PersistenceUtils;
import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class RendezVousServiceImpl implements IRendezVousService {

    private RendezVousDao rendezVousDao = null;
    private UserDao userDao = null;
    private PersonDao personDao = null;
    private FicheDao ficheDao = null;
    private PremierSoinDao premierSoinDao = null;

    public RendezVousServiceImpl() throws ClassNotFoundException {
        rendezVousDao = new RendezVousDaoImpl((Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous"));
        userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        ficheDao = new FicheDaoImpl((Class<Fiche>) Class.forName("com.gestion.hospitaliere.entity.Fiche"));
        premierSoinDao = new PremierSoinDaoImpl((Class<PremierSoin>) Class.forName("com.gestion.hospitaliere.entity.PremierSoin"));
    }

    @Override
    public void listOfAllMedicalsAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rendezvous> rendezvousList = rendezVousDao.findAll();
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);
    }

    @Override
    public void annullerRendezVous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
        Rendezvous rendezvous = findRendezVousById(rendezVousId);
        rendezvous.setStatus(RendezVousStatus.ANNULER);
        rendezVousDao.save(rendezvous);
        List<Rendezvous> rendezvousList = rendezVousDao.findAll();
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);

    }

    @Override
    public void reprogrammerRendeVous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.listOfUserPerRole(AppConst.MEDECIN);
        request.setAttribute("docteurs", users);
        if (request.getParameter("rendezVousId")!= null){
            long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
            long useId = Long.parseLong(request.getParameter("patientId"));
            Rendezvous rendezvous = findRendezVousById(rendezVousId);
            if (rendezVousId > 0) {
                Person patient = personDao.findById(useId);
                request.setAttribute("person", patient);
                request.setAttribute("rendezVous", rendezvous);
                //request.setAttribute("rendezvous", rendezvous);
                request.getRequestDispatcher("/hospital/prendreRdvPatient.jsp").forward(request, response);
            }
        }

        if (request.getParameter("userId")!= null){
            long userId = Long.parseLong(request.getParameter("userId"));
            Person patient = personDao.findByUserId(userId);
            request.setAttribute("person", patient);
            request.getRequestDispatcher("/hospital/prendreRdvPatient.jsp").forward(request, response);
        }
    }

    @Override
    public void prendreUnRendezVous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("rendezVousId") != null && request.getParameter("doctorId") != null){
            try{
                Long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
                long doctor = Long.parseLong(request.getParameter("doctorId"));
                String date = request.getParameter("date_rendezvous");
                Rendezvous rendezvous = rendezVousDao.findById(rendezVousId);
                User user = userDao.findById(doctor);
                if (rendezvous != null){
                    rendezvous.setDateRendezVous(AppUtils.convertToLocalDateTimeViaInstant(date));
                    rendezvous.setStatus(RendezVousStatus.REPROGRAMMER);
                    rendezvous.setDocteurSet(user);
                    rendezVousDao.save(rendezvous);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (request.getParameter("personId") != null){
            long userId = Long.parseLong(request.getParameter("personId"));
            long doctorId = Long.parseLong(request.getParameter("doctorId"));
            String date = request.getParameter("date_rendezvous");
            Person patient = personDao.findById(userId);
            User doctor = userDao.findById(doctorId);
            if (patient != null){
                Rendezvous rendezvous = new Rendezvous();
                rendezvous.setStatus(RendezVousStatus.NOUVEAU);
                rendezvous.setPerson(patient);
                try {
                    rendezvous.setDateRendezVous(AppUtils.convertToLocalDateTimeViaInstant(date));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                rendezvous.setDocteurSet(doctor);
                rendezVousDao.save(rendezvous);
            }
        }
        List<Rendezvous> rendezvousList = rendezVousDao.findAll();
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);

    }

    @Override
    public void voirRendezVous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
        Rendezvous rendezvous = findRendezVousById(rendezVousId);
        long patientId = Long.parseLong(request.getParameter("patientId"));
        Person patient = personDao.findById(patientId);
        Fiche fiche = null;
        try{
            fiche = ficheDao.findByPatient(patient.getId());
        }catch (NoResultException e){
            e.printStackTrace();
        }
        if (rendezVousId > 0) {
            request.setAttribute("rendezvous", rendezvous);
            request.setAttribute("patient", patient);
            if (fiche != null){
                request.setAttribute("fiche", fiche);
            }
            request.getRequestDispatcher("/hospital/patientPremierSoins.jsp").forward(request, response);
        }
    }

    @Override
    public void historyRendezVousPerPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession().getAttribute("authenticated");
        Person patient = personDao.findByUserId(userDto.getUserId());
        List<Rendezvous> rendezvousList = rendezVousDao.findByPatient(patient.getId());
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/historiqueRendezVous.jsp").forward(request, response);
    }

    @Override
    public void creerFichePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
        long patientId = Long.parseLong(request.getParameter("patientId"));
        UserDto user = (UserDto) request.getSession().getAttribute("authenticated");
        Person patient = personDao.findById(patientId);
        if ( patient != null ){
            Fiche fiche = new Fiche();
            fiche.setFicheNumber("MMY" + PersistenceUtils.getRandomNumberString());
            fiche.setGenre(Genre.MASCULIN);
            fiche.setStatus(FicheStatus.ACTIVE);
            if (user != null){
                fiche.setCreatedBy(userDao.findById(user.getUserId()));
            }
            fiche.setPatient(patient);
            fiche = ficheDao.save(fiche);
            if (fiche != null){
                Rendezvous rendezvous = findRendezVousById(rendezVousId);
                request.setAttribute("rendezvous", rendezvous);
                request.setAttribute("patient", patient);
                request.setAttribute("fiche", fiche);
                request.getRequestDispatcher("/hospital/patientPremierSoins.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void prelevementPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String temperature = request.getParameter("temperature");
        String kilo = request.getParameter("kilo");
        String tension = request.getParameter("tension");
        String taille = request.getParameter("taille");
        String respiration = request.getParameter("respiration");
        long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
        Person patientId = personDao.findById(Long.parseLong(request.getParameter("patientId")));

        if (patientId != null) {
            Fiche fiche = null;
            try{
                fiche = ficheDao.findByPatient(patientId.getId());
            }catch (NoResultException e){
                e.printStackTrace();
            }

            if (fiche != null) {
                PremierSoin premierSoin = new PremierSoin();
                premierSoin.setFiche(fiche);
                premierSoin.setTemperature(temperature);
                premierSoin.setKilo(kilo);
                premierSoin.setTension(tension);
                premierSoin.setTaille(taille);
                premierSoin.setRespiation(respiration);
                premierSoinDao.save(premierSoin);
            }
            Rendezvous rendezvous = findRendezVousById(rendezVousId);
            if (rendezvous != null){
                rendezvous.setStatus(RendezVousStatus.EN_COURS);
                rendezVousDao.save(rendezvous);
                List<Rendezvous> rendezvousList = rendezVousDao.findAll();
                request.setAttribute("rendezvousList", rendezvousList);
                request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void listerRendezVousPerDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto user = (UserDto) request.getSession().getAttribute("authenticated");
        if (user == null && user.getUserId() == null) {
            request.getRequestDispatcher("/hospital/index.jsp").forward(request, response);
        }
        List<Rendezvous> rendezvousList = rendezVousDao.findByDoctor(user.getUserId(), RendezVousStatus.EN_COURS);
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/hospital/consultations.jsp").forward(request, response);
    }

    @Override
    public void listerRendezVousPerPharmacy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rendezvous> rendezvousList = rendezVousDao.findByStatus(RendezVousStatus.PHARMACIE);
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/hospital/prendrePrescriptionPharmacie.jsp").forward(request, response);

    }

    @Override
    public void listerRendezVousPerLaboratory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rendezvous> rendezvousList = rendezVousDao.findByStatus(RendezVousStatus.LABORATOIRE);
        request.setAttribute("rendezvousList", rendezvousList);
        request.getRequestDispatcher("/hospital/prelevementLaboratoire.jsp").forward(request, response);
    }


    private Rendezvous findRendezVousById(Long rendezVousId) {
        return rendezVousDao.findById(rendezVousId);
    }
}
