package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RendezVousTest {

    UserDao userDao;
    DocteurDao docteurDao;
    MedicamentDao medicamentDao;

    PersonDao personDao;
    PrivilegeDao privilegeDao;
    RoleDao roleDao;

    RolePrivilegeDao rolePrivilegeDao;
    RendezVousDao rendezVousDao;


    Class<User> user;
    //Class<Patient> patient;
    Class<Medicament> medicament;
    Class<Docteur> docteur;
    Class<Person> person;
    Class<Role> role;

    Class<Privilege> privilege;

    Class<RolePrivilege> rolePrivilegeClass;
    Class<Rendezvous> rendezvousClass;


    @BeforeEach()
    void setUp() throws ClassNotFoundException{

        user = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        medicament = (Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament");
        docteur = (Class<Docteur>) Class.forName("com.gestion.hospitaliere.entity.Docteur");
        person = (Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person");
        role = (Class<Role>) Class.forName("com.gestion.hospitaliere.entity.Role");
        privilege = (Class<Privilege>)  Class.forName("com.gestion.hospitaliere.entity.Privilege");
        rolePrivilegeClass = (Class<RolePrivilege>) Class.forName("com.gestion.hospitaliere.entity.RolePrivilege");
        rendezvousClass = (Class<Rendezvous>) Class.forName("com.gestion.hospitaliere.entity.Rendezvous");


        userDao = new UserDaoImpl(user);
        privilegeDao = new PrivilegeDaoImpl(privilege);
        docteurDao = new DocteurDaoImpl(docteur);
        medicamentDao = new MedicamentDaoImpl(medicament);
        personDao = new PersonDaoImpl(person);
        roleDao = new RoleDaoImpl(role);
        rolePrivilegeDao = new RolePrivilegeDaoImpl(rolePrivilegeClass);

    }
    @Test
    void creerUnRendezVous()  {
        //
    }
}
