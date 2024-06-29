package com.gestion.hospitaliere.servlets.hospital;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.servlets.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


@WebServlet(urlPatterns = {"/hopital/patient/creerEtModifier"})
public class PatientCreerModifServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDao personDao = null;
        UserDao userDao = null;
        PaysDao paysDao = null;
        VilleDao villeDao = null;
        Long userId;
        try{
            personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
            userDao = new UserDaoImpl( (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
            villeDao = new VilleDaoImpl((Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville"));
            paysDao = new PaysDaoImpl((Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays"));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (villeDao != null){
            req.setAttribute("villes", villeDao.findAll());
        }
        if (paysDao != null){
            req.setAttribute("pays", paysDao.findAll());
        }
        if (req.getParameter("userId") != null) {
            userId = Long.parseLong(req.getParameter("userId"));
            if (personDao != null && userDao != null) {
                if (personDao.findById(userId) != null && userDao.findById(userId) != null) {
                    req.setAttribute("patient", personDao.findById(userId));
                    req.setAttribute("user", userDao.findById(userId));
                    req.getRequestDispatcher("/hospital/patientCreerModif.jsp").forward(req, resp);
                }
            }
        }
        req.getRequestDispatcher("/hospital/patientCreerModif.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String givenName = req.getParameter("givenname");
        String name = req.getParameter("name");
        String postnom = req.getParameter("postnom");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("sex");
        String phone = req.getParameter("telephone");
        String streetNumber = req.getParameter("streetNumber");
        String streetName = req.getParameter("streetName");
        String township = req.getParameter("townShip");
        String villeId = req.getParameter("ville");
        String paysId = req.getParameter("pays");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userId = req.getParameter("userId");

        RoleDao roleDao;
        VilleDao villeDao;
        PaysDao paysDao;
        PersonDao personDao;
        UserDao userDao;
        try {
            roleDao = new RoleDaoImpl((Class<Role>) Class.forName("com.gestion.hospitaliere.entity.Role"));
            villeDao = new VilleDaoImpl((Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville"));
            paysDao = new PaysDaoImpl((Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays"));
            personDao = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
            userDao = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        if (givenName == "" || name == "" || postnom == "" || dob == "" || gender == "" || phone == "" || streetNumber == "" || streetName == "" || township == "" ) {
            req.setAttribute("error", "Tous les champs sont obligatoires!");
            req.setAttribute("villes", villeDao.findAll());
            req.setAttribute("pays", paysDao.findAll());
            req.getRequestDispatcher("/hospital/patientCreerModif.jsp").forward(req, resp);
        }

        Role role = roleDao.findRoleByName("PATIENT");
        if (role == null){
            req.setAttribute("error", "Probleme de selection de role");
            req.getRequestDispatcher( "/register.jsp").forward(req, resp);
        }

        User user = null;
        Person person;

        if (userId.equals("")){
            user = new User();
        }else{
            if (userId != null && StringUtils.isNumeric(userId)) {
                user = userDao.findById(Long.parseLong(userId));
            }
        }

        if (user.getId() != null){
            person = personDao.findByUserId(user.getId());
        }else{
            person = new Person();
        }

        if (userId.equals("")){
            user.setEmail(email);
            user.setRole(role);
            user.setUsername(username);
            user.setMotDePasse(password);
        }

        person.setGivenName(givenName);
        person.setFirstName(name);
        person.setLastName(postnom);
        person.setDateOfBirth(WebUtils.convertToDateViaInstant(dob));

        if (gender.equals(Genre.MASCULIN.toString())){
            person.setGenre(Genre.MASCULIN);
        }
        if (gender.equals(Genre.FEMININ.toString())){
            person.setGenre(Genre.FEMININ);
        }

        person.setPhoneNumber(phone);

        Ville findVille = villeDao.findById(Long.parseLong(villeId));
        Pays findPays = paysDao.findById(Long.parseLong(paysId));

        if (findVille != null){
            person.setVille(findVille);
        }

        if (findPays != null){
            person.setPays(findPays);
        }

        person.setHouseNumber(streetNumber);
        person.setStreetName(streetName);
        person.setTownship(township);
        if (userId.equals("")){
            person.setUser(user);
        }
        person = personDao.save(person);

        if (person == null){
            req.setAttribute("error", "Probleme survenu lors de l'insertion des donnees du patients, " +
                    "veuillez reesayer plutards ou contactez le systeme admin");
            req.setAttribute("villes", villeDao.findAll());
            req.setAttribute("pays", paysDao.findAll());
            req.getRequestDispatcher("/hospital/patientCreerModif.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/hospital/patients.jsp").forward(req, resp);

    }
}
