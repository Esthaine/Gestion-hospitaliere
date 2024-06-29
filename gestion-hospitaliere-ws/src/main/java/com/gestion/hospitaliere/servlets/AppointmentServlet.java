package com.gestion.hospitaliere.servlets;

import com.gestion.hospitaliere.dao.DepartementDao;
import com.gestion.hospitaliere.dao.PersonDao;
import com.gestion.hospitaliere.dao.RendezVousDao;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.DepartementDaoImpl;
import com.gestion.hospitaliere.dao.impl.PersonDaoImpl;
import com.gestion.hospitaliere.dao.impl.RendezVousDaoImpl;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.utils.AppUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/appointement"})
public class AppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/appointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String department = request.getParameter("department");
        String docteurId = request.getParameter("docteurId");
        String date  = request.getParameter("date-rdv");
        String departement = request.getParameter("departements");

        UserDao userDao = null;
        RendezVousDao rendezVousDao = null;
        PersonDao personDao = null;
        DepartementDao departementDao = null;
        try {
            userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
            rendezVousDao = new RendezVousDaoImpl((Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous"));
            personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
            departementDao = new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("authenticated") == null) {
            response.sendRedirect(request.getContextPath() + "/authentication?action=patients");
            return;
        }
        UserDto userDto = (UserDto) session.getAttribute("authenticated");

        Person patient = null;
        User doctor = null;

        if (userDto != null) {
            patient = personDao.findByUserId(userDto.getUserId());
        }

        Rendezvous rendezvous = new Rendezvous();
        if (department!=null && docteurId!=null) {
            User docteur = userDao.findById(Long.parseLong(docteurId));
            try {
                rendezvous.setDateRendezVous(AppUtils.convertToLocalDateTimeViaInstant(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            rendezvous.setPerson(patient);
            rendezvous.setDocteurSet(docteur);
            rendezvous.setStatus(RendezVousStatus.NOUVEAU);
            rendezVousDao.save(rendezvous);
        }
        if (departement != null){

            doctor = userDao.listOfUserByDepartment(Long.parseLong(departement)).get(0);
            try {
                rendezvous.setDateRendezVous(AppUtils.convertToLocalDateTimeViaInstant(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            rendezvous.setPerson(patient);
            rendezvous.setDocteurSet(doctor);
            rendezvous.setStatus(RendezVousStatus.NOUVEAU);
            rendezVousDao.save(rendezvous);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
