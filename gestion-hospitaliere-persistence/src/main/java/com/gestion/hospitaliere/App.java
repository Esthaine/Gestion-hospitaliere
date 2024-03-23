package com.gestion.hospitaliere;

import com.gestion.hospitaliere.dao.PatientDao;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.dao.impl.PatientDaoImpl;
import com.gestion.hospitaliere.dao.impl.UserDaoImpl;
import com.gestion.hospitaliere.entity.Patient;
import com.gestion.hospitaliere.entity.User;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
//        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit");
//        EntityManager em = emf.createEntityManager();
//        Patient patient = new Patient();
//
//        em.getTransaction().begin();
//        patient.setDateOfBirth(new Date());
//        patient.setGenre("F");
//        patient.setEmails("example@mqil.com");
//        em.persist(patient);
//        em.getTransaction().commit();

//        Persistence persistence = new Persistence();
//        persistence.entityManager();


//        UserDao userDao = new UserDao(persistence);
//        FicheDao ficheDao = new FicheDao(persistence);
//        List<Fiche> fiches = ficheDao.findAll();
//        PatientDao patientDao = new PatientDao(persistence);
//        List<Patient> patients = patientDao.findAll();
//        List<User> users = userDao.findAll();

//        System.out.println("******************************");
//        System.out.println(users);
//        System.out.println("******************************");

//        System.out.println("************");
//        System.out.println(fiches);
//        System.out.println("************");

//        System.out.println("************");
//        System.out.println(patients);
//        System.out.println("************");




        Class<User> user = (Class<User>) Class.forName("com.gestion.hospitaliere.entity.User");
        Class<Patient> patient = (Class<Patient>) Class.forName("com.gestion.hospitaliere.entity.Patient");
        System.out.println("----> " + user);

        UserDao stubUserEntryRepo = new UserDaoImpl(user);
        User saveUser = new User();
//        saveUser.setUsername("Delvaux2");
//        saveUser.setEmail("delvaux@gmail.com");
//        saveUser.setMotDePasse("12345");
        //System.out.println(stubUserEntryRepo.save(saveUser));

//        Class<Patient> patient = (Class<Patient>) Class.forName("com.gestion.hospitaliere.entity.Patient");
//        PatientDao patientDao = new StubPatientEntryRepo(patient);
//        Patient savePatient = new Patient();
//        savePatient.setGenre("M");
//        System.out.println("Patient ---> " + patient);

//        patientDao.findAll().stream().forEach(System.out::println);


        stubUserEntryRepo.findAll().forEach(usr -> System.out.println(usr.getUsername()));
        //System.out.println(stubUserEntryRepo.findById(1L).getUsername());

        PatientDao patientDao = new PatientDaoImpl(patient);

        Patient pt1 = new Patient();
        pt1.setGenre("M");
        pt1.setEmails("pt1@example.com");

        Patient pt2 = new Patient();
        pt2.setGenre("F");
        pt2.setEmails("p2@example.com");
        Patient[] patients = new Patient[]{pt1, pt2};
       patientDao.saveAll(patients);

       for (Patient patient1 : patientDao.findAll()) {
           System.out.println(patient1);
        }

        //System.out.println( patientDao.deleteById(4L));
        //patientDao.deleteMany(patients).forEach(System.out::println);

        Long[] ids = new Long[]{3L, 4L};
        patientDao.deleteMany(ids).forEach(System.out::println);
    }
}
