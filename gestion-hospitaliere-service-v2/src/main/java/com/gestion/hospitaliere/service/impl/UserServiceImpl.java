package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.service.IUserService;
import com.gestion.hospitaliere.utils.AppConst;
import com.gestion.hospitaliere.utils.AppUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private final UserDao userRepository;
    private final PersonDao personRepository;
    private final RoleDao roleRepository;
    private final PaysDao paysRepository;
    private final VilleDao villeRepository;
    private final DepartementDao departmentRepository;

    public UserServiceImpl() throws ClassNotFoundException {
        userRepository = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        personRepository = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        roleRepository = new RoleDaoImpl((Class<Role>) Class.forName("com.gestion.hospitaliere.entity.Role"));
        paysRepository = new PaysDaoImpl((Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays"));
        villeRepository = new VilleDaoImpl((Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville"));
        departmentRepository = new DepartementDaoImpl((Class<Departement>) Class.forName("com.gestion.hospitaliere.entity.Departement"));
    }

    @Override
    public void authentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (username.isEmpty() || password.isEmpty()){
            request.setAttribute("error", "Veuillez renseigner le nom d'utilisateur ou le mot de passe");
            response.sendRedirect(request.getContextPath() + "/authentication?action="+role);
            return;
        }


        if (role.equals("superAdmin")) {
            System.out.println("Admin check");
            role = AppConst.ADMIN;
        }

        if (role.equals("organization")){
            System.out.println("Organization --->  check");
            role = AppConst.INFIRMIER;
        }

        if (role.equals("patients")){
            role = AppUtils.removeLastChar(role).toUpperCase();
        }

        UserDto userDto = userRepository.findUserByName(username);
        if (userDto == null){
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            response.sendRedirect(request.getContextPath() + "/authentication?action="+role);
            return;
        }

        if (!userDto.getMotDePasse().equals(password)){
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            if (userDto.getRoleDto()!=null && !userDto.getRoleDto().isEmpty() ){
                System.out.println("User role name: ----> " + userDto.getRoleDto().get(0).getRoleName());

                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.MEDECIN)) {
                    role = userDto.getRoleDto().get(0).getRoleName();
                } else if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.LABORATOIRE)) {
                    role = userDto.getRoleDto().get(0).getRoleName();
                }else if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.PHARMACIE)) {
                    role = userDto.getRoleDto().get(0).getRoleName();
                }

                if (!userDto.getRoleDto().get(0).getRoleName().equals(role)){
                    System.out.println("Check role");
                    request.setAttribute("error", "Veuillez selectionner l'onglet selon votre Role");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }

                HttpSession session = request.getSession();

                switch (userDto.getRoleDto().get(0).getRoleName()) {
                    case "ADMIN" -> {
                        System.out.println("Organsation Role: " + userDto.getRoleDto().get(0).getRoleName());
                        System.out.println("Authenticated?: " + userDto);
                        session.setAttribute("authenticated", userDto);
                        response.sendRedirect(request.getContextPath() + "/hopital/dashboard");
                    }
                    case "INFIRMIER" -> {
                        System.out.println("Organsation Role: " + userDto.getRoleDto().get(0).getRoleName());
                        System.out.println("Authenticated?: " + userDto);
                        session.setAttribute("authenticated", userDto);
                        response.sendRedirect(request.getContextPath() + "/hopital/patients");
                    }

                    case "MEDECIN" -> {
                        System.out.println("Organsation Role: " + userDto.getRoleDto().get(0).getRoleName());
                        System.out.println("Authenticated?: " + userDto);
                        session.setAttribute("authenticated", userDto);
                        response.sendRedirect(request.getContextPath() + "/hopital/medecin/consultation");
                    }

                    case "LABORATOIRE" -> {
                        System.out.println("Organsation Role: " + userDto.getRoleDto().get(0).getRoleName());
                        System.out.println("Authenticated?: " + userDto);
                        session.setAttribute("authenticated", userDto);
                        response.sendRedirect(request.getContextPath() + "/hopital/prelevement/laboratoire");
                    }

                    case "PHARMACIE" -> {
                        System.out.println("Organsation Role: " + userDto.getRoleDto().get(0).getRoleName());
                        System.out.println("Authenticated?: " + userDto);
                        session.setAttribute("authenticated", userDto);
                        response.sendRedirect(request.getContextPath() + "/hopital/prescription/pharmacie");
                    }

                    case "PATIENT" -> {
                        session.setAttribute("authenticated", userDto);
                        System.out.println("Patient Role -> " + userDto.getRoleDto().get(0).getRoleName());
                        response.sendRedirect(request.getContextPath() + "/");
                    }
                    default -> {
                        request.setAttribute("error", "Veuillez contacter l'adminitrateur du systeme.");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
            }
        }
    }

    @Override
    public void patientRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
//        String pays = request.getParameter("country");
//        String town = request.getParameter("town");


        //Validation v )
        if (email == "" || username == "" || firstName == "" || lastName == "" || password == "" || confirmPassword == "" ) { //|| pays == "" || town == ""
            request.setAttribute("error", "Veuillez renseigner les champs obligatoires");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
            return;
        }



        //verification nom utilisateur
        UserDto userDto = userRepository.findUserByName(username);
        if (userDto !=null){
            request.setAttribute("error", "Utilisateur deja existant.");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
        }


        User user = new User();
        Person person = new Person();
        user.setUsername(username);
        user.setEmail(email);

        //valider mot de passe egalite
        if (!password.equals(confirmPassword)){
            request.setAttribute("error", "Veillez comfirmer le mot de passe");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
        }
        user.setMotDePasse(password);

        Role role = roleRepository.findRoleByName("PATIENT");
        if (role == null){
            request.setAttribute("error", "Probleme de selection de role");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
        }
        user.setRole(role);
//        user = userRepository.save(user);

//        Ville findVille = villeRepository.findById(Long.parseLong(town));
//        Pays findPays = paysRepository.findById(Long.parseLong(pays));

//        Address address = null;
//
//        if (findVille != null || findPays != null){
//            address = new Address();
//            address.setPays(findPays);
//            address.setVille(findVille);
//            if (findVille.getRegion() != null)
//                address.setRegion(findVille.getRegion());
//        }

        person.setFirstName(firstName);
        person.setLastName(lastName);
        //person.setAddress(address);
        person.setUser(user);

        person = personRepository.save(person);
        if (person == null) {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/authentication?action=patients");
    }

    @Override
    public void OrganisationRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //recuperer toutes les request
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String postnom = request.getParameter("post-nom");
        String dob = request.getParameter("dob");
        String avenue = request.getParameter("street");
        String numero = request.getParameter("street-number");
        String country = request.getParameter("pays");
        String ville = request.getParameter("villes");
        String phoneNumber = request.getParameter("phone");
        String gender = request.getParameter("sexe");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        String role = request.getParameter("role");
        String department = request.getParameter("departement");
        String confirmPwd = request.getParameter("confirm-password");
        String action = request.getParameter("action");
        Genre sexe = null;



        //validation

        if (
                prenom != null
            || nom != null
            || postnom != null
            || dob != null
            || avenue != null
            || numero != null
            || country != null
            || ville != null
            || phoneNumber != null
            || gender != null
            || userName != null
            || email != null
            || pwd != null
            || confirmPwd != null
            || role != null
            || department != null
        ){
            request.setAttribute("error", "Veuillez renseigner tous les elements.");
            request.getRequestDispatcher("/hopital/docteurs.jsp");
        }



        if (gender.equals(Genre.MASCULIN.toString())) {
            sexe = Genre.MASCULIN;
        }

        if (gender.equals(Genre.FEMININ.toString())){
            sexe = Genre.FEMININ;
        }
        User newUser =  null;
        Person newPerson = null;
        if ( request.getParameter("editUser") != null && action.equals("edit")){
            Long id = Long.parseLong(request.getParameter("editUser"));
            newUser = userRepository.findById(id);
            newPerson = personRepository.findByUserId(id);
        }else{
            newUser = new User();
            newPerson = new Person();
        }


        Pays pays = paysRepository.findById(Long.parseLong(country));
        Ville town = villeRepository.findById(Long.parseLong(ville));
        newUser.setUsername(userName);
        newUser.setEmail(email);

        if (!pwd.equals(confirmPwd)){
            request.setAttribute("error", "Veuillez renseigner tous les elements.");
            request.getRequestDispatcher("/hopital/docteurs.jsp");
        }
        newUser.setMotDePasse(pwd);
        newPerson.setGivenName(prenom);
        newPerson.setFirstName(nom);
        newPerson.setLastName(postnom);
        newPerson.setUser(newUser);
        newPerson.setGenre(sexe);
        newPerson.setDateOfBirth(AppUtils.convertToDateViaInstant(dob));
        newPerson.setHouseNumber(numero);
        newPerson.setStreetName(avenue);
        newPerson.setPhoneNumber(phoneNumber);

        if (pays!= null || town != null){
            newPerson.setPays(pays);
            newPerson.setVille(town);
        }


        //verification
        Role roleToAdd = roleRepository.findRoleByName(role);
        if (role != null){
            newUser.setRole(roleToAdd);
        }

        Departement departement = departmentRepository.findById(Long.parseLong(department));
        if (departement != null){
            newUser.setDepartement(departement);
        }
        //enregistrement

        Person savedPerson = personRepository.save(newPerson);
        System.out.println("-------------" +savedPerson + "-----------");
        response.sendRedirect(request.getContextPath() + "/hopital/docteurs");

    }

    @Override
    public void OrganizationMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>(userRepository.listOfUserPerRole(AppConst.MEDECIN));

        users.addAll(userRepository.listOfUserPerRole(AppConst.INFIRMIER));
        users.addAll(userRepository.listOfUserPerRole(AppConst.PHARMACIE));
        users.addAll(userRepository.listOfUserPerRole(AppConst.LABORATOIRE));

        List<Role> roles = roleRepository.findRoleByUserNameDifferentDe(AppConst.PATIENT);
        List<Pays> pays = paysRepository.findAll();
        List<Ville> villes = villeRepository.findAll();
        List<Departement> departements = departmentRepository.findAll();

        System.out.println("users: " +  users);
        request.setAttribute("users", users);
        request.setAttribute("userDao", userRepository);
        request.setAttribute("persons", personRepository);
        request.setAttribute("departments", departmentRepository);
        request.setAttribute("roles", roles);
        request.setAttribute("pays", pays);
        request.setAttribute("villes", villes);
        request.setAttribute("departementList", departements);
        request.getRequestDispatcher("/hospital/docteurs.jsp").forward(request, response);
    }

    @Override
    public void userProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession existingSession = request.getSession();
            UserDto connectedUser = (UserDto) existingSession.getAttribute("authenticated");
            User userExists = userRepository.findById(connectedUser.getUserId());
            if (userExists != null){
                request.setAttribute("userExists", userExists);
            }
            request.getRequestDispatcher("/hospital/userProfile.jsp").forward(request, response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void userOrganisationProfileUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession existingSession = request.getSession();
            UserDto connectedUser = (UserDto) existingSession.getAttribute("authenticated");
            User userExists = userRepository.findById(connectedUser.getUserId());
            if (userExists != null){
                request.setAttribute("userExists", userExists);
            }
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            if (!oldPassword.equals(userExists.getMotDePasse())){
                request.setAttribute("error", "Mot de passe ancien incorrect");
                request.getRequestDispatcher("/hospital/userProfile.jsp").forward(request, response);
            }

            if (!newPassword.equals(confirmPassword)){
                request.setAttribute("error", "Mot de passe ne correspondent pas");
                request.getRequestDispatcher("/hospital/userProfile.jsp").forward(request, response);
            }
            userExists.setMotDePasse(newPassword);
            userRepository.save(userExists);
            request.setAttribute("success", "Mot de passe modifier avec succes");
            request.getRequestDispatcher("/hospital/userProfile.jsp").forward(request, response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void updateUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_new_password");
        long userId = Long.parseLong(request.getParameter("user_id"));

        if (oldPassword == "" || newPassword == "" || confirmPassword == ""){
            request.setAttribute("error", "Les champs sont obligatoires");
            request.getRequestDispatcher("/patientProfile.jsp").forward(request, response);
        }
        if (!newPassword.equals(confirmPassword)){
            request.setAttribute("error", "Veuillez confirmer le nouveau mot de passe");
            request.getRequestDispatcher("/patientProfile.jsp").forward(request, response);
        }

        User user = userRepository.findById(userId);
        if ( user != null ){
            if (!newPassword.equals(user.getMotDePasse())){
                request.setAttribute("error", "Veuillez entrer l'ancien mot de passe");
                response.sendRedirect(request.getContextPath() + "/patient/profile?action=edit&userProfileId="+userId);
            }
            user.setMotDePasse(newPassword);
            userRepository.save(user);
            request.getRequestDispatcher("/patientProfile.jsp").forward(request, response);
        }
    }
}
