package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.RendezVousDao;
import com.gestion.hospitaliere.dao.impl.RendezVousDaoImpl;
import com.gestion.hospitaliere.entity.RendezVousStatus;
import com.gestion.hospitaliere.entity.Rendezvous;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.service.IRendezVousService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class RendezVousServiceImpl implements IRendezVousService {

    private RendezVousDao rendezVousDao;

    public RendezVousServiceImpl() throws ClassNotFoundException {
        rendezVousDao = new RendezVousDaoImpl((Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous"));
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
        request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);

    }

    @Override
    public void reprogrammerRendeVous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
        Rendezvous rendezvous = findRendezVousById(rendezVousId);
        if (rendezVousId > 0) {
            request.setAttribute("rendezvous", rendezvous);
            request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);
        }
    }

    @Override
    public void voirRendezVous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long rendezVousId = Long.parseLong(request.getParameter("rendezVousId"));
        Rendezvous rendezvous = findRendezVousById(rendezVousId);
        if (rendezVousId > 0) {
            request.setAttribute("rendezvous", rendezvous);
            request.getRequestDispatcher("/hospital/appointment.jsp").forward(request, response);
        }
    }

    @Override
    public void historyRendezVousPerPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto user = (UserDto) request.getSession().getAttribute("authenticated");
        //long personId = Long.parseLong(request.getParameter("personId"));
        List<Rendezvous> rendezvousList = rendezVousDao.findByPatient(user.getUserId());
        if (rendezvousList != null && rendezvousList.size() > 0) {
            request.setAttribute("rendezvousList", rendezvousList);
        }
        request.getRequestDispatcher("/historiqueRendezVous.jsp").forward(request, response);
    }


    private Rendezvous findRendezVousById(Long rendezVousId) {
        return rendezVousDao.findById(rendezVousId);
    }
}
