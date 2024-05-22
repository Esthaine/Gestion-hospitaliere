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
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private final UserDao userRepository;
    private final PersonDao personRepository;
    private final RoleDao roleRepository;
    private final PaysDao paysRepository;
    private final VilleDao villeRepository;

    public UserServiceImpl() throws ClassNotFoundException {
        userRepository = new UserDaoImpl((Class<User>) Class.forName("com.gestion.hospitaliere.entity.User"));
        personRepository = new PersonDaoImpl((Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person"));
        roleRepository = new RoleDaoImpl((Class<Role>) Class.forName("com.gestion.hospitaliere.entity.Role"));
        paysRepository = new PaysDaoImpl((Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays"));
        villeRepository = new VilleDaoImpl((Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville"));
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

                if (userDto.getRoleDto().get(0).getRoleName().equals(AppConst.MEDECIN))
                    role = userDto.getRoleDto().get(0).getRoleName();

                if (!userDto.getRoleDto().get(0).getRoleName().equals(role)){
                    System.out.println("Check role");
                    request.setAttribute("error", "Veuillez selectionner l'onglet selon votre Role");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }

                HttpSession session = request.getSession();


                switch (userDto.getRoleDto().get(0).getRoleName()) {
                    case "ADMIN", "INFIRMIER", "MEDECIN" -> {
                        System.out.println("Organsation Role: " + userDto.getRoleDto().get(0).getRoleName());
                        session.setAttribute("authenticated", userDto);
                        response.sendRedirect(request.getContextPath() + "/hopital/dashboard");
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
        String imageProfile = request.getParameter("imageProfile");
        String roleName = request.getParameter("role");
        String username = request.getParameter("email");
        String password = request.getParameter("email");
        String firstName = request.getParameter("email");
        String email = request.getParameter("email");
        String lastName = request.getParameter("email");
        String userName = request.getParameter("email");
        String givenName = request.getParameter("email");
        String dateOfBirth = request.getParameter("email");
        String gender = request.getParameter("email");
        String department = request.getParameter("email");
        String value = request.getParameter("email");
        String town = request.getParameter("email");
        String pays = request.getParameter("email");




        //validation

        if (imageProfile == null || userName == null || givenName == null || dateOfBirth == null || gender == null || department == null || value == null){
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }



        Ville findVille = villeRepository.findById(Long.parseLong(town));
        Pays findPays = paysRepository.findById(Long.parseLong(pays));

        Address address = null;

        if (findVille != null || findPays != null){
            address = new Address();
            address.setPays(findPays);
            address.setVille(findVille);
            if (findVille.getRegion() != null)
                address.setRegion(findVille.getRegion());
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setMotDePasse(password);

        Person newPerson = new Person();
        newPerson.setFirstName(firstName);
        newPerson.setLastName(lastName);
        newPerson.setLastName(lastName);
        newPerson.setUser(newUser);



        //verification
        Role role = roleRepository.findRoleByName(roleName);
        if (role != null){
            newUser.setRole(role);
        }
        //enregistrement

        personRepository.save(newPerson);
        response.sendRedirect(request.getContextPath() + "/authentication?action=patients");

    }

    @Override
    public void OrganizationMemberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>(userRepository.listOfUserPerRole(AppConst.MEDECIN));
        users.addAll(userRepository.listOfUserPerRole(AppConst.INFIRMIER));
        System.out.println("users: " +  users);
        request.setAttribute("users", users);
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

//        if (action.equals("edit")){
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
//        }
    }
}
