package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        rendezVousDao = new RendezVousDaoImpl(rendezvousClass);

    }
    @Test
    void creerUnRendezVous()  {
        User docteur = userDao.findById(6L);
        User patient = userDao.findById(1L);
        Person people = personDao.findByUserId(patient.getId());
        LocalDateTime date = LocalDateTime.now().plusMonths(6);
        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setDateRendezVous(date);
        rendezvous.setPerson(people);
        rendezvous.setDocteurSet(docteur);
        rendezvous.setStatus(RendezVousStatus.NOUVEAU);
        rendezVousDao.save(rendezvous);
        //System.out.println(people.getUser().getEmail());
        //System.out.println("Find Person: " + patient.getRole());
    }

    @Test
    void listOfRendezVousPerPatient(){

//        List<Rendezvous> rendezvous = rendezVousDao.findByPatient(1L);
//        assertEquals(2, rendezvous.size());

        for (Rendezvous rendezvous : rendezVousDao.findByDoctor(6L, RendezVousStatus.NOUVEAU)) {
            System.out.println(rendezvous);
        }
    }
}
