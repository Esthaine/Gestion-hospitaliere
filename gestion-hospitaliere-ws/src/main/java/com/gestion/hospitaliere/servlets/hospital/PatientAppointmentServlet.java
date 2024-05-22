package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.service.IRendezVousService;
import com.gestion.hospitaliere.service.impl.RendezVousServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/hopital/rendez-vous"})
public class PatientAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            String rendezVousId = req.getParameter("rendezVousId");

            IRendezVousService rendezVousService = new RendezVousServiceImpl();

            if (action != null && rendezVousId != null) {
                if (action.equals("view") && rendezVousId != null) {
                    rendezVousService.voirRendezVous(req, resp);
                    return;
                }

                if (action.equals("reschedule") && rendezVousId != null) {
                    rendezVousService.reprogrammerRendeVous(req, resp);
                    return;
                }

                if (action.equals("cancel") && rendezVousId != null) {
                    rendezVousService.annullerRendezVous(req, resp);
                    return;
                }
//                if (action.equals("view") && patientId != null) {
//                }
            }
            rendezVousService.listOfAllMedicalsAppointment(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
