package com.gestion.hospitaliere.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IRendezVousService {

    void listOfAllMedicalsAppointment(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void annullerRendezVous(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void reprogrammerRendeVous(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void voirRendezVous(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;
    void historyRendezVousPerPatient(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException;

}
