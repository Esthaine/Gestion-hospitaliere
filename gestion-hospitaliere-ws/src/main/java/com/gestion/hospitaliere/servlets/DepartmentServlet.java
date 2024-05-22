package com.gestion.hospitaliere.servlets;

import com.gestion.hospitaliere.dao.DepartementDao;
import com.gestion.hospitaliere.dao.impl.DepartementDaoImpl;
import com.gestion.hospitaliere.entity.Departement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/departement"})
public class DepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartementDao departementDao = null;
        try {
            departementDao = new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Long id = Long.parseLong(req.getParameter("departmentId"));
        Departement departement = departementDao.findById(id);
        if (departement != null) {
           req.setAttribute("departementDetails", departement);
        }
        req.getRequestDispatcher("/department.jsp").forward(req, resp);
    }
}
