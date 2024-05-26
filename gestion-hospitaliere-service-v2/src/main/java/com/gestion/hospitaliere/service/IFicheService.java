package com.gestion.hospitaliere.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IFicheService {

    void fichePatient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void consultationPatient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
