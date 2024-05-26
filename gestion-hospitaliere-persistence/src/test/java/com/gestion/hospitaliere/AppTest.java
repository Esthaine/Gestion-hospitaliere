package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.*;
import com.gestion.hospitaliere.dao.impl.*;
import com.gestion.hospitaliere.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    //PatientDao patientDao;
    UserDao userDao;
    DocteurDao docteurDao;
    MedicamentDao medicamentDao;

    PersonDao personDao;
    PrivilegeDao privilegeDao;
    RoleDao roleDao;
    VilleDao villeDao;
    PaysDao paysDao;

    RolePrivilegeDao rolePrivilegeDao;


    Class<User> user;
    //Class<Patient> patient;
    Class<Medicament> medicament;
    Class<Docteur> docteur;
    Class<Person> person;
    Class<Role> role;

    Class<Privilege> privilege;

    Class<RolePrivilege> rolePrivilegeClass;
    Class<Ville> ville;
    Class<Pays> pays;

    @BeforeEach
    void init() throws ClassNotFoundException {

        user = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        medicament = (Class<Medicament>) Class.forName("com.gestion.hospitaliere.entity.Medicament");
        docteur = (Class<Docteur>) Class.forName("com.gestion.hospitaliere.entity.Docteur");
        person = (Class<Person>) Class.forName("com.gestion.hospitaliere.entity.Person");
        role = (Class<Role>) Class.forName("com.gestion.hospitaliere.entity.Role");
        privilege = (Class<Privilege>)  Class.forName("com.gestion.hospitaliere.entity.Privilege");
        rolePrivilegeClass = (Class<RolePrivilege>) Class.forName("com.gestion.hospitaliere.entity.RolePrivilege");
        ville = (Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville");
        pays = (Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays");

        userDao = new UserDaoImpl(user);
        privilegeDao = new PrivilegeDaoImpl(privilege);
        docteurDao = new DocteurDaoImpl(docteur);
        medicamentDao = new MedicamentDaoImpl(medicament);
        personDao = new PersonDaoImpl(person);
        roleDao = new RoleDaoImpl(role);
        rolePrivilegeDao = new RolePrivilegeDaoImpl(rolePrivilegeClass);

        villeDao = new VilleDaoImpl(ville);
        paysDao = new PaysDaoImpl(pays);

    }

    //@Test
    void registerUser() throws InstantiationException, IllegalAccessException {



    }


    //@Test
    void createDocteur() throws InstantiationException, IllegalAccessException{

//        Docteur docteur = new Docteur();
//        docteurDao.save(docteur);
//        assertNotNull(docteur);
    }

    //@Test
    void createMedicament() throws InstantiationException, IllegalAccessException{

        createMedicamentSuite();
        //assertNotNull(medicamentDao.findAll());

    }


    void createMedicamentSuite(){

        for (int i = 0 ; i <=10; i++){
            Medicament med = new Medicament();
            med.setNom("Medicament" + i);
            med.setPosologie("posologie" + i);
            med.setDescription("description" + i);
            med.setPrixUnitaire(100+ i);
            med.setDatePeremption(LocalDate.now());
            med.setStock(Stock.IN);
            medicamentDao.save(med);
        }
    }


    @Test
    void createRole(){
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
//        adminRole.setDescription("Admin role");
//        roleDao.save(adminRole);
//
//        Role patientRole = new Role();
//        patientRole.setName("PATIENT");
//        patientRole.setDescription("Patient role");
//        roleDao.save(patientRole);
//
//
//        Role nurseRole = new Role();
//        nurseRole.setName("INFIRMIER");
//        nurseRole.setDescription("Nurse role");
//        roleDao.save(nurseRole);
//
//
//        Role doctorRole = new Role();
//        doctorRole.setName("MEDECIN");
//        doctorRole.setDescription("Doctor role");
//        roleDao.save(doctorRole);

        Role labo = new Role();
        labo.setName("LABORATOIRE");
        labo.setDescription("Laboratoire role");
        roleDao.save(labo);


        Role pharmacie = new Role();
        pharmacie.setName("PHARMACIE");
        pharmacie.setDescription("Pharmacie role");
        roleDao.save(pharmacie);




    }

    @Test
    void createUser(){

        User user = new User();
        user.setEmail("yha@gmail.com");
        user.setUsername("yha");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("PATIENT");
        user.setRole(patient);

        Address address = new Address();
        Ville ville = villeDao.findById(1L);
        Pays pays = paysDao.findById(1L);

        address.setHouseNumber("12");
        address.setPays(pays);
        address.setVille(ville);
        address.setTownship("Ngaliema");
        address.setRegion(ville.getRegion());
        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("yha");
        person.setGivenName("Ya");
        person.setLastName("Ya");
        person.setPhoneNumber("08977902");
        person.setAddress(address);
        person.setGenre(Genre.MASCULIN);
        person.setUser(user);
        personDao.save(person);

    }

    @Test
    void createLaborantain(){

        User user = new User();
        user.setEmail("jeannette@gmail.com");
        user.setUsername("jeanette");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("LABORATOIRE");
        user.setRole(patient);

        Address address = new Address();
        Ville ville = villeDao.findById(1L);
        Pays pays = paysDao.findById(1L);

        address.setHouseNumber("12");
        address.setPays(pays);
        address.setVille(ville);
        address.setTownship("Ngaliema");
        address.setRegion(ville.getRegion());
        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("Jeannette");
        person.setGivenName("Kaniki");
        person.setLastName("Kanku");
        person.setPhoneNumber("08977902");
        //person.setAddress(address);
        person.setGenre(Genre.MASCULIN);
        person.setUser(user);
        personDao.save(person);

    }

    @Test
    void createPharmacien(){

        User user = new User();
        user.setEmail("guy@gmail.com");
        user.setUsername("guy");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("PHARMACIE");
        user.setRole(patient);

        Address address = new Address();
        Ville ville = villeDao.findById(1L);
        Pays pays = paysDao.findById(1L);

        address.setHouseNumber("12");
        address.setPays(pays);
        address.setVille(ville);
        address.setTownship("Ngaliema");
        address.setRegion(ville.getRegion());
        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("Guy");
        person.setGivenName("Lukuli");
        person.setLastName("Mvwemba");
        person.setPhoneNumber("08977902");
        //person.setAddress(address);
        person.setGenre(Genre.MASCULIN);
        person.setUser(user);
        personDao.save(person);

    }

    //@Test
    void createAdmin(){
        User user = new User();
        user.setEmail("admin2@gmail.com");
        user.setUsername("admin2");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("ADMIN");
        user.setRole(patient);

        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("admin2");
        person.setGivenName("admin2");
        person.setLastName("admin2");
        person.setUser(user);


        personDao.save(person);
    }

    //@Test
    void createNursing(){
        User user = new User();
        user.setEmail("jackie@gmail.com");
        user.setUsername("jackie");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("INFIRMIER");
        user.setRole(patient);

        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("jackie");
        person.setGivenName("kasongo");
        person.setLastName("mujinga");
        person.setUser(user);


        personDao.save(person);
    }

    @Test
    void createMedecin(){

        User user = new User();
        user.setEmail("george@gmail.com");
        user.setUsername("george");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("MEDECIN");
        user.setRole(patient);

        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("george");
        person.setGivenName("kapiamba");
        person.setLastName("mbala");
        person.setUser(user);


        personDao.save(person);
    }

    @Test
    void createMedecin2(){

        User user = new User();
        user.setEmail("simeon@gmail.com");
        user.setUsername("simeon");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("MEDECIN");
        user.setRole(patient);

        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("simeon");
        person.setGivenName("Ntoka");
        person.setGenre(Genre.MASCULIN);
        person.setGivenName("Mulenda");
        person.setLastName("Kazadi");
        person.setUser(user);


        personDao.save(person);
    }

    @Test
    void createMedecin3(){

        User user = new User();
        user.setEmail("freddy@gmail.com");
        user.setUsername("freddy");
        user.setMotDePasse("123456");

        Role patient = roleDao.findRoleByName("MEDECIN");
        user.setRole(patient);

        Person person = new Person();
        person.setDateOfBirth(new Date());
        person.setFirstName("freddy");
        person.setGivenName("rulio");
        person.setGenre(Genre.MASCULIN);
        person.setGivenName("Kabamba");
        person.setGivenName("Kisenga");
        person.setLastName("Banza");
        person.setUser(user);


        personDao.save(person);
    }

    //@Test
    void createPrivilege(){

        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole.setDescription("Admin role");
        roleDao.save(adminRole);

        Role patientRole = new Role();
        patientRole.setName("PATIENT");
        patientRole.setDescription("Patient role");
        roleDao.save(patientRole);


        Role nurseRole = new Role();
        nurseRole.setName("INFIRMIER");
        nurseRole.setDescription("Nurse role");
        roleDao.save(nurseRole);


        Role doctorRole = new Role();
        doctorRole.setName("MEDECIN");
        doctorRole.setDescription("Doctor role");
        roleDao.save(doctorRole);


        //-----------------------------------
        Privilege readPrivilege = new Privilege();
        readPrivilege.setName("READ");
        readPrivilege.setDescription("Read privilege");
        privilegeDao.save(readPrivilege);

        Privilege writePrivilege = new Privilege();
        writePrivilege.setName("WRITE");
        writePrivilege.setDescription("Write privilege");
        privilegeDao.save(writePrivilege);

        Privilege deletePrivilege = new Privilege();
        deletePrivilege.setName("DELETE");
        deletePrivilege.setDescription("Delete Privilege");
        privilegeDao.save(deletePrivilege);

        Privilege updatePrivilege = new Privilege();
        updatePrivilege.setName("UPDATE");
        updatePrivilege.setDescription("Update privilege");
        privilegeDao.save(updatePrivilege);

    }

    @Test
    void mappPrivilegesToRole(){

//        Role adminRole = roleDao.findRoleByName("ADMIN");
//        Role patientRole = roleDao.findRoleByName("PATIENT");
//        Role nurseRole = roleDao.findRoleByName("INFIRMIER");
//        Role doctorRole = roleDao.findRoleByName("MEDECIN");
        Role pharmacieRole = roleDao.findRoleByName("PHARMACIE");
        Role laboratoireRole = roleDao.findRoleByName("LABORATOIRE");


        Privilege readPrivilege = privilegeDao.findPrivilegeByName("READ");
        Privilege writePrivilege = privilegeDao.findPrivilegeByName("WRITE");
        Privilege deletePrivilege = privilegeDao.findPrivilegeByName("DELETE");
        Privilege updatePrivilege = privilegeDao.findPrivilegeByName("UPDATE");

        //-- Patient
        RolePrivilege rolePrivilege1 = new RolePrivilege();
//        rolePrivilege1.setPrivilege(readPrivilege);
//        rolePrivilege1.setRole(patientRole);
//        rolePrivilegeDao.save(rolePrivilege1);
//
//        RolePrivilege rolePrivilege2 = new RolePrivilege();
//        rolePrivilege2.setPrivilege(writePrivilege);
//        rolePrivilege2.setRole(patientRole);
//        rolePrivilegeDao.save(rolePrivilege2);
//
//        RolePrivilege rolePrivilege3 = new RolePrivilege();
//        rolePrivilege3.setPrivilege(deletePrivilege);
//        rolePrivilege3.setRole(patientRole);
//        rolePrivilegeDao.save(rolePrivilege3);
//
//        RolePrivilege rolePrivilege4 = new RolePrivilege();
//        rolePrivilege4.setPrivilege(updatePrivilege);
//        rolePrivilege4.setRole(patientRole);
//        rolePrivilegeDao.save(rolePrivilege4);
//
//
//        //-- Infirmier
//        RolePrivilege rolePrivilege5 = new RolePrivilege();
//        rolePrivilege5.setPrivilege(readPrivilege);
//        rolePrivilege5.setRole(nurseRole);
//        rolePrivilegeDao.save(rolePrivilege5);
//
//        RolePrivilege rolePrivilege6 = new RolePrivilege();
//        rolePrivilege6.setPrivilege(writePrivilege);
//        rolePrivilege6.setRole(nurseRole);
//        rolePrivilegeDao.save(rolePrivilege6);
//
//        RolePrivilege rolePrivilege7 = new RolePrivilege();
//        rolePrivilege7.setPrivilege(deletePrivilege);
//        rolePrivilege7.setRole(nurseRole);
//        rolePrivilegeDao.save(rolePrivilege7);
//
//        RolePrivilege rolePrivilege8 = new RolePrivilege();
//        rolePrivilege8.setPrivilege(updatePrivilege);
//        rolePrivilege8.setRole(nurseRole);
//        rolePrivilegeDao.save(rolePrivilege8);
//
//
//        //-- Medecin
//
//        RolePrivilege rolePrivilege9 = new RolePrivilege();
//        rolePrivilege9.setPrivilege(readPrivilege);
//        rolePrivilege9.setRole(doctorRole);
//        rolePrivilegeDao.save(rolePrivilege9);
//
//        RolePrivilege rolePrivilege10 = new RolePrivilege();
//        rolePrivilege10.setPrivilege(writePrivilege);
//        rolePrivilege10.setRole(doctorRole);
//        rolePrivilegeDao.save(rolePrivilege10);
//
//        RolePrivilege rolePrivilege11 = new RolePrivilege();
//        rolePrivilege11.setPrivilege(deletePrivilege);
//        rolePrivilege11.setRole(doctorRole);
//        rolePrivilegeDao.save(rolePrivilege11);
//
//        RolePrivilege rolePrivilege12 = new RolePrivilege();
//        rolePrivilege12.setPrivilege(updatePrivilege);
//        rolePrivilege12.setRole(doctorRole);
//        rolePrivilegeDao.save(rolePrivilege12);
//
//
//        //-- Admin
//
//        RolePrivilege rolePrivilege13 = new RolePrivilege();
//        rolePrivilege13.setPrivilege(readPrivilege);
//        rolePrivilege13.setRole(adminRole);
//        rolePrivilegeDao.save(rolePrivilege13);
//
//        RolePrivilege rolePrivilege14 = new RolePrivilege();
//        rolePrivilege14.setPrivilege(writePrivilege);
//        rolePrivilege14.setRole(adminRole);
//        rolePrivilegeDao.save(rolePrivilege14);
//
//        RolePrivilege rolePrivilege15 = new RolePrivilege();
//        rolePrivilege15.setPrivilege(deletePrivilege);
//        rolePrivilege15.setRole(adminRole);
//        rolePrivilegeDao.save(rolePrivilege15);
//
//        RolePrivilege rolePrivilege16 = new RolePrivilege();
//        rolePrivilege16.setPrivilege(updatePrivilege);
//        rolePrivilege16.setRole(adminRole);
//        rolePrivilegeDao.save(rolePrivilege16);


        //Pharmacie
        RolePrivilege rolePrivilege17 = new RolePrivilege();
        rolePrivilege17.setPrivilege(readPrivilege);
        rolePrivilege17.setRole(pharmacieRole);
        rolePrivilegeDao.save(rolePrivilege17);

        RolePrivilege rolePrivilege18 = new RolePrivilege();
        rolePrivilege18.setPrivilege(writePrivilege);
        rolePrivilege18.setRole(pharmacieRole);
        rolePrivilegeDao.save(rolePrivilege18);

        RolePrivilege rolePrivilege19 = new RolePrivilege();
        rolePrivilege19.setPrivilege(updatePrivilege);
        rolePrivilege19.setRole(pharmacieRole);
        rolePrivilegeDao.save(rolePrivilege19);

        RolePrivilege rolePrivilege20 = new RolePrivilege();
        rolePrivilege20.setPrivilege(deletePrivilege);
        rolePrivilege20.setRole(pharmacieRole);
        rolePrivilegeDao.save(rolePrivilege20);


        //Laboratoire
        RolePrivilege rolePrivilege21 = new RolePrivilege();
        rolePrivilege21.setPrivilege(readPrivilege);
        rolePrivilege21.setRole(laboratoireRole);
        rolePrivilegeDao.save(rolePrivilege21);

        RolePrivilege rolePrivilege22 = new RolePrivilege();
        rolePrivilege22.setPrivilege(writePrivilege);
        rolePrivilege22.setRole(laboratoireRole);
        rolePrivilegeDao.save(rolePrivilege22);

        RolePrivilege rolePrivilege23 = new RolePrivilege();
        rolePrivilege23.setPrivilege(deletePrivilege);
        rolePrivilege23.setRole(laboratoireRole);
        rolePrivilegeDao.save(rolePrivilege23);

        RolePrivilege rolePrivilege24 = new RolePrivilege();
        rolePrivilege24.setPrivilege(updatePrivilege);
        rolePrivilege24.setRole(laboratoireRole);
        rolePrivilegeDao.save(rolePrivilege24);
    }


    @Test
    void readListOfUserPerRole(){

        List<User> users =  userDao.listOfUserPerRole("INFIRMIER");
        users.addAll(userDao.listOfUserPerRole("MEDECIN"));
        System.out.println(users);
        assertNotNull(users);
    }


    @Test
    void findUserByName(){
        System.out.println("PHARMACIE: " +userDao.findUserByName("jeannette"));
        System.out.println("BIOLOGIE: " + userDao.findUserByName("guy"));
    }

}
