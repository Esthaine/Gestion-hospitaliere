package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.dao.RendezVousDao;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.RendezVousDaoImpl;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.Rendezvous;
import com.gestion.hospitaliere.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/download/excel-format/"})
public class GetDataInExcelFormatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String doctorId = req.getParameter("doctorId");
        //Initialization of DAOs
        UserDao userDao = null;
        RendezVousDao rendezVousDao = null;
        List<Rendezvous> rendezvous = null;
        List<User> users = new ArrayList<>();
        try {
             userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
             rendezVousDao = new RendezVousDaoImpl((Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous"));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-disposition", "attachment; filename=hospital.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(type);
            if (type != null && type.equals("RendezVous")) {
                rendezvous = rendezVousDao.findAll();
                int rowNum = 0;
                int colNum = 0;
                int index = 0;
                Row row = sheet.createRow(rowNum ++);

                Cell cell = row.createCell(colNum ++);
                cell.setCellValue("No");
                cell = row.createCell(colNum ++);
                cell.setCellValue("Non Patient");
                cell = row.createCell(colNum ++);
                cell.setCellValue("Date Rendez-vous");
                cell = row.createCell(colNum ++);
                cell.setCellValue("Departement");
                cell = row.createCell(colNum ++);
                cell.setCellValue("Nom Medecin");

                cell = row.createCell(colNum ++);
                cell.setCellValue("Statut");


                for (Rendezvous rdz: rendezvous){
                    index += 1;
                    colNum = 0;
                    row = sheet.createRow(rowNum ++);
                    cell = row.createCell(colNum ++);
                    cell.setCellValue(index);

                    if (rdz.getDateRendezVous() != null) {
                        String fullName =  rdz.getPerson().getGivenName() != null ? rdz.getPerson().getGivenName() : "";
                        fullName +=  rdz.getPerson().getFirstName() != null ? rdz.getPerson().getFirstName() : "";
                        fullName +=  rdz.getPerson().getLastName() != null ? rdz.getPerson().getLastName() : "";

                        cell = row.createCell(colNum ++);
                        cell.setCellValue(fullName);
                    }

                    cell = row.createCell(colNum ++);
                    cell.setCellValue(String.valueOf(rdz.getDateRendezVous()));
                    cell = row.createCell(colNum ++);
                    cell.setCellValue("Departement");
                    cell = row.createCell(colNum ++);
                    cell.setCellValue("");
                    cell = row.createCell(colNum ++);
                    cell.setCellValue("Nom Medecin");
                }

                workbook.write(resp.getOutputStream());
                workbook.close();

            }
        }


    }
}
