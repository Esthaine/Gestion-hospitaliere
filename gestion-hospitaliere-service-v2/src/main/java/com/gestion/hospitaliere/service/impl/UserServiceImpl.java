package com.gestion.hospitaliere.service.impl;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import com.gestion.hospitaliere.model.LoginDto;
import com.gestion.hospitaliere.model.UserDto;
import com.gestion.hospitaliere.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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

        LoginDto loginDto = new LoginDto();
        loginDto.setPassword(username);
        loginDto.setPassword(password);

        UserDto userDto = userRepository.findUserByName(username);
        if (userDto == null){
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            response.sendRedirect(request.getContextPath() + "/authentication?action="+role);
            return;
        }

        if (!userDto.getMotDePasse().equals(password)){
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            request.getRequestDispatcher("/login.jsp");
        }else {
            if (userDto.getRoleDto()!=null && !userDto.getRoleDto().isEmpty() ){
                switch (userDto.getRoleDto().get(0).getRoleName()){
                    case "ADMIN":
                        response.sendRedirect(request.getContextPath() + "/hopital/dashboard");
                        break;
                    case "PATIENT":
                        response.sendRedirect(request.getContextPath() + "/");
                        break;
                    case "INFIRMIER":
                    case "MEDECIN":
                        response.sendRedirect(request.getContextPath() + "/hopital/dashboard");
                        break;
                    default:
                        request.getRequestDispatcher("/login.jsp").forward(request, response);

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
        String pays = request.getParameter("country");
        String town = request.getParameter("town");


        //Validation v )
        if (email == "" || username == "" || firstName == "" || lastName == "" || password == "" || confirmPassword == "" || pays == "" || town == "") {
            request.setAttribute("error", "Veuillez renseigner les champs obligatoires");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
            return;
        }



        //verification nom utilisateur
        UserDto userDto = userRepository.findUserByName(username);
        if (userDto !=null){
            request.setAttribute("error", "Utilisateur deja existant.");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
            return;
        }


        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        //valider mot de passe egalite
        if (!password.equals(confirmPassword)){
            request.setAttribute("error", "Veillez comfirmer le mot de passe");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
            return;
        }
        user.setMotDePasse(password);
        Role role = roleRepository.findRoleByName("PATIENT");
        if (role == null){
            request.setAttribute("error", "Probleme de selection de role");
            request.getRequestDispatcher( "/register.jsp").forward(request, response);
            return;
        }
        user.setRole(role);

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


        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setUser(user);

        personRepository.save(person);
        response.sendRedirect(request.getContextPath() + "/authentication?action=patients");
    }
}
